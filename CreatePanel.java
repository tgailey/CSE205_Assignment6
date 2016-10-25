// Assignment #: 6
// Arizona State University - CSE205
//         Name: Trenton Gailey
//    StudentID: 1211386693
//      Lecture: Monday Wednesday Friday 9:40 - 10:30
// Description: This panel allows the user to enter in an athletes information
//				and create an athlete with the information provided
//				The user will have fields for first name, last name, and the sport
//				Everytime an athlete is created, update the list in CountPanel

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class CreatePanel extends JPanel
 {
   private Vector athleteList;
   
   //All components on left side of screen on left panel
   private JPanel leftPanel;
   //All components for first name
   private JLabel fName;
   private JTextArea fNameEntry;
   private JPanel fNamePanel;
   //All components for last name
   private JLabel lName;
   private JTextArea lNameEntry;
   private JPanel lNamePanel;
   //All components for sportEntry
   private JLabel sport;
   private JTextArea sportEntry;
   private JPanel sportPanel;
   
   private JPanel createButtonPanel;
   private JButton createButton;
   
   private JLabel athleteAddedStatus;
   private JPanel athleteAddedPanel;
   
   //Components of the right side
   private JScrollPane aListScroll;
   private JTextArea aListDisplay;
   //Other
   private CountPanel cPanel;

 //Constructor initializes components and organize them using certain layouts
 public CreatePanel(Vector athleteList, CountPanel cPanel)
  {
    this.athleteList = athleteList;
    this.cPanel = cPanel;
    setLayout(new GridLayout(1,2)); //Panel layout for left and right side
    
    //Panel for all the left side initialized
    leftPanel = new JPanel();
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
    
    //RED TEXT LABEL INITIALIZED
    athleteAddedStatus = new JLabel("Enter the athlete's information");
    athleteAddedStatus.setForeground(Color.red);
    
    //athleteAdded ADDED TO PANEL SO THAT LABEL DOES NOT MOVE WHEN TEXT IS ENTERED
    athleteAddedPanel = new JPanel();
    athleteAddedPanel.setLayout(new BoxLayout(athleteAddedPanel, BoxLayout.X_AXIS));
    athleteAddedPanel.add(athleteAddedStatus);

    //Adding athleteAdded panel to leftPanel and adding space to leftPanel
    leftPanel.add(Box.createRigidArea(new Dimension(0,75)));
    leftPanel.add(athleteAddedPanel);
    leftPanel.add(Box.createRigidArea(new Dimension(0,15)));
    
    //FIRST NAME COMPONENTS INITIALIZED
    fName = new JLabel("First Name");
    fNameEntry = new JTextArea(1,10);
    fNamePanel = new JPanel();
    
    //FIRST NAME COMPONENTS BEING ADDED TO FIRST NAME PANEL
    fNamePanel.setLayout(new GridLayout(1,2));
    fNamePanel.add(fName);
    fNamePanel.add(fNameEntry);

    //Adding first name panel to leftPanel and adding space to leftPanel
    leftPanel.add(Box.createRigidArea(new Dimension(0,15)));
    leftPanel.add(fNamePanel);
    
    //LAST NAME COMPONENTS INITIALIZED
    lName = new JLabel("Last Name");
    lNameEntry = new JTextArea(1,10);
    
    //LAST NAME COMPONENTS BEING ADDED TO LAST NAME PANEL
    lNamePanel = new JPanel(new GridLayout (1,2));
    lNamePanel.add(lName);
    lNamePanel.add(lNameEntry);
    
    //Adding last name panel to leftPanel and adding space to leftPanel
    leftPanel.add(Box.createRigidArea(new Dimension(0,15)));
    leftPanel.add(lNamePanel);
    
    //SPORT COMPONENTS INITIALIZED
    sport = new JLabel("Sport");
    sportEntry = new JTextArea(1,10);

    //SPORT COMPONENTS BEING ADDED TO SPORT PANEL
    sportPanel = new JPanel(new GridLayout(1,2));
    sportPanel.add(sport);
    sportPanel.add(sportEntry);
    
    //Adding sport panel to leftPanel and adding space to leftPanel
    leftPanel.add(Box.createRigidArea(new Dimension(0,15)));
    leftPanel.add(sportPanel);
    
    //CREATE BUTTON INITIALIZED
    createButton = new JButton("Create an Athlete");
    createButton.addActionListener(new ButtonListener());
    
    //CREATE BUTTON ADDED TO PANEL SO THAT BUTTON DOES NOT MOVE WHEN TEXT IS ENTERED
    createButtonPanel = new JPanel();
    createButtonPanel.setLayout(new BoxLayout(createButtonPanel, BoxLayout.X_AXIS));
    createButtonPanel.add(createButton);
    
    //Adding createButton panel to leftPanel and adding space from top to leftPanel
    leftPanel.add(Box.createRigidArea(new Dimension(0,15)));
    leftPanel.add(createButtonPanel);
    leftPanel.add(Box.createRigidArea(new Dimension(0,95)));
    
    //Initializing textfield where the athletes will be listed
    aListDisplay = new JTextArea("No athlete");
    aListDisplay.setEditable(false);
    aListScroll = new JScrollPane(aListDisplay);
    
    //Adding both right and left sides to main panel
    add(leftPanel);
    add(aListScroll);
  }


  //ButtonListener is a listener class that listens to
  //see if the button "Create an Athlete" is pushed.
  //When the event occurs, it adds an athlete using the information
  //entered by a user.
  //It also performs error checking.
  private class ButtonListener implements ActionListener
   {
	  public void actionPerformed(ActionEvent event)
	  {
		  //IF NONE OF THE TEXTS ARE EMPTY
    	if (!fNameEntry.getText().equals("")
    			&& !lNameEntry.getText().equals("")
    			&&!sportEntry.getText().equals(""))
    	{
    		//CREATE A NEW ATHLETE WITH ALL THE INFO OF ENTERED BY USER
    		Athlete tempAthlete = new Athlete();
    		tempAthlete.setFirstName(fNameEntry.getText());
    		fNameEntry.setText("");
    		tempAthlete.setLastName(lNameEntry.getText());
    		lNameEntry.setText("");
    		tempAthlete.setSport(sportEntry.getText());
    		sportEntry.setText("");
    		
    		//ADD ATHLETE TO LIST OF ATHLETES
    		athleteList.add(tempAthlete);
			String tempString = "";
			//TRANSVERSE THE LIST OF ATHLETES AND ADD THEIR TO STRINGS TO TEMPSTRING
    		for (int i = 0; i < athleteList.size(); i++) {
    			tempString += athleteList.get(i).toString();
    			//DISPLAY THE FINAL TEMPSTRING IN THE LISTDISPLAY
    	 		aListDisplay.setText(tempString);
    		} 
    		//UPDATE ATHELTE LIST
    		cPanel.updateAthleteList();
    		//SET RED TEXT TO ATHLETE ADDED
    		athleteAddedStatus.setText("athlete added");
    	}
    	else {
    		//TELL USER TO ENTER IN ALL THE FIELDS WITH RED TEXT
    		athleteAddedStatus.setText("Please enter all fields");
    	}

     } //end of actionPerformed method
  } //end of ButtonListener class

} //end of CreatePanel class