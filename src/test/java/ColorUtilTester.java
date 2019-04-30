
import java.awt.event.ActionEvent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import main.ColorUtil;

public class ColorUtilTester {

	
	@Test
	public void returnDefaultValue() {
		assertEquals(ColorUtil.editorColor(""),"-fx-color: rgb(238,247,121)" );
		assertEquals(ColorUtil.editorColor("  "),"-fx-color: rgb(238,247,121)" );
		assertEquals(ColorUtil.editorColor("testing"),"-fx-color: rgb(238,247,121)" );
	}
	
	@Test
	public void shouldReturnAlgorithmsColor() {
		assertEquals(ColorUtil.editorColor("Algorithms"),"-fx-color: rgb(121,169,247)" );
	}
	
	@Test
	public void shouldReturnDataStructuresColor() {
		assertEquals(ColorUtil.editorColor("Data Structures"),"-fx-color: rgb(121,230,247)" );
	}
	
	@Test
	public void shouldReturnCustomColor() {
		assertEquals(ColorUtil.editorColor("Custom"),"-fx-color: rgb(121,247,159)" );
	}
}
