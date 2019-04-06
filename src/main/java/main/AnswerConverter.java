package main;

public class AnswerConverter {
public static Answer stringToAnswer(String answer) {
		
		answer = answer.toUpperCase();
		Answer selectedAnswer;
		switch (answer) {
		case "A" :
			selectedAnswer = Answer.ANSWER_A;
		
			break;
		case "B":
			selectedAnswer = Answer.ANSWER_B;
		
			break;
		case "C":
			selectedAnswer = Answer.ANSWER_C;
			
			break;
		case "D":	
			selectedAnswer = Answer.ANSWER_D;
		
			break;
	
		default:
			selectedAnswer = Answer.ANSWER_A;
			
			
	}
		return selectedAnswer;
	}
	
}
