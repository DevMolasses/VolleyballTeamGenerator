/*
 * File: BlankClass.java
 * ---------------------
 * This class is a blank one that you can change at will. Remember, if you change
 * the class name, you'll need to change the filename so that it matches.
 * Then you can extend GraphicsProgram, ConsoleProgram, or DialogProgram as you like.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import acm.gui.TableLayout;
import acm.program.Program;
import acm.util.RandomGenerator;

@SuppressWarnings("serial")
public class TeamGenerator extends Program {
	
	/** set the HTML constants to concatenate to the list of players*/
	private static final String PLAYERS_LIST_START = "<html><font size = 4>Players</font>";
	private static final String PLAYERS_LIST_FINISH = "</html>";
	
	/** Set the overall width and height of the application window*/
	public static final int APPLICATION_WIDTH = 600;
	public static final int APPLICATION_HEIGHT = 400;
	
	/** initializes the program*/
	public void init() {
		setUpGUI();
		
		/*Initialize the arrays*/
		nameArray = new ArrayList<String>();
		genderArray = new ArrayList<String>();		
	}
	
	/**
	 * Creates the GUI for the program
	 */
	private void setUpGUI() {
		
		 /*Create a table layout with 3 columns 
		 and an unspecified number of rows*/
		setLayout(new TableLayout(0,3));
		
		/*Add column headers - Row 1*/
		add(new JLabel("<html><font size = 5 color = green>" +
				"Add Player</font></html>", JLabel.CENTER), "gridwidth=2");
		add(new JLabel("<html><font size = 5 color = red>" +
				"Remove Player</font></html>", JLabel.CENTER), "width=250");
		
		/*Add brief instructions - Row 2*/
		add(new JLabel("<html><font size = 4>" +
				"Enter the Player's Name</font></html>", JLabel.CENTER), "gridwidth=2");
		add(new JLabel("<html><font size = 4>" +
				"Select the Player's Name</font></html>", JLabel.CENTER));
		
		/*Add text field to enter new players name - Row 3 Column 1-2*/
		final JTextField playerToAdd = new JTextField();
		add(playerToAdd, "gridwidth=2");
		
		/*Add combobox to select player to remove - Row 3 Column 3*/
		playerToRemove = new JComboBox<String>();
		playerToRemove.addItem("");
		playerToRemove.setPrototypeDisplayValue("12345678901234567890");
		add(playerToRemove, "fill=NONE");
		
		/*Add brief instructions to select the player's gender - Row 4 Column 1-2 */
		add(new JLabel("<html><font size = 4>" +
				"Select the Player's Gender</font></html>", JLabel.CENTER), "gridwidth=2");
		
		/*Fill cell with empty label - Row 4 Column 3*/
		add(new JLabel(""));
		
		/*Add button to select female gender - Row 5 Column 1*/
		JButton addFemaleButton = new JButton("Female");
		add(addFemaleButton, "width=100 fill=NONE");
		addFemaleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!playerToAdd.getText().trim().equals("")) {
					addPlayer(playerToAdd.getText(), "female");
					playerToAdd.setText("");
				}
				playerToAdd.requestFocus();
			}
		});
		
		/*Add button to select male gender - Row 5 Column 2*/
		JButton addMaleButton = new JButton("Male");
		add(addMaleButton, "width=100 fill=NONE");
		addMaleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!playerToAdd.getText().trim().equals("")) {
					addPlayer(playerToAdd.getText(), "male");
					playerToAdd.setText("");
				}
				playerToAdd.requestFocus();
			}
		});
		
		/*Add button to remove player - Row 5 Column 3*/
		JButton removeButton = new JButton("Remove");
		add(removeButton, "fill=NONE");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!playerToRemove.getSelectedItem().toString().trim().equals(""))
					removePlayer((String) playerToRemove.getSelectedItem());
			}
		});
		
		/*Add button to select prayer to the SOUTH frame*/
		JButton selectPrayerButton = new JButton("Select Prayer");
		add(selectPrayerButton, SOUTH);
		selectPrayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectPrayer();
			}
		});
		
		/*Add button to create the teams to the SOUTH frame*/
		JButton createTeamsButton = new JButton("Shuffle Teams");
		add(createTeamsButton, SOUTH);
		createTeamsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createTeams();
			}
		});
		
		/*Add button to display the teams to the SOUTH frame*/
		JButton displayTeamsButton = new JButton("Display Teams");
		add(displayTeamsButton, SOUTH);
		displayTeamsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayTeams();
			}
		});
		
		/*Add the listOfPlayers to the WEST frame*/
		listOfPlayers = new JLabel(PLAYERS_LIST_START + PLAYERS_LIST_FINISH);
		add(listOfPlayers, WEST);		
		
	}
	
	/**
	 * Adds a player to the roster
	 * 
	 * @param name The name of the player to add
	 * @param gender The gender of the player to add
	 */
	private void addPlayer(String name, String gender) {
		addPlayerToArrays(name, gender);
		updatePlayerToRemove();
		updateListOfPlayers();
	}
	
	/**
	 * Add the player's name and gender to their respective ArrayLists
	 * 
	 * @param name The name of the player to add
	 * @param gender The gender of the player to add
	 */
	private void addPlayerToArrays(String name, String gender) {
		nameArray.add(name);
		genderArray.add(gender);
	}
	
	/**
	 * Updates the items in the playerToRemove combobox
	 */
	private void updatePlayerToRemove() {
		playerToRemove.removeAllItems();
		playerToRemove.addItem("");
		for (int i = 0; i < nameArray.size(); i++) {
			playerToRemove.addItem(nameArray.get(i));
		}
	}
	
	/**
	 * Updates the list of players displayed on the WEST side of the screen
	 */
	private void updateListOfPlayers() {
		String listText = PLAYERS_LIST_START;
		for (int i = 0; i < nameArray.size(); i++) {
			listText += "<p>" + nameArray.get(i);
		}
		listText += PLAYERS_LIST_FINISH;
		listOfPlayers.setText(listText);
	}
	
	/** 
	 * Removes a player from the roster
	 * 
	 * @param name The name of the player to remove
	 */
	private void removePlayer(String name) {
		removePlayerFromArrays(name);
		updatePlayerToRemove();
		updateListOfPlayers();
	}
	
	/**
	 * Removes the player and their associated gender from the ArrayLists
	 * Assumes the gender of the player has the same index as the name
	 * of the player in their respective ArrayList.
	 * 
	 * @param name The name of the player to remove
	 */
	private void removePlayerFromArrays(String name) {
		int index = nameArray.indexOf(name);
		nameArray.remove(index);
		genderArray.remove(index);
	}
	
	/** 
	 * Randomly selects a player from the roster to say the prayer.
	 * Displays the person to pray in a MessageDialog box.
	 */
	private void selectPrayer() {
		RandomGenerator rgen = RandomGenerator.getInstance();
		int index = rgen.nextInt(0, nameArray.size() - 1);
		String prayer = "<html><font size = 16>Prayer: " 
			+ nameArray.get(index) + "</font></html>";
		JOptionPane.showMessageDialog(null , prayer,
				"Prayer", JOptionPane.PLAIN_MESSAGE);
	}
	
	/**
	 * Randomly creates the teams keeping balance in team size
	 * and males per team.  No teams will have more than 8 players
	 * and extra teams will be generated to reduce the number of
	 * players required to rotate in to the game.
	 */
	private void createTeams() {
		shuffleMales();
		shuffleFemales();
		displayTeams();
	}
	
	private void shuffleFemales() {
		femalesShuffled = new ArrayList<String>();
		ArrayList<String> females = new ArrayList<String>();
		for (int i = 0; i < genderArray.size(); i++) {
			if (genderArray.get(i).equalsIgnoreCase("female"))
				females.add(nameArray.get(i));
		}
		RandomGenerator rgen = RandomGenerator.getInstance();
		for (int i = females.size() - 1; i >= 0; i--) {
			int rndNum = rgen.nextInt(0, i);
			String temp = females.get(rndNum);
			females.set(rndNum, females.get(i));
			females.set(i, temp);
		}
		for (int i = 0; i < females.size(); i++) {
			femalesShuffled.add(females.get(i));
		}
	}

	private void shuffleMales() {
		malesShuffled = new ArrayList<String>();
		ArrayList<String> males = new ArrayList<String>();
		for (int i = 0; i < genderArray.size(); i++) {
			if (genderArray.get(i).equalsIgnoreCase("male"))
				males.add(nameArray.get(i));
		}
		RandomGenerator rgen = RandomGenerator.getInstance();
		for (int i = males.size() - 1; i >= 0; i--) {
			int rndNum = rgen.nextInt(0, i);
			String temp = males.get(rndNum);
			males.set(rndNum, males.get(i));
			males.set(i, temp);
		}
		for (int i = 0; i < males.size(); i++) {
			malesShuffled.add(males.get(i));
		}
	}

	private void displayTeams() {
		String htmlCode = createdTeams(numberOfTeams());
		JOptionPane.showMessageDialog(rootPane,
				htmlCode,
			    "The Teams Are:",
			    JOptionPane.PLAIN_MESSAGE);
	}
	
	/**
	 * determines the number of teams necessary to accommodate all the players
	 * 
	 * @return int The number of required teams
	 */
	private int numberOfTeams() {
		int num = nameArray.size();
		if (num <= 14) return 2;
		if (num / 6 < 4 && num % 6 <= num / 6) return num / 6;
		if (num / 6 >= 4 && num - (num / 6) * 6 <= 2 * (num / 6)) {
			int cnt = 4;
			while (num - cnt * 6 > 2 * cnt) {
				cnt++;
			}
			return cnt;
		}
		return num / 6 + 1;
	}

	/**
	 * This method returns a String which contains the 
	 * html code necessary to display the teams in a 
	 * MessageDialog
	 * 
	 * @return String A string of html code which creates a
	 * table of players in their teams
	 */
	private String createdTeams(int numberOfTeams) {
		/*Begin the string that defines the table with all the players*/
		String html = tablePreamble();
		
		/* Add the Team # headers for the columns */
		html += addTableHeaders(numberOfTeams);
		
		/*Add The players*/
		html += addTablePlayers(numberOfTeams);
		
		/*Finish the table*/
		html += tableClose();
		return html;
	}

	private String addTablePlayers(int numberOfTeams) {
		String htmlPlayers = "";
		int cellCnt = 0;
		for (int i = 0; i < malesShuffled.size(); i++) {
			if (cellCnt % numberOfTeams == 0) htmlPlayers += startTableRow();
			htmlPlayers += "<td>" + malesShuffled.get(i) + "</td>";
			if (cellCnt % numberOfTeams == numberOfTeams - 1) htmlPlayers += endTableRow();
			cellCnt++;
		}
		for (int i = 0; i < femalesShuffled.size(); i++) {
			if (cellCnt % numberOfTeams == 0)
				htmlPlayers += startTableRow();
			if (needsBlank(cellCnt, numberOfTeams)) {
				htmlPlayers += "<td></td>";
				i--;
			} else {
				htmlPlayers += "<td>" + femalesShuffled.get(i) + "</td>";
			}
			if (cellCnt % numberOfTeams == numberOfTeams - 1) htmlPlayers += endTableRow();
			cellCnt++;
		}
		
		return htmlPlayers;
	}

	private boolean needsBlank(int cellCnt, int numberOfTeams) {
		return nameArray.size() % numberOfTeams > 0 
		&& cellCnt >= nameArray.size() / numberOfTeams * numberOfTeams 
		&& cellCnt < (nameArray.size() / numberOfTeams * 
				numberOfTeams + numberOfTeams) - (nameArray.size() % numberOfTeams);  
	}

	private String addTableHeaders(int numberOfTeams) {
		String str = startTableRow();
		for (int i = 1; i <= numberOfTeams; i++) {
			str += addTeamHeader(i);
		}
		str += endTableRow();
		return str;
	}

	private String addTeamHeader(int i) {
		return "<th>Team " + i + "</th>";
	}

	private String startTableRow() {
		return "<tr text-align=\"center\">";
	}

	private String endTableRow() {
		return "</tr>";
	}

	private String tablePreamble() {
		return 
			"<html>" +
			"<style type=\"text/css\">" +
			".tftable {font-size:10px;color:#333333;width:100%;" +
			"border-width: 1px;border-color: #729ea5;border-collapse: collapse;}" + 
			".tftable th {font-size:12px;background-color:#acc8cc;" +
			"border-width: 1px;padding: 8px;border-style: solid;" +
			"border-color: #729ea5;text-align:center;}" +
			".tftable tr {background-color:#ffffff;}" +
			".tftable td {font-size:10px;border-width: 1px;" +
			"padding: 8px;border-style: solid;border-color: #729ea5;}" +
			"</style>" +
			"<table class=\"tftable\">";
	}
	
	private String tableClose() {
		return "</table></html>";
	}
	
	/**Create instance variables*/
	JComboBox<String> playerToRemove;
	JLabel listOfPlayers;
	JLabel prayer;
	
	ArrayList<String> nameArray;
	ArrayList<String> genderArray;
	ArrayList<String> malesShuffled;
	ArrayList<String> femalesShuffled;
	
	// Code required to make this a JAR file
	public static void main(String[] args) {
		new TeamGenerator().start(args);
	}
}

