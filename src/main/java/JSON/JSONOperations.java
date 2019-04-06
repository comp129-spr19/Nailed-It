package JSON;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.*;

import main.Answer;
import main.AnswerConverter;
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
	
	// returns a list of question objects based on category and difficulty.
	public static ArrayList<Question> getQuestions(String qCategory, String qDifficulty) {
		ArrayList<Question> questions = new ArrayList<Question>();
		
		JSONObject file = createJSONObject("Project2.json");
		JSONObject category =  file.getJSONObject(qCategory);
		JSONObject difficulty =  category.getJSONObject(qDifficulty);
		
		for (int i = 0; i < difficulty.length(); i++) {
			JSONObject currentQuestion = difficulty.getJSONObject("q"+ (i+1));
			
			String questionDescription = currentQuestion.getString("question");
			String answerA = currentQuestion.getString("answer_a");
			String answerB = currentQuestion.getString("answer_b");
			String answerC = currentQuestion.getString("answer_c");
			String answerD = currentQuestion.getString("answer_d");
			String hint = currentQuestion.getString("hint");
			Answer correctAnswer = AnswerConverter.stringToAnswer(currentQuestion.getString("correct_answer"));
			
			questions.add(new Question(questionDescription,answerA,answerB,answerC,
										answerD,hint, correctAnswer));
			
			
		}
		return questions;
		
		
	}
	
	
	public static void main(String args[]) {
		JSONObject obj = createJSONObject("Project2.json");
		ArrayList<Question> questions = getQuestions("Algorithms","medium");
		for (Question question : questions) {
			System.out.println(question.getQuestion() + question.getAnswerA());
		}
	} 
}