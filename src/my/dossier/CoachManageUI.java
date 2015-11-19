package my.dossier;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * CoachManageUI is the GUI that enables coach to manage players and teams
 */
public class CoachManageUI extends javax.swing.JFrame {
    
    private int ID;
    private ArrayList<Player> allPlayers = new ArrayList<Player>();
    private ArrayList<Team> allTeams = new ArrayList<Team>();
    
    //Constructor never called throughout program
    public CoachManageUI() {
        initComponents();
    }
    
    public CoachManageUI(int myID) {
        ID = myID;
        initComponents();
    }
    
    public void readPlayerData(){
        allPlayers.clear();
        String thisLine = null;
        String file = "Data/PlayerData.txt";
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((thisLine = br.readLine()) != null){
                String[] part = thisLine.split(" ");
                int pID = Integer.parseInt(part[0]);
                String password = part[2];
                String team = part[3];
                String firstName = part[4];
                String lastName = part[5];
                allPlayers.add(new Player(firstName, lastName, password, pID, team));
            }
            br.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void writePlayerData(){
        try{
            boolean isFirst = true;
            String file = "Data/PlayerData.txt";
            File f = new File(file);
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            for(Player p : allPlayers){
                if(!isFirst){
                    bw.newLine();
                }
                bw.write(p.print());
                isFirst = false;
            }
            bw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void readTeamData(){
        readPlayerData();
        allTeams.clear();
        String thisLine = null;
        String file = "Data/TeamData.txt";
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((thisLine = br.readLine()) != null){
                String[] part = thisLine.split(" ");
                String tID = part[0];
                String name = part[1];
                String pIDs = part[2].substring(part[2].indexOf(":") + 1);
                Team t = new Team(name, Integer.parseInt(tID));
                if(pIDs.length() > 0){
                    String[] players = pIDs.split(",");
                    for(Player p : allPlayers){
                        for(String s : players){
                            if(!s.equals("") && (p.getID() == Integer.parseInt(s))){
                                t.addPlayer(p);
                            }
                        }
                    }
                }
                allTeams.add(t);
            }
            br.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void writeTeamData(){
        try{
            boolean isFirst = true;
            String file = "Data/TeamData.txt";
            File f = new File(file);
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            for(Team t : allTeams){
                if(!isFirst){
                    bw.newLine();
                }
                bw.write(t.print());
                isFirst = false;
            }
            bw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Manage Teams and Players");

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("First Name");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("Last Name");

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("Team");

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("If you do not select a team, player will be on Team \"unaffiliated.\"");

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton5.setText("Create Player");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(18, 18, 18)
                            .addComponent(jTextField3))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel8)
                    .addComponent(jButton5))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add Player", jPanel1);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("First Name");

        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setText("Last Name");

        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton6.setText("Remove Player");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton6))
                .addContainerGap(146, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Remove Player", jPanel2);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setText("First Name");

        jTextField8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setText("Last Name");

        jTextField9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextField10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setText("To Team");

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton7.setText("Transfer");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addGap(18, 18, 18)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGap(20, 20, 20)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton7))
                .addContainerGap(146, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Transfer Player", jPanel3);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Name");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setText("Create");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap(207, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add Team", jPanel4);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Name");

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setText("Remove");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addContainerGap(207, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Remove Team", jPanel5);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }
	
    //Option to exit program
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit((0));
    }

    //Option to go back to coach menu
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        CoachLoggedInUI cli = new CoachLoggedInUI(ID);
        this.dispose();
        cli.setVisible(true);
    }

    //Create new team
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        readTeamData();
        String teamName = jTextField1.getText().toLowerCase();
        int tID = -1;
        boolean exists = false;
        for(Team t : allTeams){
            if(t.getName().equals(teamName)){
                exists = true;
                tID = t.getID();
                break;
            }
        }
        if(!exists){
            Team newTeam = new Team(teamName);
            allTeams.add(newTeam);
            JOptionPane.showMessageDialog(rootPane, "Team " + newTeam.getName() + " with ID " + newTeam.getID() + " was successfully added.");
        }
        else if(exists){
            JOptionPane.showMessageDialog(rootPane, "Team already exists with ID " + tID +". Please enter another name.");
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Error");
        }
        jTextField1.setText("");
        writeTeamData();
    }

    //Remove team
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        readTeamData();
        String teamName = jTextField2.getText().toLowerCase();
        int tID = -1;
        boolean exists = false;
        for(Team t : allTeams){
            if(t.getName().equals(teamName)){
                exists = true;
                tID = t.getID();
                allTeams.remove(t);
                break;
            }
        }
        if(!exists){
            JOptionPane.showMessageDialog(rootPane, "Sorry. Team does not exist. Please enter another name.");
            return;
        }
        else if (exists){
            JOptionPane.showMessageDialog(rootPane, "Team " + teamName + " with ID " + tID + " was succesfully removed.");
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Error");
        }
        jTextField2.setText("");
        writeTeamData();
    }

    //Create player
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        readTeamData();
        String firstName = jTextField3.getText();
        String lastName = jTextField4.getText();
        String team = jTextField5.getText();
        boolean playerExists = false;
        int pID = -1;
        String pTeam = "";
        for(Player p : allPlayers){
            if(p.getLastName().toLowerCase().equals(lastName.toLowerCase()) && p.getFirstName().toLowerCase().equals(firstName.toLowerCase())){
                playerExists = true;
                pID = p.getID();
                pTeam = p.getTeam();
                break;
            }
        }
        if(playerExists){
            JOptionPane.showMessageDialog(rootPane, "Player already exists with ID " + pID + " on team " + pTeam + ".");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            return;
        }
        boolean teamExists = false;
        for(Team t : allTeams){
            if(t.getName().toLowerCase().equals(team.toLowerCase())){
                teamExists = true;
                break;
            }
        }
        if(team.equals("")){
            team = "unaffiliated";
            teamExists = true;
        }
        if(!teamExists){
            JOptionPane.showMessageDialog(rootPane, "Team does not exist.");
            jTextField5.setText("");
            return;
        }
        Player newPlayer = new Player(firstName, lastName, team);
        for(Team t : allTeams){
            if(t.getName().toLowerCase().equals(team.toLowerCase())){
                allPlayers.add(newPlayer);
                t.addPlayer(newPlayer);
                JOptionPane.showMessageDialog(rootPane, "Player " + newPlayer.getUsername() + " with ID " + newPlayer.getID() + " was successfully added to team " + newPlayer.getTeam() + ".");
            }
        }
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        writePlayerData();
        writeTeamData();
    }

    //Remove player
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        readTeamData();
        String firstName = jTextField6.getText();
        String lastName = jTextField7.getText();
        boolean playerExists = false;
        String pUser = "";
        int pID = -1;
        String pTeam = "";
        for(Player p : allPlayers){
            if(p.getLastName().toLowerCase().equals(lastName.toLowerCase()) && p.getFirstName().toLowerCase().equals(firstName.toLowerCase())){
                playerExists = true;
                pUser = p.getUsername();
                pID = p.getID();
                pTeam = p.getTeam().toLowerCase();
                for(Team t : allTeams){
                    if(t.getName().toLowerCase().equals(pTeam)){
                        t.removePlayer(pID);
                        break;
                    }
                }
                allPlayers.remove(p);
                JOptionPane.showMessageDialog(rootPane, "Player " + pUser + " with ID " + pID + " was successfully removed from team " + pTeam + ".");
                break;
            }
        }
        jTextField6.setText("");
        jTextField7.setText("");    
        if(!playerExists){
            JOptionPane.showMessageDialog(rootPane, "Player does not exist.");
        }
        writePlayerData();
        writeTeamData();
    }

    //Transfer player
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
        readTeamData();
        String firstName = jTextField8.getText().trim().toLowerCase();
        String lastName = jTextField9.getText().trim().toLowerCase();
        String toTeam = jTextField10.getText().trim().toLowerCase();
        boolean playerExists = false;
        boolean teamExists = false;
        String pUser = "";
        int pID = -1;
        String pTeam = "";
        String newTeam = "";
        
        //Check if team exists
        for(Team t : allTeams){
            if(t.getName().toLowerCase().equals(toTeam)){
                teamExists = true;
                break;
            }
        }
        if(!teamExists){
            JOptionPane.showMessageDialog(rootPane, "Team does not exist.");
            jTextField10.setText("");
            return;
        }
        for(Player p : allPlayers){
            if(p.getLastName().toLowerCase().equals(lastName) && p.getFirstName().toLowerCase().equals(firstName)){
                playerExists = true;
                pUser = p.getUsername();
                pID = p.getID();
                pTeam = p.getTeam().toLowerCase();
                for(Team t : allTeams){
                    if(t.getName().toLowerCase().equals(pTeam)){
                        t.removePlayer(pID);
                        break;
                    }
                }
                for(Team t : allTeams){
                    if(t.getName().toLowerCase().equals(toTeam)){
                        t.addPlayer(p);
                        p.setTeam(t.getName());
                        newTeam = p.getTeam();
                        break;
                    }
                }
                break;
            }
        }
        if(!playerExists){
            JOptionPane.showMessageDialog(rootPane, "Player does not exist.");
            jTextField8.setText("");
            jTextField9.setText("");
            return;
        }
        JOptionPane.showMessageDialog(rootPane, "Player " + pUser + " with ID " + pID + " was successfully transfered from " + pTeam + " to "  + newTeam + ".");
        writePlayerData();
        writeTeamData();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CoachManageUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
}
