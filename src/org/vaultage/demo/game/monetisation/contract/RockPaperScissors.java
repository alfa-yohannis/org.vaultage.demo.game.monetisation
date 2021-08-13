package org.vaultage.demo.game.monetisation.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class RockPaperScissors extends Contract {
    public static final String BINARY = "608060405260008060006101000a81548160ff0219169083151502179055506000600355600060045560006005556000600660006101000a81548160ff0219169083600381111562000056576200005562000257565b5b02179055506000600660016101000a81548160ff0219169083600381111562000084576200008362000257565b5b02179055503480156200009657600080fd5b50604051620018cb380380620018cb8339818101604052810190620000bc9190620001bd565b33600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555082600060016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555081600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600381905550505050620002bf565b600081519050620001a0816200028b565b92915050565b600081519050620001b781620002a5565b92915050565b600080600060608486031215620001d957620001d862000286565b5b6000620001e9868287016200018f565b9350506020620001fc868287016200018f565b92505060406200020f86828701620001a6565b9150509250925092565b600062000226826200022d565b9050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b600080fd5b620002968162000219565b8114620002a257600080fd5b50565b620002b0816200024d565b8114620002bc57600080fd5b50565b6115fc80620002cf6000396000f3fe6080604052600436106100c25760003560e01c80638bbec06d1161007f578063a3b93a3f11610059578063a3b93a3f14610229578063c2b6b58c14610245578063f48ab10614610270578063fc0e3d901461029b576100c2565b80638bbec06d146101c95780638e6e1fc7146101f45780638e7ea5b2146101fe576100c2565b806301ab2a66146100c75780631bb6cb47146100f257806342517eaa1461011d57806344a6f727146101485780636f9fb98a146101735780637a4440721461019e575b600080fd5b3480156100d357600080fd5b506100dc6102c6565b6040516100e991906111bc565b60405180910390f35b3480156100fe57600080fd5b506101076102f0565b60405161011491906111bc565b60405180910390f35b34801561012957600080fd5b5061013261031a565b60405161013f91906112ad565b60405180910390f35b34801561015457600080fd5b5061015d610324565b60405161016a91906111f2565b60405180910390f35b34801561017f57600080fd5b5061018861033b565b60405161019591906112ad565b60405180910390f35b3480156101aa57600080fd5b506101b361035a565b6040516101c091906111bc565b60405180910390f35b3480156101d557600080fd5b506101de610383565b6040516101eb91906112ad565b60405180910390f35b6101fc61038d565b005b34801561020a57600080fd5b5061021361065a565b60405161022091906111bc565b60405180910390f35b610243600480360381019061023e91906110a4565b610684565b005b34801561025157600080fd5b5061025a611058565b60405161026791906111d7565b60405180910390f35b34801561027c57600080fd5b5061028561106e565b60405161029291906111f2565b60405180910390f35b3480156102a757600080fd5b506102b0611085565b6040516102bd91906112ad565b60405180910390f35b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b6000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b6000600554905090565b6000600660019054906101000a900460ff16905090565b60003073ffffffffffffffffffffffffffffffffffffffff1631905090565b60008060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b6000600454905090565b6000151560008054906101000a900460ff161515146103e1576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016103d89061128d565b60405180910390fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16148061048a5750600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16145b6104c9576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016104c09061122d565b60405180910390fd5b600354341461050d576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016105049061120d565b60405180910390fd5b3373ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156105b4576000600454146105a8576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161059f9061124d565b60405180910390fd5b34600481905550610658565b3373ffffffffffffffffffffffffffffffffffffffff16600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156106575760006005541461064f576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106469061124d565b60405180910390fd5b346005819055505b5b565b6000600660029054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b6000151560008054906101000a900460ff161515146106d8576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106cf9061128d565b60405180910390fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614806107815750600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16145b6107c0576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016107b79061122d565b60405180910390fd5b600060038111156107d4576107d361142f565b5b60ff168160038111156107ea576107e961142f565b5b60ff1611801561082457506003808111156108085761080761142f565b5b60ff1681600381111561081e5761081d61142f565b5b60ff1611155b610863576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161085a9061126d565b60405180910390fd5b3373ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156108e85780600660006101000a81548160ff021916908360038111156108de576108dd61142f565b5b021790555061096a565b3373ffffffffffffffffffffffffffffffffffffffff16600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156109695780600660016101000a81548160ff021916908360038111156109635761096261142f565b5b02179055505b5b6001600381111561097e5761097d61142f565b5b600660009054906101000a900460ff1660038111156109a05761099f61142f565b5b1480156109e05750600260038111156109bc576109bb61142f565b5b600660019054906101000a900460ff1660038111156109de576109dd61142f565b5b145b15610a4d57600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16600660026101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610eb9565b60016003811115610a6157610a6061142f565b5b600660009054906101000a900460ff166003811115610a8357610a8261142f565b5b148015610ac25750600380811115610a9e57610a9d61142f565b5b600660019054906101000a900460ff166003811115610ac057610abf61142f565b5b145b15610b2f57600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16600660026101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610eb8565b60026003811115610b4357610b4261142f565b5b600660009054906101000a900460ff166003811115610b6557610b6461142f565b5b148015610ba5575060016003811115610b8157610b8061142f565b5b600660019054906101000a900460ff166003811115610ba357610ba261142f565b5b145b15610c1257600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16600660026101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610eb7565b60026003811115610c2657610c2561142f565b5b600660009054906101000a900460ff166003811115610c4857610c4761142f565b5b148015610c875750600380811115610c6357610c6261142f565b5b600660019054906101000a900460ff166003811115610c8557610c8461142f565b5b145b15610cf457600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16600660026101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610eb6565b600380811115610d0757610d0661142f565b5b600660009054906101000a900460ff166003811115610d2957610d2861142f565b5b148015610d69575060016003811115610d4557610d4461142f565b5b600660019054906101000a900460ff166003811115610d6757610d6661142f565b5b145b15610dd657600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16600660026101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610eb5565b600380811115610de957610de861142f565b5b600660009054906101000a900460ff166003811115610e0b57610e0a61142f565b5b148015610e4b575060026003811115610e2757610e2661142f565b5b600660019054906101000a900460ff166003811115610e4957610e4861142f565b5b145b15610eb457600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16600660026101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b5b5b5b5b5b600073ffffffffffffffffffffffffffffffffffffffff16600660029054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16146110555760003073ffffffffffffffffffffffffffffffffffffffff163190506000600a600983610f3c919061130a565b610f4691906112d9565b90506000600a600184610f59919061130a565b610f6391906112d9565b9050600660029054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc839081150290604051600060405180830381858888f19350505050158015610fcd573d6000803e3d6000fd5b50600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc829081150290604051600060405180830381858888f19350505050158015611036573d6000803e3d6000fd5b5060016000806101000a81548160ff0219169083151502179055505050505b50565b60008060009054906101000a900460ff16905090565b6000600660009054906101000a900460ff16905090565b6000600354905090565b60008135905061109e816115b6565b92915050565b6000602082840312156110ba576110b961145e565b5b60006110c88482850161108f565b91505092915050565b6110da81611364565b82525050565b6110e981611376565b82525050565b6110f8816113bf565b82525050565b600061110b602a836112c8565b915061111682611463565b604082019050919050565b600061112e602a836112c8565b9150611139826114b2565b604082019050919050565b60006111516025836112c8565b915061115c82611501565b604082019050919050565b60006111746017836112c8565b915061117f82611550565b602082019050919050565b6000611197601b836112c8565b91506111a282611579565b602082019050919050565b6111b6816113b5565b82525050565b60006020820190506111d160008301846110d1565b92915050565b60006020820190506111ec60008301846110e0565b92915050565b600060208201905061120760008301846110ef565b92915050565b60006020820190508181036000830152611226816110fe565b9050919050565b6000602082019050818103600083015261124681611121565b9050919050565b6000602082019050818103600083015261126681611144565b9050919050565b6000602082019050818103600083015261128681611167565b9050919050565b600060208201905081810360008301526112a68161118a565b9050919050565b60006020820190506112c260008301846111ad565b92915050565b600082825260208201905092915050565b60006112e4826113b5565b91506112ef836113b5565b9250826112ff576112fe611400565b5b828204905092915050565b6000611315826113b5565b9150611320836113b5565b9250817fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff0483118215151615611359576113586113d1565b5b828202905092915050565b600061136f82611395565b9050919050565b60008115159050919050565b6000819050611390826115a2565b919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b60006113ca82611382565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b600080fd5b7f5374616b652073686f756c6420626520657175616c20746f207468652061677260008201527f656564207374616b652e00000000000000000000000000000000000000000000602082015250565b7f596f7520617265206e6f7420726567697374656420666f72207468697320747260008201527f616e73616374696f6e2e00000000000000000000000000000000000000000000602082015250565b7f596f7520686176652070757420796f7572207374616b6520696e20746865206d60008201527f617463682e000000000000000000000000000000000000000000000000000000602082015250565b7f596f75722063686f69636520697320696c6c6567616c2e000000000000000000600082015250565b7f436f6e747261637420697320616c726561647920636c6f7365642e0000000000600082015250565b600481106115b3576115b261142f565b5b50565b600481106115c357600080fd5b5056fea2646970667358221220c41d755dbbda4040721d3754ad5567ced9f8c5206ceaf6409399d21deeba5fdb64736f6c63430008050033";

    public static final String FUNC_GETCONTRACTBALANCE = "getContractBalance";

    public static final String FUNC_GETDEVELOPER = "getDeveloper";

    public static final String FUNC_GETPLAYER1 = "getPlayer1";

    public static final String FUNC_GETPLAYER1CHOICE = "getPlayer1Choice";

    public static final String FUNC_GETPLAYER1STAKE = "getPlayer1Stake";

    public static final String FUNC_GETPLAYER2 = "getPlayer2";

    public static final String FUNC_GETPLAYER2CHOICE = "getPlayer2Choice";

    public static final String FUNC_GETPLAYER2STAKE = "getPlayer2Stake";

    public static final String FUNC_GETSTAKE = "getStake";

    public static final String FUNC_GETWINNER = "getWinner";

    public static final String FUNC_ISCLOSED = "isClosed";

    public static final String FUNC_PUTCHOICE = "putChoice";

    public static final String FUNC_PUTSTAKE = "putStake";

    @Deprecated
    protected RockPaperScissors(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected RockPaperScissors(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected RockPaperScissors(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected RockPaperScissors(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<BigInteger> getContractBalance() {
        final Function function = new Function(FUNC_GETCONTRACTBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getDeveloper() {
        final Function function = new Function(FUNC_GETDEVELOPER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getPlayer1() {
        final Function function = new Function(FUNC_GETPLAYER1, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getPlayer1Choice() {
        final Function function = new Function(FUNC_GETPLAYER1CHOICE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getPlayer1Stake() {
        final Function function = new Function(FUNC_GETPLAYER1STAKE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getPlayer2() {
        final Function function = new Function(FUNC_GETPLAYER2, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getPlayer2Choice() {
        final Function function = new Function(FUNC_GETPLAYER2CHOICE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getPlayer2Stake() {
        final Function function = new Function(FUNC_GETPLAYER2STAKE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getStake() {
        final Function function = new Function(FUNC_GETSTAKE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getWinner() {
        final Function function = new Function(FUNC_GETWINNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> isClosed() {
        final Function function = new Function(FUNC_ISCLOSED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> putChoice(BigInteger choice) {
        final Function function = new Function(
                FUNC_PUTCHOICE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(choice)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> putStake() {
        final Function function = new Function(
                FUNC_PUTSTAKE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static RockPaperScissors load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new RockPaperScissors(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static RockPaperScissors load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new RockPaperScissors(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RockPaperScissors load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new RockPaperScissors(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static RockPaperScissors load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new RockPaperScissors(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<RockPaperScissors> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String developerAddress, String player2address, BigInteger matchStake) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, developerAddress), 
                new org.web3j.abi.datatypes.Address(160, player2address), 
                new org.web3j.abi.datatypes.generated.Uint256(matchStake)));
        return deployRemoteCall(RockPaperScissors.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<RockPaperScissors> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String developerAddress, String player2address, BigInteger matchStake) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, developerAddress), 
                new org.web3j.abi.datatypes.Address(160, player2address), 
                new org.web3j.abi.datatypes.generated.Uint256(matchStake)));
        return deployRemoteCall(RockPaperScissors.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<RockPaperScissors> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String developerAddress, String player2address, BigInteger matchStake) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, developerAddress), 
                new org.web3j.abi.datatypes.Address(160, player2address), 
                new org.web3j.abi.datatypes.generated.Uint256(matchStake)));
        return deployRemoteCall(RockPaperScissors.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<RockPaperScissors> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String developerAddress, String player2address, BigInteger matchStake) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, developerAddress), 
                new org.web3j.abi.datatypes.Address(160, player2address), 
                new org.web3j.abi.datatypes.generated.Uint256(matchStake)));
        return deployRemoteCall(RockPaperScissors.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
