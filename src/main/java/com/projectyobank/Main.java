package com.projectyobank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root  = FXMLLoader.load(Main.class.getResource("view/login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("The Banker's App");
        stage.setResizable(false);
        stage.show();
    }

    public static  void main(String[] args )
    {
        launch();
    }
}
