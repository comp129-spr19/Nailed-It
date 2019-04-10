package Scrapper;
import com.gargoylesoftware.htmlunit.WebClient;

public class Scrapper {
	public static String baseUrl = "https://www.geeksforgeeks.org/algorithms-gq/analysis-of-algorithms-gq/";
	public static WebClient client = new WebClient();

public static void main(String args[]) {

	client.getOptions().setCssEnabled(false);
	client.getOptions().setJavaScriptEnabled(false);
	
}

}
	
