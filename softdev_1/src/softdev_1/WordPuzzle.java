package softdev_1;

import java.awt.Point;

public class WordPuzzle {
	
	private char[][] letterArray;
	private int numberOfRows, numberOfColumns;
	Point boundary = new Point();
	public Point start;
	public Direction dir;
	
	public enum Direction{
		NORTH (0,-1),
		NORTHEAST (1,-1),
		EAST(1,0),
		SOUTHEAST (1,1),
		SOUTH (0,1),
		SOUTHWEST (-1,1),
		WEST (-1,0),
		NORTHWEST (-1,-1);
		
	    private final int dx;   
	    private final int dy; 
	    
	    Direction(int dx, int dy) {
	        this.dx = dx;
	        this.dy = dy;
	    }
	} 
	
/*
 * Takes in a puzzle in a String format, divided by numberOfRows. The constructor 
 * parses the info to a char[][] array. 
 **/
	
	public WordPuzzle(String puzzle, int numberOfRows){
		//Dimensions of the puzzle
		this.numberOfRows = numberOfRows;
		this.numberOfColumns = puzzle.length()/numberOfRows;
		//Create 2D array
		this.letterArray = new char[numberOfRows][numberOfColumns];
		//Define the boundary of the puzzle		
		boundary.y = numberOfRows -1;
		boundary.x = numberOfColumns -1;
		//Fill empty array with letters
		int index = 0;
		for(int i = 0; i < numberOfRows; i++){
			for(int j = 0; j < numberOfColumns; j++){
				this.letterArray[i][j] = puzzle.charAt(index);
				index++;
			}
		}
	}
	
	public boolean searchWord(String word){
		/*
		 * Search for the first letter, if letter is found, call explore method
		 * to match remaining letters.
		*/
		for(int i = 0; i <= boundary.x; i++){
			for(int j = 0; j <= boundary.y; j++){
				if(letterArray[i][j] == word.charAt(0)){
					Point coord = new Point(i, j);
					
					if(doraTheExplorer(word, coord) == true){
						//System.out.println("Found word: " + word + "!");
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean doraTheExplorer(String word, Point coord){
		Direction[] directions = Direction.values();
		
		for(Direction dir: directions){  	//search every direction for next matching letter
			
			//reinitialize conditions for every direction
			int length =  word.length() -1;
			String truncatedWord = word.substring(1);
			Point follower = new Point();
			follower.setLocation(coord.getX(), coord.getY());
			
			//System.out.println(dir.toString());
			
			for(int i = length; i >= 0; i--){
				
				//if out of bounds, skip
				if(((follower.x + dir.dx) < 0) || ((follower.y + dir.dy) < 0) || ((follower.y + dir.dy) > boundary.y) || ((follower.x + dir.dx) > boundary.x)){
					continue;
				}
				//check if next letter is correct, keep checking until all the letters are matched, else go back to square 1
				if(truncatedWord.charAt(0) == letterArray[follower.x + dir.dx][follower.y + dir.dy]){
					length --;
					truncatedWord = truncatedWord.substring(1);
					follower.x += dir.dx;
					follower.y += dir.dy;
				}
				
				if(length == 0){
					this.dir = dir;
					this.start = coord;
					return true;
				}
			}
		}
		return false;
	}
	
	public int getNumberOfRows(){
		return numberOfRows;
	}
	
	public int getNumberOfColumns(){
		return numberOfColumns;
	}

	public char[][] getLetterArray() {
		return letterArray;
	}
	
	public char getLetterAt(int x, int y){
		return letterArray[x][y];
	}
}
	

