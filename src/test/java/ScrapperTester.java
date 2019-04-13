import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.Answer;
import main.Question;



import Scrapper.Scrapper;
public class ScrapperTester {
	
	/*
	@Before
	public void setUp() {
		PowerMockito.mockStatic(Scrapper.class);
		
		ArrayList<Question> sample = new ArrayList<Question>();
		
		for (int i = 0; i < 10; i++) {
			sample.add(new Question("test","1","2","3","4","hint",Answer.ANSWER_A));
		}
		PowerMockito.when(Scrapper.getQuestions("https://www.geeksforgeeks.org/algorithms-gq/graph-shortest-paths-gq/")).thenReturn(sample);
		PowerMockito.when(Scrapper.getQuestions("https://www.geeksforgeeks.org/algorithms-gq/searching-and-sorting-gq/")).thenReturn(sample);
	}
	*/
	@Test public void testSiteOne() {
		for (Question q :Scrapper.getQuestions("https://www.geeksforgeeks.org/algorithms-gq/graph-shortest-paths-gq/")) {
			assertNotNull(q);
		}
		
		
	} 
	
	@Test public void testSiteTwo() {
		for (Question q :Scrapper.getQuestions("https://www.geeksforgeeks.org/algorithms-gq/searching-and-sorting-gq/")) {
			assertNotNull(q);
		}
	}
	
	
	//public ArrayList<Question> scrapperGettingQuestions() {
		
	//}
	

}
