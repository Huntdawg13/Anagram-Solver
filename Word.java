import java.util.*;

/**
 * Class used to manage, store a orginal version of a word, store 
 * the word's canonical version, and help compare words. 
 *  
 * @author Hunter J. McClure
 * @version (May 14 2019)
 */
public class Word implements Comparable<Word>
{
   private String theWord;
   private String Canonical;
   
   /**
    * Constructor that creates and stores a word and it's canonical form  
    * 
    * @param x The word that is being sent to be stored and that is being used to find the canonical form
    * @return nothing
    */
   public Word(String x)
   {
      theWord = x;

      char[] temp = theWord.toCharArray();
      Arrays.sort(temp);
      
      Canonical = String.valueOf(temp);
   }
   
   /**
    * Gets and returns the orginal form of the word  
    * 
    * @param nothing
    * @return The original and unchanged version of the word
    */
   public String getWord()
   {
      return theWord;
   }
   
   /**
    * Gets and returns the canonical form of the word  
    * 
    * @param nothing
    * @return The canonical version of the word
    */
   public String getForm()
   {
      return Canonical;
   }
   
   /**
    * Creates a string that shows the original word and then it's canonical form  
    * 
    * @param nothing
    * @return A string showing the original and canonical form of a word
    */
   public String toString()
   {
      return "[" + theWord + "=" + Canonical + "]";
   }
   
   /**
    * A method that compares two words' canonical version of the words and returns a int  
    * 
    * @param x The selected word that is being compared to
    * @return A number based on if it is equal or not
    */
   public int compareTo(Word x)
   {
      return this.Canonical.compareTo(x.getForm());
   }
}