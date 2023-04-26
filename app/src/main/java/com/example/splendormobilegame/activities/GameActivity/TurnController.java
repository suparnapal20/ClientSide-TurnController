package com.example.splendormobilegame.activities.GameActivity;

import android.util.Log;
import java.util.Scanner;

import com.example.splendormobilegame.Controller;
import com.example.splendormobilegame.CustomAppCompatActivity;
import com.example.splendormobilegame.model.User;
import com.example.splendormobilegame.websocket.ReactionUtils;
import com.example.splendormobilegame.websocket.UserReaction;
import com.github.splendor_mobile_game.websocket.communication.ServerMessage;
import com.github.splendor_mobile_game.websocket.communication.UserMessage;
import com.github.splendor_mobile_game.websocket.handlers.reactions.GetTokens;
import com.github.splendor_mobile_game.websocket.response.ErrorResponse;

public class TurnController<T extends GameActivity> extends Controller {

    private T gameActivity;
    private NewTurnAnnouncementMessageHandler newTurnAnnouncementMessageHandler;
    private PassTurnAnnouncementMessageHandler passTurnAnnouncementMessageHandler;

    protected TurnController(T activity) {
        super(activity);
        this.gameActivity = activity;
        this.newTurnAnnouncementMessageHandler = new NewTurnAnnouncementMessageHandler();
        this.passTurnAnnouncementMessageHandler = new PassTurnAnnouncementMessageHandler();
    }

    public void endTurn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you sure you want to end your turn? (y/n)");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("y")) {
            // End the turn
            currentPlayer = getNextPlayer();
            sendRequestToEndTurn(currentPlayer);
        }
        
        else {
            System.out.println("Turn not ended.");
        }

        this.sendRequestToEndTurn();
    }

    public void passTurn() {
        // Maybe you want to check some things before sending request
        this.sendRequestToPassTurn();
    }

    private void sendRequestToEndTurn() {
        // Construct the message to send to the server
        UserMessage message = new UserMessage("endTurn", null);

        // Send the message to the server using the WebSocket
        ReactionUtils.sendMessage(message, newTurnAnnouncementMessageHandler, activity);
    }

    private void sendRequestToPassTurn() {
        // TODO Compose up the messsage 
        // TODO Send the request 
    }

    public NewTurnAnnouncementMessageHandler getNewTurnAnnouncementMessageHandler() {
        return newTurnAnnouncementMessageHandler;
    }

    public PassTurnAnnouncementMessageHandler getPassTurnAnnouncementMessageHandler() {
        return passTurnAnnouncementMessageHandler;
    }

    public class NewTurnAnnouncementMessageHandler extends UserReaction {

        @Override
        public UserMessage react(ServerMessage serverMessage) {
            Log.i("UserReaction", "Entered NewTurnAnnouncementMessageHandler");

            // Get the data from the request
            // ...

            // TODO Update the model
            // ...

            // TODO Update the view
            // ...
            // this.gameActivity.updateTurnIndicator()
            // if this is your turn
            // this.gameActivity.unblockButtons()
            // else
            // this.gameActivity.blockButtons()
            // ...

            // Return null if you don't want to send anything to the server
            return null;
        }

        @Override
        public UserMessage onFailure(ErrorResponse errorResponse) {
            activity.showToast("NewTurnAnnouncement error: " + errorResponse.data.error);
            return null;
        }

        @Override
        public UserMessage onError(ErrorResponse errorResponse) {
            activity.showToast("NewTurnAnnouncement error: " + errorResponse.data.error);
            return null;
        }

    }

    public class PassTurnAnnouncementMessageHandler extends UserReaction {

        @Override
        public UserMessage react(ServerMessage serverMessage) {
            Log.i("UserReaction", "Entered PassTurnAnnouncementMessageHandler");

            // As this is probably repeated work what is done in the previous message handler
            // You should create some shared private functions for both of these handlers

            // Return null if you don't want to send anything to the server
            return null;
        }

        @Override
        public UserMessage onFailure(ErrorResponse errorResponse) {
            activity.showToast("PassTurnAnnouncement error: " + errorResponse.data.error);
            return null;
        }

        @Override
        public UserMessage onError(ErrorResponse errorResponse) {
            activity.showToast("PassTurnAnnouncement error: " + errorResponse.data.error);
            return null;
        }

    }

}