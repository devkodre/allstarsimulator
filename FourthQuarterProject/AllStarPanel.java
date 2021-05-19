
import edu.fcps.Turtle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import javax.swing.Timer;
import java.awt.image.*;
import java.util.*;
import java.util.List;
import java.io.*;
//@Author Dev Kodre
//Period: 2
//Date: 4/26/21

/**
* The AllStarPanel allows the user to select players.
* Allows the user to run a simulation based on players selected.
*
*
* @param start_Button  Executes simulation of game between players selected.
* @param cancel_Button Terminates simulation after start button is pressed.
* @param JComboBox Drop down menu to select players
* @param SelectButton   selects the player highlighted in the JComboBox
* @param team1Score_Label   Displays total score for team one 
* @param team2Score_Label   Displays total score for team two
*
* Displays points scored, scoring ratio for 2 pointers and 3 pointers for each player
*
* @param team1p1Stats_Label 
* @param team1p2Stats_Label
* @param team1p3Stats_Label
* @param team1p4Stats_Label
* @param team1p5Stats_Label
*
* @param team2p1Stats_Label
* @param team2p2Stats_Label
* @param team2p3Stats_Label
* @param team2p4Stats_Label
* @param team2p5Stats_Label
* @param Directions_Label  Information on how to run the simulation
   
*/
public class AllStarPanel extends JPanel{

   BufferedImage image;
   BufferedImage hoop;
   int xPos = 0;
   int yPos = 650;
   int direction =1;
   int playernumt1 = 0;
   int playernumt2 = 0;
   Boolean firstpressed = false;
   Boolean firstpressedt2 = false;
   
   
   Player p1t1 = new Player();
   Player p2t1 = new Player();
   Player p3t1 = new Player();
   Player p4t1 = new Player();
   Player p5t1 = new Player();
   
   Player p1t2 = new Player();
   Player p2t2 = new Player();
   Player p3t2 = new Player();
   Player p4t2 = new Player();
   Player p5t2 = new Player();
   
   ArrayList<Player> allPlayers = new ArrayList<>();
   Player [] allPlayersArray;
   Team [] teams;
   
   int [] team1scores = new int [5];
   int [] team2scores = new int [5];
   
   String [] turnResult;
   
   Team t1 = new Team("Donuts",0.5,0.5);
   Team t2 = new Team("Hot Dogs", 0.5, 0.5);
   int scoret1 = 0;
   int scoret2 = 0;
   Game game = new Game();
   
   
   
      
   JLabel directions = new JLabel("                                  Select players from the drop down menu.");
   JLabel directions2 = new JLabel("Then, add them to the perfered team.");
   JButton addt1 = new JButton("Add Team 1");
   JButton deletet1 = new JButton("Delete Team 1");
   JButton addt2 = new JButton("Add Team 2");
   JButton deletet2 = new JButton("Delete Team 2");
      
   JLabel menuLabel = new JLabel("Select a player:");
      
   JComboBox playerlist = new JComboBox();
      
   JLabel team1 = new JLabel("Team One:");
      
   JLabel player1t1 = new JLabel("player 1");
   JLabel player2t1 = new JLabel("player 2");
   JLabel player3t1 = new JLabel("player 3");
   JLabel player4t1 = new JLabel("player 4");
   JLabel player5t1 = new JLabel("player 5");
      
   JLabel team1score = new JLabel("Score:");
      
   JLabel team2 = new JLabel("Team Two:");
      
   JLabel player1t2 = new JLabel("player 1");
   JLabel player2t2 = new JLabel("player 2");
   JLabel player3t2 = new JLabel("player 3");
   JLabel player4t2 = new JLabel("player 4");
   JLabel player5t2 = new JLabel("player 5");
      
   JLabel team2score = new JLabel("Score:");
   JButton start = new JButton("START");
   JButton reset = new JButton("RESTART");
   JButton cancel = new JButton("CANCEL");
   
   JLabel score = new JLabel("0:0");
   JLabel info = new JLabel("Info");
   Boolean flag = false;
   
   
   ArrayList<String> players = new ArrayList<>();
   String line = "";
      
   public AllStarPanel () 
   {
      // Working on this part
      
      
      try{
         BufferedReader br = new BufferedReader(new FileReader("players.csv")); 
         
         while ((line = br.readLine()) != null)
         {
            
            String [] parameters = line.split(",");
            for(int x = 0; x < parameters.length; x++)
            {
               players.add(parameters[x]);
            } 
         }
      }
      catch(IOException e)
      {
      
      }  
      for(int x = 0; x < players.size();x+=25)
      {
         playerlist.addItem((players.get(x)));
      }
      
      for (int x = 25; x < players.size(); x+=25)
      {
         
         allPlayers.add(new Player(players.get(x),Double.parseDouble(players.get(x+4)),Double.parseDouble(players.get(x+5)),Double.parseDouble(players.get(x+6)),Double.parseDouble(players.get(x+7)),Double.parseDouble(players.get(x+8)),Double.parseDouble(players.get(x+9)),Double.parseDouble(players.get(x+10)),Double.parseDouble(players.get(x+11)),Double.parseDouble(players.get(x+12)),Double.parseDouble(players.get(x+13)),Double.parseDouble(players.get(x+14)),Double.parseDouble(players.get(x+15)),Double.parseDouble(players.get(x+16)),Double.parseDouble(players.get(x+17)),Double.parseDouble(players.get(x+18)),Double.parseDouble(players.get(x+19)),Double.parseDouble(players.get(x+20)),Double.parseDouble(players.get(x+22)),Double.parseDouble(players.get(x+23)),Double.parseDouble(players.get(x+24))));
      }
      allPlayersArray = new Player[allPlayers.size()];
      for(int x = 0; x < allPlayers.size(); x++)
      {
         allPlayersArray[x] = allPlayers.get(x);
      }
     
      JPanel top = new JPanel();
     
      
      top.setLayout(new GridLayout(0,2));
      
      start.addActionListener(new StartPressed());
      reset.addActionListener(new ResetPressed());
      cancel.addActionListener(new CencelPressed());
      addt1.addActionListener(new SelectDropDownTeam1());
      addt2.addActionListener(new SelectDropDownTeam2());
      deletet1.addActionListener(new DeleteTeam1Pressed());
      deletet2.addActionListener(new DeleteTeam2Pressed());
      
      
      
      
      
      
      
   
      top.add(directions);
      
      top.add(directions2); 
      
      
      top.add(addt1);
      top.add(addt2);
      
      
      
      top.add(deletet1);
      top.add(deletet2);
      
      top.add(menuLabel);
      top.add(new JLabel()); //used to occupy a slot allowing the formatting to look better
      
      top.add(playerlist);
      
      top.add(new JLabel());
      
      
      
      top.add(team1);
      top.add(team2);
      
      top.add(player1t1);
      top.add(player1t2);
      
      top.add(player2t1);
      top.add(player2t2);
      
      top.add(player3t1);
      top.add(player3t2);
      
      top.add(player4t1);
      top.add(player4t2);
      
      top.add(player5t1);
      top.add(player5t2);
      
      
      
      top.add(start);
      top.add(reset);
      top.add(cancel);
      
      add(top, BorderLayout.NORTH);
      
      JPanel subPanel = new JPanel();
      subPanel.setLayout(new GridLayout(0,1));
      //score.setBounds(600,0, 150, 100);
      score.setFont(new Font("Serif", Font.PLAIN, 100));
      subPanel.add(score);
      //info.setBounds(600,0, 150, 100);
      info.setFont(new Font("Serif", Font.PLAIN, 50));
      subPanel.add(info);
      add(subPanel, BorderLayout.SOUTH);
      try{
      
         image = ImageIO.read(new File("basketball.png"));
      
      }
      catch(IOException ex)
      {
      
      }
      try{
      
         hoop = ImageIO.read(new File("basketballHoop.png"));
      
      }
      catch(IOException ex)
      {
      
      }
   
               
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
     
      
      
      
      
      
   }
   /**
   * Displays animation during the run of the program.
   * The animation will only run once the start button has been pressed.
   *
   * @param Graphics component
   */
   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.drawImage(image, xPos,yPos,50,50, this);
      g.drawImage(hoop, 170,600,250,250, this);
      
   }
  
   /**
   * Adds number of points scored to player's total number of scored points
   * The method will be called after the start button has been pressed.
   *
   * @param player_points   points scored by player
   * @return total  total number of points scored by player
   */
   public int PlayerAddPoint(int points )
   {
      
      return 1;
   } 
   /**
   * Adds number of points scored to team's total number of scored points
   * The method will be called after the start button has been pressed.
   *
   * @param player_points   points scored by player
   * @return total  total points scored by team
   */
   public int TeamAddPoint(int points )
   {
      
      return 1;
   }
   /**
   * Listener class for when the start button has been pressed.
   *
   *
   */
   private class StartPressed implements ActionListener
   {
   /**
   * Runs the simulation of a game between the selected players
   *
   * @param actionEvent e
   */
      public void actionPerformed(ActionEvent e)
      {
         flag = false;
         t1.addPlayer(p1t1);
         t1.addPlayer(p2t1);
         t1.addPlayer(p3t1);
         t1.addPlayer(p4t1);
         t1.addPlayer(p5t1);
         
         t2.addPlayer(p1t2);
         t2.addPlayer(p2t2);
         t2.addPlayer(p3t2);
         t2.addPlayer(p4t2);
         t2.addPlayer(p5t2);
         
         teams = new Team[2];
         teams[0] = t1;
         teams[1] = t2;
      
         String[] team1players = new String[]{p1t1.get_name(),p2t1.get_name(),p3t1.get_name(),p4t1.get_name(),p5t1.get_name()};
         String[] team2players = new String[]{p1t2.get_name(),p2t2.get_name(),p3t2.get_name(),p4t2.get_name(),p5t2.get_name()};
         
         game = new Game(teams, allPlayersArray, 200);
         
         game.add_players_to_teams(team1players, team2players);
         
         try {
              
            Timer timer = new Timer(20, 
               new ActionListener() {
                  @Override
                    public void actionPerformed(ActionEvent e) {
                     if (flag == false)
                     {
                        yPos = (int)((0.006)*(Math.pow(xPos, 2)) - 1.3*xPos + 670);
                        xPos += direction;
                        if (xPos + image.getWidth() > getWidth()+50) {
                           xPos = getWidth() - image.getWidth();
                           direction *= -1;
                        } else if (xPos < 0) {
                           xPos = 0;
                           direction *= -1;
                        }
                        if (yPos + image.getHeight() > getHeight() +240 && xPos > 200)
                        {
                        
                           xPos = 0;
                        }
                        repaint();
                     
                        turnResult = game.play_turn();
                     // scoret1 = game.get_team1_scores()[0] + game.get_team1_scores()[1] + game.get_team1_scores()[2] + game.get_team1_scores()[3] + game.get_team1_scores()[4];
                        scoret1 = game.get_team1_score();
                        scoret2 = game.get_team2_score();
                     //System.out.println(scoret1);
                        score.setText(scoret1 + ":" + scoret2);
                        if(turnResult.length == 3)
                           info.setText(turnResult[2]);
                        else
                           flag = true;
                     
                     // scoret2 = game.get_team2_scores()[0] + game.get_team1_scores()[1] + game.get_team1_scores()[2] + game.get_team1_scores()[3] + game.get_team1_scores()[4];
                     //team1score.setText("Score " + scoret2);
                     }
                     
                  }
                
               
               });
            timer.setRepeats(true);
            timer.setCoalesce(true);
            timer.start();
         } 
         catch(Exception ex) 
         {
         
         }
         
         
      }
   }
   /**
   * Listener class for when a player has been selected from the drop down menu and wants to be added to the team.
   *
   *
   */
   private class SelectDropDownTeam1 implements ActionListener
   {
   /**
   * Ensures the player selected is added to the team.
   *
   * @param actionEvent e
   */
      public void actionPerformed(ActionEvent e)
      {
         int count = 0;
         if(firstpressed == true && playernumt1 != 0)
         {
            playernumt1 -=1;
            firstpressed = false;
         }
         if(firstpressed == true && playernumt1 == 0)
         {
            firstpressed = false;
         }
         if(playernumt1 < 5)
         {
            playernumt1+=1;
         }
         if (playernumt1 == 1)
         {
            player1t1.setText("Player: " + playerlist.getSelectedItem().toString());
            while (!players.get(count).equals(playerlist.getSelectedItem().toString()))
            {
               count+=25;
            }
            p1t1 = new Player(players.get(count),Double.parseDouble(players.get(count+4)),Double.parseDouble(players.get(count+5)),Double.parseDouble(players.get(count+6)),Double.parseDouble(players.get(count+7)),Double.parseDouble(players.get(count+8)),Double.parseDouble(players.get(count+9)),Double.parseDouble(players.get(count+10)),Double.parseDouble(players.get(count+11)),Double.parseDouble(players.get(count+12)),Double.parseDouble(players.get(count+13)),Double.parseDouble(players.get(count+14)),Double.parseDouble(players.get(count+15)),Double.parseDouble(players.get(count+16)),Double.parseDouble(players.get(count+17)),Double.parseDouble(players.get(count+18)),Double.parseDouble(players.get(count+19)),Double.parseDouble(players.get(count+20)),Double.parseDouble(players.get(count+22)),Double.parseDouble(players.get(count+23)),Double.parseDouble(players.get(count+24)));
            
         }
         else if (playernumt1 == 2)
         {
            player2t1.setText("Player: " + playerlist.getSelectedItem().toString());
            while (!players.get(count).equals(playerlist.getSelectedItem().toString()))
            {
               count+=25;
            } 
            p2t1 = new Player(players.get(count),Double.parseDouble(players.get(count+4)),Double.parseDouble(players.get(count+5)),Double.parseDouble(players.get(count+6)),Double.parseDouble(players.get(count+7)),Double.parseDouble(players.get(count+8)),Double.parseDouble(players.get(count+9)),Double.parseDouble(players.get(count+10)),Double.parseDouble(players.get(count+11)),Double.parseDouble(players.get(count+12)),Double.parseDouble(players.get(count+13)),Double.parseDouble(players.get(count+14)),Double.parseDouble(players.get(count+15)),Double.parseDouble(players.get(count+16)),Double.parseDouble(players.get(count+17)),Double.parseDouble(players.get(count+18)),Double.parseDouble(players.get(count+19)),Double.parseDouble(players.get(count+20)),Double.parseDouble(players.get(count+22)),Double.parseDouble(players.get(count+23)),Double.parseDouble(players.get(count+24)));
               
         }
         else if (playernumt1 == 3)
         {
            player3t1.setText("Player: " + playerlist.getSelectedItem().toString());
            while (!players.get(count).equals(playerlist.getSelectedItem().toString()))
            {
               count+=25;
            } 
            p3t1 = new Player(players.get(count),Double.parseDouble(players.get(count+4)),Double.parseDouble(players.get(count+5)),Double.parseDouble(players.get(count+6)),Double.parseDouble(players.get(count+7)),Double.parseDouble(players.get(count+8)),Double.parseDouble(players.get(count+9)),Double.parseDouble(players.get(count+10)),Double.parseDouble(players.get(count+11)),Double.parseDouble(players.get(count+12)),Double.parseDouble(players.get(count+13)),Double.parseDouble(players.get(count+14)),Double.parseDouble(players.get(count+15)),Double.parseDouble(players.get(count+16)),Double.parseDouble(players.get(count+17)),Double.parseDouble(players.get(count+18)),Double.parseDouble(players.get(count+19)),Double.parseDouble(players.get(count+20)),Double.parseDouble(players.get(count+22)),Double.parseDouble(players.get(count+23)),Double.parseDouble(players.get(count+24)));
               
         }
         else if (playernumt1 == 4)
         {
            player4t1.setText("Player: " + playerlist.getSelectedItem().toString());
            while (!players.get(count).equals(playerlist.getSelectedItem().toString()))
            {
               count+=25;
            } 
            p4t1 = new Player(players.get(count),Double.parseDouble(players.get(count+4)),Double.parseDouble(players.get(count+5)),Double.parseDouble(players.get(count+6)),Double.parseDouble(players.get(count+7)),Double.parseDouble(players.get(count+8)),Double.parseDouble(players.get(count+9)),Double.parseDouble(players.get(count+10)),Double.parseDouble(players.get(count+11)),Double.parseDouble(players.get(count+12)),Double.parseDouble(players.get(count+13)),Double.parseDouble(players.get(count+14)),Double.parseDouble(players.get(count+15)),Double.parseDouble(players.get(count+16)),Double.parseDouble(players.get(count+17)),Double.parseDouble(players.get(count+18)),Double.parseDouble(players.get(count+19)),Double.parseDouble(players.get(count+20)),Double.parseDouble(players.get(count+22)),Double.parseDouble(players.get(count+23)),Double.parseDouble(players.get(count+24)));
               
         }
         else if (playernumt1 == 5)
         {
            player5t1.setText("Player: " + playerlist.getSelectedItem().toString());
            
            
            while (!players.get(count).equals(playerlist.getSelectedItem().toString()))
            {
               count+=25;
            } 
            p5t1 = new Player(players.get(count),Double.parseDouble(players.get(count+4)),Double.parseDouble(players.get(count+5)),Double.parseDouble(players.get(count+6)),Double.parseDouble(players.get(count+7)),Double.parseDouble(players.get(count+8)),Double.parseDouble(players.get(count+9)),Double.parseDouble(players.get(count+10)),Double.parseDouble(players.get(count+11)),Double.parseDouble(players.get(count+12)),Double.parseDouble(players.get(count+13)),Double.parseDouble(players.get(count+14)),Double.parseDouble(players.get(count+15)),Double.parseDouble(players.get(count+16)),Double.parseDouble(players.get(count+17)),Double.parseDouble(players.get(count+18)),Double.parseDouble(players.get(count+19)),Double.parseDouble(players.get(count+20)),Double.parseDouble(players.get(count+22)),Double.parseDouble(players.get(count+23)),Double.parseDouble(players.get(count+24)));
            
               
         }
         
      }
   }
   /**
   * Listener class for when a player has been selected from the drop down menu and wants to be added to the team.
   *
   *
   */
   private class SelectDropDownTeam2 implements ActionListener
   {
   /**
   * Ensures the player selected is added to the team.
   *
   * @param actionEvent e
   */
      public void actionPerformed(ActionEvent e)
      {
         int count = 0;
         if(firstpressedt2 == true && playernumt2 != 0)
         {
            playernumt2 -=1;
            firstpressedt2 = false;
         }
         if(firstpressedt2 == true && playernumt2 == 0)
         {
            firstpressedt2 = false;
         } 
           
         if(playernumt2 < 5)
         {
            playernumt2 +=1;
         }
         if (playernumt2 == 1)
         {
            player1t2.setText("Player: " + playerlist.getSelectedItem().toString());
            while (!players.get(count).equals(playerlist.getSelectedItem().toString()))
            {
               count+=25;
            }
            p1t2 = new Player(players.get(count),Double.parseDouble(players.get(count+4)),Double.parseDouble(players.get(count+5)),Double.parseDouble(players.get(count+6)),Double.parseDouble(players.get(count+7)),Double.parseDouble(players.get(count+8)),Double.parseDouble(players.get(count+9)),Double.parseDouble(players.get(count+10)),Double.parseDouble(players.get(count+11)),Double.parseDouble(players.get(count+12)),Double.parseDouble(players.get(count+13)),Double.parseDouble(players.get(count+14)),Double.parseDouble(players.get(count+15)),Double.parseDouble(players.get(count+16)),Double.parseDouble(players.get(count+17)),Double.parseDouble(players.get(count+18)),Double.parseDouble(players.get(count+19)),Double.parseDouble(players.get(count+20)),Double.parseDouble(players.get(count+22)),Double.parseDouble(players.get(count+23)),Double.parseDouble(players.get(count+24)));   
         }
         else if (playernumt2 == 2)
         {
            player2t2.setText("Player: " + playerlist.getSelectedItem().toString());
            while (!players.get(count).equals(playerlist.getSelectedItem().toString()))
            {
               count+=25;
            }
            p2t2 = new Player(players.get(count),Double.parseDouble(players.get(count+4)),Double.parseDouble(players.get(count+5)),Double.parseDouble(players.get(count+6)),Double.parseDouble(players.get(count+7)),Double.parseDouble(players.get(count+8)),Double.parseDouble(players.get(count+9)),Double.parseDouble(players.get(count+10)),Double.parseDouble(players.get(count+11)),Double.parseDouble(players.get(count+12)),Double.parseDouble(players.get(count+13)),Double.parseDouble(players.get(count+14)),Double.parseDouble(players.get(count+15)),Double.parseDouble(players.get(count+16)),Double.parseDouble(players.get(count+17)),Double.parseDouble(players.get(count+18)),Double.parseDouble(players.get(count+19)),Double.parseDouble(players.get(count+20)),Double.parseDouble(players.get(count+22)),Double.parseDouble(players.get(count+23)),Double.parseDouble(players.get(count+24)));   
         }
         else if (playernumt2 == 3)
         {
            player3t2.setText("Player: " + playerlist.getSelectedItem().toString());
            while (!players.get(count).equals(playerlist.getSelectedItem().toString()))
            {
               count+=25;
            }
            p3t2 = new Player(players.get(count),Double.parseDouble(players.get(count+4)),Double.parseDouble(players.get(count+5)),Double.parseDouble(players.get(count+6)),Double.parseDouble(players.get(count+7)),Double.parseDouble(players.get(count+8)),Double.parseDouble(players.get(count+9)),Double.parseDouble(players.get(count+10)),Double.parseDouble(players.get(count+11)),Double.parseDouble(players.get(count+12)),Double.parseDouble(players.get(count+13)),Double.parseDouble(players.get(count+14)),Double.parseDouble(players.get(count+15)),Double.parseDouble(players.get(count+16)),Double.parseDouble(players.get(count+17)),Double.parseDouble(players.get(count+18)),Double.parseDouble(players.get(count+19)),Double.parseDouble(players.get(count+20)),Double.parseDouble(players.get(count+22)),Double.parseDouble(players.get(count+23)),Double.parseDouble(players.get(count+24)));  
         }
         else if (playernumt2 == 4)
         {
            player4t2.setText("Player: " + playerlist.getSelectedItem().toString());
            while (!players.get(count).equals(playerlist.getSelectedItem().toString()))
            {
               count+=25;
            }
            p4t2 = new Player(players.get(count),Double.parseDouble(players.get(count+4)),Double.parseDouble(players.get(count+5)),Double.parseDouble(players.get(count+6)),Double.parseDouble(players.get(count+7)),Double.parseDouble(players.get(count+8)),Double.parseDouble(players.get(count+9)),Double.parseDouble(players.get(count+10)),Double.parseDouble(players.get(count+11)),Double.parseDouble(players.get(count+12)),Double.parseDouble(players.get(count+13)),Double.parseDouble(players.get(count+14)),Double.parseDouble(players.get(count+15)),Double.parseDouble(players.get(count+16)),Double.parseDouble(players.get(count+17)),Double.parseDouble(players.get(count+18)),Double.parseDouble(players.get(count+19)),Double.parseDouble(players.get(count+20)),Double.parseDouble(players.get(count+22)),Double.parseDouble(players.get(count+23)),Double.parseDouble(players.get(count+24)));   
         }
         else if (playernumt2 == 5)
         {
            player5t2.setText("Player: " + playerlist.getSelectedItem().toString());
            while (!players.get(count).equals(playerlist.getSelectedItem().toString()))
            {
               count+=25;
            }
            p5t2 = new Player(players.get(count),Double.parseDouble(players.get(count+4)),Double.parseDouble(players.get(count+5)),Double.parseDouble(players.get(count+6)),Double.parseDouble(players.get(count+7)),Double.parseDouble(players.get(count+8)),Double.parseDouble(players.get(count+9)),Double.parseDouble(players.get(count+10)),Double.parseDouble(players.get(count+11)),Double.parseDouble(players.get(count+12)),Double.parseDouble(players.get(count+13)),Double.parseDouble(players.get(count+14)),Double.parseDouble(players.get(count+15)),Double.parseDouble(players.get(count+16)),Double.parseDouble(players.get(count+17)),Double.parseDouble(players.get(count+18)),Double.parseDouble(players.get(count+19)),Double.parseDouble(players.get(count+20)),Double.parseDouble(players.get(count+22)),Double.parseDouble(players.get(count+23)),Double.parseDouble(players.get(count+24)));   
         }
            
      }
   }
   /**
   * Ends the simulation of the game after the start button has been pressed.
   *
   *
   */
   private class CencelPressed implements ActionListener
   {
   /**
   * Terminates all calcuations and resets all variables.
   *
   * @param actionEvent e
   */
      public void actionPerformed(ActionEvent e)
      {
            flag = true;
      }
   }
   /**
   * Resets the points after a match has been simulated for the ability to run another simulation.
   *
   *
   */
   private class ResetPressed implements ActionListener
   {
   /**
   * Resets total points scored and total points scored by each individual player.
   *
   * @param actionEvent e
   */
      public void actionPerformed(ActionEvent e)
      {
         if(flag == true)
         {
            player1t1.setText("Player 1");
            player2t1.setText("Player 2");
            player3t1.setText("Player 3");
            player4t1.setText("Player 4");
            player5t1.setText("Player 5");
            
            player1t2.setText("Player 1");
            player2t2.setText("Player 2");
            player3t2.setText("Player 3");
            player4t2.setText("Player 4");
            player5t2.setText("Player 5");
            
            p1t1.reset_score();
            p1t1.reset_splits();
            p2t1.reset_score();
            p2t1.reset_splits();
            p3t1.reset_score();
            p3t1.reset_splits();
            p4t1.reset_score();
            p4t1.reset_splits();
            p5t1.reset_score();
            p5t1.reset_splits();
            
            p1t2.reset_score();
            p1t2.reset_splits();
            p2t2.reset_score();
            p2t2.reset_splits();
            p3t2.reset_score();
            p3t2.reset_splits();
            p4t2.reset_score();
            p4t2.reset_splits();
            p5t2.reset_score();
            p5t2.reset_splits();
            
            score.setText("0:0");
            info.setText("Info:");
            
            game.init_game();
            
            t1.reset_scores();
            t1.reset_splits();
            t2.reset_scores();
            t2.reset_splits();
            
            playernumt1 = 0;
            playernumt2 = 0;
         }
            
      }
   }
   /**
   * removes most recent player selected
   *
   *
   */
   private class DeleteTeam1Pressed implements ActionListener
   {
   /**
   * Removes player selected from the team
   *
   * @param actionEvent e
   */
      public void actionPerformed(ActionEvent e)
      {
         if(firstpressed == false && playernumt1 != 6)
         {
            playernumt1 +=1;
            firstpressed = true;
         }
         else if(firstpressed == false && playernumt1 == 6)
         {
            firstpressed = true;
         }
         if (playernumt1 >0)
         {
            playernumt1 -= 1;
         }
         if (playernumt1 == 1)
         {
            playernumt1 -=1;
            player1t1.setText("Player: ");
            
         }
         if (playernumt1 == 2)
         {
            player2t1.setText("Player: ");
            
         }
         if (playernumt1 == 3)
         {
            player3t1.setText("Player: ");
            
         }
         if (playernumt1 == 4)
         {
            player4t1.setText("Player: ");
            
         }
         if (playernumt1 == 5)
         {
            player5t1.setText("Player: ");
            
         }
         
      }
   }
   private class DeleteTeam2Pressed implements ActionListener
   {
   /**
   * Removes player selected from the team
   *
   * @param actionEvent e
   */
      public void actionPerformed(ActionEvent e)
      {
         if(firstpressedt2 == false && playernumt2 != 6)
         {
            playernumt2 +=1;
            firstpressedt2 = true;
         }
         else if(firstpressedt2 == false && playernumt2 == 6)
         {
            firstpressedt2 = true;
         }
         if (playernumt2 >0)
         {
            playernumt2 -= 1;
         }
         if (playernumt2 == 1)
         {
            playernumt2 -=1;
            player1t2.setText("Player: ");
            
         }
         if (playernumt2 == 2)
         {
            player2t2.setText("Player: ");
            
         }
         if (playernumt2 == 3)
         {
            player3t2.setText("Player: ");
            
         }
         if (playernumt2 == 4)
         {
            player4t2.setText("Player: ");
            
         }
         if (playernumt2 == 5)
         {
            player5t2.setText("Player: ");
            
         }
      }
   }
   
}
