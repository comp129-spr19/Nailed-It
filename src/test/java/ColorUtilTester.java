
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
	}
}
