package library.management;


public class CheckException extends Exception {

    public CheckException() {
         super(" \n Enter Valid Date \n");
    }

    public CheckException(String string) {
        super(string);
    }

    
}
