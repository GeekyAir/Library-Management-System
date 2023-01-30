package library.management;

import java.util.*;
import java.io.*;
import javax.swing.*;

public class Libraray {

    public static ArrayList<Type> list;

    public static boolean Valid(String User, int Pass) {
        String u1 = "ahmed";
        int pass = 123;
        if (User.equals(u1) && Pass == pass) {
            return true;
        }
        return false;
    }

    public static void Fileread(String Library) {
        //initialize Arraylist And file
        list = new ArrayList<Type>();
        Scanner input;
        try {
            File f = new File(Library);
            input = new Scanner(f);
            while (input.hasNext()) {
                String Type = input.next();
                double Price = input.nextDouble();
                String Fname = input.next();
                String Sname = input.next();
                String BName = input.nextLine();
                //put the information in the array
                if (Type.equals("Book")) {
                    list.add(new Book(Price, Fname, Sname, BName.trim()));
                } else if (Type.equals("Magazine")) {
                    list.add(new Magazine(Price, Fname, Sname, BName.trim()));
                } else if (Type.equals("Booklet")) {
                    list.add(new Magazine(Price, Fname, Sname, BName.trim()));
                }
            }
            input.close();

        } catch (FileNotFoundException e) {

            System.out.print(e.getMessage());
        } catch (Exception ex) {

            System.out.print(ex.getMessage());
        }
    }

    public static void Filewrite(String Library) {
        PrintWriter write;
        try {
            File f = new File(Library);
            write = new PrintWriter(f);

            for (int i = 0; i < list.size(); i++) {
                //generalize for ease in handle
                Type t = list.get(i);
                if (t instanceof Book) {
                    write.print("Book ");
                    write.println(t.getPrice() + " " + t.getAuthor_1stName() + " " + t.getAuthor_2ndName() + " " + t.getBookName().trim());
                } else if (t instanceof Magazine) {
                    write.print("Magazine ");
                    write.println(t.getPrice() + " " + t.getAuthor_1stName() + " " + t.getAuthor_2ndName() + " " + t.getBookName().trim());
                }
                if (t instanceof Booklet) {
                    write.print("Booklet ");
                    write.println(t.getPrice() + " " + t.getAuthor_1stName() + " " + t.getAuthor_2ndName() + " " + t.getBookName().trim());
                }

            }
            write.close();

        } catch (FileNotFoundException e) {

            System.out.print(e.getMessage());
        } catch (Exception ex) {

            System.out.print(ex.getMessage());
        }
    }

    public static boolean isAvailable(String Bname) {
        for (int i = 0; i < list.size(); i++) {
            Type t = list.get(i);
            if (t.getBookName().contains(Bname)) {
                return true;
            }
        }
        return false;
    }

    public static void AddNewBook() {
        Scanner input = new Scanner(System.in);
        boolean f = true;
        String Type;
        do {
            System.out.println(" Enter  Type : ");
            Type = input.next();
            if (Type.equals("Book") || Type.equals("book") || Type.equals("Magazine") || Type.equals("magazine") || Type.equals("Booklet") || Type.equals("booklet")) {
                System.out.println(" Enter Publication  Name : ");
                input.nextLine();
                String BName = input.nextLine();
                System.out.println(" Enter author's First Name : ");
                String Fname = input.next();
                System.out.println(" Enter author's Second Name : ");
                String Sname = input.next();
                System.out.println(" Enter  Price : ");
                double Price = input.nextDouble();
                if (Type.equals("Book") || Type.equals("book")) {
                    list.add(new Book(Price, Fname, Sname, BName));
                    System.out.println("✔ ✔ ✔  Book Added  ✔ ✔ ✔");
                    f = true;

                } else if (Type.equals("Magazine") || Type.equals("magazine")) {
                    list.add(new Magazine(Price, Fname, Sname, BName));
                    System.out.println("✔ ✔ ✔  Magazine Added  ✔ ✔ ✔ ");
                    f = true;

                } else if (Type.equals("Booklet") || Type.equals("booklet")) {
                    list.add(new Booklet(Price, Fname, Sname, BName));
                    System.out.println("✔ ✔ ✔  Booklet Added  ✔ ✔ ✔");
                    f = true;
                }
            } else {
                System.out.println("\n*** Enter  Valid Publication Name *** \n");
                f = false;
            }
        } while (!f);

    }
    

    public static void ModifyPrice(String Bname) {
        Scanner input = new Scanner(System.in);
        if (isAvailable(Bname)) {
            for (int i = 0; i < list.size(); i++) {
                Type t = list.get(i);
                if (t.getBookName().contains(Bname)& t.isBorrowed() == false) {
                    System.out.print("Enter new Price :");
                    double Price = input.nextDouble();
                    t.setPrice(Price);
                    System.out.println("✔ ✔ ✔  Price Modified  ✔ ✔ ✔ ");
                    break;
                }

            }
        } else {
            System.out.println("Publication Not available");
        }

    }

    public static void DeleteBook(String Bname) {
        if (isAvailable(Bname)) {
            for (int i = 0; i < list.size(); i++) {
                Type t = list.get(i);
                if (t.getBookName().contains(Bname)) {
                    list.remove(i);
                    System.out.println("✔ ✔ ✔  Publication DELETED  ✔ ✔ ✔ \n");
                    break;
                }
            }
        } else {
            System.out.println("Publication Not available");
        }

    }

    public static void SearchBook(String Letters) {
        if (isAvailable(Letters)) {
            for (int i = 0; i < list.size(); i++) {
                Type t = list.get(i);
                if (t.getBookName().contains(Letters) && t.isBorrowed() == false) {
                    System.out.print(t);
                }
            }
        } else {
            System.out.println("Publication Not available");
        }
    }

    public static void AvailabBooks() {
        for (int i = 0; i < list.size(); i++) {
            Type t = list.get(i);
            if (!t.isBorrowed()) {
                System.out.print(t + "\n \n");
            }
        }
    }

    public static void Borrow(String Bname) {
        Scanner input = new Scanner(System.in);
        if (isAvailable(Bname)) {
            for (int i = 0; i < list.size(); i++) {
                Type t = list.get(i);
                if (t.getBookName().contains(Bname) && t.isBorrowed() == false) {
                    boolean f = true;
                    do {
                        try {
                            System.out.println("Enter Issue Date \n DD/MM/YY");
                            System.out.println();
                            System.out.println("Enter Issue Day :");
                           int dii = input.nextInt();
                            if (dii < 1 || dii > 30) {
                                throw new CheckException();
                            }
                            System.out.println("Enter Issue Month :");
                           int mii = input.nextInt();
                            if (mii < 1 || mii > 12) {
                                throw new CheckException();
                            }
                            System.out.println("Enter Issue Year :");
                           int yii = input.nextInt();
                            if (yii < 2020) {
                                throw new CheckException();
                            }

                            System.out.println("Enter Student Name :");
                            input.nextLine();
                            String s = input.nextLine();
                            Date issue = new Date(yii, mii, dii);
                            t.Borrow(issue, s);
                            System.out.println("✔ ✔ ✔  Publication is Lended successfully ✔ ✔ ✔ ");
                            f = true;
                        } catch (CheckException ex) {
                            System.out.println(ex.getMessage());
                            f = false;
                        }
                    } while (!f);
                    break;
                } else if (t.getBookName().contains(Bname) && t.isBorrowed()) {
                    System.out.println("Publication is Already borrowed");
                }
            }
        } else {
            System.out.println("Publication Not available");
        }
    }

    public static void ReturnBook(String Bname) {
        Scanner input = new Scanner(System.in);
        if (isAvailable(Bname)) {
            for (int i = 0; i < list.size(); i++) {
                Type t = list.get(i);
                if (t.getBookName().contains(Bname) && t.isBorrowed()) {
                    boolean f = true;
                    do {
                        try {
                            System.out.println("Enter Return date \n DD/MM/YY");
                            System.out.println();
                            System.out.println("Enter Return Day :");
                            int di = input.nextInt();
                            if (di < 1 || di > 30) {
                                throw new CheckException();
                            }
                            System.out.println("Enter Return Month :");
                            int mi = input.nextInt();
                            if (mi < 1 || mi > 12) {
                                throw new CheckException();
                            }
                            System.out.println("Enter Return Year :");
                            int yi = input.nextInt();
                            if (yi < 2020) {
                                throw new CheckException();
                            }

                            Date ActualReturn = new Date(yi, mi, di);
                            t.Return(ActualReturn);
                      //      System.out.println("\nIssued Date: " + dii + "/" + mii + "/" + yii);
                            t.Fine();
                            System.out.println("\n ✔ ✔ ✔  Publication RETURNED ✔ ✔ ✔ ");
                            f = true;
                        } catch (CheckException ex) {
                            System.out.println(ex.getMessage());
                            f = false;
                        }
                    } while (!f);
                    break;
                } else if (t.getBookName().contains(Bname) && t.isBorrowed() == false) {
                    System.out.println("Publication is NOT borrowed");
                }
            }
        } else {
            System.out.print("Publication Not available");
        }
    }

    public static void BorrowedBooks() {
        boolean empty = true;
        for (int i = 0; i < list.size(); i++) {
            Type t = list.get(i);
            if (t.isBorrowed()) {
                System.out.print(t + "\n Borrowd by : " + t.getStudentname() + "\n------------------------------------\n");
                empty = false;
            }
        }
        if (empty) {
            System.out.print(" *** There is No Borrowed Publications *** ");
        }
    }

    public static void Overperiod() {
        boolean empty = true;
        Scanner input = new Scanner(System.in);
        boolean f = true;
        do {
            try {
                System.out.println("Enter Today date \n DD/MM/YY");
                System.out.println();
                System.out.println("Enter Today's date");
                int di = input.nextInt();
                if (di < 1 || di > 30) {
                    throw new CheckException();
                }
                System.out.println("Enter Today's Month \n DD/MM/YY");
                int mi = input.nextInt();
                if (mi < 1 || mi > 12) {
                    throw new CheckException();
                }
                int yi = input.nextInt();
                if (yi < 2020) {
                    throw new CheckException();
                }
                Date TodayDate = new Date(yi, mi, di);
                for (int i = 0; i < list.size(); i++) {
                    Type t = list.get(i);
                    t.TodayCheck(TodayDate);
                    if (t.isBorrowed() && t.todayisOverperiod()) {
                        t.Lateby();
                        empty = false;
                    }
                }
                f = true;
            } catch (CheckException ex) {
                System.out.println(ex.getMessage());
                f = false;
            }
        } while (!f);

        if (empty) {
            System.out.print(" *** There is No Borrowed Publications *** ");
        }
    }
/////////////////////////////////////////////////////////////////////////////////       MAIN       /////////////////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        Fileread("Books.data");
        boolean f = true;
        do {
            try {
                int choice;
                do {
                    //First Menu
                    System.out.println("▅ ▆ ▇  Choose  ▇ ▆ ▅ \n \n"
                            + " 1: Search a Book \n"
                            + " 2: LOGIN \n"
                            + " 3: Exit");
                    choice = input.nextInt();
                    switch (choice) {
                        case 1:
                            boolean f_1 = true;
                            do {
                                try {
                                    System.out.println("*****  Enter Search key  *****");
                                    String Key = input.next();
                                    SearchBook(Key);
                                    scan.nextLine();
                                    f_1 = true;
                                    break;
                                } catch (NullPointerException e) {
                                    f_1 = false;
                                    System.out.println("* ERROR * \n *** Enter a vslid TYPE ***");
                                    input.next();
                                    scan.nextLine();
                                } catch (InputMismatchException e) {
                                    f_1 = false;
                                    System.out.println("* ERROR * \n *** Enter a valid INPUT ***");
                                    input.next();
                                    scan.nextLine();
                                } catch (Exception e) {
                                    f_1 = false;
                                    System.out.println("* ERROR * \n" + e.getMessage());
                                    input.next();
                                    scan.nextLine();
                                }
                            } while (!f_1);
                            break;
                        case 2:
                            boolean f_2 = true;
do{
                            System.out.println("*****  Enter Username  *****");
                            String UserName = input.next();
                            System.out.println("*****  Enter Password  *****");
                            int Pass = input.nextInt();

                            if (Valid(UserName, Pass)) {
                                f_2=true;
                                int Choice;
                                do {
                                    try {
                                        do {
                                            //Second Menu
                                            System.out.println("\n \n *♤*♤*♤*♤*  Choose your operation  *♤*♤*♤*♤* \n \n"
                                                    + " ⓵ Add a Publication ✚"
                                                    + "\n ⓶ Modify a Publication ✎"
                                                    + "\n ⓷ Delete a Publication ✘"
                                                    + "\n ⓸ Display available publications ✉"
                                                    + "\n ⓹ Lend to a student ↪"
                                                    + "\n ⓺ Return from a student ↺"
                                                    + "\n ⓻ Display borrowed publications ☄"
                                                    + "\n ⓼ Display OVERPERIOD borrowed publications ☢"
                                                    + "\n ⓽ Logout ✈");

                                            Choice = input.nextInt();
                                            switch (Choice) {
                                                case 1:
                                                    boolean f1 = true;
                                                    do {
                                                        try {
                                                            

                                                            AddNewBook();
                                                            scan.nextLine();
                                                            f1 = true;
                                                        } catch (NullPointerException e) {
                                                            f1 = false;
                                                            System.out.println("✘✘ ERROR ✘✘\n ✘✘✘ Enter a valid TYPE ✘✘✘");
                                                            scan.nextLine();

                                                        } catch (NumberFormatException e) {
                                                            f1 = false;
                                                            System.out.println("✘✘ ERROR ✘✘ \n ✘✘✘ Enter a valid NUMBER ✘✘✘");
                                                            scan.nextLine();

                                                        } catch (InputMismatchException e) {
                                                            f1 = false;
                                                            System.out.println("✘✘ ERROR ✘✘ \n ✘✘✘ Enter a valid INPUT ✘✘✘");
                                                            scan.nextLine();

                                                        } catch (Exception e) {
                                                            f1 = false;
                                                            System.out.println("✘✘ ERROR ✘✘ \n" + e.getMessage());
                                                            scan.nextLine();

                                                        }
                                                    } while (!f1);
                                                    break;
                                                case 2:
                                                    boolean f2 = true;
                                                    do {
                                                        try {
                                                            System.out.println(" Enter publication Name : ");
                                                            input.next();
                                                            String bName = input.nextLine();
                                                           
                                                            ModifyPrice(bName);
                                                            scan.nextLine();
                                                            f2 = true;
                                                        } catch (NullPointerException e) {
                                                            f2 = false;
                                                            System.out.println("✘✘ ERROR ✘✘ \n ✘✘✘ Enter a valid TYPE ✘✘✘");
                                                            scan.nextLine();

                                                        } catch (NumberFormatException e) {
                                                            f2 = false;
                                                            System.out.println("✘✘ ERROR ✘✘\n ✘✘✘ Enter a valid NUMBER ✘✘✘");
                                                            scan.nextLine();

                                                        } catch (InputMismatchException e) {
                                                            f2 = false;
                                                            System.out.println("✘✘ ERROR ✘✘ \n ✘✘✘ Enter a valid INPUT ✘✘✘");
                                                            scan.nextLine();

                                                        } catch (Exception e) {
                                                            f2 = false;
                                                            System.out.println("✘✘ ERROR ✘✘ \n" + e.getMessage());
                                                            scan.nextLine();

                                                        }
                                                    } while (!f2);
                                                    break;
                                                case 3:
                                                    boolean f3 = true;
                                                    do {
                                                        try {
                                                            System.out.println(" Enter publication Name : ");
                                                            input.nextLine();
                                                            String dName = input.nextLine();

                                                            DeleteBook(dName);
                                                            scan.nextLine();
                                                            f3 = true;
                                                        } catch (NullPointerException e) {
                                                            f3 = false;
                                                            System.out.println("✘✘ ERROR ✘✘ \n ✘✘✘ Enter a valid TYPE ✘✘✘");
                                                            scan.nextLine();
                                                        } catch (InputMismatchException e) {
                                                            f3 = false;
                                                            System.out.println("✘✘ ERROR ✘✘ \n ✘✘✘ Enter a valid INPUT ✘✘✘");
                                                            scan.nextLine();
                                                        } catch (Exception e) {
                                                            f3 = false;
                                                            System.out.println("✘✘ ERROR ✘✘ \n" + e.getMessage());
                                                            scan.nextLine();
                                                        }

                                                    } while (!f3);
                                                    break;
                                                case 4:
                                                    AvailabBooks();
                                                    scan.nextLine();
                                                    break;

                                                case 5:
                                                    boolean f5 = true;
                                                    do {
                                                        try {
                                                            System.out.println(" Enter desired publication Name : ");
                                                            input.nextLine();
                                                            String lName = input.nextLine();
                                                            Borrow(lName);
                                                            f5 = true;
                                                            scan.nextLine();

                                                        } catch (NullPointerException e) {
                                                            f5 = false;
                                                            System.out.println("✘✘ ERROR ✘✘ \n ✘✘✘ Enter a valid TYPE ✘✘✘");
                                                            scan.nextLine();
                                                        } catch (InputMismatchException e) {
                                                            f5 = false;
                                                            System.out.println("✘✘ ERROR ✘✘ \n ✘✘✘ Enter a valid INPUT ✘✘✘");
                                                            scan.nextLine();

                                                        } catch (Exception e) {
                                                            f5 = false;
                                                            System.out.println("✘✘ ERROR ✘✘ \n" + e.getMessage());
                                                            scan.nextLine();
                                                        }

                                                    } while (!f5);
                                                    break;

                                                case 6:
                                                    boolean f6 = true;
                                                    do {
                                                        try {
                                                            System.out.println(" Enter returned Publication Name : ");
                                                            input.nextLine();
                                                            String Brname = input.nextLine();
                                                            ReturnBook(Brname);
                                                            f6 = true;
                                                            scan.nextLine();
                                                        } catch (NullPointerException e) {
                                                            f6 = false;
                                                            System.out.println("✘✘ ERROR ✘✘ \n ✘✘✘ Enter a valid TYPE ✘✘✘");
                                                            scan.nextLine();
                                                        } catch (InputMismatchException e) {
                                                            f6 = false;
                                                            System.out.println("✘✘ ERROR ✘✘ \n ✘✘✘Enter a valid INPUT ✘✘✘");
                                                            scan.nextLine();
                                                        } catch (Exception e) {
                                                            f6 = false;
                                                            System.out.println("✘✘ ERROR ✘✘ \n" + e.getMessage());
                                                            scan.nextLine();
                                                        }
                                                    } while (!f6);
                                                    break;
                                                case 7:
                                                    BorrowedBooks();
                                                    scan.nextLine();
                                                    break;
                                                case 8:
                                                    boolean f8 = true;
                                                    do {
                                                        try {
                                                            Overperiod();
                                                            scan.nextLine();
                                                            f8 = true;;
                                                        } catch (NullPointerException e) {
                                                            f8 = false;
                                                            System.out.println("✘✘ ERROR ✘✘ \n ✘✘✘ Enter a valid TYPE ✘✘✘");
                                                            scan.nextLine();
                                                        } catch (InputMismatchException e) {
                                                            f8 = false;
                                                            System.out.println("✘✘ ERROR ✘✘ \n ✘✘✘ Enter a valid INPUT ✘✘✘");
                                                            scan.nextLine();
                                                        } catch (Exception e) {
                                                            f8 = false;
                                                            System.out.println("✘✘ ERROR ✘✘ \n" + e.getMessage());
                                                            scan.nextLine();
                                                        }
                                                    } while (!f8);

                                                    break;
                                                case 9:
                                                    Filewrite("Books.data");
                                                    break;
                                                default:
                                                    System.out.println("✘✘✘ Enter a VALID choice ✘✘✘");
                                                    scan.nextLine();
                                            }
                                        } while (Choice != 9);

                                        f_2 = true;
                                    } catch (NullPointerException e) {
                                        f_2 = false;
                                        System.out.println("✘✘ ERROR ✘✘ \n ✘✘✘ Enter a valid TYPE ✘✘✘");
                                        input.next();
                                        scan.nextLine();
                                    } catch (NumberFormatException e) {
                                        f_2 = false;
                                        System.out.println("✘✘ ERROR ✘✘ \n ✘✘✘ Enter a valid NUMBER ✘✘✘");
                                        input.next();
                                        scan.nextLine();
                                    } catch (InputMismatchException e) {
                                        f_2 = false;
                                        System.out.println("✘✘ ERROR ✘✘ \n ✘✘✘ Enter a valid INPUT ✘✘✘");
                                        input.next();
                                        scan.nextLine();
                                    } catch (Exception e) {
                                        f_2 = false;
                                        System.out.println("✘✘ ERROR ✘✘ \n" + e.getMessage());
                                        input.next();
                                        scan.nextLine();
                                    }
                                } while (!f_2);
                                //login if   
                                    break;
                                } else {
                                System.out.println("Enter valid CREDENTIALS");
                                    f_2 = false;
                                }
                            } while (!f_2);
                            break;
                        case 3:
                            System.out.println("♥㋡  Thank You  ㋡♥");
                            scan.nextLine();
                            break;
                        default:
                            System.out.println("Enter Valid input");
                            scan.nextLine();
                        //General Switch
                    }
                } while (choice != 3);

                //LOOP 
                f = true;
            } catch (NullPointerException e) {
                f = false;
                System.out.println("✘✘ ERROR ✘✘ \n ✘✘✘ Enter a valid TYPE ✘✘✘");
                input.next();
                scan.nextLine();
            } catch (NumberFormatException e) {
                f = false;
                System.out.println("✘✘ ERROR ✘✘ \n ✘✘✘ Enter a valid NUMBER ✘✘✘");
                input.next();
                scan.nextLine();
            } catch (InputMismatchException e) {
                f = false;
                System.out.println("✘✘ ERROR ✘✘ \n ✘✘✘ Enter a valid INPUT ✘✘✘");
                input.next();
                scan.nextLine();
            } catch (Exception e) {
                f = false;
                System.out.println("✘✘ ERROR ✘✘ \n" + e.getMessage());
                input.next();
                scan.nextLine();
            }
        } while (!f);

        //MAIN
    }

}
