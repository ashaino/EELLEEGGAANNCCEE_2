package datastructures;

/**
 *
 * @author ashaindesilva
 */
public class WordLetters {
    private char [] letters;
    private boolean [] occupyStates;
    private boolean [] wordEndFlags;
    private int length;
    private String word;

    public WordLetters(char[] letters){
        this.letters=letters;
        this.length=this.letters.length;
        this.word="";

        this.occupyStates= new boolean[this.length];
        this.wordEndFlags= new boolean[this.length];
        for (int x=0; x<this.length;x++){
            this.occupyStates[x]=true;
            this.wordEndFlags[x]=false;
        }
    }

    public WordLetters(WordLetters obj){

        this.length=obj.length;

        this.letters= new char[this.length];
        System.arraycopy(obj.letters, 0, this.letters, 0, obj.letters.length);

        this.word=obj.word;

        this.occupyStates= new boolean[this.length];
        System.arraycopy(obj.occupyStates, 0, this.occupyStates, 0, this.length);

        this.wordEndFlags= new boolean[this.length];
        System.arraycopy(obj.wordEndFlags, 0, this.wordEndFlags, 0, this.length);
    }

    public void setAsTaken(int x,boolean status){
        //consumption++;
        this.word+=this.letters[x];
        this.occupyStates[x]=false;//set as occupied
        this.wordEndFlags[this.word.length()-1]=status; //mark index if it's a word
    }

    public String getRawWord(){
        return word;
    }

    public int getMaxWordSuffixLength(){
        int max=-1;
        for (int x=this.length-1; x>=0;x--){
            if(this.wordEndFlags[x]){
                if(x>max){
                    max=x;
                    break;
                }
            }
        }
        return max+1;
    }

    /**
     * @return the letters
     */
    public char[] getLetters() {
        return letters;
    }

    public String getWord(){
        return this.word.substring(0, this.getMaxWordSuffixLength());
    }

    /**
     * @return the occupyStates
     */
    public boolean[] getOccupyStates() {
        return this.occupyStates;
    }

    public boolean[] getWordEndFlags() {
        return this.wordEndFlags;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @return the completeWord
     */
    public boolean isCompleteWord() {
        return this.getMaxWordSuffixLength()>0;
    }
}
