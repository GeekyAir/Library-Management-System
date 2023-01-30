package library.management;

import java.util.*;

public class Magazine extends Type {

    public Magazine() {
    }

    public Magazine(double Price, String Author_1stName, String Author_2ndName, String BookName) {
        super(Price, Author_1stName, Author_2ndName, BookName);
    }

    @Override
    public void Fine() {
        if (this.isOverperiod()) {
            double LateDays = getPeriod() - 20;
            double fine = LateDays * 2;
            System.out.println("\n" + getStudentname() + ":\nPublication :" + getBookName() + "\nYou are late by : " + LateDays + " DAYS. \n" + " Fine = " + fine + "\n");
        } else {
            System.out.println("You don't have to pay any additional fees :) \n");
        }
    }
    
    public void Lateby() {
        if (this.todayisOverperiod()) {
            double LateDays = periodCheck() - 20;
            System.out.println("\n" + getStudentname() + "\nPublication :" + getBookName() + "\n LATE by " + LateDays + " DAYS. \n");
        }
    }

    @Override
    public boolean todayisOverperiod() {
        return periodCheck() > 20;
    }
    
    @Override
    public boolean isOverperiod() {
        if (getPeriod() > 20) {
            return true;
        } else {
            return false;
        }
    }

}
