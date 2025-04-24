package arbitraryarithmetic;

public class MyInfArith {
    public static void main(String[] args) {
        //If the number of input arguments is less than 4 exit.
        if (args.length < 4) {
            System.out.println("incorrect input1");
            return;
        }
        //Taking inputs from the command line.
        String ds = args[0];
        String opr = args[1];
        String s1 = args[2];
        String s2 = args[3];

        //Creating if for integer numbers
        if (ds.equals("int")) {

        } 
        //Creating if for floating point numbers
        else if (ds.equals("float")) {

        } 
        //Incirrect input if none is encountered.
        else {
            System.out.println("incorrect input");
        }
    }
}
