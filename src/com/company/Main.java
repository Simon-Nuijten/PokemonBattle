package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javafx.scene.media.Media;

import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.sound.sampled.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class Main extends Application{
    private Pokemon onix;
    private Pokemon pikachu;
    private Label info;
    private ImageView battleAttack;
    private Label pikachuHealth;
    private Label onixHealth;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        BorderPane borderPane = new BorderPane();


//        String musicFile = "battle.mp3";
//        Media sound = new Media(new File(musicFile).toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(sound);
//        mediaPlayer.setVolume(0.1);
//        mediaPlayer.play();

        Image blast = new Image("blast.gif");
        Image wave = new Image("shockwave.gif");
        Image pikachuImage = new Image("Pikachu.png");
        Image onixImage = new Image("Onix.png");

        info = new Label();

        ImageView pikachuView = new ImageView();
        pikachuView.setImage(pikachuImage);
        pikachuView.setFitHeight(150);
        pikachuView.setFitWidth(150);
        battleAttack = new ImageView();
        battleAttack.setFitHeight(100);
        battleAttack.setFitWidth(200);
        ImageView onixView = new ImageView();
        onixView.setImage(onixImage);
        onixView.setFitHeight(150);
        onixView.setFitWidth(150);

        BorderPane battlePane = new BorderPane();

        pikachu = new Pokemon("Pikachu", 21);
        onix = new Pokemon("Onix", 21);

        Move shockblast = new Move("Shockblast", 10, "Electric");
        pikachu.addMove(shockblast);

        Move shockwave = new Move("Shockwave", 15, "Electric");
        pikachu.addMove(shockwave);

        Move posion = new Move("Posion", 10, "Rock");
        onix.addMove(posion);
        posion.setUrl("posion.gif");

        Move smash = new Move("Rock smash", 17, "Rock");
        onix.addMove(smash);
        smash.setUrl("rocksmash.gif");

        pikachuHealth = new Label("" + pikachu.getHealth());
        onixHealth = new Label("" + onix.getHealth());


        Battle battle = new Battle(pikachu, onix);

        HBox health = new HBox(pikachuHealth, onixHealth);
        health.setSpacing(750);
        styleHBox(health);

        Button attack1 = new Button("Shockwave");
        Button attack2 = new Button("Shockblast");

        styleButton(attack1);
        styleButton(attack2);
        styleLabel(info);
        attack1.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                battleAttack.setImage(wave);
                onix.setHealth(onix.getHealth() - shockwave.getPower());
                info.setText("Pikachu did Shockwave");
                attack1.setVisible(false);
                attack2.setVisible(false);
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(() -> {
                                    battleAttack.setImage(null);
                                    info.setText(getHealth(onix));
                                    setHealth();
                                    if(checkHealth()){
                                        showMessageDialog(null, "Battle is over.... ");
                                        primaryStage.close();
                                    }
                                    new java.util.Timer().schedule(
                                            new java.util.TimerTask() {
                                                @Override
                                                public void run() {
                                                    Platform.runLater(() -> {
                                                        onixMove();
                                                        attack1.setVisible(true);
                                                        attack2.setVisible(true);
                                                    });
                                                }
                                            },
                                            2000
                                    );
                                });
                            }
                        },
                        2000
                );
            }
        });

        attack2.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                battleAttack.setImage(blast);
                onix.setHealth(onix.getHealth() - shockblast.getPower());
                info.setText("Pikachu did shockblast");
                attack1.setVisible(false);
                attack2.setVisible(false);
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(() -> {
                                    battleAttack.setImage(null);
                                    info.setText(getHealth(onix));
                                    setHealth();
                                    checkHealth();
                                    if(checkHealth()){
                                        showMessageDialog(null, "Battle is over.... ");
                                        primaryStage.close();
                                    }
                                    new java.util.Timer().schedule(
                                            new java.util.TimerTask() {
                                                @Override
                                                public void run() {
                                                    Platform.runLater(() -> {
                                                        onixMove();
                                                        attack1.setVisible(true);
                                                        attack2.setVisible(true);
                                                    });
                                                }
                                            },
                                            3000
                                    );
                                });
                            }
                        },
                        2000
                );
            }
        });
        VBox AttacksInfo = new VBox();
        StackPane root = new StackPane();
        HBox hBox = new HBox(attack1, attack2);
        BorderPane attacksPane = new BorderPane(hBox);
        HBox Background = new HBox();


        Background.getChildren().addAll(pikachuView, battleAttack ,onixView);
        HBox Attacks = new HBox(hBox);
        HBox labelInfo = new HBox(info);
        info.setText("");
        VBox pane = new VBox(Background);
        Background.setSpacing(100);


        AttacksInfo.getChildren().addAll(Attacks, labelInfo);

        borderPane.setStyle("-fx-background-image: url(\"background.png\");");
        borderPane.setTop(health);
        borderPane.setLeft(pane);
        borderPane.setBottom(AttacksInfo);
        Scene scene = new Scene(borderPane, 800, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private String getHealth(Pokemon pokemon){
        return pokemon.getName() + " is down to " + pokemon.getHealth() + "hp...";
    }
    private void styleLabel(Label label) {
        label.setStyle("-fx-border-color: black; -fx-background-color: white;");
        label.setMinWidth(200);
        label.setMinHeight(25);
    }
    private void styleHBox(HBox HBox) {
        HBox.setStyle("-fx-border-color: black; -fx-background-color: white;");
        HBox.setMinWidth(200);
        HBox.setMinHeight(25);
    }
    private void styleButton(Button b) {
        b.setMinWidth(130);
        b.setFont(Font.font("Consolas", FontWeight.BOLD, 15));
        b.setStyle("-fx-border-width: 8");
        b.setStyle("-fx-border-color: red");
    }
    public void setHealth(){
        pikachuHealth.setText("" + pikachu.getHealth());
        onixHealth.setText("" + onix.getHealth());
    }

    private void onixMove(){
        ArrayList<Move> moves = onix.getMoves();
        Random rand = new Random();
        int upperbound = 2;
        int int_random = rand.nextInt(upperbound);
        Move chosenMove = moves.get(int_random);

        pikachu.setHealth(pikachu.getHealth() - chosenMove.getPower());
        info.setText("Onix did " + chosenMove.getName() + "....");
        Image move = new Image(chosenMove.getMoveUrl());
        battleAttack.setImage(move);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            battleAttack.setImage(null);
                            info.setText(getHealth(pikachu));
                            setHealth();
                            if(checkHealth()){
                                showMessageDialog(null, "Battle is over.... ");
                            }
                        });
                    }
                },
                2000
        );
    }
    private boolean checkHealth(){
        if(pikachu.getHealth() == 0){
            return true;
        }
        if(onix.getHealth() == 0){
            return true;
        } else {
            return false;
        }
    }
}
