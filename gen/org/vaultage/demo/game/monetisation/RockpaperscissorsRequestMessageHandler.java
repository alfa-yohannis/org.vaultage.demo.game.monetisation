package org.vaultage.demo.game.monetisation;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.vaultage.core.RequestMessageHandler;
import org.vaultage.core.Vaultage;
import org.vaultage.core.VaultageMessage;

public class RockpaperscissorsRequestMessageHandler extends RequestMessageHandler {

	@Override
	public void process(VaultageMessage message, String senderPublicKey, Object vault) throws Exception {
	
		super.process(message, senderPublicKey, vault);
		if (vault instanceof Player){
			Player player = (Player) vault;
			// Player requester = new Player();
			// requester.setPublicKey(senderPublicKey);
			String requester = senderPublicKey;
			String operation = message.getOperation();
			String messageToken = message.getToken();
			switch (operation) {
			
				case "sendChallenge": {
					Match match = Vaultage.deserialise(message.getValue("match"), Match.class);
					player.sendChallenge(requester, messageToken, match);
				}
				break;
				
				case "acceptChallenge": {
					String matchId = Vaultage.deserialise(message.getValue("matchId"), String.class);
					boolean response = Vaultage.deserialise(message.getValue("response"), boolean.class);
					player.acceptChallenge(requester, messageToken, matchId, response);
				}
				break;
				
				case "makeAChoice": {
					String matchId = Vaultage.deserialise(message.getValue("matchId"), String.class);
					Choice choice = Vaultage.deserialise(message.getValue("choice"), Choice.class);
					player.makeAChoice(requester, messageToken, matchId, choice);
				}
				break;
				default:
				//throw new Exception("Operation " + operation + " is not supported!");
			}
		}
	}
}
