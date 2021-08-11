package org.vaultage.demo.game.monetisation;

import java.util.ArrayList;
import java.util.List;

import org.vaultage.demo.game.monetisation.gui.Main;
import org.vaultage.demo.game.monetisation.gui.MatchController;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

// import org.vaultage.demo.game.monetisation.Match;
// import org.vaultage.demo.game.monetisation.PlayerBase;

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

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

	// operations

	public void challenge(String requesterPublicKey, String requestToken, Match match) throws Exception {

		match.setPlayer2address(match.getPlayer1address());
		match.setPlayer2pk(match.getPlayer1pk());
		match.setPlayer2Name(match.getPlayer1Name());
		
		match.setPlayer1address(Main.LOCAL_ACCOUNT_ADDRESS);
		match.setPlayer1pk(Main.LOCAL_VAULT.getPublicKey());
		match.setPlayer1Name(Main.LOCAL_PLAYER_ID);
		match.setMatchState(MatchState.RECEIVED);
		
		this.matches.add(match);

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
		remotePlayer.respondToChallenge(match, requestToken);
	}

	public void respondToChallenge(String requesterPublicKey, String requestToken, String matchId, boolean response)
			throws Exception {
		throw new Exception();
	}

	public void makeAChoice(String requesterPublicKey, String requestToken, String matchId, Choice choice)
			throws Exception {
		throw new Exception();
	}

}