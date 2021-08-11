package org.vaultage.demo.game.monetisation;

import java.net.InetSocketAddress;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.vaultage.core.Vault;
import org.vaultage.wallet.PaymentInformation;

public abstract class PlayerBase extends Vault {

	public PlayerBase() throws Exception {
		super();
	}
	
	public PlayerBase(String address, int port) throws Exception {
		super(address, port);
	}
	
	protected void initialiseMessageHandlers() throws NoSuchAlgorithmException {
		vaultage.setRequestMessageHandler(new RockpaperscissorsRequestMessageHandler());
		vaultage.setResponseMessageHandler(new RockpaperscissorsResponseMessageHandler());
	}
	
	// operations
	public abstract void challenge(String requesterPublicKey, String requestToken, Match match) throws Exception;
	public abstract void respondToChallenge(String requesterPublicKey, String requestToken, String matchId, boolean response) throws Exception;
	public abstract void makeAChoice(String requesterPublicKey, String requestToken, String matchId, Choice choice) throws Exception;
}
