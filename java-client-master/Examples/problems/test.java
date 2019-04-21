package problems;
/**
 * Example presents usage of the successful test() API method  
 */


import com.SphereEngine.Api.ProblemsClientV3;
import com.SphereEngine.Api.Exception.NotAuthorizedException;
import com.SphereEngine.Api.Exception.ClientException;
import com.SphereEngine.Api.Exception.ConnectionException;
import com.google.gson.JsonObject;

public class test
{

	public static void main(String[] args) 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"cca1223f4cc2ad09f9d863d5361c534d", 
				"4e9d390b.compilers.sphere-engine.com");
		
		try {
			JsonObject response = client.test();
		} catch (NotAuthorizedException e) {
			System.out.println("Invalid access token");
		} catch (ClientException e) {
			System.out.println(e.getMessage());
		} catch (ConnectionException e) {
			System.out.println(e.getMessage());
		}
	}	
}
