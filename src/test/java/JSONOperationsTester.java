import static org.junit.Assert.*;

import JSON.JSONOperations;

import org.json.JSONException;
import org.junit.Test;

public class JSONOperationsTester {
	
	@Test public void testJSONObjectIsNotNullGivenAValidPathname() {
		assertNotNull(JSONOperations.createJSONObject("Project2.json"));
	}
	
	@Test(expected = JSONException.class) public void testCreateJSONObjectThrowsExceptionWhenGivenAnInvalidPathname() {
		JSONOperations.createJSONObject("filenotfound.json");
	}
	
	@Test public void testQuestionArrayListSizeIsNotZeroWithValidCategoryAndDifficulty() {
		assertTrue(JSONOperations.getQuestions("Algorithms", "hard").size() > 0);
	}
	
	@Test(expected = JSONException.class) public void testGetQuestionsThrowsExceptionWithInvalidCategoryAndDifficulty() {
		JSONOperations.getQuestions("Python", "extreme");
	}
}
