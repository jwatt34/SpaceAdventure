package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;


public class InfoLabel extends Label {
    public static String backgroundPath = "ShipChooser/yellow_button13.png";
    public  InfoLabel(String text){
        setPrefWidth(400);
        setPrefHeight(40);
        setText(text);
        setWrapText(true);
        setLabelFont();
        setLabelBackground();
        setAlignment(Pos.CENTER);
    }

    public void setLabelFont(){
        setFont(Font.font("Verdana",23));
    }

    public void setLabelBackground(){
        BackgroundImage backgroundImage = new BackgroundImage(new Image(backgroundPath, 380,40,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        setBackground(new Background(backgroundImage));
    }
}
