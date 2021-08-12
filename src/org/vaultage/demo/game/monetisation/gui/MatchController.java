package org.vaultage.demo.game.monetisation.gui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import org.vaultage.demo.game.monetisation.Match;
import org.vaultage.demo.game.monetisation.MatchState;
import org.vaultage.demo.game.monetisation.RemotePlayer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
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
	private Button buttonCancel;

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
		assert buttonCancel != null : "fx:id=\"buttonDecline\" was not injected: check your FXML file 'Match.fxml'.";
		assert buttonPlay != null : "fx:id=\"buttonPlay\" was not injected: check your FXML file 'Match.fxml'.";

		labelContractAddress.setText("");
		labelPlayer1Choice.setText("");
		labelPlayer1Name.setText("");
		labelPlayer2Choice.setText("");
		labelPlayer2Name.setText("");
		labelStake.setText("");
		labelTimestamp.setText("");

		if (match.getMatchState() != null)
			labelState.setText(match.getMatchState().name());
		if (match.getTimetamp() != null) {
			Date date = new Date(match.getTimetamp() * 1000L); // convert seconds to milliseconds
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
			String formattedDate = dateFormat.format(date);
			labelTimestamp.setText(formattedDate);
		}
		if (match.getStake() != null) {
			double stake = match.getStake() / 1000000000.0;
			labelStake.setText(String.valueOf(stake) + " ETH");
		}

		if (match.getPlayer1Name() != null)
			labelPlayer1Name.setText(match.getPlayer1Name());
		if (match.getPlayer2Name() != null)
			labelPlayer2Name.setText(match.getPlayer2Name());

	}

	@FXML
	void buttonAcceptOnAction(ActionEvent event) throws Exception {
		if (match.getMatchState() == MatchState.RECEIVED || match.getMatchState() == MatchState.SENT) {
			match.setMatchState(MatchState.ACCEPTED);

			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					// fill the match list
					try {
						buttonCancel.setDisable(true);
						buttonAccept.setDisable(true);
						labelState.setText(match.getMatchState().name());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			RemotePlayer remotePlayer = new RemotePlayer(Main.LOCAL_VAULT, match.getPlayer2pk());
			remotePlayer.acceptChallenge(match.getId(), true);
		}
	}

	@FXML
	void buttonCancelOnAction(ActionEvent event) {

	}

	@FXML
	void buttonPlayOnAction(ActionEvent event) {

		match.setMatchState(MatchState.PLAYING);
		Main.ACTIVE_MATCH = match;

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					Main.MAIN_CONTROLLER.getLabelOpponentName().setText(match.getPlayer2Name());
					Main.MAIN_CONTROLLER.getLabelPlayer1Choice().setText("???");
					Main.MAIN_CONTROLLER.getLabelPlayer2Choice().setText("???");
					Main.MAIN_CONTROLLER.getButtonPaper().setDisable(false);
					Main.MAIN_CONTROLLER.getButtonRock().setDisable(false);
					Main.MAIN_CONTROLLER.getButtonScissors().setDisable(false);
					buttonAccept.setDisable(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Label getLabelPlayer1Name() {
		return labelPlayer1Name;
	}

	public Label getLabelPlayer2Name() {
		return labelPlayer2Name;
	}

	public Label getLabelPlayer1Choice() {
		return labelPlayer1Choice;
	}

	public Label getLabelPlayer2Choice() {
		return labelPlayer2Choice;
	}

	public Label getLabelState() {
		return labelState;
	}

	public Button getButtonAccept() {
		return buttonAccept;
	}

	public Label getLabelContractAddress() {
		return labelContractAddress;
	}

	private Match match;

	public Match getMatch() {
		return match;
	}

	public Button getButtonCancel() {
		return buttonCancel;
	}

	public Button getButtonPlay() {
		return buttonPlay;
	}

	public MatchController(Match match) {
		this.match = match;
	}

	public void enableButtonAccept(boolean state) {
		buttonAccept.disableProperty().set(!state);
	}

	/***
	 * Update localMatch based on the received remote match.
	 * 
	 * @param remoteMatch
	 */
	public void updateLocalMatch(Match remoteMatch) {
		match.setPlayer2Name(remoteMatch.getPlayer1Name());
		match.setPlayer2pk(remoteMatch.getPlayer1pk());
		match.setPlayer2address(remoteMatch.getPlayer1address());

		match.setMatchState(remoteMatch.getMatchState());

		if (remoteMatch.getContractAddress() != null) {
			match.setContractAddress(remoteMatch.getContractAddress());
		}

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					labelPlayer2Name.setText(match.getPlayer2Name());
					labelState.setText(match.getMatchState().name());
					labelContractAddress.setText(match.getContractAddress());
					if (match.getMatchState().equals(MatchState.READY)) {
						buttonPlay.setDisable(false);
						buttonAccept.setDisable(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
