package Scrapper;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsoupScrapper {

    private static final String USER_AGENT = "\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36\"";
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	public static void main(String[] args) throws IOException {
		
		//downloads the GEEKSFOR GEEKS and parses it
		final Document page = Jsoup.connect("https://www.geeksforgeeks.org/algorithms-gq/analysis-of-algorithms-gq/").get();
		
		//result list for all elements
		final Elements srcElement = page.select("#mtq_question_container-1");
		
		for (Element x : srcElement) {
			System.out.println(x.text());
		}
	}
}
