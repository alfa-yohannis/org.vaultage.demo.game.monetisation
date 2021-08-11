package org.vaultage.demo.game.monetisation.gui;

import java.util.ArrayList;
import java.util.List;

import org.vaultage.core.VaultageServer;
import org.vaultage.demo.game.monetisation.ChallengeResponse;
import org.vaultage.demo.game.monetisation.MakeAChoiceResponse;
import org.vaultage.demo.game.monetisation.Player;
import org.vaultage.demo.game.monetisation.ResponseToChallengeResponse;
import org.vaultage.demo.game.monetisation.RockpaperscissorsBroker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static RockpaperscissorsBroker BROKER;
	public static String BROKER_ADDRESS = "tcp://139.162.228.32:61616";
	public static String LOCAL_PLAYER_ID;
	public static String LOCAL_ACCOUNT_ADDRESS;
	public static String LOCAL_PRIVATE_KEY;
	public static Player LOCAL_VAULT;
	public static List<MatchController> MATCHES = new ArrayList<>();
	public static MainController MAIN_CONTROLLER;

	public static void main(String[] args) throws Exception {

//		BROKER = new RockpaperscissorsBroker();
//		BROKER.start(BROKER_ADDRESS);

		LOCAL_PLAYER_ID = args[0];
		LOCAL_ACCOUNT_ADDRESS = args[1];
		LOCAL_PRIVATE_KEY = args[2];

		VaultageServer vaultageServer = new VaultageServer(BROKER_ADDRESS);
		Player vault = new Player();
		vault.setId(LOCAL_PLAYER_ID);
		vault.setName(LOCAL_PLAYER_ID);
		vault.addOperationResponseHandler(new ResponseToChallengeResponse(MATCHES));
		vault.addOperationResponseHandler(new ChallengeResponse(MATCHES));
		vault.addOperationResponseHandler(new MakeAChoiceResponse(MATCHES));

		System.out.println(vault.getName() + "'s Public Key:");
		System.out.println(vault.getPublicKey());
		System.out.println();

		if (vault.register(vaultageServer)) {
			System.out.println("Connected!");
		}

		LOCAL_VAULT = vault;

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
			MainController mainController = new MainController();
			loader.setController(mainController);
			Main.MAIN_CONTROLLER = mainController;
			BorderPane root = (BorderPane) loader.load(); 
			primaryStage.setTitle(Main.LOCAL_PLAYER_ID + " - " + Main.LOCAL_ACCOUNT_ADDRESS);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() throws Exception {
		LOCAL_VAULT.getVaultage().disconnect();
		LOCAL_VAULT.shutdownServer();
//		BROKER.stop();
		System.out.println("Terminated!");
		System.exit(0);
	}

}
