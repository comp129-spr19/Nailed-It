
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
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
	
	
	@Test
	public void noneToggledShouldReturnEmptyList() throws IOException {
		MainStage x = mock(MainStage.class);
		
		// need a fxPanel to test fx components.
		JFXPanel fxPanel = new JFXPanel();
		
		ArrayList<ToggleButton> categoryButtons = new ArrayList<ToggleButton>();
		
		
		ToggleButton a = new ToggleButton("Custom");
		a.setSelected(false);
		
		
		categoryButtons.add(a);
		
		assertEquals(0,GenerateQuestionScreens.generate(categoryButtons,x).size());
		 
	} 
	
	@Test
	public void OneToggledShouldReturnNonEmptyList() throws IOException {
		MainStage x = mock(MainStage.class);
		
		// need a fxPanel to test fx components.
		JFXPanel fxPanel = new JFXPanel();
		
		ArrayList<ToggleButton> categoryButtons = new ArrayList<ToggleButton>();
		
		
		ToggleButton a = new ToggleButton("Custom");
		a.setSelected(true);
		
		categoryButtons.add(a);
		
		assertTrue(GenerateQuestionScreens.generate(categoryButtons,x).size() > 0);
		 
	}
}
