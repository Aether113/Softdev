//Author: Jens Vangindertael
package softdev_1;
public class main {
	
	static WordPuzzle myPuzzle = new WordPuzzle("VNYBKGSRORANGEETRNXWPLAEALKAPMHNWMRPOCAXBGATNOMEL", 7);
	String wordToSearch;

	public static void main(String[] args) {
	
		Gui flair = new Gui(myPuzzle);
		flair.show(myPuzzle);
		
		//TODO: visually show the word that was found instead of MessageBox
	}
}

