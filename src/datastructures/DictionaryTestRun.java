package datastructures;
import java.util.ArrayList;

/**
 *
 * @author ashaindesilva
 */
public class DictionaryTestRun {

    /**
     * @param args the command line arguments
     */

    private static String  dicPath= "dictionary.txt";
    public static void main(String[] args){

       // writeToDist(); /*uncomment to genarate dictionary ( first time only needed!! )*/
//        String r="aewwwwi";
//        String g=r.replaceAll("[aeiou]*", "f");
//        System.out.println(g);


        Dictionary myDyc2= new Dictionary(dicPath);
        System.out.println(myDyc2.verifyInputWord("zzz"));
        System.out.println(myDyc2.verifyInputWord("hellob"));
        System.out.println(myDyc2.verifyInputWord("abab"));

        char []s1={'b','a','b','l'};
        System.out.println(myDyc2.verifyPrimaryLetters(s1));

        char []s2={'h','r','l','l','a','b','e'};

        for(String x:myDyc2.findLongestWord(s2)){
           System.out.print(","+x);
        }
        System.out.println("");

     }

    public static void writeToDist(){
        ArrayList<String> collection= new ArrayList<>();
        collection.add("hall");
        collection.add("hello");
        collection.add("eel");
        collection.add("ball");
        collection.add("hell");
        collection.add("bal");
        collection.add("bat");
        collection.add("add");

        Dictionary myDyc= new Dictionary(collection);
        myDyc.writeToDisk(dicPath);
    }
}
