package Layouts;


// This class is a quick and dirty way to add web question
// functionality. Should be refactored later.

public class DiffScreenWebRow extends DiffScreenRowLayout {

	public DiffScreenWebRow(String categoryName, DifficultyScreenLayout parentPane) {
		super(categoryName, parentPane);
		this.easy.setText("Graphs");
		this.medium.setText("Algorithms");
		this.hard.setText("Sorting");
		this.isWebRow = true;
	}

}
