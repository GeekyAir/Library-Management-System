package library.management;

import java.util.*;

public class Booklet extends Type {

    public Booklet() {
    }

    public Booklet(double Price, String Author_1stName, String Author_2ndName, String BookName) {
        super(Price, Author_1stName, Author_2ndName, BookName);
    }

    @Override
    public void Fine() {
        if (this.isOverperiod()) {
            double LateDays = getPeriod() - 12;
            double fine = LateDays * 2;
            System.out.println("\n" + getStudentname() + ":\nPublication :" + getBookName() + "\nYou are late by : " + LateDays + " DAYS. \n" + " Fine = " + fine + "\n");
        } else {
            System.out.println("You don't have to pay any additional fees :) \n");
        }
    }
    
    public void Lateby() {
        if (this.todayisOverperiod()) {
            double LateDays = periodCheck() - 12;
            System.out.println("\n" + getStudentname() + "\nPublication :" + getBookName() + "\n LATE by " + LateDays + " DAYS. \n");
        }
    }
            
    @Override
    public boolean todayisOverperiod(){
        return periodCheck()> 12;
    }

    @Override
    public boolean isOverperiod() {
        return getPeriod() > 12;
    }
}
