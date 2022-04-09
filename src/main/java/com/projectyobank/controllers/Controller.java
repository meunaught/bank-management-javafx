package com.projectyobank.controllers;

import com.projectyobank.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToScene(String FileName, ActionEvent e) throws IOException
    {
        root  = FXMLLoader.load(Main.class.getResource(FileName));
        stage  = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene =  new Scene(root, Color.DEEPSKYBLUE);
        stage.setScene(scene);
        stage.show();
    }
    public void giveFilename(String filename,ActionEvent e)
    {
        try {
            switchToScene(filename,e);
        }
        catch (IOException ex) {
            System.out.println(filename);
            ex.printStackTrace();
        }
    }

}
