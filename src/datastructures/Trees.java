/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ashaindesilva
 */

public class Trees implements Serializable{
    private char data;
    private ArrayList<Trees> children = new ArrayList<>();
    private boolean isRoot;
    private boolean isLeaf;
    private boolean isWordEnd;

                                                //root node constructor
    public Trees(){
        this.isRoot=true;
        this.isLeaf=true;
        this.isWordEnd=false;
    }

                                                //child node constructor
    public Trees(char c){
        this.data=c;
        this.isRoot=false;
        this.isLeaf=true;
        this.isWordEnd=false;
    }

                                                //add children
    public void add(char c){
        this.isLeaf=false;                     //if this has child, its not a leaf
        if(!isChildExist(c)){
            children.add(new Trees(c));
        }
    }

                                                //if a child exist, with the given character
    public boolean isChildExist(char c){
        boolean returnState=false;
        for (int i = 0; i < children.size(); i++) {
            if(children.get(i).getData()==c) {
                returnState=true;
                break;
            }
        }
        return returnState;
    }

                                                //if parent||root not has children
    public boolean hasChildren(){
        return (!this.isLeaf()) && !this.children.isEmpty();
    }

                                                //get data at this node
    public char getData(){
        return this.data;
    }

                                                //if the character is a end of a word
    public boolean isIsWordEnd() {
        return isWordEnd;
    }

                                                //set true if  the character is a end of a word
    public void setIsWordEnd(boolean isWordEnd) {
        this.isWordEnd = isWordEnd;
    }

    public boolean searchStrings(String word,int startPointer, boolean isSuffix) throws Exception{
        boolean wordFound=false;
        Trees successChild=null;
        boolean SuccesschildFound=false;
        boolean isLastCharacter=startPointer+1==word.length();

        if((word.length()-1)>=startPointer){//if valid search-index provided

            if(this.hasChildren()){

                //if node has children
                char searchChar=word.charAt(startPointer);   //searching character

                int childIndex=getChildIndex(searchChar);
                if(childIndex!=-1){                         //if child found with given character
                    successChild=children.get(childIndex);
                    SuccesschildFound=true;
                }

                if(SuccesschildFound){ //search child node recursive if found
                    if(isLastCharacter){
                        wordFound= isSuffix||successChild.isWordEnd;//if is a word-end character
                    }else{
                        //go to next level
                        wordFound=successChild.searchStrings(word,startPointer+1,isSuffix);
                    }
                }else{
                    wordFound= false;
                }

            }else{
                 wordFound= false; //no children, so false
            }

        }else{
           throw new Exception("Invalid index in tree search");
        }
        return wordFound;
    }

    public boolean searchWord(String word) throws Exception{
        if(this.isRoot()){
            return this.searchWord(word,0);
        }else{
           throw new Exception("Word search should start from a root node");
        }
    }

    public boolean searchWord(String word,int startPointer) throws Exception{
        return searchStrings(word,startPointer, false);
    }

    public boolean searchSuffix(String word) throws Exception{
        if(this.isRoot()){
            return this.searchSuffix(word,0);
        }else{
           throw new Exception("Word search should start from a root node");
        }
    }

    public boolean searchSuffix(String word,int startPointer) throws Exception{
        return searchStrings(word,startPointer, true);
    }

    public boolean isSuffix(String suffix){
        return true;
    }

    public void addWord(String word, int putCharAt) throws Exception{
        boolean islastCharacter=word.length()-1==putCharAt;
        if(word.length()-1>=putCharAt){
            char putChar=word.charAt(putCharAt);
            Trees tr=getChild(putChar);

            if(tr==null){
                this.add(putChar);
            }
            tr=getChild(putChar);
            if(!islastCharacter){
                tr.addWord(word,putCharAt+1);
            }else{
                tr.setIsWordEnd(true);
            }
        }else{
           throw new Exception("Invalid index for character addition");
        }
    }

    public void addWord(String word) throws Exception{
        if(this.isRoot()){
            this.addWord(word,0);
        }else{
           throw new Exception("Word addition should start from a root node");
        }

    }

    public Trees getChild(char c){
        Trees tr=null;
        for (int i = 0; i < children.size(); i++) {  //earch if child found
            Trees tmp=children.get(i);
            if(tmp.getData()== c) {//if char found in child
                tr=tmp;
                break;
            }
        }
        return tr;
    }

    public int getChildIndex(char c){
        int index=-1;
        for (int i = 0; i < children.size(); i++) {  //earch if child found
            if(children.get(i).getData()== c) {//if char found in child
                index=i;
                break;
            }
        }
        return index;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public Trees getFirstChild(){
        return children.get(0);
    }

    public ArrayList<WordLetters>  MaxWordSearch(WordLetters letterSet){

        ArrayList<WordLetters> returnSets = new ArrayList<>();//return values container

        for(int l=0; l<letterSet.getLength();l++){//for each character
            if(letterSet.getOccupyStates()[l]){//if character is not already taken

                for (int c = 0; c < children.size(); c++) {  //for each child found

                    if(children.get(c).getData()== letterSet.getLetters()[l]) {//if char found in child

                        WordLetters temp= new WordLetters(letterSet); //create new instance

                        temp.setAsTaken(l,children.get(c).isWordEnd);//and mark the used character word end flag status
                        ArrayList<WordLetters> tmp=children.get(c).MaxWordSearch(temp);//forward to child

                        for (Iterator<WordLetters> it = tmp.iterator(); it.hasNext();) {
                            returnSets.add(it.next());
                        }

                    }
                }
            }

        }

        if (returnSets.isEmpty()){

            returnSets.add(letterSet);
        }else{
            //System.out.println("not empty at "+this.data);
            int maxLength=returnSets.get(0).getMaxWordSuffixLength();

            //find biggest suffix
            for (int i = 0; i < returnSets.size(); i ++) {

                if(returnSets.get(i).getMaxWordSuffixLength()>maxLength)
                {
                    maxLength=returnSets.get(i).getMaxWordSuffixLength();
                }
            }

            //remove smaller words suffixes
            for (int i = 0; i < returnSets.size(); i ++) {
                if(returnSets.get(i).getMaxWordSuffixLength()<maxLength)
                {
                    returnSets.remove(i);
                }
            }
        }
        return returnSets;
    }


}
