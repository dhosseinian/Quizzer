package my.dossier;

import java.util.*;
import java.io.*;

public class Player
{
    private final String firstName;
    private final String lastName;
    private final String username;
    private String password;
    private String team;
    private final int ID;
    private static int currID = 0;
    public ArrayList<Question> qList = new ArrayList<Question>();
    
    public Player(int myID){
        ID = myID;
        firstName = "";
        lastName = "";
        username = "";
        password = "";
        team = "";
    }
    
    public Player(String myFirstName, String myLastName, String myTeam)
    {
        firstName = myFirstName.toLowerCase();
        lastName = myLastName.toLowerCase();
        username = firstName.substring(0, 1) + lastName;
        password = "password";
        if(currID == 0){
            String thisLine = null;
            String readID = "";
            String file = "Data/PlayerData.txt";
            try{
                BufferedReader br = new BufferedReader(new FileReader(file));
                while((thisLine = br.readLine()) != null){
                    readID = thisLine.substring(0, thisLine.indexOf(" "));
                }
                currID = Integer.parseInt(readID);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        currID++;
        ID = currID;
        team = myTeam.toLowerCase();
    }
    
    public Player(String myFirstName, String myLastName, String myPassword, int myID, String myTeam)
    {
        firstName = myFirstName;
        lastName = myLastName;
        username = firstName.substring(0, 1) + lastName;
        password = myPassword;
        ID = myID;
        team = myTeam;
    }

    public String getFirstName()
    {
        return firstName;
    }
    
     public String getLastName()
    {
        return lastName;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String newPass){
        password = newPass.toLowerCase();
    }
    
    public int getID(){
        return ID;
    }
    
    public String getTeam(){
        return team;
    }
    
    public void setTeam(String newTeam){
        team = newTeam.toLowerCase();
    }
    
    public void addQuestion(Question q){
        qList.add(q);
    }
    
    //Print player data onto text file
    public String print(){
        String questions = "";
        for(Question q : qList){
            questions += q.printForPlayer();
        }
        return getID() + " " + getUsername() + " " + getPassword() + " " + getTeam() + " " + getFirstName() + " " + getLastName() + " questions:" + questions;
    }
    
    public String printPerformance(){
        String s = "";
        ArrayList<String> categories = new ArrayList<String>(); 
        for(Question q : qList){
            if(categories.isEmpty()){
                categories.add(q.getCategory());
                continue;
            }
            for(int i = 0; i < categories.size(); i++){
                String c = categories.get(i);
                if(!q.getCategory().equals(c.toLowerCase())){
                    categories.add(q.getCategory());
                }
            }
        }
        int[] part = new int[categories.size()];
        for(int p : part){
            p = 0;
        }
        int[] whole = new int[categories.size()];
        for(int w : whole){
            w = 0;
        }
        for(Question q : qList){
            if(q.getEvaluation().equals("u")){
                break;
            }
            int index = -2;
            for(String c : categories){
                if(c.toLowerCase().equals(q.getCategory().toLowerCase())){
                    index = categories.indexOf(c);
                }
            }
            if(index >= 0){
                whole[index] = whole[index] + 1;
                if(q.getEvaluation().equals("c")){
                    part[index]++;
                }
            }
        }
        int a = 0;
        for(String c : categories){
            s += (c + ": " + part[a] + "/" + whole[a] + "\n");
            a++;
        }
        return s;
        
    }
}