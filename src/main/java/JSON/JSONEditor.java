package JSON;


import java.io.*;
import java.util.*;
import org.json.*;
import main.*;


public abstract class JSONEditor {

	/*
	 * Takes in updates to a question along with question category
	 * Changes JSON file accordingly
	 */
	public static boolean updateQuestion(String categoryStr, Question question) {
		//getting the needed JSON Objects
		JSONObject file = JSONOperations.createJSONObject(Constants.FILENAME);
		JSONObject category = file.getJSONObject(categoryStr);
		JSONObject editQ = category.getJSONObject(question.getName());
		
		//altering the question object
		editQ.put("topic", question.getTopic());
		editQ.put("question", question.getQuestion());
		editQ.put("answer_a", question.getAnswerA());
		editQ.put("answer_b", question.getAnswerB());
		editQ.put("answer_c", question.getAnswerC());
		editQ.put("answer_d", question.getAnswerD());
		editQ.put("correct_answer", question.getCorrectAnswer().toString());
		editQ.put("hint", question.getHint());
		
		//placing the edited question into the full file text
		category.put(question.getName(), editQ);
		file.put(categoryStr, category);
		
		try {
			//rewriting the full file text to the JSON file
			FileWriter file2 = new FileWriter(Constants.FILENAME);
			file2.write(file.toString());
			file2.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean addQuestion(String categoryStr, Question question) {
		//getting the needed JSON Objects
		JSONObject file = JSONOperations.createJSONObject(Constants.FILENAME);
		JSONObject category = file.getJSONObject(categoryStr);
		JSONObject newQ = new JSONObject();
		
		//finding out what new question's name is
		String name = "q" + (category.length() + 1);
		
		//filling the question object
		newQ.put("topic", question.getTopic());
		newQ.put("question", question.getQuestion());
		newQ.put("answer_a", question.getAnswerA());
		newQ.put("answer_b", question.getAnswerB());
		newQ.put("answer_c", question.getAnswerC());
		newQ.put("answer_d", question.getAnswerD());
		newQ.put("correct_answer", question.getCorrectAnswer().toString());
		newQ.put("hint", question.getHint());
		
		//placing the new question into the full file text
		category.put(name, newQ);
		file.put(categoryStr, category);
		
		try {
			//rewriting the full file text to the JSON file
			FileWriter file2 = new FileWriter(Constants.FILENAME);
			file2.write(file.toString());
			file2.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean deleteQuestion(String categoryStr, String question) {
		//getting the needed JSON Objects
		JSONObject file = JSONOperations.createJSONObject(Constants.FILENAME);
		JSONObject category = file.getJSONObject(categoryStr);
		
		//removing the question requested
		int index = Integer.parseInt(question.substring(1));
		for (int j = index; j < category.length(); j++) {
			//put next question into current question space
			JSONObject moveQ = category.getJSONObject("q" + (j+1));
			category.put("q" + j, moveQ);
		}
		//remove last question
		category.remove("q" + category.length());
		
		//placing the altered file text back into the file object
		file.put(categoryStr, category);
		
		try {
			//rewriting the new file text to the JSON file
			FileWriter file2 = new FileWriter(Constants.FILENAME);
			file2.write(file.toString());
			file2.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean reloadBackupFile() {
		JSONObject file = JSONOperations.createJSONObject(Constants.BACKUP_FILENAME);
		
		try {
			//reloading the full file text to the Project2 JSON file
			FileWriter file2 = new FileWriter(Constants.FILENAME);
			file2.write(file.toString());
			file2.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static boolean addCategory(String category) {
		
		if (categoryDoesNotExist(category)) {
		
		JSONObject file = JSONOperations.createJSONObject(Constants.FILENAME);
		file.put(category, new JSONObject());
		
		try {
			//reloading the full file text to the Project2 JSON file
			FileWriter file2 = new FileWriter(Constants.FILENAME);
			file2.write(file.toString());
			file2.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
		return false;
	}

	// checks to see if category exists
	private static boolean categoryDoesNotExist(String category) {
		ArrayList<String> currCategories = JSONOperations.returnCategories();
		
		for (String cat : currCategories) {
			if (cat.equals(category)) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		addCategory("test");
	}
	
	
}
