package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ViewManager manager = new ViewManager();

        primaryStage = manager.getStage();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
