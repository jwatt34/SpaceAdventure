package sample;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ShipSelector extends VBox {
    private ImageView circleImage;
    private ImageView shipImage;
    private String circleImageEmpty = "ShipChooser/grey_circle.png";
    private String circleImageChosen = "ShipChooser/green_boxTick.png";
    private Ship ship;
    private boolean isChosen;

    ShipSelector(Ship ship){
        circleImage = new ImageView(circleImageEmpty);
        shipImage = new ImageView(ship.getUrlShip());
        this.ship = ship;
        isChosen = false;
        setAlignment(Pos.CENTER);
        setSpacing(20);
        getChildren().add(circleImage);
        getChildren().add(shipImage);

    }

    public Ship getShip(){
        return ship;
    }

    public boolean getIsChosen(){
        return isChosen;
    }

    public void setChosen(boolean isChosen){
        this.isChosen = isChosen;
        String imageToSet = this.isChosen  ? circleImageChosen : circleImageEmpty;
        circleImage.setImage(new Image(imageToSet));

    }
}
