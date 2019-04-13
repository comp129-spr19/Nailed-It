import java.awt.event.ActionEvent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import Layouts.DiffScreenRowLayout;
import Layouts.DifficultyScreenLayout;
import main.MainStage;
import main.Question;

public class DiffScreenTester {
	
	@Test 
	public void testIfSsDifficultySelectedRtTrue() {
		DiffScreenRowLayout x = mock(DiffScreenRowLayout.class);
		
		
		boolean[] shouldReturnTrue = {true,true,true,false};
		when(x.getDifficultySet()).thenReturn(shouldReturnTrue);
		//MainStage main = new MainStage();
		//DifficultyScreenLayout test = new DifficultyScreenLayout(main); 
		assertEquals(true,DifficultyScreenLayout.checkDiffSet(x.getDifficultySet()));
		
	}

	
}
