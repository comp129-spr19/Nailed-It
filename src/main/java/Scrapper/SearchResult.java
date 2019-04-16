package Scrapper;

import java.util.List;

public class SearchResult {

	private final String question;
	
	private final List<String> answer;

	public SearchResult(String question, List<String> answer2) {
		super();
		this.question = question;
		this.answer = answer2;
	}

	public String getQuestion() {
		return question;
	}

	public List<String> getAnswer() {
		return answer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchResult other = (SearchResult) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SearchResult [question=" + question + ", answer=" + answer + "]";
	}

	
}
