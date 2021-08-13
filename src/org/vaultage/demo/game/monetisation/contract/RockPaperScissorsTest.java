package org.vaultage.demo.game.monetisation.contract;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.vaultage.demo.game.monetisation.Choice;

public class RockPaperScissorsTest {

	private static String ganacheServer = "http://localhost:7545";
	private static String developerAddress = "0x607dF12535e2058b513f6D4e9bA1BC8bfb1EcB65";
	private static String player1PrivateKey = "313468a36422fc25b66ff66614146d9cd8e76db2d87bc27901d3a3f7fcbfbc4a";
	private static String player1Address = "0x056183De3c5Daa1058ddA04F565Cc7d029B5b23a";
	private static String player2Address = "0x5659dFc466c5B682f5D03B7AaC60f7492fE0cce4";
	private static String player2PrivateKey = "cbc278bacd9679e5619daa9d4a916cdba49fff5cef3c5aefba2dd9724a2856f0";
	private static RockPaperScissorsContract contractPlayer1;
	private static RockPaperScissorsContract contractPlayer2;
	private static String contractAddress;
	private static double stake = 0.1;

	@Test
	public void testAll() throws Exception {
		contractPlayer1 = new RockPaperScissorsContract(ganacheServer, player1PrivateKey, player1Address,
				developerAddress, player2Address, stake);
		contractAddress = contractPlayer1.createContract();
		System.out.println("Contract Address: " + contractAddress);
		assertEquals(true, contractAddress.length() > 0);
		
		System.out.println("Contract's balance: " + contractPlayer1.getContractBalance());
		
		contractPlayer2 = new RockPaperScissorsContract(ganacheServer, player2PrivateKey, player1Address,
				developerAddress, player2Address, stake, contractAddress);
		boolean succesful = contractPlayer2.loadContract();
		assertEquals(true, succesful);
		
		System.out.println("Contract's balance: " + contractPlayer2.getContractBalance());

		String transactionId = contractPlayer1.putPlayer1Stake();
		System.out.println("Player 1 Stake's TransactionId: " + transactionId);
		assertEquals(true, transactionId.length() > 0);

		System.out.println("Contract's balance: " + contractPlayer1.getContractBalance());
		
		transactionId = contractPlayer2.putPlayer2Stake();
		System.out.println("Player 2 Stake's TransactionId: " + transactionId);
		assertEquals(true, transactionId.length() > 0);
		
		System.out.println("Contract's balance: " + contractPlayer2.getContractBalance());

		transactionId = contractPlayer1.putPlayer1Choice(Choice.ROCK);
		System.out.println("Player 1 Choice's TransactionId: " + transactionId);
		assertEquals(true, transactionId.length() > 0);
		
		System.out.println("Contract's balance: " + contractPlayer1.getContractBalance());

		transactionId = contractPlayer2.putPlayer2Choice(Choice.PAPER);
		System.out.println("Player 2 Choice's TransactionId: " + transactionId);
		assertEquals(true, transactionId.length() > 0);
		
		System.out.println("Contract's balance: " + contractPlayer1.getContractBalance());
	}

}
