package JsoupScrapper;

import org.apache.http.HttpHost;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UnirestForJsoupScrapper {

	public static void main(String[] args) throws Exception {
		//Change the default user-agent header
		Unirest.setDefaultHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");
		
		//Use a proxy
		Unirest.setProxy(new HttpHost("84.241.22.20", 23500));
		
		//Making a simple get request to httpbin.org
		final HttpResponse<JsonNode> response = Unirest.get("https://httpbin.org/get").queryString("limit", 10).asJson();
		
		System.out.println("IP-Address: " + response.getBody().getObject().getString("origin"));
		System.out.println("User-Agent:  " + response.getBody().getObject().getJSONObject("headers").getString("User-Agent"));
	}
}
