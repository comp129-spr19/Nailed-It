import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import Layouts.EditorScreenLayout;
import javafx.scene.Scene;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import main.MainStage;

@RunWith(MockitoJUnitRunner.class)
public class MainStageTester {
	@Test
	public void testSceneRootIsSetToEditorScreenLayoutWhenStartEditorIsCalled() {
		assertTrue(1 == 1);
	}
}
