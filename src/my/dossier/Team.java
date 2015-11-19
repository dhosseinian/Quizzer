package my.dossier;

import java.util.*;
import java.io.*;

public class Team
{
    public ArrayList <Player> teamList;
    private final String name;
    private final int ID;
    private static int currID = 0;
    
    public Team(String myName)
    {
        teamList = new ArrayList <Player>();
        name = myName.toLowerCase();
        if(currID == 0){
            String thisLine = null;
            String readID = "";
            String file = "Data/TeamData.txt";
            try{
                BufferedReader br = new BufferedReader(new FileReader(file));
                while((thisLine = br.readLine()) != null){
                    if(!thisLine.equals("")){
                        readID = thisLine.substring(0, thisLine.indexOf(" "));
                        currID = Integer.parseInt(readID);
                    }
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        currID++;
        ID = currID;
    }
    
    public Team(String myName, int myID)
    {
        teamList = new ArrayList <Player>(0);
        name = myName;
        ID = myID;
    }
    
    public String getName()
    {
        return name.toLowerCase();
    }
    
    public int getID(){
        return ID;
    }
    
    public void addPlayer(Player p)
    {
        teamList.add(p);
    }
    
    public void removePlayer(int pID){
        for(Player p : teamList){
            if(p.getID() == pID){
                teamList.remove(p);
                return;
            }
        }
    }
    
    //Provides to be recorded in data
    public String print(){
        String players = "";
        for(Player p : teamList){
            players += p.getID() + ",";
        }
        //players = players.substring(0, players.indexOf(","));
        return getID() + " " + getName() + " players:" + players;
    }
}