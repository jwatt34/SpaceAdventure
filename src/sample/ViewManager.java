package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class ViewManager {
    private AnchorPane anchorPane;
    private Scene scene;
    private Stage stage;
    private static int width = 1024;
    private static int height = 768;
    private final static int menuButtonX = 100;
    private final static int menuButtonY = 350;
    private SpaceRunnerSubScene shipChooserScene;
    private SpaceRunnerSubScene exitSubScene;
    List<SpaceRunnerButton> menuButtons;
    List<ShipSelector> shipSelectorList;
    private Ship chosenShip;

    public ViewManager(){
        menuButtons = new ArrayList<>();
        anchorPane = new AnchorPane();
        scene = new Scene(anchorPane, width,height);
        stage = new Stage();
        stage.setScene(scene);
        createButton();
        createBackground();
        createLogo();
        createSubScene();
    }

    private void createSubScene(){
        exitSubScene = new SpaceRunnerSubScene();
        anchorPane.getChildren().add(exitSubScene);
        createShipChooserScene();
    }

    private void createShipChooserScene() {
        shipChooserScene = new SpaceRunnerSubScene();
        InfoLabel infoLabel = new InfoLabel("CHOOSE YOUR SHIP");
        infoLabel.setLayoutX(78);
        infoLabel.setLayoutY(30);
        shipChooserScene.getPane().getChildren().add(infoLabel);
        shipChooserScene.getPane().getChildren().add(createShipToChoose());
        shipChooserScene.getPane().getChildren().add(createShipStartButton());
        anchorPane.getChildren().add(shipChooserScene);
    }

    private HBox createShipToChoose(){
        HBox hBox = new HBox(20);
        shipSelectorList = new ArrayList<>();
        for(Ship ship : Ship.values()){
            ShipSelector shipSelector = new ShipSelector(ship);
            shipSelectorList.add(shipSelector);
            hBox.getChildren().add(shipSelector);
            shipSelector.setOnMouseClicked(new EventHandler<MouseEvent>(){

                @Override
                public void handle(MouseEvent event) {
                    for(ShipSelector ship: shipSelectorList){
                        ship.setChosen(false);//sets all other ships to not chosen
                    }
                    shipSelector.setChosen(true);//sets chosen ship to true
                    chosenShip = shipSelector.getShip();
                }
            });
        }
        hBox.setLayoutX(33);
        hBox.setLayoutY(100);
        return hBox;
    }

    private SpaceRunnerButton createShipStartButton (){
        SpaceRunnerButton startButton = new SpaceRunnerButton("START");
        startButton.setLayoutX(180);
        startButton.setLayoutY(300);
        startButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(chosenShip != null){
                    GameViewManager gameViewManager = new GameViewManager();
                    gameViewManager.createNewGame(stage, chosenShip);
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("WARNING");
                    alert.setHeaderText(null);
                    alert.setContentText("You're going to need a ship!");
                    alert.showAndWait();

                }
            }
        });
        return startButton;
    }

    public Stage getStage(){
        return stage;
    }
    private void addMenuButtons(SpaceRunnerButton button){          //positions menu buttons
        button.setLayoutX(menuButtonX);
        button.setLayoutY(menuButtonY+menuButtons.size()*75);
        menuButtons.add(button);
        anchorPane.getChildren().add(button);
    }
    private void createButton(){
        createStartButton();
        createExitButton();
    }

    private void createStartButton(){
        SpaceRunnerButton startButton = new SpaceRunnerButton("PLAY");
        addMenuButtons(startButton);
        startButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                shipChooserScene.moveSubScene();
            }
        });
    }


    private void createExitButton(){
        SpaceRunnerButton exitButton = new SpaceRunnerButton("EXIT");
        addMenuButtons(exitButton);
        exitButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                stage.close();
            }
        });

    }

    private void createBackground(){
        Image backgroundImage = new Image("Resources2/purple.png",256,256,false,true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        anchorPane.setBackground(new Background(background));
    }

    private void createLogo(){                     //creates game title
        ImageView logo = new ImageView("Resources2/cooltext387205527709640.png");
        logo.setLayoutX(230);
        logo.setLayoutY(50);
        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(new DropShadow());
            }
        });
        logo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(null);
            }
        });
        anchorPane.getChildren().add(logo);
    }
}
