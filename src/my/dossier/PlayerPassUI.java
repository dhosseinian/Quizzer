/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.dossier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * PlayerPassUI is the GUI to change player password 
 */
public class PlayerPassUI extends javax.swing.JFrame {

    int ID;
    private ArrayList<Question> allQuestions = new ArrayList<Question>();
    private ArrayList<Player> allPlayers = new ArrayList<Player>();
    private Player thisP = null;
    
    //Deafult constructor is never called
    public PlayerPassUI() {
        initComponents();
    }
    
    //The following constructor finds player based on ID
    public PlayerPassUI(int myID) {
        initComponents();
        ID = myID;
        readPlayerData();
        for(Player p : allPlayers){
            if(p.getID() == ID){
                thisP = p;
                break;
            }
        }
    }
    
    public void readQuestionData(){
        allQuestions.clear();
        String thisLine = null;
        String file = "Data/QuestionData.txt";
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((thisLine = br.readLine()) != null){
                String[] part = thisLine.split("/");
                int qID = Integer.parseInt(part[0]);
                String phrase = part[1];
                String answer = part[2].toLowerCase();
                String category = part[3].toLowerCase();
                allQuestions.add(new Question(phrase, answer, qID, category));
            }
            br.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void writeQuestionData(){
        try{
            boolean isFirst = true;
            String file = "Data/QuestionData.txt";
            File f = new File(file);
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            for(Question q : allQuestions){
                if(!isFirst){
                    bw.newLine();
                }
                bw.write(q.print());
                isFirst = false;
            }
            bw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void readPlayerData(){
        readQuestionData();
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
                String questions = part[6].substring(part[6].indexOf(":") + 1);
                Player p = new Player(firstName, lastName, password, pID, team);
                if(questions.length() > 0){
                    String[] qList = questions.split("/");
                    for(String s : qList){
                        String[] tike = s.split(",");
                        int qID = Integer.parseInt(tike[0]);
                        String qEval = tike[1];
                        for(Question q : allQuestions){
                            if(qID == q.getID()){
                                Question toAdd = new Question(q.getPhrase(), q.getAnswer(), q.getID(), q.getCategory());
                                toAdd.setEvaluation(qEval);
                                p.addQuestion(toAdd);
                            }
                        }
                    }
                }
                allPlayers.add(p);
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

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPasswordField3 = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Change");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Change Password");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Old Password");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Rewrite New Password");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("New Password");

        jPasswordField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        jPasswordField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPasswordField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField2ActionPerformed(evt);
            }
        });

        jPasswordField3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPasswordField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField3ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPasswordField1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPasswordField3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton1))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(jLabel1))
                        .addGap(0, 43, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(0, 59, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addContainerGap())))
        );

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String oldPassword = new String(jPasswordField1.getPassword());
        String newPassword = new String(jPasswordField2.getPassword());
        String reNewPassword = new String(jPasswordField3.getPassword());
        System.out.println(oldPassword);
        if(!oldPassword.toLowerCase().equals(thisP.getPassword().toLowerCase())){
            jPasswordField1.setText("");
            jPasswordField2.setText("");
            jPasswordField3.setText("");
            System.out.println(oldPassword);
            JOptionPane.showMessageDialog(rootPane, "Your old password is incorrect. Please enter the correct old password.");
            return;
        }
        if(!newPassword.toLowerCase().equals(reNewPassword.toLowerCase())){
            jPasswordField2.setText("");
            jPasswordField3.setText("");
            JOptionPane.showMessageDialog(rootPane, "Your new passwords do not match. Please try again.");
            return;
        }
        thisP.setPassword(newPassword.toLowerCase());
        writePlayerData();
        jPasswordField1.setText("");
        jPasswordField2.setText("");
        jPasswordField3.setText("");
        JOptionPane.showMessageDialog(rootPane, "Your password was successfully changed.");
    }
    
    //Old password
    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {
        //No actions performed
    }

    //New password
    private void jPasswordField2ActionPerformed(java.awt.event.ActionEvent evt) {
        //No actions performed
    }
	
    //New password check
    private void jPasswordField3ActionPerformed(java.awt.event.ActionEvent evt) {
        //No actions performed
    }

    //Option to exit program
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }
    
    //Option to go back to player menu
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        PlayerLoggedInUI pl = new PlayerLoggedInUI(ID);
        this.dispose();
        pl.setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlayerPassUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
}
