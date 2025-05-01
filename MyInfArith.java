
import arbitraryarithmetic.AInteger;
import arbitraryarithmetic.AFloat;

public class MyInfArith {
    public static void main(String[] args) {
        // If the number of input arguments is less than 4 exit.
        if (args.length < 4) {
            System.out.println("incorrect input");
            return;
        }
        // Taking inputs from the command line.
        String ds = args[0];
        String operation = args[1];
        String string_1 = args[2];
        String string_2 = args[3];

        // Creating if for integer numbers
        if (ds.equals("int")) {
            // Creating AInteger Objects from given string
            AInteger s1 = new AInteger(string_1);
            AInteger s2 = new AInteger(string_2);
            // Switch case for each operation.
            switch (operation) {
                case "add": {
                    System.out.println(s1.add(s2).toString());
                    break;
                }
                case "sub": {
                    System.out.println(s1.sub(s2).toString());
                    break;
                }
                case "mul": {
                    System.out.println(s1.mul(s2).toString());
                    break;
                }
                case "div": {
                    System.out.println(s1.div(s2).toString());
                    break;
                }
                default: {
                    System.out.println("operation incorrect");
                }
            }
        }
        // Creating if for floating point numbers
        else if (ds.equals("float")) {
            // Creating AFloat Objects from given string
            AFloat s1 = new AFloat(string_1);
            AFloat s2 = new AFloat(string_2);
            // Switch case for each operation.
            switch (operation) {
                case "add": {
                    System.out.println((s1.add(s2).toString()));
                    break;
                }
                case "sub": {
                    System.out.println((s1.sub(s2).toString()));
                    break;
                }
                case "mul": {
                    System.out.println((s1.mul(s2).toString()));
                    break;
                }
                case "div": {

                    System.out.println((s1.div(s2).toString()));
                    break;
                }
                default: {
                    System.out.println("Invalid operation");
                }
            }
        }
        // Incorrect input if none is encountered.
        else {
            System.out.println("incorrect input");
        }
    }
}
