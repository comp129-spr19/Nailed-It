import main.Answer;
import main.AnswerConverter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AnswerConverterTester {
	
	@Test
	public void testStringToAnswerReturnsCorrectAnswerWhenGivenAValidAnswerString() {		
		assertTrue(AnswerConverter.stringToAnswer("A") == Answer.ANSWER_A);
	}
}
