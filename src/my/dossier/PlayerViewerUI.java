package my.dossier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * PlayerViewerUI is the GUI to view player performance 
 */
public class PlayerViewerUI extends javax.swing.JFrame {

    int ID;
    private ArrayList<Player> allPlayers = new ArrayList<Player>();
    private ArrayList<Question> allQuestions = new ArrayList<Question>();
    
    //Deafult constructor is never called
    public PlayerViewerUI() {
        initComponents();
    }
    
    //The following constructor finds player based on ID
    public PlayerViewerUI(int myID) {
        ID = myID;
        initComponents();
        look();
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
    
    public void look(){
        readPlayerData();
        for(Player p : allPlayers){
            //Removes defalut user 
            if(!p.getUsername().equals("user")){
                //Finds player with appropriate ID
                if(p.getID() == ID){
                    jTextArea1.append(p.getFirstName() + " " + p.getLastName() + "\n");
                    jTextArea1.append("Team: " + p.getTeam() + "\n");
                    jTextArea1.append("Performance\n");
                    jTextArea1.append(p.printPerformance() + "\n");
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("View Performance");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setEditable(false);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Exit");
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
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }
    
    //Option to go back to player menu
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        PlayerLoggedInUI pl = new PlayerLoggedInUI(ID);
        this.dispose();
        pl.setVisible(true);
    }
    
    //Option to exit program
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlayerViewerUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
}
