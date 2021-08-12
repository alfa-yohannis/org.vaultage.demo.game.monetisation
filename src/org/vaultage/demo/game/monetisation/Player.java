package org.vaultage.demo.game.monetisation;

import java.util.ArrayList;
import java.util.List;

import org.vaultage.demo.game.monetisation.gui.Main;
import org.vaultage.demo.game.monetisation.gui.MatchController;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;

public class Player extends PlayerBase {
	private String name = new String();
	private String accountAddress = new String();
	private List<Match> matches = new ArrayList<Match>();

	public Player() throws Exception {
		super();
	}

	public Player(String address, int port) throws Exception {
		super(address, port);
	}

	// getter
	public String getName() {
		return this.name;
	}

	public String getAccountAddress() {
		return this.accountAddress;
	}

	public List<Match> getMatches() {
		return this.matches;
	}

	// setter
	public void setName(String name) {
		this.name = name;
	}

	public void setAccountAddress(String accountAddress) {
		this.accountAddress = accountAddress;
	}

	/***
	 * Receive a challenge from a remote player.
	 */
	public void sendChallenge(String requesterPublicKey, String requestToken, Match match) throws Exception {

		match.setPlayer2address(match.getPlayer1address());
		match.setPlayer2pk(match.getPlayer1pk());
		match.setPlayer2Name(match.getPlayer1Name());

		match.setPlayer1address(Main.LOCAL_ACCOUNT_ADDRESS);
		match.setPlayer1pk(Main.LOCAL_VAULT.getPublicKey());
		match.setPlayer1Name(Main.LOCAL_PLAYER_ID);
		match.setMatchState(MatchState.RECEIVED);

		this.matches.add(match);

//		System.out.println("ALFA: " + Main.LOCAL_VAULT.id + " : " + Main.LOCAL_VAULT.matches);

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// fill the match list
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("./gui/Match.fxml"));
					MatchController controller = new MatchController(match);
					loader.setController(controller);
					BorderPane borderPane = (BorderPane) loader.load();
					Main.MAIN_CONTROLLER.getMatchList().getChildren().add(0, borderPane);
					Main.MATCHES.add(controller);
					controller.enableButtonAccept(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		RemotePlayer remotePlayer = new RemotePlayer(this, requesterPublicKey);
		remotePlayer.respondToAcceptChallenge(match, requestToken);
	}

	public void makeAChoice(String requesterPublicKey, String requestToken, String matchId, Choice choice)
			throws Exception {
		Match match = this.matches.stream().filter(m -> m.getId().equals(matchId)).findFirst().orElse(null);
		if (match != null) {
			match.setPlayer2choice(choice);
			if (match.getPlayer1choice() != null) {

				if (match.getPlayer1choice() == Choice.PAPER && match.getPlayer2choice() == Choice.ROCK) {
					match.setWinner(match.getPlayer1Name());
					match.setMatchState(MatchState.PLAYED);
				} else if (match.getPlayer1choice() == Choice.PAPER && match.getPlayer2choice() == Choice.SCISSORS) {
					match.setWinner(match.getPlayer2Name());
					match.setMatchState(MatchState.PLAYED);
				} else if (match.getPlayer1choice() == Choice.ROCK && match.getPlayer2choice() == Choice.PAPER) {
					match.setWinner(match.getPlayer2Name());
					match.setMatchState(MatchState.PLAYED);
				} else if (match.getPlayer1choice() == Choice.ROCK && match.getPlayer2choice() == Choice.SCISSORS) {
					match.setWinner(match.getPlayer1Name());
					match.setMatchState(MatchState.PLAYED);
				} else if (match.getPlayer1choice() == Choice.SCISSORS && match.getPlayer2choice() == Choice.ROCK) {
					match.setWinner(match.getPlayer2Name());
					match.setMatchState(MatchState.PLAYED);
				} else if (match.getPlayer1choice() == Choice.SCISSORS && match.getPlayer2choice() == Choice.PAPER) {
					match.setWinner(match.getPlayer1Name());
					match.setMatchState(MatchState.PLAYED);
				}

				if (match.getPlayer1choice() != Choice.UNKNOWN) {
					MatchController controller = Main.MATCHES.stream() //
							.filter(mc -> mc.getMatch().equals(match)).findFirst().orElse(null);

					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {

								controller.getLabelPlayer1Choice().setText(match.getPlayer1choice().name());
								controller.getLabelPlayer2Choice().setText(match.getPlayer2choice().name());

								if (match.getPlayer1Name().equals(match.getWinner())) {
									controller.getLabelPlayer1Choice().setStyle("-fx-font-weight: bold;");
									controller.getLabelPlayer1Name().setStyle("-fx-font-weight: bold;");
									(new Alert(Alert.AlertType.INFORMATION, match.getPlayer1Name() + " Win!")).show();
								} else if (match.getPlayer2Name().equals(match.getWinner())) {
									controller.getLabelPlayer2Choice().setStyle("-fx-font-weight: bold;");
									controller.getLabelPlayer2Name().setStyle("-fx-font-weight: bold;");
									(new Alert(Alert.AlertType.INFORMATION, match.getPlayer1Name() + " Lose!")).show();
								}

								if (match.getPlayer2choice() == Choice.PAPER) {
									Main.MAIN_CONTROLLER.getLabelPlayer2Choice().setText("Paper");
								} else if (match.getPlayer2choice() == Choice.ROCK) {
									Main.MAIN_CONTROLLER.getLabelPlayer2Choice().setText("Rock");
								} else if (match.getPlayer2choice() == Choice.SCISSORS) {
									Main.MAIN_CONTROLLER.getLabelPlayer2Choice().setText("Scissors");
								}

								if (match.getMatchState() == MatchState.PLAYED) {
									controller.getButtonPlay().setDisable(true);
									controller.getLabelState().setText(match.getMatchState().name());
									Main.MAIN_CONTROLLER.getButtonPaper().setDisable(true);
									Main.MAIN_CONTROLLER.getButtonScissors().setDisable(true);
									Main.MAIN_CONTROLLER.getButtonRock().setDisable(true);
								}

							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
				// ----
			}

			RemotePlayer remotePlayer = new RemotePlayer(this, requesterPublicKey);
			remotePlayer.respondToMakeAChoice(match, requestToken);
		}
	}

	/***
	 * Receive a message that a challenge for that matchId has been accepted.
	 */
	@Override
	public void acceptChallenge(String requesterPublicKey, String requestToken, String matchId, boolean response)
			throws Exception {
		Match match = this.matches.stream().filter(m -> m.getId().equals(matchId)).findFirst().orElse(null);
		if (match != null) {
			match.setMatchState(MatchState.ACCEPTED);

			MatchController controller = Main.MATCHES.stream() //
					.filter(mc -> mc.getMatch().equals(match)).findFirst().orElse(null);

			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					try {
						controller.getLabelState().setText(match.getMatchState().name());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			/***
			 * MAKE A SMART CONTRACT HERE BEFORE RESPONDING BACK
			 */

			match.setContractAddress("DUMMY ADDRESS");
			match.setMatchState(MatchState.READY);

			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					try {
						controller.getLabelState().setText(match.getMatchState().name());
						controller.getButtonPlay().setDisable(false);
						controller.getButtonCancel().setDisable(true);
						controller.getButtonAccept().setDisable(true);
						controller.getLabelContractAddress().setText(match.getContractAddress());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			RemotePlayer remotePlayer = new RemotePlayer(this, requesterPublicKey);
			remotePlayer.respondToAcceptChallenge(match, requestToken);
		}

	}

}