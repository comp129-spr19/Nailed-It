
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.control.ToggleButton;
import main.ColorUtil;
import main.GenerateQuestionScreens;
import main.MainStage;

public class GenerateQuestionsTester {
	
	
	@Test
	public void shouldReturnEmptyList() throws IOException {
		MainStage x = mock(MainStage.class);
		
		
		ArrayList<ToggleButton> categoryButtons = new ArrayList<ToggleButton>();
		
		assertEquals(0,GenerateQuestionScreens.generate(categoryButtons,x).size());
		 

	}
	
	/*
	@Test
	public void noneToggledShouldReturnEmptyList() throws IOException {
		MainStage x = mock(MainStage.class);
		
		
		ArrayList<ToggleButton> categoryButtons = new ArrayList<ToggleButton>();
		//ToggleButton a = mock(ToggleButton.class);
		ToggleButton a = new ToggleButton("Custom");
		//when(a.isSelected()).thenReturn(false);
		
		categoryButtons.add(a);
		
		assertEquals(0,GenerateQuestionScreens.generate(categoryButtons,x).size());
		 
	} */
}
