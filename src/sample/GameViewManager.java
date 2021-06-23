package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Random;

public class GameViewManager {
    private Stage gameStage;
    private Scene gameScene;
    private AnchorPane gamePane;
    private static double stageWidth = 600;
    private static double stageHeight = 600;
    private boolean isLeftPressed;
    private boolean isRightPressed;
    private int angle;
    private AnimationTimer gameTimer;
    private Stage menuStage;
    private ImageView ship;
    private GridPane gridPane;
    private GridPane gridPane2;
    private final static String backgroundPath = "Resources2/purple.png";
    private final static String brownMeteorPath = "Resources2/meteorBrown_small1.png";
    private final static String greyMeteorPath = "Resources2/meteorGrey_small1.png";
    private ImageView [] brownMeteor;
    private ImageView [] greyMeteor;
    private ImageView star;
    private ScoreLabel scoreLabel;
    private ImageView [] playerLives;
    private int life;
    private int points;
    private final static String starPath = "Resources2/star_gold.png";
    Random randomPosition;
    private final static int starRadius = 17;
    private final static int shipRadius = 27;
    private final static int meteorRadius = 20;

    GameViewManager(){
        InitializeStage();
        createKeyListeners();
        randomPosition = new Random();
    }

    private void createKeyListeners() {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.LEFT){
                    isLeftPressed = true;

                } else if (event.getCode() == KeyCode.RIGHT){
                    isRightPressed = true;

                }
            }
        });
        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.LEFT){
                    isLeftPressed = false;

                } else if (event.getCode() == KeyCode.RIGHT){
                    isRightPressed = false;

                }
            }
        });
    }

    private void InitializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, stageWidth, stageHeight);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    public void createNewGame(Stage menuStage, Ship chosenShip){ //opens gameStage and hides the menu
        this.menuStage = menuStage;
        this.menuStage.hide();
        createBackground();
        addShip(chosenShip);
        createGameLoop();
        createSceneElements(chosenShip);
        gameStage.show();
    }

    private void createSceneElements(Ship chosenShip){      //add elements to gamePane
        life = 2;
        star = new ImageView(starPath);
        setElementPosition(star);
        scoreLabel = new ScoreLabel("POINTS :"+points);
        scoreLabel.setLayoutX(460);
        scoreLabel.setLayoutY(30);
        gamePane.getChildren().addAll(star, scoreLabel);
        playerLives = new ImageView[3];
        for(int i = 0; i < playerLives.length; i++){
            playerLives[i] = new ImageView(chosenShip.getUrlLife());
            playerLives[i].setLayoutX(455 + i * 50);
            playerLives[i].setLayoutY(90);
            gamePane.getChildren().add(playerLives[i]);
        }
        brownMeteor = new ImageView[5];
        for(int i = 0; i < brownMeteor.length; i++){
            brownMeteor[i] = new ImageView(brownMeteorPath);
            setElementPosition(brownMeteor[i]);
            gamePane.getChildren().add(brownMeteor[i]);
        }
        greyMeteor = new ImageView[5];
        for(int i = 0; i < greyMeteor.length; i++){
            greyMeteor[i] = new ImageView(greyMeteorPath);
            setElementPosition(greyMeteor[i]);
            gamePane.getChildren().add(greyMeteor[i]);
        }
    }

    private void setElementPosition(ImageView image){   //creates starting position for falling elements
        image.setLayoutX(randomPosition.nextInt(600));
        image.setLayoutY(-randomPosition.nextInt(2000));

    }

    private void moveElements(){                        //moves elements down screen and controls speed
        star.setLayoutY(star.getLayoutY()+5);
        for(int i = 0; i < brownMeteor.length; i++){
            brownMeteor[i].setLayoutY(brownMeteor[i].getLayoutY()+9);
            brownMeteor[i].setRotate(brownMeteor[i].getRotate()+4);
        }
        for(int i = 0; i < greyMeteor.length; i++){
            greyMeteor[i].setLayoutY(greyMeteor[i].getLayoutY()+4);
            greyMeteor[i].setRotate(greyMeteor[i].getRotate()-5);
        }
    }

    private void checkPosition(){                       //checks if element has fallen below the scene resets position
        for(int i = 0; i < brownMeteor.length; i++){
            if(brownMeteor[i].getLayoutY() > 1000){
                setElementPosition(brownMeteor[i]);
            }
        }
        for(int i = 0; i < greyMeteor.length; i++){
            if(greyMeteor[i].getLayoutY() > 1000){
                setElementPosition(greyMeteor[i]);
            }
        }
        if(star.getLayoutY() > 1200){
            setElementPosition(star);
        }
    }

    public void addShip(Ship chosenShip){                   //positions ship on screen
        ship = new ImageView(chosenShip.getUrlShip());
        ship.setLayoutX(stageWidth/2 - 40);
        ship.setLayoutY(stageHeight-100);
        gamePane.getChildren().add(ship);
    }
    private void createGameLoop(){                          //creates animation timers to move game elements
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveShip();
                moveBackground();
                moveElements();
                checkPosition();
                elementCollision();
            }
        };
        gameTimer.start();
    }

    private void moveShip(){
        if(isLeftPressed && !isRightPressed){
            if(angle> -30){                                 //tilt ship left
                angle -= 5;
            }
            ship.setRotate(angle);
            if(ship.getLayoutX() > -10){
                ship.setLayoutX(ship.getLayoutX()-7);       //move left
            }
        }
        if(isRightPressed && !isLeftPressed){
            if(angle < 30){                                 //tilt ship right
                angle += 5;
            }
            ship.setRotate(angle);
            if(ship.getLayoutX() < 505){
                ship.setLayoutX(ship.getLayoutX()+7);       //move right
            }
        }
        if(isLeftPressed && isRightPressed){
            if(angle < 0){                                  //straighten ship
                angle += 5;
            } else if (angle > 0){
                angle -= 5;
            }
            ship.setRotate(angle);

        }
        if(!isLeftPressed && !isRightPressed){
            if(angle < 0){                                  //straighten ship
                angle += 5;
            } else if (angle > 0){
                angle -= 5;
            }
            ship.setRotate(angle);
        }
    }
    private void createBackground(){
        gridPane = new GridPane();
        gridPane2 = new GridPane();

        for(int i=0; i < 12; i++){
            ImageView backgroundImage1 = new ImageView(backgroundPath);
            ImageView backgroundImage2 = new ImageView(backgroundPath);
            GridPane.setConstraints(backgroundImage1, i % 3, i / 3);
            GridPane.setConstraints(backgroundImage2, i % 3, i / 3);
            gridPane.getChildren().add(backgroundImage1);
            gridPane2.getChildren().add(backgroundImage2);
        }
        gridPane2.setLayoutY(-1024);
        gamePane.getChildren().addAll(gridPane, gridPane2);
    }

    private void moveBackground(){
        gridPane.setLayoutY(gridPane.getLayoutY() + .5);        //moves image down
        gridPane2.setLayoutY(gridPane2.getLayoutY() + .5);
        if(gridPane.getLayoutY() >= 1024){                      //resets background position
            gridPane.setLayoutY(-1024);
        }
        if(gridPane2.getLayoutY() >= 1024){                     //resets background position
            gridPane2.setLayoutY(-1024);
        }
    }

    private void elementCollision(){                    //detects collision between ship and space elements
        if (shipRadius + starRadius > calculateDistance(ship.getLayoutX()+49, star.getLayoutX()+15,
                ship.getLayoutY()+37, star.getLayoutY()+15)) {
            setElementPosition(star);
            points++;
            scoreLabel.setText("POINTS: " + points);    //points increase with star collision
        }
        for (int i = 0; i < brownMeteor.length; i++){
            if(shipRadius + meteorRadius >  calculateDistance(ship.getLayoutX()+49, brownMeteor[i].getLayoutX()+20,
                    ship.getLayoutY()+37, brownMeteor[i].getLayoutY()+20)){
                loseLife();
                setElementPosition(brownMeteor[i]);
            }
        }
        for (int i = 0; i < greyMeteor.length; i++) {
            if (shipRadius + meteorRadius > calculateDistance(ship.getLayoutX() + 49, greyMeteor[i].getLayoutX() + 20,
                    ship.getLayoutY() + 37, greyMeteor[i].getLayoutY() + 20)) {
                loseLife();
                setElementPosition(greyMeteor[i]);
            }
        }
    }

    private void loseLife(){
        gamePane.getChildren().remove(playerLives[life]);   //removes life icon
        life--;                                             //lowers life count
        if(life < 0){                                       //ends game
            gameStage.close();
            gameTimer.stop();
            menuStage.show();
        }
    }

    private double calculateDistance(double x1, double x2, double y1, double y2){
        return Math.sqrt(Math.pow((y2-y1),2) + Math.pow((x2-x1),2));
    }
}
