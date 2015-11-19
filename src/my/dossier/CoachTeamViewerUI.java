package my.dossier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * CoachTeamViewerUI is the GUI to view player performances 
 */
public class CoachTeamViewerUI extends javax.swing.JFrame {

    int ID;
    private ArrayList<Player> allPlayers = new ArrayList<Player>();
    private ArrayList<Question> allQuestions = new ArrayList<Question>();
    
    //Deafult constructor is never called
    public CoachTeamViewerUI() {
        initComponents();
        look();
    }
    
    public CoachTeamViewerUI(int myID) {
        ID = myID;
        initComponents();
        look();
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
    
    //Present player performances
    public void look(){
        readPlayerData();
        for(Player p : allPlayers){
            if(!p.getUsername().equals("user")){
                jTextArea1.append(p.getFirstName() + " " + p.getLastName() + "\n");
                jTextArea1.append("Team: " + p.getTeam() + "\n");
                jTextArea1.append("Performance\n");
                jTextArea1.append(p.printPerformance() + "\n");
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
        jLabel1.setText("View Team Performances");

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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }

    //Returns to coach's menu
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        CoachLoggedInUI cli = new CoachLoggedInUI(ID);
        this.dispose();
        cli.setVisible(true);
    }

    //Exits the program
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CoachTeamViewerUI().setVisible(true);
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
