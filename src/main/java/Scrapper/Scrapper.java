package Scrapper;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;

import com.gargoylesoftware.*;

public class Scrapper {
	public static String baseUrl = "https://www.geeksforgeeks.org/algorithms-gq/searching-and-sorting-gq/";
	public static WebClient client = new WebClient();

public static void main(String args[]) {

	client.getOptions().setCssEnabled(false);
	client.getOptions().setJavaScriptEnabled(false);
	
	
		
		HtmlPage page;
		try {
			page = client.getPage(baseUrl);
			//System.out.println(page.asXml());
			
			System.out.println(page.getHtmlElementById("mtq_question_text-9-1").asText());
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	
	
}

}
	
