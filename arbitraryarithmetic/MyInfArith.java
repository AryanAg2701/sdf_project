package arbitraryarithmetic;

public class MyInfArith {
    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("incorrect input1");
            return;
        }

        String ds = args[0];
        String opr = args[1];
        String s1 = args[2];
        String s2 = args[3];

        if (ds.equals("int")) {

        } else if (ds.equals("float")) {

        } else {
            System.out.println("incorrect input");
        }
    }
}
