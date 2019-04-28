import static org.junit.Assert.*;

import JSON.JSONOperations;
import main.Constants;

import org.json.JSONException;
import org.junit.Test;

public class JSONOperationsTester {
	
	@Test
	public void testJSONObjectIsNotNullGivenAValidPathname() {
		assertNotNull(JSONOperations.createJSONObject(Constants.FILENAME));
	}
	
	@Test (expected = JSONException.class) 
	public void testCreateJSONObjectThrowsExceptionWhenGivenAnInvalidPathname() {
		JSONOperations.createJSONObject("filenotfound.json");
	}
	
	@Test 
	public void testQuestionArrayListSizeIsNotZeroWithValidCategoryAndDifficulty() {
		assertTrue(JSONOperations.getQuestions("Algorithms").size() > 0);
	}
	
	@Test (expected = JSONException.class) 
	public void testGetQuestionsThrowsExceptionWithInvalidCategoryAndDifficulty() {
		JSONOperations.getQuestions("Bears");
	}
}
