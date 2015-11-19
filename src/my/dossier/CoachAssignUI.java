package my.dossier;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

/*
 * CoachAssignUI is the GUI to assign questions to the players
 */
public class CoachAssignUI extends javax.swing.JFrame {
    
    private int ID;
    private ArrayList<Question> allQuestions = new ArrayList<Question>();
    private ArrayList<Player> allPlayers = new ArrayList<Player>();
    
    //Default constructor is never called
    public CoachAssignUI() {
        initComponents();
    }
    
    //The following constructor finds player based on ID
    public CoachAssignUI(int myID) {
        ID = myID;
        initComponents();
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
                String answer = part[2];
                String category = part[3];
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
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Assign Questions");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Question Phrase");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
        jTextArea1.setLineWrap(true);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Answer");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 102, 0));
        jButton3.setText("Assign");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Category");

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
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

    //Option to go back to player menu
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        CoachLoggedInUI cli = new CoachLoggedInUI(ID);
        this.dispose();
        cli.setVisible(true);
    }

    //Option to submit question to the database
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        readPlayerData();
        String phrase = jTextArea1.getText();
        String answer = jTextField1.getText().toLowerCase();
        String category = jTextField2.getText().toLowerCase();
        
        //Create new question object
        Question newQuestion = new Question(phrase, answer, category);
        allQuestions.add(newQuestion);
        
        //Assign question to all players
        for(Player p : allPlayers){
            p.addQuestion(newQuestion);
        }
        jTextArea1.setText("");
        jTextField1.setText("");
        jTextField2.setText("");
        writeQuestionData();
        writePlayerData();
        JOptionPane.showMessageDialog(rootPane, "Question with ID " + newQuestion.getID() + " was successfully assigned.");
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CoachAssignUI().setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
}
