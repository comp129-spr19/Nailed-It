//Attempt at a Mockito test - failed due to dependency issues.

import static org.junit.Assert.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
import org.mockito.Mockito;
import org.mockito.mock.*;

import JSON.*;
import main.*;

//@RunWith(PowerMockRunner.class)
@PrepareForTest(JSONEditor.class)
public class JSONEditorTester {

	@Test
	public void testJSONEditorRecievesInputFromScreen() {
		PowerMockito.mockStatic(JSONEditor.class);
		Mockito.when(JSONEditor.updateQuestion(new Question("Jim","Bob","Joe","Fred","Frank","name",Answer.ANSWER_A))).thenReturn(true);
		assertTrue(JSONEditor.updateQuestion(new Question("Jim","Bob","Joe","Fred","Frank","name",Answer.ANSWER_A)));
	}

}
