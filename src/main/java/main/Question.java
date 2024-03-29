package main;

// class that represents a question
public class Question {
	private String name, topic, image,explanation, question, answerA, answerB, answerC, answerD, hint;
	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	private Answer correctAnswer;
	
	public Question(String topic, String question, String answerA, String answerB, String answerC, 
	 		String answerD, String hint,Answer answer) {
		this("", topic, "", "", question, answerA, answerB, answerC, answerD, hint, answer);
	}
	
	public Question(String name, String topic, String question,String answerA, String answerB, String answerC, 
	 		String answerD, String hint,Answer answer) {
		this(name, topic, "", "", question, answerA, answerB, answerC, answerD, hint, answer);
	}
	
	public Question(String name, String topic, String image, String question,String answerA, String answerB, String answerC, 
	 		String answerD, String hint,Answer answer) {
		this(name, topic, image, "", question, answerA, answerB, answerC, answerD, hint, answer);
	}
	
	public Question(String name, String topic, String image,String explanation, String question,String answerA, String answerB, String answerC, 
			 		String answerD, String hint,Answer answer) {
		this.name = name;
		
		this.topic = topic;
		this.image = image;
		this.question = question;
		this.explanation = explanation;
		
		this.answerA = answerA;
		this.answerB = answerB;
		this.answerC = answerC;
		this.answerD = answerD;
		
		this.correctAnswer = answer;
		
		this.hint = hint;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswerA() {
		return answerA;
	}
	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}
	public String getAnswerB() {
		return answerB;
	}
	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}
	public String getAnswerC() {
		return answerC;
	}
	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}
	public String getAnswerD() {
		return answerD;
	}
	public void setAnswerD(String answerD) {
		this.answerD = answerD;
	}
	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
	public Answer getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(Answer correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return ("Question: " + this.question + "\nA: " + getAnswerA()
		  + "\nB: " + getAnswerB()+ "\nC: " + getAnswerC() + "\nD: " + getAnswerD() +
		  "\nhint: " + getHint() + "\nCorrect answ: " +  getCorrectAnswer().toString());
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
