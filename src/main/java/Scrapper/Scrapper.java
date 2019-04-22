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
						"None", validAnswer);
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
		
		//Prints out to terminal
		for (Question x : algoQuestions) {
			System.out.println(x.toString());
		} 
		
		// Maps and creates the JSON file : algo_geeksforgeeks.json
		OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("algo_geeksforgeeks.json"), algoQuestions);
	}
	
	private static void createDSQuestions() throws JsonGenerationException, JsonMappingException, IOException {
				// Stack questions
				ArrayList<Question> dsQuestions = getQuestions("https://www.geeksforgeeks.org/data-structure-gq/stack-gq/");
				// Queue
				dsQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/data-structure-gq/queue-gq/"));
				// Binary Search Trees
				dsQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/data-structure-gq/binary-search-trees-gq/"));
				// Balanced Binary Search Trees
				dsQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/data-structure-gq/balanced-binary-search-trees-gq/"));
				// Graph
				dsQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/data-structure-gq/graph-gq/"));
				// Hash
				dsQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/data-structure-gq/hash-gq/"));
				// Array
				dsQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/data-structure-gq/array-gq/"));
				// DS miscellaneous
				dsQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/data-structure-gq/misc-3-gq/"));
				// B and B+ trees
				dsQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/data-structure-gq/b-and-b-trees-gq/"));
				// Heap
				dsQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/data-structure-gq/heap-gq/"));
				// Tree traversal
				dsQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/data-structure-gq/tree-traversals-gq/"));
				
				//LINKED LIST AND BINARY SEARCH TREES LINKS DID NOT WORK
				
				//Prints out to terminal
				for (Question x : dsQuestions) {
					System.out.println(x.toString());
				} 
				
				// Maps and creates the JSON file : ds_geeksforgeeks.json
				OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("ds_geeksforgeeks.json"), dsQuestions);
	}
	
	private static void createOSQuestions() throws JsonGenerationException, JsonMappingException, IOException {
		// creates array for list of questions to add
		//gets questions for OS process Management
		ArrayList<Question> q = getQuestions("https://www.geeksforgeeks.org/operating-systems-gq/process-synchronization-gq/");
		// Memory management
		q.addAll(getQuestions("https://www.geeksforgeeks.org/operating-systems-gq/memory-management-gq/"));
		// OS input output systems
		q.addAll(getQuestions("https://www.geeksforgeeks.org/operating-systems-gq/iinput-output-systems-gq/"));
		// UNIX	
		q.addAll(getQuestions("https://www.geeksforgeeks.org/operating-systems-gq/unix-gq/"));
		// OS CPU scheduling
		q.addAll(getQuestions("https://www.geeksforgeeks.org/cpu-scheduling-gq/"));
		// Deadlock
		q.addAll(getQuestions("https://www.geeksforgeeks.org/deadlock-gq/"));

		
		//Prints out to terminal
		for (Question x : q) {
			System.out.println(x.toString());
		} 
		
		// Maps and creates the JSON file : os_geeksforgeeks.json
		OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("os_geeksforgeeks.json"), q);
	}
	
	private static void createDBMSQuestions() throws JsonGenerationException, JsonMappingException, IOException {
		// creates array for list of questions to add
		//gets questions for ER and Relational Models
		ArrayList<Question> q = getQuestions("https://www.geeksforgeeks.org/dbms-gq/er-and-relational-models-gq/");
		// Database Design (Normal Forms)
		q.addAll(getQuestions("https://www.geeksforgeeks.org/dbms-gq/database-design-normal-forms-gq/"));
		// SQL
		q.addAll(getQuestions("https://www.geeksforgeeks.org/dbms-gq/sql-gq/"));
		// Transactions and concurrency control
		q.addAll(getQuestions("https://www.geeksforgeeks.org/dbms-gq/transactions-and-concurrency-control-gq/"));
		// File Structures
		q.addAll(getQuestions("https://www.geeksforgeeks.org/dbms-gq/file-structures-sequential-files-indexing-b-and-b-trees-gq/"));
		
		//Prints out to terminal
		for (Question x : q) {
			System.out.println(x.toString());
		} 
		
		// Maps and creates the JSON file : DBMS_geeksforgeeks.json
		OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("DBMS_geeksforgeeks.json"), q);
	}
	
	private static void createCompTheoryQuestions() throws JsonGenerationException, JsonMappingException, IOException {
		// creates array for list of questions to add
		//gets questions for Regular languages and finite automata
		ArrayList<Question> q = getQuestions("https://www.geeksforgeeks.org/regular-languages-and-finite-automata-gq/");
		// Context free languages and push down automata
		q.addAll(getQuestions("https://www.geeksforgeeks.org/context-free-languages-and-push-down-automata-gq/"));
		// recursively enumerate sets and turing machines
		q.addAll(getQuestions("https://www.geeksforgeeks.org/recursively-enumerable-sets-and-turing-machines-gq/"));
		// Undecidability
		q.addAll(getQuestions("https://www.geeksforgeeks.org/undecidability-gq/"));
		
		//Prints out to terminal
		for (Question x : q) {
			System.out.println(x.toString());
		} 
		
		// Maps and creates the JSON file : CompTheory_geeksforgeeks.json
		OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("CompTheory_geeksforgeeks.json"), q);
		
	}
	
	private static void createPythonQuestions() throws JsonGenerationException, JsonMappingException, IOException {
		// creates array for list of questions to add
		//gets questions for OPERATORS
		ArrayList<Question> q = getQuestions("https://www.geeksforgeeks.org/operators-gq/");
		// functions- LINK DOES NOT WORK WITH SCRAPPER
//		q.addAll(getQuestions("https://www.geeksforgeeks.org/functions-python-gq/"));
		// Data Type
		q.addAll(getQuestions("https://www.geeksforgeeks.org/data-type-gq/"));
		// Output
		q.addAll(getQuestions("https://www.geeksforgeeks.org/output-type-gq/"));
		// Miscellaneous
		q.addAll(getQuestions("https://www.geeksforgeeks.org/miscellaneous-gq/"));
		
		//Prints out to terminal
		for (Question x : q) {
			System.out.println(x.toString());
		} 
		
		// Maps and creates the JSON file : Python_geeksforgeeks.json
		OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("Python_geeksforgeeks.json"), q);
	}
	
	private static void createJavaQuestions() throws JsonGenerationException, JsonMappingException, IOException {
		// creates array for list of questions to add
		//gets questions for Inheritance
		ArrayList<Question> q = getQuestions("https://www.geeksforgeeks.org/java-gq/inheritance-2-gq/");
		// 
		q.addAll(getQuestions("https://www.geeksforgeeks.org/java-gq/abstract-class-and-interface-in-java-gq/"));
		// 
		q.addAll(getQuestions("https://www.geeksforgeeks.org/java-gq/arrays-gq/"));

		// 
		q.addAll(getQuestions("https://www.geeksforgeeks.org/java-gq/data-types-2-gq/"));
		// 
		q.addAll(getQuestions("https://www.geeksforgeeks.org/java-gq/constructors-2-gq/"));

		// 
		q.addAll(getQuestions("https://www.geeksforgeeks.org/java-gq/exception-handling-2-gq/"));

		// 
		q.addAll(getQuestions("https://www.geeksforgeeks.org/java-gq/packages-gq/"));

		// 
		q.addAll(getQuestions("https://www.geeksforgeeks.org/java-gq/class-and-object-2-gq/"));

		// 
		q.addAll(getQuestions("https://www.geeksforgeeks.org/java-gq/functions-2-gq/"));

		// 
		q.addAll(getQuestions("https://www.geeksforgeeks.org/java-gq/final-keyword-gq/"));

		// 
		q.addAll(getQuestions("https://www.geeksforgeeks.org/java-gq/operators-gq/"));


		
		//Prints out to terminal
		for (Question x : q) {
			System.out.println(x.toString());
		} 
		
		// Maps and creates the JSON file : Java_geeksforgeeks.json
		OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("Java_geeksforgeeks.json"), q);
	}
	
	private static void createCplusplusQuestions() throws JsonGenerationException, JsonMappingException, IOException {
		// creates array for list of questions to add
		//gets questions for references
		ArrayList<Question> q = getQuestions("https://www.geeksforgeeks.org/c-plus-plus-gq/this-pointer-gq/");
		//q.addAll(getQuestions("https://www.geeksforgeeks.org/c-plus-plus-gq/references-gq/")); 
		//q.addAll(getQuestions("https://www.geeksforgeeks.org/c-plus-plus-gq/class-and-object-gq/")); 
		//q.addAll(getQuestions("https://www.geeksforgeeks.org/c-plus-plus-gq/misc-c-gq/"));
		//q.addAll(getQuestions("https://www.geeksforgeeks.org/c-plus-plus-gq/destructors-gq/"));
		//q.addAll(getQuestions("https://www.geeksforgeeks.org/c-plus-plus-gq/operator-overloading-gq/"));
		//q.addAll(getQuestions("https://www.geeksforgeeks.org/c-plus-plus-gq/this-pointer-gq/")); 
		q.addAll(getQuestions("https://www.geeksforgeeks.org/c-plus-plus-gq/function-overloading-2-gq/"));
		q.addAll(getQuestions("https://www.geeksforgeeks.org/c-plus-plus-gq/static-keyword-gq/"));
		q.addAll(getQuestions("https://www.geeksforgeeks.org/c-plus-plus-gq/friend-function-and-class-gq/"));
		q.addAll(getQuestions("https://www.geeksforgeeks.org/c-plus-plus-gq/inheritance-gq/"));
		q.addAll(getQuestions("https://www.geeksforgeeks.org/c-plus-plus-gq/const-keyword-gq/"));
		q.addAll(getQuestions("https://www.geeksforgeeks.org/c-plus-plus-gq/new-and-delete-gq/"));
		q.addAll(getQuestions("https://www.geeksforgeeks.org/c-plus-plus-gq/virtual-functions-gq/"));
		q.addAll(getQuestions("https://www.geeksforgeeks.org/c-plus-plus-gq/templates-gq/"));
		q.addAll(getQuestions("https://www.geeksforgeeks.org/c-plus-plus-gq/exception-handling-gq/"));
		
		//Prints out to terminal
		for (Question x : q) {
			System.out.println(x.toString());
		} 
		
		// Maps and creates the JSON file : cplusplus_geeksforgeeks.json
		OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("cpluscplus_geeksforgeeks.json"), q);
	}
	
	public static void main(String args[]) throws JsonGenerationException, JsonMappingException, IOException {
		//createAlgoQuestions();
		//createDSQuestions();
		//createOSQuestions();
		//createDBMSQuestions();
		//createCompTheoryQuestions();
		//createPythonQuestions(); 
		//createJavaQuestions();
		createCplusplusQuestions();
	}
}
