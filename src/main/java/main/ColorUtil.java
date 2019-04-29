package main;

public class ColorUtil {

	
	// returns a string that represents RGB color value mapped to a particular category
	public static String editorColor(String category) {
		int r,g,b;
		switch(category) {
		case "Algorithms":
			r = 244;
			g = 66; 
			b = 104;
			break;
		case "Data Structures":
			r = 244;
			g = 66;
			b = 188;
			break;
		case "Comp Theory":
			r = 66;
			g = 134;
			b = 244;
			break;
		case "Database":
			r = 66;
			g = 206;
			b = 244;
			break;
		case "Custom":
			r = 66;
			g = 244;
			b = 194;
			break;
			
		case "Operating Systems":
			r = 65;
			g = 244;
			b = 80;
			break;
		case "Python":
			r = 161;
			g = 244;
			b = 66;
			break;
		default:
			r = 244;
			g = 191;
			b = 66;
			
		
		}
		String colors = "rgb(" + r + "," + g + "," + b + ")";
		return "-fx-color: " + colors;
	}
}
