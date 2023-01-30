package library.management;
import java.util.*;
import javax.swing.JOptionPane;

abstract class Type {
    // VARIABLES

    protected String BookName;
    protected String Author_1stName;
    protected String Author_2ndName;
    protected double Price;
    protected boolean Borrowed;
    protected Date IssueDate;
    protected Date ReturnDate;
    protected Date TodayDate;
    protected String Studentname;
    
       //Constructors 
    public Type() {

    }

    public Type(double Price, String Author_1stName, String Author_2ndName,String BookName ) {
        this.BookName = BookName;
        this.Author_1stName = Author_1stName;
        this.Author_2ndName = Author_2ndName;
        this.Price = Price;
    }

    //Getter

    public String getBookName() {
        return BookName;
    }

    public String getAuthor_1stName() {
        return Author_1stName;
    }

    public String getAuthor_2ndName() {
        return Author_2ndName;
    }

    public double getPrice() {
        return Price;
    }

    public boolean isBorrowed() {
        return Borrowed;
    }

    public Date getIssueDate() {
        return IssueDate;
    }

    public Date getReturnDate() {
        return ReturnDate;
    }

    public Date getTodayDate() {
        return TodayDate;
    }
 



    public String getStudentname() {
        return Studentname;
    }
    
    //Setters

    public void setStudentname(String Studentname) {
        this.Studentname = Studentname;
    }

    public void setBookName(String BookName) {
        this.BookName = BookName;
    }

    public void setAuthor_1stName(String Author_1stName) {
        this.Author_1stName = Author_1stName;
    }

    public void setAuthor_2ndName(String Author_2ndName) {
        this.Author_2ndName = Author_2ndName;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public void setBorrowed(boolean isBorrowed) {
        this.Borrowed = isBorrowed;
    }

    public void setIssueDate(Date BorrowDate) {
        this.IssueDate = BorrowDate;
    }

    public void setReturnDate(Date ReturnDate) {
        this.ReturnDate = ReturnDate;
    }

    public void setTodayDate(Date TodayDate) {
        this.TodayDate = TodayDate;
    }

     
    

    //Functions";
    
    
    public abstract boolean isOverperiod();
    public abstract boolean todayisOverperiod();
    public abstract void Fine();
    public abstract void Lateby();
    
    public void Borrow (Date Issue ,String Sname)  {
    
        this.setStudentname(Sname);
        this.setBorrowed(true);
        this.setIssueDate(Issue);
    
    }
public void Return(Date ActualDate){
    this.setReturnDate(ActualDate);
    this.setBorrowed(false);
}
 

public void TodayCheck(Date TodayDate){
this.setTodayDate(TodayDate);
}

public long periodCheck(){
long Today = getTodayDate().getTime();
long Issue = getIssueDate().getTime();
long difference = Today - Issue;

return difference / 24 / 60 / 60 / 1000; 
}

public long getPeriod(){
        
    long IssuDate = getIssueDate().getTime();
    long ActualDate = getReturnDate().getTime();
    long difference = ActualDate-IssuDate;
    
    return difference / 24 / 60 / 60 / 1000; 
    
    };
    
  
    public String toString(){
        return "Publication : " + getBookName() + "\n" + "By : "+ getAuthor_1stName() + " " + getAuthor_2ndName() + "\nPrice : " + getPrice()  ;
    }
}

