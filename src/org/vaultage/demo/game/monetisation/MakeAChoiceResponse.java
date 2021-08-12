package org.vaultage.demo.game.monetisation;

import java.util.List;

import org.vaultage.core.Vault;
import org.vaultage.demo.game.monetisation.gui.Main;
import org.vaultage.demo.game.monetisation.gui.MatchController;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class MakeAChoiceResponse extends MakeAChoiceResponseHandler {

	List<MatchController> matchControllers;

	public MakeAChoiceResponse(List<MatchController> matchControllers) {
		this.matchControllers = matchControllers;
	}

	@Override
	public void run(Vault localVault, RemotePlayer remoteVault, String responseToken, Match result) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void run(Player localVault, RemotePlayer remoteVault, String responseToken, Match remoteMatch)
			throws Exception {
		MatchController matchController = matchControllers.stream()
				.filter(mc -> mc.getMatch().getId().equals(remoteMatch.getId())).findFirst().orElse(null);

		if (matchController != null) {
			Match match = matchController.getMatch();
			match.setPlayer2choice(remoteMatch.getPlayer1choice());

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
						}else if (match.getPlayer2Name().equals(match.getWinner())) {
							controller.getLabelPlayer2Choice().setStyle("-fx-font-weight: bold;");
							controller.getLabelPlayer2Name().setStyle("-fx-font-weight: bold;");
							(new Alert(Alert.AlertType.INFORMATION, match.getPlayer1Name() + " Lose!")).show();
						}

						controller.getButtonPlay().setDisable(true);

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
	}

}
