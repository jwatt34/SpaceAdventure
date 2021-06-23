package sample;

public enum Ship {
    BLUE("ShipChooser/playerShip1_blue.png", "Resources2/playerLife1_blue.png"),
    GREEN("ShipChooser/playerShip2_green.png", "Resources2/playerLife2_green.png"),
    ORANGE("ShipChooser/playerShip3_orange.png", "Resources2/playerLife3_orange.png"),
    RED("ShipChooser/playerShip2_red.png", "Resources2/playerLife2_red.png");


    private java.lang.String urlShip;
    private String urlLife;

    private Ship(String urlShip, String urlLife){

        this.urlShip = urlShip;
        this.urlLife = urlLife;
    }

    public String getUrlShip(){

        return this.urlShip;
    }

    public String getUrlLife(){
        return this.urlLife;
    }



}
