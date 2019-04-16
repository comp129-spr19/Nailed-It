package Scrapper;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import main.*;
import java.io.IOException;
import java.util.ArrayList;

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

	public static ArrayList<Question> getQuestions(String url) {

		ArrayList<Question> questions = null;
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);

		HtmlPage page;
		try {
			page = client.getPage(url);
			int numQuestions = getNumQuestions(url);
	
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

				Question newQuestion = new Question(question, answers[0], answers[1], answers[2], answers[3],
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
	public static void main(String args[]) {

		ArrayList<Question> q = getQuestions("https://www.geeksforgeeks.org/algorithms-gq/graph-shortest-paths-gq/");
		//System.out.println(getNumAnswers(1));
		for (Question x : q) {
			System.out.println(x.toString());
		} 
		
		
	
		
	}
}
