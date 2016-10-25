// Assignment #: 6
// Arizona State University - CSE205
//         Name: Trenton Gailey
//    StudentID: 1211386693
//      Lecture: Monday Wednesday Friday 9:40 - 10:30
// Description: This class is the panel which keeps count of all the athlete medals
//				This Panel allows the user to select an athlete from the list
//				Select the medal they want to add, then add it to selected athlete
//				And update the list
//				The list is updated from create panel everytime an athlete is created
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class CountPanel extends JPanel
 {
     private Vector athleteList;
     private JList medalList;
     
     private JScrollPane medalScroll;
     
     private JLabel choose;
     
     //Radio Button Stuff
     private JRadioButton gold, silver, bronze;
     private JPanel medalPanel;
     
     private JPanel bottomPanel;
     private JButton increaseCount;

     
   public CountPanel(Vector athleteList)
     {
         setLayout(new BorderLayout());
	   	 this.athleteList = athleteList;
	   	 
	   	 //The label giving the instructions to the user
	   	 choose = new JLabel("Choose an athlete and a medal type to increase its count:");
         
	   	 //The list of athletes that can be selected
	   	 medalList = new JList(this.athleteList);
         medalScroll = new JScrollPane (medalList);

         //All the medal buttons
         gold = new JRadioButton("Gold", false);
         silver = new JRadioButton("Silver", false);
         bronze = new JRadioButton("Bronze", false);
         gold.addActionListener(new CountListener());
         silver.addActionListener(new CountListener());
         bronze.addActionListener(new CountListener());
         
         //Panel that holds all the medal buttons
         medalPanel = new JPanel();
         medalPanel.setLayout(new GridLayout(1,3));
         medalPanel.add(gold);
         medalPanel.add(silver);
         medalPanel.add(bronze);
        
         //Button pressed to add the selected medal to the selected athlete
         increaseCount = new JButton("Increase Medal Count");
         increaseCount.addActionListener(new ButtonListener());
         
         //Create a bottom panel that holds the medal buttons and the add button
         bottomPanel = new JPanel (new GridLayout(2,1));
         bottomPanel.add(medalPanel);
         bottomPanel.add(increaseCount);
         
         //ADD TO MAIN PANEL IN POSITIONS NEEDED
         add(choose, BorderLayout.NORTH);
         add(medalScroll, BorderLayout.CENTER);
         add(bottomPanel, BorderLayout.SOUTH);
     }

 //This method  refreshes the JComboBox with
 //updated vector information
 public void updateAthleteList()
  {
	 medalList.updateUI();
      //call updateUI() for the JList object
  }
     

 //CountListener class listens to see the radio buttons
 //was chosen
 private class CountListener implements ActionListener
     {
         public void actionPerformed(ActionEvent event)
         {
        	 //If the event is started by the gold button
        	 if (event.getSource().equals(gold)) {
        		 gold.setSelected(true);
        		 silver.setSelected(false);
        		 bronze.setSelected(false);
        	 }
        	 //Else if the event is started by the gold button
        	 else if (event.getSource().equals(silver)) {
        		 gold.setSelected(false);
        		 silver.setSelected(true);
        		 bronze.setSelected(false);
        	 }
        	 //Else if the event is started by the gold button
        	 else if (event.getSource().equals(bronze)) {
        		 gold.setSelected(false);
        		 silver.setSelected(false);
        		 bronze.setSelected(true);
        	 }
         }
     }
     


 //ButtonListener class listens to see the button "Increase Medal Count" is pushed.
 //A user can choose which athlete to increase medal counts and that will update the
 //medal count of such athlete.
 private class ButtonListener implements ActionListener
  {
       public void actionPerformed(ActionEvent event)
        {
      	 //If one of the medals are selected
      	 if (!medalList.isSelectionEmpty()) {
      			//If the selected medal is gold
      		 	//then add the gold medal to selected athlete, 
      		 	//clear selection, and update the list
      		 	if (gold.isSelected()) {
      				 ((Athlete)medalList.getSelectedValue()).increaseGold();
      				 gold.setSelected(false);
      				 updateAthleteList();
      			 }
      			//Else if the selected medal is silver
      		 	//then add the silver medal to selected athlete, 
      		 	//clear selection, and update the list
      			 else if (silver.isSelected()) {
      				 ((Athlete)medalList.getSelectedValue()).increaseSilver();
      				 silver.setSelected(false);
      				 updateAthleteList();
      			 }
      			//Else if the selected medal is bronze
      		 	//then add the bronze medal to selected athlete, 
      		 	//clear selection, and update the list
      			 else if (bronze.isSelected()) {
      				 ((Athlete)medalList.getSelectedValue()).increaseBronze();
      				 bronze.setSelected(false);
      				 updateAthleteList();
      			 }
      	 }
        }
  } //end of ButtonListener class

} //end of CountPanel class