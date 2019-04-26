package Scrapper;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import JSON.JSONEditor;
import JSON.JSONOperations;
import main.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;
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


	public static ArrayList<Question> getQuestions(String url, String topic) throws IOException {

		ArrayList<Question> questions = null;
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		//final Document doc = Jsoup.connect(url).get();
		HtmlPage page;
		try {
			page = client.getPage(url);
			int numQuestions = getNumQuestions(url);
			//String title = doc.getElementsByClass(TITLE).text();
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

				Question newQuestion = new Question("",topic, "", "", question,answers[0], answers[1], answers[2], answers[3],
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
		String category = "Algorithms";
		JSONEditor.addCategory(category);
		
		ArrayList<Question> algoQuestions = getQuestions("https://www.geeksforgeeks.org/algorithms-gq/analysis-of-algorithms-gq/","analysisofalgorithms");
		// Search
		algoQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/algorithms-gq/searching-gq/","searching"));
		// Sorting and searching
		algoQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/algorithms-gq/searching-and-sorting-gq/","sorting"));
		// Recurrences
		algoQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/algorithms-gq/analysis-of-algorithms-recurrences-gq/","analysisofalgorithmsrecurrences"));
		// Divide and Conquer - does not work
		// Greedy Algorithm - does not work!
		// Recursion
		algoQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/algorithms-gq/recursion-gq/","recursion"));
		// Dynamic Programming
		algoQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/algorithms-gq/dynamic-programming-gq/","dynamicprogramming"));
		// Bit Algorithms
		algoQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/algorithms-gq/bit-algorithms-gq/","bitalgorithms"));
		// Graph Traversals
		algoQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/algorithms-gq/graph-traversals-gq/","graphtraversal"));
		// Backtracking
		algoQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/algorithms-gq/backtracking-gq/","backtracking"));
		// Graph Shortest Paths
		algoQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/algorithms-gq/graph-shortest-paths-gq/","graphshortestpaths"));
		// Graph Minimum Spanning Tree
		algoQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/algorithms-gq/graph-minimum-spanning-tree-gq/","graphminimumspanningtree"));
		// NP complete
		algoQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/algorithms-gq/np-complete-gq/","npcomplete"));
		//Miscellaneous
		algoQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/algorithms-gq/misc-2-gq/","algorithmmisc"));
		
		//Prints out to terminal
		for (Question x : algoQuestions) {
			JSONEditor.addQuestionWithImgAndExplanation(category, x);
		} 
		
		// Maps and creates the JSON file : algo_geeksforgeeks.json
		//OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("algo_geeksforgeeks.json"), algoQuestions);
	}
	
	private static void createDSQuestions() throws JsonGenerationException, JsonMappingException, IOException {
		
		String category = "Data Structures";
		JSONEditor.addCategory(category);
				// Stack questions
				ArrayList<Question> dsQuestions = getQuestions("https://www.geeksforgeeks.org/data-structure-gq/stack-gq/","stacked");
				// Queue
				dsQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/data-structure-gq/queue-gq/","queue"));
				// Binary Search Trees
				dsQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/data-structure-gq/binary-search-trees-gq/","binarysearchtrees"));
				// Balanced Binary Search Trees
				dsQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/data-structure-gq/balanced-binary-search-trees-gq/","balancedbinarysearchtrees"));
				// Graph
				dsQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/data-structure-gq/graph-gq/","graph"));
				// Hash
				dsQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/data-structure-gq/hash-gq/","hash"));
				// Array
				dsQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/data-structure-gq/array-gq/","array"));
				// DS miscellaneous
				dsQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/data-structure-gq/misc-3-gq/","datastructuresmisc"));
				// B and B+ trees
				dsQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/data-structure-gq/b-and-b-trees-gq/","bandbplustrees"));
				// Heap
				dsQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/data-structure-gq/heap-gq/","heap"));
				// Tree traversal
				dsQuestions.addAll(getQuestions("https://www.geeksforgeeks.org/data-structure-gq/tree-traversals-gq/","treetraversals"));
				
				//LINKED LIST AND BINARY SEARCH TREES LINKS DID NOT WORK
				
				//Prints out to terminal
				for (Question x : dsQuestions) {
					JSONEditor.addQuestionWithImgAndExplanation(category, x);
				} 
				
				// Maps and creates the JSON file : ds_geeksforgeeks.json
				//OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("ds_geeksforgeeks.json"), dsQuestions);
	}
	
	private static void createOSQuestions() throws JsonGenerationException, JsonMappingException, IOException {
		// creates array for list of questions to add
		//gets questions for OS process Management
		
		String category = "Operating Systems";
		JSONEditor.addCategory(category);
		
		//String category = 
		ArrayList<Question> q = getQuestions("https://www.geeksforgeeks.org/operating-systems-gq/process-synchronization-gq/","processsynchronization");
		// Memory management
		q.addAll(getQuestions("https://www.geeksforgeeks.org/operating-systems-gq/memory-management-gq/","osmemorymanagement"));
		// OS input output systems
		q.addAll(getQuestions("https://www.geeksforgeeks.org/operating-systems-gq/iinput-output-systems-gq/","osinputoutputsystems"));
		// UNIX	
		q.addAll(getQuestions("https://www.geeksforgeeks.org/operating-systems-gq/unix-gq/","unix"));
		// OS CPU scheduling
		q.addAll(getQuestions("https://www.geeksforgeeks.org/cpu-scheduling-gq/","oscpuscheduling"));
		// Deadlock
		q.addAll(getQuestions("https://www.geeksforgeeks.org/deadlock-gq/","osdeadlock"));

		
		//Prints out to terminal
		for (Question x : q) {
			JSONEditor.addQuestionWithImgAndExplanation(category, x);
		} 
		
		// Maps and creates the JSON file : os_geeksforgeeks.json
		OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("os_geeksforgeeks.json"), q);
	}
	
	private static void createDBMSQuestions() throws JsonGenerationException, JsonMappingException, IOException {
		String category = "Database";
		JSONEditor.addCategory(category);
		
		// creates array for list of questions to add
		//gets questions for ER and Relational Models
		ArrayList<Question> q = getQuestions("https://www.geeksforgeeks.org/dbms-gq/er-and-relational-models-gq/","dbmserandrelationalmodels");
		// Database Design (Normal Forms)
		q.addAll(getQuestions("https://www.geeksforgeeks.org/dbms-gq/database-design-normal-forms-gq/","dbmsdatabasedesignnormalforms"));
		// SQL
		q.addAll(getQuestions("https://www.geeksforgeeks.org/dbms-gq/sql-gq/","dbmssql"));
		// Transactions and concurrency control
		q.addAll(getQuestions("https://www.geeksforgeeks.org/dbms-gq/transactions-and-concurrency-control-gq/","dbmstransactionandconcurrencycontrol"));
		// File Structures
		q.addAll(getQuestions("https://www.geeksforgeeks.org/dbms-gq/file-structures-sequential-files-indexing-b-and-b-trees-gq/","dbmsfilestructures"));
		
		//Prints out to terminal
		for (Question x : q) {
			JSONEditor.addQuestionWithImgAndExplanation(category, x);
		} 
		
		// Maps and creates the JSON file : DBMS_geeksforgeeks.json
		//OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("DBMS_geeksforgeeks.json"), q);
	}
	
	private static void createCompTheoryQuestions() throws JsonGenerationException, JsonMappingException, IOException {
		String category = "Comp Theory";
		JSONEditor.addCategory(category);
		
		// creates array for list of questions to add
		//gets questions for Regular languages and finite automata
		ArrayList<Question> q = getQuestions("https://www.geeksforgeeks.org/regular-languages-and-finite-automata-gq/","comptheoryregularlanguagesandfiniteautomata");
		// Context free languages and push down automata
		q.addAll(getQuestions("https://www.geeksforgeeks.org/context-free-languages-and-push-down-automata-gq/","contextfreelanguagesandpushdownautomata"));
		// recursively enumerate sets and turing machines
		q.addAll(getQuestions("https://www.geeksforgeeks.org/recursively-enumerable-sets-and-turing-machines-gq/","comptheoryrecusivelyenumerablesetsturingmachines"));
		// Undecidability
		q.addAll(getQuestions("https://www.geeksforgeeks.org/undecidability-gq/","comptheoryundecidability"));
		
		//Prints out to terminal
		for (Question x : q) {
			//System.out.println(x.toString());
			JSONEditor.addQuestionWithImgAndExplanation(category, x);
		} 
		
		// Maps and creates the JSON file : CompTheory_geeksforgeeks.json
		//OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("CompTheory_geeksforgeeks.json"), q);
		
	}
	
	private static void createPythonQuestions() throws JsonGenerationException, JsonMappingException, IOException {
		String category = "Python";
		JSONEditor.addCategory(category);
		// creates array for list of questions to add
		//gets questions for OPERATORS
		ArrayList<Question> q = getQuestions("https://www.geeksforgeeks.org/data-type-gq/","pythondatatype");

		q.addAll(getQuestions("https://www.geeksforgeeks.org/output-type-gq/","pythonoutputtype"));
	
		q.addAll(getQuestions("https://www.geeksforgeeks.org/miscellaneous-gq/","pythonmisc"));
	
		
		//Prints out to terminal
		for (Question x : q) {
			//System.out.println(x.toString());
			JSONEditor.addQuestionWithImgAndExplanation(category, x);
		} 
		
		// Maps and creates the JSON file : Python_geeksforgeeks.json
		OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("Python_geeksforgeeks.json"), q);
	}
	/*
	private static void createJavaQuestions() throws JsonGenerationException, JsonMappingException, IOException {
		String category = "Java";
		JSONEditor.addCategory(category);
		
		// creates array for list of questions to add
		//gets questions for Inheritance
		ArrayList<Question> q = getQuestions("https://www.geeksforgeeks.org/java-gq/inheritance-2-gq/"); 
		q.addAll(getQuestions("https://www.geeksforgeeks.org/java-gq/abstract-class-and-interface-in-java-gq/"));
		q.addAll(getQuestions("https://www.geeksforgeeks.org/java-gq/arrays-gq/"));
		q.addAll(getQuestions("https://www.geeksforgeeks.org/java-gq/data-types-2-gq/"));
		q.addAll(getQuestions("https://www.geeksforgeeks.org/java-gq/constructors-2-gq/"));
		q.addAll(getQuestions("https://www.geeksforgeeks.org/java-gq/exception-handling-2-gq/"));
		q.addAll(getQuestions("https://www.geeksforgeeks.org/java-gq/packages-gq/"));
		q.addAll(getQuestions("https://www.geeksforgeeks.org/java-gq/class-and-object-2-gq/")); 
		q.addAll(getQuestions("https://www.geeksforgeeks.org/java-gq/functions-2-gq/")); 
		q.addAll(getQuestions("https://www.geeksforgeeks.org/java-gq/final-keyword-gq/"));
		q.addAll(getQuestions("https://www.geeksforgeeks.org/java-gq/operators-gq/"));


		
		//Prints out to terminal
		for (Question x : q) {
			JSONEditor.addQuestionWithImgAndExplanation(category, x);
		} 
		
		// Maps and creates the JSON file : Java_geeksforgeeks.json
		//OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("Java_geeksforgeeks.json"), q);
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

	private static void createCQuestions() throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<Question> q = getQuestions("https://www.geeksforgeeks.org/c-quiz-101-gq/");
		q.addAll(getQuestions("https://www.geeksforgeeks.org/c-quiz-103-gq/")); 
		q.addAll(getQuestions("https://www.geeksforgeeks.org/c-quiz-104-gq/")); 
		q.addAll(getQuestions("https://www.geeksforgeeks.org/c-quiz-108-gq/")); 
		q.addAll(getQuestions("https://www.geeksforgeeks.org/c-quiz-110-gq/")); 
		q.addAll(getQuestions("https://www.geeksforgeeks.org/c-quiz-111-gq/")); 
		
		//Prints out to terminal
		for (Question x : q) {
			System.out.println(x.toString());
		} 
		
		// Maps and creates the JSON file : c_geeksforgeeks.json
		OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("c_geeksforgeeks.json"), q);
		
	} */
	
	public static void removeAllButCustom() {
		ArrayList<String> categories = JSONOperations.returnCategories();
		
		for (String category : categories) {
			if (!category.equals("Custom")) {
				JSONEditor.deleteCategory(category);
			}
		}
	}
	
	
	public static void addImagesNExplanations() {
		JSONObject file = JSONOperations.createJSONObject(Constants.FILENAME);
		ArrayList<String> categories = JSONOperations.returnCategories();
		
		for (String category : categories) {
			JSONObject curCategory = file.getJSONObject(category);
			
			int numQuestions = curCategory.length();
			
			for (int i  = 0; i < numQuestions; i++) {
				String questionNum = "q" + (i+1);
				JSONObject question = curCategory.getJSONObject(questionNum);
				
				String topic = question.getString("topic");
				
				String explanation = getExplanationFile(questionNum,topic);
				
				
				if (explanation != null) {
					question.put("explanation", explanation);
				}
				
				
				
				String image = getImageFile(questionNum,topic);
				
				if (image != null) {
					question.put("image", image);
				}
				
				curCategory.put(questionNum,question);
				file.put(category, curCategory);
				
				try {
					//rewriting the full file text to the JSON file
					FileWriter file2 = new FileWriter(Constants.FILENAME);
					file2.write(file.toString());
					file2.close();
					//return true;
				} catch (Exception e) {
					//e.printStackTrace();
					//return false;
				}
			}
		}
	}
	
	
	private static String getImageFile(String questionNum, String topic) {
		File directory = new File("src/main/resources/images");
		File[] filesInDir = directory.listFiles();
		
		for (File file : filesInDir) {
			String nameOfFile = file.getName();
			
			if (nameOfFile.contains(questionNum) && nameOfFile.contains(topic)) {
				return nameOfFile;
			}
		}
		return null;
	}

	private static String getExplanationFile(String questionNum, String topic) {
		File directory = new File("src/main/resources/explanations");
		File[] filesInDir = directory.listFiles();
		
		for (File file : filesInDir) {
			String nameOfFile = file.getName();
			
			if (nameOfFile.contains(questionNum) && nameOfFile.contains(topic)) {
				return nameOfFile;
			}
		}
		return null;
	}

	public static void main(String args[]) throws JsonGenerationException, JsonMappingException, IOException {
	  /*
		createAlgoQuestions();
		createDSQuestions();
		createOSQuestions();
		createDBMSQuestions();
		createCompTheoryQuestions();
		createPythonQuestions(); 
		*/
		
		addImagesNExplanations();
		
		//removeAllButCustom();
	}

}
