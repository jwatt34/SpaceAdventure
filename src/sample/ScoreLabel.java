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

public class ScoreLabel extends Label {
    public ScoreLabel(String text){
        setPrefHeight(50);
        setPrefWidth(120);
        BackgroundImage image = new BackgroundImage(new Image("Resources2/green_button13.png",120,50,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        setBackground(new Background(image));
        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10,10,10,10));
        setText(text);
        setWrapText(true);
        setLabelFont();
    }
    private void setLabelFont(){
        setFont(Font.font("VERDANA", 15));

    }
}
