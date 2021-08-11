/**
 * Sample Skeleton for 'Main.fxml' Controller Class
 */

package org.vaultage.demo.game.monetisation.gui;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.vaultage.demo.game.monetisation.Match;
import org.vaultage.demo.game.monetisation.MatchState;
import org.vaultage.demo.game.monetisation.RemotePlayer;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;

public class MainController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="matchList"
	private VBox matchList; // Value injected by FXMLLoader

	@FXML // fx:id="buttonSendChallenge"
	private Button buttonSendChallenge; // Value injected by FXMLLoader

	@FXML // fx:id="textFieldStake"
	private TextField textFieldStake; // Value injected by FXMLLoader

	@FXML // fx:id="textAreaOpponentId"
	private TextArea textAreaOpponentId; // Value injected by FXMLLoader

	@FXML // fx:id="buttonRock"
	private Button buttonRock; // Value injected by FXMLLoader

	@FXML // fx:id="buttonPaper"
	private Button buttonPaper; // Value injected by FXMLLoader

	@FXML // fx:id="buttonScissors"
	private Button buttonScissors; // Value injected by FXMLLoader

	@FXML // fx:id="labelPlayer1Choice"
	private Label labelPlayer1Choice; // Value injected by FXMLLoader

	@FXML // fx:id="labelPlayer2Choice"
	private Label labelPlayer2Choice; // Value injected by FXMLLoader

	@FXML // fx:id="labelYourName"
	private Label labelYourName; // Value injected by FXMLLoader

	@FXML // fx:id="labelOpponentName"
	private Label labelOpponentName; // Value injected by FXMLLoader
	

    @FXML // fx:id="rootPane"
    private BorderPane rootPane; // Value injected by FXMLLoader

    @FXML
    private TextField textFieldAccountAddress;

    @FXML
    private TextArea textAreaMyId1;
  
	@FXML
	void buttonSendChallengeOnAction(ActionEvent event) throws Exception {
		
		
		// initialise the match
		String opponentPK = textAreaOpponentId.getText().trim();
		long stakeInGwei = (long) (Double.parseDouble(textFieldStake.getText()) * 1000000000);
		
		Match match = new Match();
		match.setStake(stakeInGwei); 
		match.setTimetamp(Instant.now().getEpochSecond());
		match.setMatchState(MatchState.SENT);
		
		match.setPlayer1address(Main.LOCAL_ACCOUNT_ADDRESS);
		match.setPlayer1Name(Main.LOCAL_PLAYER_ID);
		match.setPlayer1pk(Main.LOCAL_VAULT.getPublicKey());
		
		match.setPlayer2pk(opponentPK);
		
		
		// send the match
		RemotePlayer remotePlayer = new RemotePlayer(Main.LOCAL_VAULT, opponentPK);
		remotePlayer.challenge(Main.LOCAL_PLAYER_ID);
		
		// load the form 
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Match.fxml"));
		MatchController controller = new MatchController(match);
		loader.setController(controller);
		BorderPane borderPane = (BorderPane) loader.load();
		matchList.getChildren().add(0, borderPane);
		
		// add controller to match controller list
		Main.MATCHES.add(controller);
	}

	@FXML
	void buttonPaperOnAction(ActionEvent event) {

	}

	@FXML
	void buttonRockOnAction(ActionEvent event) {

	}

	@FXML
	void buttonScissorsOnAction(ActionEvent event) {

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert buttonSendChallenge != null
				: "fx:id=\"buttonSendChallenge\" was not injected: check your FXML file 'Main.fxml'.";
		assert textFieldStake != null : "fx:id=\"textFieldStake\" was not injected: check your FXML file 'Main.fxml'.";
		assert textAreaOpponentId != null
				: "fx:id=\"textAreaOpponentId\" was not injected: check your FXML file 'Main.fxml'.";
		assert buttonRock != null : "fx:id=\"buttonRock\" was not injected: check your FXML file 'Main.fxml'.";
		assert buttonPaper != null : "fx:id=\"buttonPaper\" was not injected: check your FXML file 'Main.fxml'.";
		assert buttonScissors != null : "fx:id=\"buttonScissors\" was not injected: check your FXML file 'Main.fxml'.";
		assert labelPlayer1Choice != null
				: "fx:id=\"labelPlayer1Choice\" was not injected: check your FXML file 'Main.fxml'.";
		assert labelPlayer2Choice != null
				: "fx:id=\"labelPlayer2Choice\" was not injected: check your FXML file 'Main.fxml'.";
		
		textFieldStake.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					textFieldStake.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		
		labelYourName.setText(Main.LOCAL_PLAYER_ID);
		textFieldAccountAddress.setText(Main.LOCAL_ACCOUNT_ADDRESS);
		textAreaMyId1.setText(Main.LOCAL_VAULT.getPublicKey());
	}
	
	
}
