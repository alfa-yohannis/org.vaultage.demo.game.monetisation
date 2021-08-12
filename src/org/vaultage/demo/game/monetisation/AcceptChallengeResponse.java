package org.vaultage.demo.game.monetisation;

import java.util.List;

import org.vaultage.core.Vault;
import org.vaultage.demo.game.monetisation.gui.MatchController;

public class AcceptChallengeResponse extends AcceptChallengeResponseHandler {

	List<MatchController> matchControllers;

	public AcceptChallengeResponse(List<MatchController> matchControllers) {
		this.matchControllers = matchControllers;
	}

	@Override
	public void run(Vault localVault, RemotePlayer remoteVault, String responseToken, Match result) throws Exception {

	}

	@Override
	public void run(Player localVault, RemotePlayer remoteVault, String responseToken, Match result) throws Exception {
		MatchController matchController = matchControllers.stream().filter(mc -> mc.getMatch().getId().equals(result.getId())).findFirst().orElse(null);
		if (matchController != null) {
			matchController.updateLocalMatch(result);
			
			
		}
	}

}
