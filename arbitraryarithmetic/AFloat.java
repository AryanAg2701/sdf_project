package arbitraryarithmetic;

public class AFloat {

    private String num; // digits without decimal point
    private int dec; // number of integer digits

    private boolean sign; // true if negative

    // default constructor
    public AFloat() {
        this.num = "0";
        this.dec = 0;
        this.sign = false;
    }

    // construct from string
    public AFloat(String string) {
        parse(string);
    }

    // make AFloat contructor with a string given
    private AFloat parse(String string) {
        this.sign = false;
        // handle negative sign
        if (string.charAt(0) == '-') {
            this.sign = true;
            String output = "";
            for (int i = 1; i < string.length(); i++) {
                output += string.charAt(i); // shift each char right by one
            }
            string = output;
        }

        // locate decimal point
        int position = -1;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '.') {
                position = i;
                break;
            }
        }

        //remove all decimal points
        String output = "";
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) != '.')
                output += string.charAt(i);
        }
        string = output;

        // if no decimal set position to end
        if (position == -1)
            position = string.length();

        int i = 0;
        //storing index for 0'string at start of string
        while (i < position && string.charAt(i) == '0') {
            i++;
        }

        int j = string.length() - 1;
        //storing index for 0'string at end of string
        while (j > position && string.charAt(j) == '0') {
            j--;
        }

        // use only significant digits make the substring from i to j
        this.num = "";
        for (int k = i; k <= j; k++) {
            this.num += string.charAt(k);
        }

        this.dec = position - i; // set decimal count

        // return
        AFloat ans = new AFloat();
        ans.num = this.num;
        ans.dec = this.dec;
        ans.sign = this.sign;
        return ans;
    }

    // copy constructor
    public AFloat(AFloat other) {
        this.num = other.num;
        this.dec = other.dec;
        this.sign = other.sign;
    }

    private static AInteger makeint(String string, boolean sign) {
        //function to convert flow as a instance of AInteger to be able to use its functions
        AInteger string_1 = new AInteger();
        string_1.num = string;
        string_1.sign = sign;
        return string_1;
    }

    public AFloat add(AFloat a) {
        AFloat ans = new AFloat();  //make ans variable
        
        //add zeros for the string which has lesser number of digits after decimal
        if (this.num.length() - this.dec > a.num.length() - a.dec) {
            a.num = addzeros(a.num, this.num.length() - this.dec - a.num.length() + a.dec);//adding zeros and giving the count of extra zeros needed
        } else {
            this.num = addzeros(this.num, a.num.length() - a.dec - this.num.length() + this.dec);
        }

        //converting the given variables to int after removing the decimal place 
        //and adding the two ints from the methods of AInteger
        AInteger string_2 = makeint(this.num, this.sign).add(makeint(a.num, a.sign)); 
        ans.num = string_2.num;

        ans.sign = string_2.sign;

        //set dec as the greater number of digits after decimal of the two float values
        ans.dec = Math.max(this.num.length() - this.dec, a.num.length() - a.dec);
        return ans;     //return ans
    }

    private static String addzeros(String string, int count) {
        //run loop for count times and add that many number of zeros at end
        for (int i = 0; i < count; i++) {
            string += "0";
        }
        return string;
    }

    public AFloat sub(AFloat a) {
        AFloat ans = new AFloat();   //make ans variable

        //add zeros for the string which has lesser number of digits after decimal
        if (this.num.length() - this.dec > a.num.length() - a.dec) {
            a.num = addzeros(a.num, this.num.length() - this.dec - a.num.length() + a.dec);
        } else {
            this.num = addzeros(this.num, a.num.length() - a.dec - this.num.length() + this.dec);
        }

        //converting the given variables to int after removing the decimal place 
        //and substracting the two ints from the methods of AInteger
        AInteger string_2 = makeint(this.num, this.sign).sub(makeint(a.num, a.sign));
        ans.num = string_2.num;
        ans.sign = string_2.sign;

        //set dec as the greater number of digits after decimal of the two float values
        ans.dec = Math.max(this.num.length() - this.dec, a.num.length() - a.dec);
        return ans;     //return ans
    }

    public AFloat mul(AFloat a) {
        AFloat ans = new AFloat();  //make ans variable

        //converting the given variables to int after removing the decimal place 
        //and multiplying the two ints from the methods of AInteger
        AInteger string_2 = makeint(this.num, this.sign).mul(makeint(a.num, a.sign));
        ans.num = string_2.num;
        ans.sign = string_2.sign;

        //set dec as the sum number of digits after decimal for the two float values
        ans.dec = (this.num.length() - this.dec) + (a.num.length() - a.dec);
        return ans;
    }

    public AFloat div(AFloat a) {
        //Edge case of division by zero
        if (removezero(a.num).equals("0")) {
            AFloat ans = new AFloat();
            ans.num = "Division by zero error";
            return ans;     //return dummy ans in error case
        }

        String string_1 = this.num;    //Making strings for operands
        String string_2 = a.num;
        String temp=removezero(string_2); //remove zeros at start of string

        int removed_zeros=string_2.length()-temp.length();    //counting the number of deleted zeros.
        string_2=temp;
        int diff = string_1.length() + a.dec - this.dec - string_2.length();  //extra digits after the decimal for whichever is greater

        if (diff > 0) {
            // Divisor has more decimals add zeros to numerator
            for (int i = 0; i < diff; i++) {
                string_2 = string_2 + "0";
            }
        } else {
            // Else add zeros to divisor
            for (int i = 0; i < -diff; i++) {
                string_1 = string_1 + "0";
            }
        }

        int size1 = string_1.length();     //Storing the sizes of both stings
        int size2 = string_2.length();

        String remainder;
        //If the dividend is greter than divisor take the starting divisor as only start
        if (size2 <= size1) {
            remainder = "";

            //Making the substring
            for (int i = 0; i < size2 && i < size1; i++) {
                remainder += string_1.charAt(i);
            }
        } else {
            //else take the whole divident as rem
            remainder = string_1;
        }

        String quotient = "";

        for (int i = 0; i <= size1 - size2; i++) {

            int count = 0;      //Count variable for counting number of substraction in each iteration

            //Add zeros to remainder till it is smaller than divisor
            while (remainder.length() < size2) {
                remainder = "0" + remainder;
            }

            //Continue substracting divisor from remainder till it is larger than divisor
            while (remainder.length() > size2|| (remainder.length() == size2 && remainder.compareTo(string_2) >= 0)) {
                //converting both the strings to AInteger for infinite substraction
                remainder = makeint(remainder, false).sub(makeint(string_2, false)).toString();    
                remainder = removezero(remainder);      //removing zeros at start if any.
                count++;                                //Incrementing count number if substract operations.

            }

            quotient += (char) (count + '0'); // Add the quotient count to the ans after parsing to char

            // Move the remainder forward and remove leading zeros if any
            if (i + size2 < size1) {
                remainder += string_1.charAt(i + size2);
                remainder = removezero(remainder);
            }
        }

        quotient += "."; // Add the '.' to the quotient after integer part is done

        //For loop to do number of digits after decimal 
        //Although asked for 30 digits but using 1000 here.
        for (int i = 0; i < 1000; i++) {
            remainder += "0";                       //For maintaning greater size
            remainder = removezero(remainder);      //remove zeros at start

            //Break if it is exactly divisible so remainder becomes zero.
            if (remainder.equals("0")) {
                break;
            }

            int count = 0;      //new cont for same use of number of subs.

            //Adding zeros to make remainder same size as divisor
            while (remainder.length() < size2) {
                remainder = "0" + remainder;
            }

            //Doing same substracting of divisor from remainder till it is greater
            while (remainder.length() > size2 || (remainder.length() == size2 && remainder.compareTo(string_2) >= 0)) {
                //Converting to AInteger then substracting
                remainder = makeint(remainder, false).sub(makeint(string_2, false)).toString();
                remainder = removezero(remainder);
                count++;    //Increment count
            }

            quotient += (char) (count + '0');   //Add to count after parting to char

        }

        AFloat ans = new AFloat(quotient);      //Making new AFloat to return
        //If the sign of both num is different the ans will be -ve
        if (this.sign != a.sign) {
            ans.sign = true;
        } 
        //Else +ve ans
        else {
            ans.sign = false;
        }
        //converting tp Number of decimal places before '.' to after it.
        ans.dec = ans.num.length() - ans.dec - removed_zeros;
        return ans;     //Returning
    }

    // Helper function to remove leading zeros
    private String removezero(String string) {
        int i = 0;
        //Count number of zeros at end of string
        while (i < string.length() - 1 && string.charAt(i) == '0') {
            i++;
        }
        //if all of them are zeros return only 0.
        if (i == string.length()) {
            return "0";
        }

        //making substring after deleting the zeros
        String ans = "";
        for (int j = i; j < string.length(); j++) {
            ans += string.charAt(j);
        }

        return ans;     //Returning.
    }

    @Override
    public String toString() {

        //Edge case if object is zero already return 0
        if (num.equals("0")){
            return "0";
        }

        int i = 0;
        //storing index for 0'string at start of string
        while (i < num.length() - dec && num.charAt(i) == '0') {
            i++;
        }

        int j = num.length() - 1;
        //storing index for 0'string at end of string
        while (j >= num.length() - dec && num.charAt(j) == '0') {
            j--;
        }

        String string = "";      //initializing substring

        //make substring from i to j.
        for (int k = i; k <= j; k++) {
            string += num.charAt(k);
        }

        //if the string is empty means it contained only 0'string so return 0.
        if (string.length() == 0) {
            return "0";
        }

        String ans = "";    //initializing ans string

        //New position of decimal as xzremoving 0'string can change it.
        int decnew = string.length() - (num.length() - dec - i);
        //if it is greater than the substring length then we need to add 0'string after decimal and before ans string
        if (decnew >= string.length()) {
            ans = "0.";     //starting the string with "0."

            //adding required number of 0'string after decimal.
            for (int temp = string.length(); temp < decnew; temp++) {
                ans += "0";
            }
            //Appending the main ans string
            for (int k = 0; k < string.length(); k++) {
                ans += string.charAt(k);
            }
        } 
        //if not place the decimal after decnew digits are traversed
        else {
            for (int k = 0; k < string.length(); k++) {
                if (k == string.length() - decnew)//At decnew from back position add a '.'.
                {
                    ans += ".";
                }
                ans = ans + string.charAt(k);    //Append the char at kth index in main string.
            }
        }

        //If last element is '.' then make a new string and dont include the last decimal symbol in it
        if (ans.charAt(ans.length() - 1) == '.') {
            String temp_2 = "";
            //Iterating only till n-2.
            for (int k = 0; k < ans.length() - 1; k++) {
                temp_2 += ans.charAt(k);
            }
            ans = temp_2;
        }

        //If string starts with '.' add a 0 before it.
        if (ans.charAt(0) == '.') {
            ans = "0" + ans;
        }

        //Add '-' at start of string if negative
        if (sign) {
            ans = "-" + ans;
        }
        //Adding zero after decimal if dec is -ve
        if(dec<0){
            for(int k=0;k>dec;k--){
                ans+="0";
            }
        }
        return ans;     //return the modified string after conversion
    }
}
