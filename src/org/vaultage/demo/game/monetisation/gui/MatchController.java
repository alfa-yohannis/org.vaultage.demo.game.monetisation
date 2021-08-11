package org.vaultage.demo.game.monetisation.gui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import org.vaultage.demo.game.monetisation.Match;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MatchController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label labelState;

	@FXML
	private Label labelTimestamp;

	@FXML
	private Label labelStake;

	@FXML
	private Label labelPlayer1Name;

	@FXML
	private Label labelPlayer2Name;

	@FXML
	private Label labelContractAddress;

	@FXML
	private Label labelPlayer1Choice;

	@FXML
	private Label labelPlayer2Choice;

	@FXML
	private Button buttonAccept;

	@FXML
	private Button buttonDecline;

	@FXML
	private Button buttonPlay;

	@FXML
	void initialize() {
		assert labelState != null : "fx:id=\"labelState\" was not injected: check your FXML file 'Match.fxml'.";
		assert labelTimestamp != null : "fx:id=\"labelTimestamp\" was not injected: check your FXML file 'Match.fxml'.";
		assert labelStake != null : "fx:id=\"labelStake\" was not injected: check your FXML file 'Match.fxml'.";
		assert labelPlayer1Name != null
				: "fx:id=\"labelPlayer1Name\" was not injected: check your FXML file 'Match.fxml'.";
		assert labelPlayer2Name != null
				: "fx:id=\"labelPlayer2Name\" was not injected: check your FXML file 'Match.fxml'.";
		assert labelContractAddress != null
				: "fx:id=\"labelContractAddress\" was not injected: check your FXML file 'Match.fxml'.";
		assert labelPlayer1Choice != null
				: "fx:id=\"labelPlayer1Choice\" was not injected: check your FXML file 'Match.fxml'.";
		assert labelPlayer2Choice != null
				: "fx:id=\"labelPlayer2Choice\" was not injected: check your FXML file 'Match.fxml'.";
		assert buttonAccept != null : "fx:id=\"buttonAccept\" was not injected: check your FXML file 'Match.fxml'.";
		assert buttonDecline != null : "fx:id=\"buttonDecline\" was not injected: check your FXML file 'Match.fxml'.";
		assert buttonPlay != null : "fx:id=\"buttonPlay\" was not injected: check your FXML file 'Match.fxml'.";

		labelContractAddress.setText("");
		labelPlayer1Choice.setText("");
		labelPlayer1Name.setText("");
		labelPlayer2Choice.setText("");
		labelPlayer2Name.setText("");
		labelStake.setText("");
		labelTimestamp.setText("");
		
		labelPlayer1Name.setText(match.getPlayer1Name());
		labelState.setText(match.getMatchState().name());
		
		Date date = new Date(match.getTimetamp()*1000L); // convert seconds to milliseconds
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
		String formattedDate = dateFormat.format(date); 
		labelTimestamp.setText(formattedDate);
		
		double stake = match.getStake() / 1000000000.0;
        labelStake.setText(String.valueOf(stake) + " ETH");
	}

	private Match match;

	public Match getMatch() {
		return match;
	}

	public MatchController(Match match) {
		this.match = match;
	}

}
