package main;
public enum Answer {
	ANSWER_A,
	ANSWER_B,
	ANSWER_C,
	ANSWER_D;

	public String toString() {
		switch(this) {
		case ANSWER_A:
			return "a";
		case ANSWER_B:
			return "b";
		case ANSWER_C:
			return "c";
		case ANSWER_D:
			return "d";
		default:
			return "error";
		}
	}
}