package com.example.projectyobank;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.projectyobank.Model_Sqlite.conection;

public class CurrentAccount extends AccountHolders{

    private ArrayList<Integer> arrayList = new ArrayList<Integer>();

    @Override
    public void createBalance(int Year,int Month,int Day,int Hour,int Minute,int Second)
    {
        try {
            Calendar calendar = Calendar.getInstance();
            double passed_Hours = accountHolderObj.Count_Hours(calendar,arrayList,Year,Month,Day,Hour,Minute,Second);

            if(passed_Hours>=1) {
                accountHolderObj.setBalance(accountHolderObj.getBalance() + (accountHolderObj.getMain_Balance() * currentAccount_IR * passed_Hours) / 100);
                accountHolderObj.Update_Database(calendar);
            }
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("ArrayList e problem");
            System.out.println(e);
        }
    }

    @Override
    public void withdraw(int amount)
    {
        if(accountHolderObj.getBalance()<amount)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Withdraw Money");
            alert.setHeaderText("");
            alert.setContentText("Not sufficient balance in your account!!!");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else if(accountHolderObj.getMain_Balance()>(accountHolderObj.getBalance()-amount))
        {
            ButtonType type = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Withdraw Money");
            alert.setHeaderText("");
            alert.setContentText("TK: " + amount + " will be withdrawn from your account and your main balance will become Tk: "
                    + (accountHolderObj.getBalance() - amount) +  "\nDo you want to confirm that withdrawal ?");
            alert.getDialogPane().getButtonTypes().add(type);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

            if (alert.showAndWait().get() == ButtonType.OK) {
                double now = accountHolderObj.getBalance()-amount;
                Update_Database((int) Math.round(now),accountHolderObj.getBalance()-amount);
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Withdraw Money");
            alert.setHeaderText("");
            alert.setContentText("TK: " + amount + " will be credited from your account\nDo you want to confirm?");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

            if (alert.showAndWait().get() == ButtonType.OK) {
                double now = accountHolderObj.getBalance()-amount;
                Update_Database((int) Math.round(now),accountHolderObj.getMain_Balance());
            }
        }
    }

    @Override
    public void deposit(int amount) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deposit Money");
        alert.setHeaderText("");
        alert.setContentText("TK: " + amount + " will be debited from your account\nDo you want to confirm?");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        if (alert.showAndWait().get() == ButtonType.OK) {
            double now = accountHolderObj.getBalance()+amount;
            Update_Database((int) Math.round(now),accountHolderObj.getMain_Balance() + amount);
        }
    }
}
