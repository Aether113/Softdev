package softdev_1;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;


public class Gui extends JFrame implements KeyListener{
	
	private static final long serialVersionUID = 1L;
	
	JTextField wordEntry;
	WordPuzzle myPuzzle;
	
	public Gui(WordPuzzle myPuzzle){
		this.myPuzzle = myPuzzle;
	}

	
	public void show(WordPuzzle myPuzzle){
		
	    JFrame frame = new JFrame("WordPuzzle");
		
		JPanel main = new JPanel();
	    JPanel grid = new JPanel();
	    JPanel wordPane = new JPanel();
	    
	    main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
	    
	    wordEntry = new JTextField(20);
	    wordEntry.addKeyListener(this);
	    wordEntry.setAlignmentX(Component.RIGHT_ALIGNMENT);
	    
	    JLabel wordLabel = new JLabel("Search word:    ");
	    wordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    
	    wordPane.add(wordLabel);
	    wordPane.add(wordEntry);
	    wordPane.setBackground(Color.LIGHT_GRAY);
	    wordPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);

	    grid.setLayout(new GridLayout(myPuzzle.getNumberOfColumns(), myPuzzle.getNumberOfRows()));
	    grid.setPreferredSize(new Dimension(400, 400));
	    
	    for (int i = 0; i < myPuzzle.getNumberOfRows(); i++) {
	        for (int j = 0; j < myPuzzle.getNumberOfColumns(); j++) {
	        	JLabel letter = new JLabel(Character.toString(myPuzzle.getLetterAt(i, j)));
	        	letter.setHorizontalAlignment(JLabel.CENTER);
	        	
	        	grid.add(letter);
	        }
	    }
	    
	    main.add(grid);
	    main.add(wordPane);
		  
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(main);
	    frame.pack();
	    frame.setVisible(true);
	}
	
	public void search(){
		String word = null;
		
		try {

			word = wordEntry.getText();

		} catch (Exception e){

		}
		
		word = word.toUpperCase();
		
		if(this.myPuzzle.searchWord(word) == true){
			JOptionPane.showMessageDialog(null, "The word "+word+" was found!");
		}
		
		else{
			JOptionPane.showMessageDialog(null, "The word "+word+" was NOT found! :(");
		}
	}

	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			search();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
