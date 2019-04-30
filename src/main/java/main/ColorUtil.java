package main;

public class ColorUtil {

	
	// returns a string that represents RGB color value mapped to a particular category
	public static String editorColor(String category) {
		int r,g,b;
		switch(category) {
		case "Algorithms":
			r = 121;
			g = 169; 
			b = 247;
			break;
		case "Data Structures":
			r = 121;
			g = 230;
			b = 247;
			break;
		case "Comp Theory":
			r = 66;
			g = 134;
			b = 244;
			break;
		case "Database":
			r = 121;
			g = 247;
			b = 211;
			break;
		case "Custom":
			r = 121;
			g = 247;
			b = 159;
			break;
			
		case "Operating Systems":
			r = 135;
			g = 247;
			b = 121;
			break;
		case "Python":
			r = 188;
			g = 247;
			b = 121;
			break;
		default:
			r = 238;
			g = 247;
			b = 121;
			
		
		}
		String colors = "rgb(" + r + "," + g + "," + b + ")";
		return "-fx-color: " + colors;
	}
}
