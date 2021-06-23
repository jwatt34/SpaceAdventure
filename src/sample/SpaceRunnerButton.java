package sample;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class SpaceRunnerButton extends Button {
    private final String buttonPressedStyle="-fx-background-color:gold;-fx-background-image: url('Resources/yellow_button03.jpg')";
    private final String buttonFreeStyle = "-fx-background-color:gold;-fx-background-image: url('Resources/yellow_button04.jpg')" ;

    public SpaceRunnerButton (String text){     //formats menu buttons
        setText(text);
        setButtonFont();
        setPrefSize(190,49);
        setStyle(buttonFreeStyle);
        initializeButtonListener();
    }

    private void setButtonFont(){
        setFont(Font.font("Verdana",23));
    }

    public void setButtonPressedStyle(){        //button appearance when clicked
        setStyle(buttonPressedStyle);
        setPrefHeight(45);
        setLayoutY(getLayoutY()+4);
        setLayoutX(getLayoutX()+4);
    }

    public void setButtonFreeStyle(){           //button appearance when not clicked
        setStyle(buttonFreeStyle);
        setPrefHeight(49);
        setLayoutY(getLayoutY()-4);
        setLayoutX(getLayoutX()-4);
    }
    public void initializeButtonListener(){

        setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                if (event.getButton().equals(MouseButton.PRIMARY)){
                    setButtonPressedStyle();
                }
            }
        });
        setOnMouseReleased(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                if (event.getButton().equals(MouseButton.PRIMARY)){
                    setButtonFreeStyle();
                }
            }
        });
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(null);
            }
        });
    }
}
