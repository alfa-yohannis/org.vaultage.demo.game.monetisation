package org.vaultage.demo.game.monetisation.contract;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.vaultage.demo.game.monetisation.Choice;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;

public class RockPaperScissorsContract {

	String privateKey;
	Web3j web3j;
	Credentials creds;
	HttpService httpService;
	String developerAddress;
	String player1Address;
	String player2Address;
	BigInteger stake;
	ContractGasProvider contractGasProvider = new CustomGasProvider();
	String contractAddress = null;
	private RockPaperScissors contract;

	public RockPaperScissorsContract(String ganacheServer, String privateKey, String player1Address,
			String developerAddress, String player2Address, double stake) {
		this(ganacheServer, privateKey, player1Address, developerAddress, player2Address, stake, null);
	}

	public RockPaperScissorsContract(String ganacheServer, String privateKey, String player1Address,
			String developerAddress, String player2Address, double stake, String contractAddress) {
		this.privateKey = privateKey;
		this.httpService = new HttpService(ganacheServer);
		this.web3j = Web3j.build(httpService);
		this.creds = Credentials.create(privateKey);
		this.player1Address = player1Address;
		this.player2Address = player2Address;
		this.developerAddress = developerAddress;
		this.stake = Convert.toWei(BigDecimal.valueOf(stake), Convert.Unit.ETHER).toBigInteger();
		this.contractAddress = contractAddress;
	}

	public String createContract() throws Exception {
		contract = RockPaperScissors.deploy(web3j, creds, contractGasProvider, developerAddress, player2Address, stake)
				.send();
		contractAddress = contract.getContractAddress();
		return contractAddress;
	}

	public boolean loadContract() {
		contract = RockPaperScissors.load(contractAddress, web3j, creds, contractGasProvider);
		return (contract != null) ? true : false;
	}

	public String putPlayer1Stake() throws Exception {
		return this.putStake(player1Address);
	}

	public String putPlayer2Stake() throws Exception {
		return this.putStake(player2Address);
	}

	private String putStake(String playerAddress) throws Exception {
		EthGetTransactionCount ethGetTransactionCount = web3j
				.ethGetTransactionCount(playerAddress, DefaultBlockParameterName.LATEST).send();
		BigInteger nonce = ethGetTransactionCount.getTransactionCount();
		Function function = new Function("putStake", Collections.emptyList(), Collections.emptyList());
		String encodedFunction = FunctionEncoder.encode(function);
//		
		Transaction transaction = Transaction.createFunctionCallTransaction(playerAddress, nonce, nonce,
				contractGasProvider.getGasLimit(), contractAddress, this.stake, encodedFunction);
		EthSendTransaction transactionResponse = web3j.ethSendTransaction(transaction).send();
		String transactionHash = transactionResponse.getTransactionHash();
		return transactionHash;
	}

	public String putPlayer1Choice(Choice choice) throws Exception {
		return this.putChoice(player1Address, choice);
	}

	public String putPlayer2Choice(Choice choice) throws Exception {
		return this.putChoice(player2Address, choice);
	}

	private String putChoice(String playerAddress, Choice choice) throws Exception {
		EthGetTransactionCount ethGetTransactionCount = web3j
				.ethGetTransactionCount(playerAddress, DefaultBlockParameterName.LATEST).send();
		BigInteger nonce = ethGetTransactionCount.getTransactionCount();

		List<Type> parameters = new ArrayList<Type>();
		int temp = choice.ordinal();
		Uint8 choiceVal = new Uint8(temp);
		parameters.add(choiceVal);

		Function function = new Function("putChoice", parameters, Collections.emptyList());
		String encodedFunction = FunctionEncoder.encode(function);
//		
		Transaction transaction = Transaction.createFunctionCallTransaction(playerAddress, nonce, nonce,
				contractGasProvider.getGasLimit(), contractAddress, BigInteger.valueOf(0), encodedFunction);
		EthSendTransaction transactionResponse = web3j.ethSendTransaction(transaction).send();
		String transactionHash = transactionResponse.getTransactionHash();
		return transactionHash;
	}

	public double getContractBalance() throws Exception {
		List<TypeReference<?>> returnTypes = Arrays.asList(new TypeReference<Uint256>() {
		});
		Function function = new Function("getContractBalance", Collections.emptyList(), returnTypes);
		String encodedFunction = FunctionEncoder.encode(function);
		
		Transaction transaction = Transaction.createEthCallTransaction(player1Address, contractAddress,
				encodedFunction);
		EthCall ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();

		String result = null;
		if (ethCall.getError() != null) {
			result = ethCall.getError().getMessage();
			throw new Exception(result);
		} else {
			List<Type> someTypes = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
			result = Convert.fromWei(((Uint256) someTypes.get(0)).getValue().toString(), Convert.Unit.ETHER).toString();
		}
		return Double.parseDouble(result);
	}

}
