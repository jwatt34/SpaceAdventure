package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class SpaceRunnerSubScene extends SubScene {
    private final static String backgroundPath = "Resources2/yellow_panel.png";
    private static boolean isHidden;


    public SpaceRunnerSubScene() {
        super(new AnchorPane(), 550, 450);
        BackgroundImage image = new BackgroundImage(new Image(backgroundPath, 550,450,false,true),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setBackground(new Background(image));
        setLayoutX(1024);
        setLayoutY(150);
        isHidden = true;
    }

    public void moveSubScene(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.4));
        transition.setNode(this);
        if(isHidden){                           //moves subscene left if it is not visible
            transition.setToX(-794);
            isHidden = false;

        } else{                                 //moves subscene right if not visible
            transition.setToX(794);
            isHidden = true;
        }


        transition.play();
    }

    public AnchorPane getPane(){
        return (AnchorPane) this.getRoot();
    }


}
