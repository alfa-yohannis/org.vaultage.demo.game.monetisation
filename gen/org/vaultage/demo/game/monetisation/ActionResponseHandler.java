package org.vaultage.demo.game.monetisation;

import java.util.List;

import org.vaultage.core.OperationResponseHandler;
import org.vaultage.core.Vault;

public abstract class ActionResponseHandler extends OperationResponseHandler {

	/* local vault's type is different from the remote vault's type */
	public abstract void run(Vault localVault, RemotePlayer remoteVault, String responseToken, Match result) throws Exception;
	
	/* local vault has the same type as the remote vault */ 
	public abstract void run(Player localVault, RemotePlayer remoteVault, String responseToken, Match result) throws Exception;
}
