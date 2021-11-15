import java.util.*;

/**
 * Class used to manage, sort, and get information on the words 
 * and their canonical forms. This class helps create sets and 
 * arrays of the anagrams and helps find each word's anagram.
 *  
 * @author Hunter J. McClure
 * @version (May 14 2019)
 */
public class AnagramManager
{
   private Word[] theWords;
   private Random rand = new Random();
   
   /**
    * Creates a constructor for the Anagram Finder that
    * stores the starting words/dictionary. It uses a for 
    * each loop to add each word from a list into a string array
    * 
    * @param List It is a list that contains the starting amount of words being used
    * @return nothing
    * @throws IllegalArgumentException When length of the list is less than 1 or when the list is null
    */
   public AnagramManager(List<String> List)
   {
      if (List == null || List.size() < 1)
      {
         throw new IllegalArgumentException();
      }
      
      theWords = new Word[List.size()];
      int counter = 0;
      for (String currentWord : List)
      {
         theWords[counter] = new Word(currentWord);
         counter++;
      } 
  }
   
   /**
    * Sorts the main array of words by the word itself   
    * 
    * @param nothing
    * @return nothing
    */
   public void sortByWord()
   {
      sortByWord(theWords);
   }
   
   /**
    * Sorts the main array of words by the words' canonical form   
    * 
    * @param nothing
    * @return nothing
    */
   public void sortByForm()
   {
       Arrays.sort(theWords);
   }
   
   /**
    * Takes in the given word and finds its anagrams 
    * and randomly chooses one of the anagrams in the set  
    * 
    * @param x A given string that is used to get its set of anagrams
    * @return The randomly chosen anagram
    */
   public String getAnagram(String x)
   {
      String[] foundAnagrams = getAnagrams(x).toArray(new String[0]);
      
      return foundAnagrams[rand.nextInt(foundAnagrams.length)];
   }
   
   /**
    * Takes in the given word and finds its anagrams 
    * and returns a created set of the word's anagrams  
    * 
    * @param x A given string that is used to get its set of anagrams
    * @return The set of the word's anagrams
    */
   public Set<String> getAnagrams(String x)
   {
      Word usedWord = new Word(x);
      
      Set<String> usedAnagrams = new TreeSet<>();
      
      for(int i = 0; i < theWords.length; i++)
      {
         if(usedWord.getForm().equals(theWords[i].getForm()))
         {
            usedAnagrams.add(theWords[i].getWord());
         }
      }
      
      if(usedAnagrams.size() == 0)
      {
         usedAnagrams.add(x);
      }
      
      return usedAnagrams;
   }
   
   /**
    * Creates a toString of the first 5 and last five words of the current array of words   
    * 
    * @param nothing
    * @return The string of the first and last 5 words in teh current array
    */
   public String toString()
   {
      String WordsUsed = "";
      int ArrayLength = theWords.length;

      for(int i = 0; i < 5; i++)
      {
         WordsUsed += ("[" + theWords[i].getWord() + "=" + theWords[i].getForm() + "]");
      }
      
      WordsUsed += "[...]";
      
      for(int i = theWords.length-5; i < theWords.length; i++)
      {
         WordsUsed += ("[" + theWords[i].getWord() + "=" + theWords[i].getForm() + "]");
      }
            
      return WordsUsed;
   }
   
   //Used to find a word's canonical form
   private static String CanonicalForm(String currentWord)
   {
      if(currentWord == null || currentWord.length() < 1)
      {
         throw new IllegalArgumentException();
      }

      char[] temp = currentWord.toCharArray();
      Arrays.sort(temp);
      
      return String.valueOf(temp);
   }
  
  //A merge sort used to sort words by the word itself
  private void sortByWord(Word[] theWords)
   {
      if(theWords.length >= 2)
      {
          Word[] left = Arrays.copyOfRange(theWords, 0, theWords.length/2);
          Word[] right = Arrays.copyOfRange(theWords, theWords.length/2, theWords.length);
                
           sortByWord(left);
           sortByWord(right);
      
           merge(theWords, left, right);
     }
  }
  
  //the merging part of the merge sort 
  private static void merge (Word[] theWords, Word[] left, Word[] right)
  {
      int iL = 0;
      int iR = 0;
      
      for(int i = 0; i < theWords.length; i++)
      {
         if(iR >= right.length || (iL < left.length && left[iL].getWord().compareTo(right[iR].getWord()) <= 0))
         {
            theWords[i] = left[iL];
            iL++;
         }
         else
         {
            theWords[i] = right[iR];
            iR++;
         }
      }
  }
}