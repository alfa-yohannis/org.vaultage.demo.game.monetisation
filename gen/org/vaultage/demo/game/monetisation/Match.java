package org.vaultage.demo.game.monetisation;

import java.util.List;
import java.util.UUID;

import org.vaultage.core.Entity;

public class Match  extends Entity {

	private MatchState matchState;
	private String contractAddress;
	private String winner;
	private java.lang.Long stake;
	private java.lang.Long timetamp;
	private String player1Name;
	private String player1pk;
	private String player1address;
	private Choice player1choice;
	private String player2Name;
	private String player2pk;
	private String player2address;
	private Choice player2choice;
	
	// getter
	public MatchState getMatchState() {
		return this.matchState;
	}
	public String getContractAddress() {
		return this.contractAddress;
	}
	public String getWinner() {
		return this.winner;
	}
	public java.lang.Long getStake() {
		return this.stake;
	}
	public java.lang.Long getTimetamp() {
		return this.timetamp;
	}
	public String getPlayer1Name() {
		return this.player1Name;
	}
	public String getPlayer1pk() {
		return this.player1pk;
	}
	public String getPlayer1address() {
		return this.player1address;
	}
	public Choice getPlayer1choice() {
		return this.player1choice;
	}
	public String getPlayer2Name() {
		return this.player2Name;
	}
	public String getPlayer2pk() {
		return this.player2pk;
	}
	public String getPlayer2address() {
		return this.player2address;
	}
	public Choice getPlayer2choice() {
		return this.player2choice;
	}
	
	// setter
	public void setMatchState(MatchState matchState) {
		this.matchState = matchState;
	}
	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public void setStake(java.lang.Long stake) {
		this.stake = stake;
	}
	public void setTimetamp(java.lang.Long timetamp) {
		this.timetamp = timetamp;
	}
	public void setPlayer1Name(String player1Name) {
		this.player1Name = player1Name;
	}
	public void setPlayer1pk(String player1pk) {
		this.player1pk = player1pk;
	}
	public void setPlayer1address(String player1address) {
		this.player1address = player1address;
	}
	public void setPlayer1choice(Choice player1choice) {
		this.player1choice = player1choice;
	}
	public void setPlayer2Name(String player2Name) {
		this.player2Name = player2Name;
	}
	public void setPlayer2pk(String player2pk) {
		this.player2pk = player2pk;
	}
	public void setPlayer2address(String player2address) {
		this.player2address = player2address;
	}
	public void setPlayer2choice(Choice player2choice) {
		this.player2choice = player2choice;
	}
	
	// operations
	
	
	
}