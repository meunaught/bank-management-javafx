package com.projectyobank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

//    @Override public void init() throws Exception {
//
//    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root  = FXMLLoader.load(Main.class.getResource("view/LOGINPAGE.fxml"));
        Scene scene = new Scene(root, Color.DEEPSKYBLUE);
        stage.setScene(scene);
        stage.setTitle("Yo Online Bank");
        //Image icon = new Image("file:///C:/Users/User/IdeaProjects/HelloFX/Icon.png");
        //stage.getIcons().add(icon);
        //stage.setWidth(650);
        //stage.setHeight(500);
        stage.setResizable(false);

        stage.show();
    }

    public static  void main(String[] args )
    {
        launch();
    }
}
