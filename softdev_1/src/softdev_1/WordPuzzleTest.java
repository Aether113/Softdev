/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package softdev_1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the implementation of the {@link WordPuzzle}
 * @author Jeroen Van Aken
 *
 */
public class WordPuzzleTest {

    WordPuzzle myPuzzle = null;

    /**
     * This function will initialize the myPuzzle variable before you start a new test method
     * @throws Exception
     */
    @Before
    public void setUp() {
        try {
            this.myPuzzle = new WordPuzzle("VNYBKGSRORANGEETRNXWPLAEALKAPMHNWMRPOCAXBGATNOMEL", 7);
        } catch (IllegalArgumentException ex) {
                System.out.println("An exception has occured");
                System.out.println(ex.getMessage());
        }
            
    }

    /**
     * Test the constructor of the {@link WordPuzzle} class
     */
    @Test
    public void testWordPuzzle() {
            assertNotNull("The object failed to initialize", this.myPuzzle);
            char[][] expectedArray = {{'V','N','Y','B','K','G','S'},
                                     {'R','O','R','A','N','G','E'},
                                     {'E','T','R','N','X','W','P'},
                                     {'L','A','E','A','L','K','A'},
                                     {'P','M','H','N','W','M','R'},
                                     {'P','O','C','A','X','B','G'},
                                     {'A','T','N','O','M','E','L'}};
            assertArrayEquals(expectedArray, this.myPuzzle.getLetterArray());
    }

    /**
     * Test to search for some words...
     */
    @Test
    public void testSearchWord() {
        	assertTrue("The word BANANA is not found", this.myPuzzle.searchWord("BANANA"));
        	assertTrue("The word ORANGE is not found", this.myPuzzle.searchWord("ORANGE"));
        	assertTrue("The word APPLE is not found", this.myPuzzle.searchWord("APPLE"));
        	assertTrue("The word CHERRY is not found", this.myPuzzle.searchWord("CHERRY"));
        	assertTrue("The word TOMATO is not found", this.myPuzzle.searchWord("TOMATO"));
        	assertTrue("The word LEMON is not found", this.myPuzzle.searchWord("LEMON"));
            assertFalse("The word SOFTWARE is found, and may not be found", this.myPuzzle.searchWord("SOFTWARE"));
    }

}