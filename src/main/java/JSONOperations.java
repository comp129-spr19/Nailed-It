
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.json.*;


public class JSONOperations {
	
	// creates a new JSON object based on filename 
	// assumes user of the method enters correct filename and path
	public JSONObject createJSONObject(String filename) {
		 File jsonFile = new File(filename);
		 String text = convertFileToString(jsonFile);
		 return new JSONObject(text);
	 }
	
	// helper function for createJSONObject. Reads content
	// in a file line by line and stores it in a string.
	private String convertFileToString(File file) {
		String content = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			StringBuilder stringBuffer = new StringBuilder();
			String line = reader.readLine();
			while (line != null) {
				stringBuffer.append(line);
				line = reader.readLine();
			}
			content = stringBuffer.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
}
