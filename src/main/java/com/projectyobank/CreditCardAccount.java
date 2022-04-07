package com.example.projectyobank;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.Calendar;

public class CreditCardAccount extends AccountHolders{

    private ArrayList<Integer> arrayList = new ArrayList<Integer>();

    @Override
    public void createBalance(int Year,int Month,int Day,int Hour,int Minute,int Second)
    {
        try {
            Calendar calendar = Calendar.getInstance();
            double passed_Hours = accountHolderObj.Count_Hours(calendar,arrayList,Year,Month,Day,Hour,Minute,Second);

            if(passed_Hours>=1) {
                accountHolderObj.setBalance(accountHolderObj.getBalance()*Math.pow((1+(creditCard_IR/100)),passed_Hours));
                accountHolderObj.Update_Database(calendar);
            }
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("ArrayList e problem in creditcard account");
            System.out.println(e);
        }
    }

    @Override
    public double maxWithdraw()
    {
        return 20000.0;
    }
}
