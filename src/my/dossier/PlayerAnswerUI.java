package my.dossier;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
 * PlayerAnswerUI is the GUI to answer the questions posted by the coach
 */
public class PlayerAnswerUI extends javax.swing.JFrame {

    int ID;
    private ArrayList<Question> allQuestions = new ArrayList<Question>();
    private ArrayList<Player> allPlayers = new ArrayList<Player>();
    private Player thisP = null;
    private Question current = null;
    
    //Default constructor is never called
    public PlayerAnswerUI() {
        initComponents();
    }
    
    //The following constructor finds player based on ID
    public PlayerAnswerUI(int myID) {
        ID = myID;
        initComponents();
        readPlayerData();
        for(Player p : allPlayers){
            if(p.getID() == ID){
                thisP = p;
            }
        }
        showNextQuestion();
    }
    
    //Reads the questions posted by the coach
    public void readQuestionData(){
        allQuestions.clear();
        String thisLine = null;
        String file = "Data/QuestionData.txt";
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((thisLine = br.readLine()) != null){
                // Each question's components are split by a "/" delmimiter
                String[] part = thisLine.split("/");
                int qID = Integer.parseInt(part[0]); //Record ID
                String phrase = part[1]; //Record question phrase
                String answer = part[2].toLowerCase(); //Record answer phrase
                String category = part[3].toLowerCase(); //Record category
                allQuestions.add(new Question(phrase, answer, qID, category)); //Create a new question object
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
            //Loops through question bank
            for(Question q : allQuestions){
                //Skips first line
                if(!isFirst){
                    bw.newLine();
                }
                bw.write(q.print()); //Records question onto data file
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
                //Record player attributes from player file
                String[] part = thisLine.split(" ");
                int pID = Integer.parseInt(part[0]);
                String password = part[2];
                String team = part[3];
                String firstName = part[4];
                String lastName = part[5];
                String questions = part[6].substring(part[6].indexOf(":") + 1);
                Player p = new Player(firstName, lastName, password, pID, team); //Create new player object
                
                //Load questions player must answer
                if(questions.length() > 0){
                    String[] qList = questions.split("/");
                    for(String s : qList){
                        String[] tike = s.split(",");
                        int qID = Integer.parseInt(tike[0]);
                        String qEval = tike[1];
                        for(Question q : allQuestions){
                            //Compare questions based on ID
                            if(qID == q.getID()){
                                Question toAdd = new Question(q.getPhrase(), q.getAnswer(), q.getID(), q.getCategory()); //Create new player object
                                toAdd.setEvaluation(qEval);
                                p.addQuestion(toAdd); //Adds the question to the player's list
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
                //Skips test player in data file
                if(!isFirst){
                    bw.newLine();
                }
                bw.write(p.print()); //Records player data onto file
                isFirst = false;
            }
            bw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void showNextQuestion(){
        jTextField1.setText("");
        jTextArea1.setText("");
        for(Question q : thisP.qList){
            //Show question if unanswered
            if(q.getEvaluation().equals("u")){
                current = q;
                jLabel3.setText("Category: " + q.getCategory());
                jTextArea1.setText(current.getPhrase());
                break;
            }
        }
        if(jTextArea1.getText().equals("")){
            jTextArea1.setText("No questions to answer!");
        }
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
        jTextArea1.setLineWrap(true);

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

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Question");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Answer");

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 102, 0));
        jButton3.setText("Enter Answer");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("No Category");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jButton3)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }
    
    //Option to exit program
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    }
    
    //Option to go back to player menu
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        PlayerLoggedInUI pl = new PlayerLoggedInUI(ID);
        this.dispose();
        pl.setVisible(true);
    }
    
    //Option to answer question
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        //Check if there are questons left
        if(jTextArea1.getText().equals("No questions to answer!")){
            return;
        }
        boolean isCorrect = false;
        String answerGiven = jTextField1.getText().toLowerCase();
        
        //Checks if user inputted answer
        if(answerGiven.equals("")){
            JOptionPane.showMessageDialog(rootPane, "Please enter an answer.");
            return;
        }
        
        //Check if answer is correct
        if(answerGiven.equals(current.getAnswer())){
            isCorrect = true;
            JOptionPane.showMessageDialog(rootPane, "You answered correctly!");
        }
        else{
            //This part allows for rectification of spelling errors
            //HIGHLY dependent on honor system
            Object[] options = {"I was wrong, continue.", "I was right, override answer."};
            int n = JOptionPane.showOptionDialog(null, "You were wrong. The correct answer is " + current.getAnswer().toUpperCase() + ". How would you like to continue?", "Incorrect answer", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if(n == 1){
                isCorrect = true;
            }
        }
        if(isCorrect){
            current.setCorrect();
        }
        else if(!isCorrect){
            current.setWrong();
        }
        writePlayerData(); //Update player data
        showNextQuestion();    
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlayerAnswerUI().setVisible(true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
}
