import main.Answer;
import main.AnswerConverter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

// @RunWith(MockitoJUnitRunner.class)
public class AnswerConverterTester {
	
	@Test
	public void testStringToAnswerReturnsCorrectAnswerWhenGivenAValidAnswerString() {		
		// assertTrue(AnswerConverter.stringToAnswer("A") == Answer.ANSWER_A);
		assertEquals(AnswerConverter.stringToAnswer("A"), Answer.ANSWER_A);
	}
	
	@Test
	public void testStringToAnswerReturnsNullAnswerWhenGivenAnInvalidAnswerString() {
		assertNull(AnswerConverter.stringToAnswer("445646456zzaaa"));
	}
	
	@Test
	public void testStringToAnswerReturnsCorrectAnswerWhenGivenAValidLowercaseAnswerString() {
		assertEquals(AnswerConverter.stringToAnswer("d"), Answer.ANSWER_D);
	}	
}
