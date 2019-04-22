package JSON;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.*;

import javafx.scene.control.ComboBox;
import main.Answer;
import main.AnswerConverter;
import main.Constants;
import main.Question;


public class JSONOperations {
	
	// creates a new JSON object based on filename 
	// assumes user of the method enters correct filename and path
	public static JSONObject createJSONObject(String filename) {
		 File jsonFile = new File(filename);
		 String text = convertFileToString(jsonFile);
		 return new JSONObject(text);
	 }
	
	// helper function for createJSONObject. Reads content
	// in a file line by line and stores it in a string.
	private static String convertFileToString(File file) {
		String content = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			StringBuilder stringBuffer = new StringBuilder();
			String line = reader.readLine();
			while (line != null) {
				stringBuffer.append(line);
				line = reader.readLine();
			}
			content = stringBuffer.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
	
	// returns a list of question objects based on category.
	public static ArrayList<Question> getQuestions(String qCategory) {
		//if(!containsCategory(qCategory) && !isValidDiff(qDifficulty)) {
		//	return null;
		//}
		ArrayList<Question> questions = new ArrayList<Question>();
		
		JSONObject file = createJSONObject(Constants.FILENAME);
		JSONObject category =  file.getJSONObject(qCategory);
		
		for (int i = 0; i < category.length(); i++) {
			String name = "q" + (i+1);
			JSONObject currentQuestion = category.getJSONObject(name);

			String topic = currentQuestion.getString("topic");
			String questionDescription = currentQuestion.getString("question");
			String answerA = currentQuestion.getString("answer_a");
			String answerB = currentQuestion.getString("answer_b");
			String answerC = currentQuestion.getString("answer_c");
			String answerD = currentQuestion.getString("answer_d");
			String hint = currentQuestion.getString("hint");
			Answer correctAnswer = AnswerConverter.stringToAnswer(currentQuestion.getString("correct_answer"));
			
			questions.add(new Question(name, topic, questionDescription,answerA,answerB,answerC,
										answerD,hint, correctAnswer));
			
			
		}
		return questions;
		
		
	}
	
	
	
	public static int getNumCategories() {
		JSONObject file = createJSONObject(Constants.FILENAME);
		return file.length();
	}
	
	
	public static ComboBox<String> returnCategoryList(){
		JSONObject file = createJSONObject(Constants.FILENAME);
		ComboBox<String> catList = new ComboBox<String>();
		Iterator<String> iterator = file.keys();
		
		while(iterator.hasNext()) {
			catList.getItems().add(iterator.next());
		}
		return catList;
	}
	/*
	private static ArrayList<String> getStrCatList() {
		JSONObject file = createJSONObject(Constants.FILENAME);
		ArrayList<String> catList = new ArrayList<String>();
		Iterator<String> iterator = file.keys();
		
		while(iterator.hasNext()) {
			catList.add(iterator.next());
		}
		return catList;
	}
	
	private static boolean containsCategory(String category) {
		ArrayList<String> catList = getStrCatList();
		//System.out.println(category);
		for (String cat : catList) {
			if (category.equals(cat));
				return true;
		}
		return false;
	}
	
	private static boolean isValidDiff(String diff) {
		return (diff.equals("easy") || diff.equals("medium") || diff.equals("hard"));
	} */
	
	public static void main(String args[]) {
		JSONObject obj = createJSONObject(Constants.FILENAME);
		System.out.println(obj.length());
		ArrayList<Question> questions = getQuestions("Algorithms");
		for (Question question : questions) {
			System.out.println(question.getQuestion() + question.getAnswerA());
		}
		//for (String key : obj.keys().) {
		Iterator<String> x = obj.keys();
		
		while (x.hasNext()) {
			System.out.println(x.next());
		}
		//}//
	} 
}
