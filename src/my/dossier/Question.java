package my.dossier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Question {
    
    private final int ID;
    private final String phrase;
    private final String answer;
    private static int currID = 0;
    private String evaluation = "u"; //u = unanswered; c = correct; w = wrong
    private String category; 
    
    public Question(String myPhrase, String myAnswer, String myCategory){
        phrase = myPhrase;
        answer = myAnswer.toLowerCase();
        category = myCategory.toLowerCase();
        if(currID == 0){
            String thisLine = null;
            String readID = "";
            String file = "Data/QuestionData.txt";
            try{
                BufferedReader br = new BufferedReader(new FileReader(file));
                while((thisLine = br.readLine()) != null){
                    readID = thisLine.substring(0, thisLine.indexOf("/"));
                }
                currID = Integer.parseInt(readID);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        currID++;
        ID = currID;
    }
    
    public Question(String myPhrase, String myAnswer, int myID, String myCategory){
        phrase = myPhrase;
        answer = myAnswer.toLowerCase();
        ID = myID;
        category = myCategory.toLowerCase();
    }
    
    public String getPhrase(){
        return phrase;
    }
    
    public String getAnswer(){
        return answer;
    }
    
    public int getID(){
        return ID;
    }
    
    public void setCorrect(){
        evaluation = "c";
    }
    
    public void setWrong(){
        evaluation = "w";
    }
    
    public void setUnanswered(){
        evaluation = "u";
    }
    
    public void setEvaluation(String myEval){
        if(myEval.equals("c") || myEval.equals("w") || myEval.equals("u")){
            evaluation = myEval;
        }
    }
    
    public String getEvaluation(){
        return evaluation;
    }
    
    public void setCategory(String myCategory){
        category = myCategory;
    }
    
    public String getCategory(){
        return category.toLowerCase();
    }
    
    //Provides string to recorded into question database
    public String print(){
        return ID + "/" + phrase + "/" + answer + "/" + category;
    }
    
    //Provides string to be recorded for player data
    public String printForPlayer(){
        return ID + "," + evaluation + "," + category + "/";
    }
}
