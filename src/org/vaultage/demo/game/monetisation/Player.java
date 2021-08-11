package org.vaultage.demo.game.monetisation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.vaultage.wallet.PaymentInformation;

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
	
	public void challenge(String requesterPublicKey, String requestToken, String friendName) throws Exception {
		throw new Exception();
	}
	
	
	public void respondToChallenge(String requesterPublicKey, String requestToken, String matchId, boolean response) throws Exception {
		throw new Exception();
	}
	
	
	public void makeAChoice(String requesterPublicKey, String requestToken, String matchId, Choice choice) throws Exception {
		throw new Exception();
	}
	
	
}