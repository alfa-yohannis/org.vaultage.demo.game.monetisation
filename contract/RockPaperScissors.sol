// SPDX-License-Identifier: MIT
pragma solidity ^0.8.5;

contract RockPaperScissors {
    
    enum Choice { Unknown, Rock, Paper, Scissors }
    
    bool private closed = false;
    address private developer; // developer's address (we send some money also to the developer)
    address private player1; // the creator of the contract
    address private player2; // player that receive challenge
    uint private stake = 0;
    uint private player1stake = 0;
    uint private player2stake = 0;
    Choice private player1choice = Choice.Unknown;
    Choice private player2choice = Choice.Unknown;
    address private winner;

    constructor(address developerAddress, address player2address, uint matchStake){
        player1 = msg.sender;
        developer = developerAddress;
        player2 = player2address;
        stake = matchStake;
    }
    
    function putStake() external payable {
        require(closed == false, "Contract is already closed.");
        require(msg.sender == player1 || msg.sender == player2, "You are not registed for this transaction.");
        require(msg.value == stake, "Stake should be equal to the agreed stake.");
        if (player1 == msg.sender){
            require(player1stake == 0, "You have put your stake in the match.");
            player1stake = msg.value;
        }
        else if (player2 == msg.sender){
            require(player2stake == 0, "You have put your stake in the match.");
            player2stake = msg.value;
        }
    }
    
    function putChoice(Choice choice) external payable {
        require(closed == false, "Contract is already closed.");
        require(msg.sender == player1 || msg.sender == player2, "You are not registed for this transaction.");
        require(uint8(choice)  > uint8(Choice.Unknown) && uint8(choice)  <= uint8(Choice.Scissors), "Your choice is illegal.");
        if (player1 == msg.sender){
            player1choice = choice;
        }
        else if (player2 == msg.sender){
            player2choice = choice;
        }
        
        if (player1choice == Choice.Rock && player2choice == Choice.Paper){
            winner = player2;
        } else if (player1choice == Choice.Rock && player2choice == Choice.Scissors){
            winner = player1;
        } else if (player1choice == Choice.Paper && player2choice == Choice.Rock){
            winner = player1;
        } else if (player1choice == Choice.Paper && player2choice == Choice.Scissors){
            winner = player2;
        } else if (player1choice == Choice.Scissors && player2choice == Choice.Rock){
            winner = player2;
        } else if (player1choice == Choice.Scissors && player2choice == Choice.Paper){
            winner = player1;
        }
        
        if (winner != address(0)){
            uint256 balance = address(this).balance;
            uint256 winnerAmount =  (balance * uint(9)) / uint(10) ;
            uint256 developerAmount =  (balance * uint(1)) / uint(10);
            payable(winner).transfer(winnerAmount);
            payable(developer).transfer(developerAmount);
            closed = true;
        }
    }
  
  function getContractBalance() external view returns (uint) {
        return address(this).balance;
    }
  function isClosed() external view returns (bool) {
        return closed;
    }
  
    function getStake() external view returns (uint) {
        return stake;
    }
    
    function getPlayer1Stake() external view returns (uint) {
        return player1stake;
    }
    
    function getPlayer2Stake() external view returns (uint) {
        return player2stake;
    }
    
    function getPlayer1Choice() external view returns (Choice) {
        return player1choice;
    }
    
    function getPlayer2Choice() external view returns (Choice) {
        return player2choice;
    }
    
    function getWinner() external view returns (address) {
        return winner;
    }
    
    function getDeveloper() external view returns (address) {
        return developer;
    }
    
    function getPlayer1() external view returns (address) {
        return player1;
    }
    
    function getPlayer2() external view returns (address) {
        return player2;
    }
    
    
    
}