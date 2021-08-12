package org.vaultage.demo.game.monetisation;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.util.List;

import org.vaultage.core.BytesToOutputTypeConverter;
import org.vaultage.core.OnStreamingFinishedHandler;
import org.vaultage.core.RemoteVault;
import org.vaultage.core.StreamReceiver;
import org.vaultage.core.Streamer;
import org.vaultage.core.Vault;
import org.vaultage.core.Vaultage;
import org.vaultage.core.VaultageMessage;
import org.vaultage.core.VaultageMessage.MessageType;
import org.vaultage.wallet.PaymentInformation;
import org.vaultage.wallet.Wallet;

public class RemotePlayer extends RemoteVault {

	//protected Player localVault;
	
	public RemotePlayer() {
		super(null, null);
	}
	
	//public RemotePlayer(Player localVault, String remotePublicKey) {
		public RemotePlayer(Vault localVault, String remotePublicKey) {
			super(localVault, remotePublicKey);
		}
		
		public RemotePlayer(Vault localVault, String remotePublicKey, InetSocketAddress receiverSocketAddress) {
			super(localVault, remotePublicKey, receiverSocketAddress);
		}
		
		public String sendChallenge(Match match) throws Exception {
			return this.sendChallenge(match, true);
		}
		public String sendChallenge(Match match, boolean isEncrypted) throws Exception {
			VaultageMessage request = new VaultageMessage();
			request.initToken();
			request.setSenderId(localVault.getId());
			request.setFrom(localVault.getPublicKey());
			request.setTo(remotePublicKey);
			request.setMessageType(MessageType.REQUEST);
			request.setOperation("sendChallenge");
			
			request.putValue("match", Vaultage.serialise(match));
			
			
			localVault.getVaultage().sendMessage(request.getTo(), localVault.getPublicKey(),
			localVault.getPrivateKey(), request, isEncrypted);
			
			return request.getToken();
		}
		
		
		public void respondToSendChallenge(Match result, String token) throws Exception {
			this.respondToSendChallenge(result, token, true);
		}
		
		public void respondToSendChallenge(Match result, String token, boolean isEncrypted) throws Exception {
		
			VaultageMessage response = new VaultageMessage();
			response.setToken(token); // TODO: this token is not known by the vault
			response.setFrom(localVault.getPublicKey());
			response.setTo(remotePublicKey);
			response.setOperation("sendChallenge");
			response.setMessageType(MessageType.RESPONSE);
			response.setRemoteVaultType(this.getClass().getName());
			
			Method m = new Object(){}.getClass().getEnclosingMethod();
			Type x = m.getGenericParameterTypes()[0];
			String returnType = x.getTypeName();
			response.setReturnType(returnType);
			
			response.putValue("result", Vaultage.serialise(result));
			
			localVault.getVaultage().sendMessage(response.getTo(), localVault.getPublicKey(),
			localVault.getPrivateKey(), response, isEncrypted);
		}
		
		public String acceptChallenge(String matchId, boolean response) throws Exception {
			return this.acceptChallenge(matchId, response, true);
		}
		public String acceptChallenge(String matchId, boolean response, boolean isEncrypted) throws Exception {
			VaultageMessage request = new VaultageMessage();
			request.initToken();
			request.setSenderId(localVault.getId());
			request.setFrom(localVault.getPublicKey());
			request.setTo(remotePublicKey);
			request.setMessageType(MessageType.REQUEST);
			request.setOperation("acceptChallenge");
			
			request.putValue("matchId", Vaultage.serialise(matchId));
			request.putValue("response", Vaultage.serialise(response));
			
			
			localVault.getVaultage().sendMessage(request.getTo(), localVault.getPublicKey(),
			localVault.getPrivateKey(), request, isEncrypted);
			
			return request.getToken();
		}
		
		
		public void respondToAcceptChallenge(Match result, String token) throws Exception {
			this.respondToAcceptChallenge(result, token, true);
		}
		
		public void respondToAcceptChallenge(Match result, String token, boolean isEncrypted) throws Exception {
		
			VaultageMessage response = new VaultageMessage();
			response.setToken(token); // TODO: this token is not known by the vault
			response.setFrom(localVault.getPublicKey());
			response.setTo(remotePublicKey);
			response.setOperation("acceptChallenge");
			response.setMessageType(MessageType.RESPONSE);
			response.setRemoteVaultType(this.getClass().getName());
			
			Method m = new Object(){}.getClass().getEnclosingMethod();
			Type x = m.getGenericParameterTypes()[0];
			String returnType = x.getTypeName();
			response.setReturnType(returnType);
			
			response.putValue("result", Vaultage.serialise(result));
			
			localVault.getVaultage().sendMessage(response.getTo(), localVault.getPublicKey(),
			localVault.getPrivateKey(), response, isEncrypted);
		}
		
		public String makeAChoice(String matchId, Choice choice) throws Exception {
			return this.makeAChoice(matchId, choice, true);
		}
		public String makeAChoice(String matchId, Choice choice, boolean isEncrypted) throws Exception {
			VaultageMessage request = new VaultageMessage();
			request.initToken();
			request.setSenderId(localVault.getId());
			request.setFrom(localVault.getPublicKey());
			request.setTo(remotePublicKey);
			request.setMessageType(MessageType.REQUEST);
			request.setOperation("makeAChoice");
			
			request.putValue("matchId", Vaultage.serialise(matchId));
			request.putValue("choice", Vaultage.serialise(choice));
			
			
			localVault.getVaultage().sendMessage(request.getTo(), localVault.getPublicKey(),
			localVault.getPrivateKey(), request, isEncrypted);
			
			return request.getToken();
		}
		
		
		public void respondToMakeAChoice(Match result, String token) throws Exception {
			this.respondToMakeAChoice(result, token, true);
		}
		
		public void respondToMakeAChoice(Match result, String token, boolean isEncrypted) throws Exception {
		
			VaultageMessage response = new VaultageMessage();
			response.setToken(token); // TODO: this token is not known by the vault
			response.setFrom(localVault.getPublicKey());
			response.setTo(remotePublicKey);
			response.setOperation("makeAChoice");
			response.setMessageType(MessageType.RESPONSE);
			response.setRemoteVaultType(this.getClass().getName());
			
			Method m = new Object(){}.getClass().getEnclosingMethod();
			Type x = m.getGenericParameterTypes()[0];
			String returnType = x.getTypeName();
			response.setReturnType(returnType);
			
			response.putValue("result", Vaultage.serialise(result));
			
			localVault.getVaultage().sendMessage(response.getTo(), localVault.getPublicKey(),
			localVault.getPrivateKey(), response, isEncrypted);
		}
		
		
	}
