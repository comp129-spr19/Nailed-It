package Scrapper;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import main.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.*;

public class Scrapper {
	public static String baseUrl = "https://www.geeksforgeeks.org/algorithms-gq/analysis-of-algorithms-gq/";

	private static final int MAX_QUESTIONS_PER_PAGE = 10;
	private static final int MAX_ANSWERS_PER_PAGE = 4;

	private static final String QUESTION_QUERY_FIRST_HALF = "mtq_question_text-";
	private static final String QUESTION_QUERY_SECOND_HALF = "-1";

	// mtq_answer_text-8-1-1
	private static final String ANSWER_QUERY_FIRST_HALF = "mtq_answer_text-";
	private static final String ANSWER_QUERY_SECOND_HALF = "-1";

	// mtq_marker-9-1-1
	private static final String COR_ANSW_FIRST_HALF = "mtq_marker-";
	private static final String COR_ANSW_SECOND_HALF = "-1";
	
	// title of page
	private static final String TITLE = "entry-title";
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


	public static ArrayList<Question> getQuestions(String url) throws IOException {

		ArrayList<Question> questions = null;
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		final Document doc = Jsoup.connect(url).get();
		HtmlPage page;
		try {
			page = client.getPage(url);
			int numQuestions = getNumQuestions(url);
			String title = doc.getElementsByClass(TITLE).text();
			questions = new ArrayList<Question>();
			for (int i = 0; i < numQuestions; i++) {
				String answers[] = new String[4];
				Answer validAnswer = null;
				String question = page
						.getHtmlElementById(QUESTION_QUERY_FIRST_HALF + (i + 1) + QUESTION_QUERY_SECOND_HALF).asText();
				int numAnswers = getNumAnswers((i+1),url);
				for (int j = 0; j < MAX_ANSWERS_PER_PAGE; j++) {
					if (j < numAnswers) {
					answers[j] = page
							.getHtmlElementById(
									ANSWER_QUERY_FIRST_HALF + (i + 1) + "-" + (j + 1) + ANSWER_QUERY_SECOND_HALF)
							.asText();

					String correctAnswerQuery = page
							.getHtmlElementById(COR_ANSW_FIRST_HALF + (i + 1) + "-" + (j + 1) + COR_ANSW_SECOND_HALF)
							.toString();
					if (checkCorrectAnswer(correctAnswerQuery, j) != null && validAnswer == null) {
						validAnswer = checkCorrectAnswer(correctAnswerQuery, j);
					}
					} else {
						answers[j] = "N/A";
					}

				}

				Question newQuestion = new Question(title, question, answers[0], answers[1], answers[2], answers[3],
						"NO HINTS WITH GEEKSFORGEEKS", validAnswer);
				questions.add(newQuestion);

			}

		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client.close();
		return questions;
	}

	private static Answer checkCorrectAnswer(String answerID, int index) {
		Answer rtAnswer = null;
		String attributeToCheck = "correct";
		if (answerID.contains(attributeToCheck)) {
			switch (index) {
			case 0:
				rtAnswer = Answer.ANSWER_A;
				break;
			case 1:
				rtAnswer = Answer.ANSWER_B;
				break;
			case 2:
				rtAnswer = Answer.ANSWER_C;
				break;
			case 3:
				rtAnswer = Answer.ANSWER_D;
				break;
			default:
				System.out.println("Error");
			}
		}
		return rtAnswer;
	}

	
	private static int getNumQuestions(String url) {
		HtmlPage page;
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		int numQuestions = 0;
		try {
			page = client.getPage(url);
			//int i = 0;
			
			while (page.getElementById(QUESTION_QUERY_FIRST_HALF + (numQuestions+1) +QUESTION_QUERY_SECOND_HALF) != null) {
				
				numQuestions++;
			}
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numQuestions;
	}
	
	private static int getNumAnswers(int questionNumber, String url) {
		HtmlPage page;
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		int numAnswers = 0;
		
		try {
			page = client.getPage(url);
			//int i = 0;
			
			while (page.getElementById(ANSWER_QUERY_FIRST_HALF + (questionNumber) + "-" + (numAnswers+1) + ANSWER_QUERY_SECOND_HALF) != null) {
				
				numAnswers++;
			}
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numAnswers;
	}
	
	/*************
	 * Algorithms
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException *
	 *************/
	public static void createAlgoQuestions() throws JsonGenerationException, JsonMappingException, IOException {
		// Analysis of Algorithms questions
		ArrayList<Question> algoQuestions = getQuestions("https://www.geeksforgeeks.org/algorithms-gq/analysis-of-algorithms-gq/");
		// Search
		algoQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/algorithms-gq/searching-gq/"));
		// Sorting and searching
		algoQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/algorithms-gq/searching-and-sorting-gq/"));
		// Recurrences
		algoQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/algorithms-gq/analysis-of-algorithms-recurrences-gq/"));
		// Divide and Conquer - does not work
		// Greedy Algorithm - does not work!
		// Recursion
		algoQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/algorithms-gq/recursion-gq/"));
		// Dynamic Programming
		algoQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/algorithms-gq/dynamic-programming-gq/"));
		// Bit Algorithms
		algoQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/algorithms-gq/bit-algorithms-gq/"));
		// Graph Traversals
		algoQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/algorithms-gq/graph-traversals-gq/"));
		// Backtracking
		algoQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/algorithms-gq/backtracking-gq/"));
		// Graph Shortest Paths
		algoQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/algorithms-gq/graph-shortest-paths-gq/"));
		// Graph Minimum Spanning Tree
		algoQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/algorithms-gq/graph-minimum-spanning-tree-gq/"));
		// NP complete
		algoQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/algorithms-gq/np-complete-gq/"));
		//Miscellaneous
		algoQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/algorithms-gq/misc-2-gq/"));
		
		for (Question x : algoQuestions) {
			System.out.println(x.toString());
		} 
		OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("algo_geeksforgeeks.json"), algoQuestions);
	}
	
	public static void main(String args[]) throws JsonGenerationException, JsonMappingException, IOException {
		createAlgoQuestions();
	}
}
