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
    public AFloat(String s) {
        parse(s);
    }

    // make AFloat contructor with a string given
    private AFloat parse(String s) {
        this.sign = false;
        // handle negative sign
        if (s.charAt(0) == '-') {
            this.sign = true;
            String t = "";
            for (int i = 1; i < s.length(); i++) {
                t += s.charAt(i); // shift each char right by one
            }
            s = t;
        }

        // locate decimal point
        int pos = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                pos = i;
                break;
            }
        }

        //remove all decimal points
        String t = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '.')
                t += s.charAt(i);
        }
        s = t;

        // if no decimal set pos to end
        if (pos == -1)
            pos = s.length();

        int i = 0;
        //storing index for 0's at start of string
        while (i < pos && s.charAt(i) == '0') {
            i++;
        }

        int j = s.length() - 1;
        //storing index for 0's at end of string
        while (j > pos && s.charAt(j) == '0') {
            j--;
        }

        // use only significant digits make the substring from i to j
        this.num = "";
        for (int k = i; k <= j; k++) {
            this.num += s.charAt(k);
        }

        this.dec = pos - i; // set decimal count

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

    private static AInteger makeint(String s, boolean sign) {
        //function to convert flow as a instance of AInteger to be able to use its functions
        AInteger x = new AInteger();
        x.num = s;
        x.sign = sign;
        return x;
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
        AInteger y = makeint(this.num, this.sign).add(makeint(a.num, a.sign)); 
        ans.num = y.num;

        ans.sign = y.sign;

        //set dec as the greater number of digits after decimal of the two float values
        ans.dec = Math.max(this.num.length() - this.dec, a.num.length() - a.dec);
        return ans;     //return ans
    }

    private static String addzeros(String s, int count) {
        //run loop for count times and add that many number of zeros at end
        for (int i = 0; i < count; i++) {
            s += "0";
        }
        return s;
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
        AInteger y = makeint(this.num, this.sign).sub(makeint(a.num, a.sign));
        ans.num = y.num;
        ans.sign = y.sign;

        //set dec as the greater number of digits after decimal of the two float values
        ans.dec = Math.max(this.num.length() - this.dec, a.num.length() - a.dec);
        return ans;     //return ans
    }

    public AFloat mul(AFloat a) {
        AFloat ans = new AFloat();  //make ans variable

        //converting the given variables to int after removing the decimal place 
        //and multiplying the two ints from the methods of AInteger
        AInteger y = makeint(this.num, this.sign).mul(makeint(a.num, a.sign));
        ans.num = y.num;
        ans.sign = y.sign;

        //set dec as the sum number of digits after decimal for the two float values
        ans.dec = (this.num.length() - this.dec) + (a.num.length() - a.dec);
        return ans;
    }

    // public AFloat div(AFloat a) {
    //     return;
    // }

    @Override
    public String toString() {

        //Edge case if object is zero already return 0
        if (num.equals("0")){
            return "0";
        }

        int i = 0;
        //storing index for 0's at start of string
        while (i < num.length() - dec && num.charAt(i) == '0') {
            i++;
        }

        int j = num.length() - 1;
        //storing index for 0's at end of string
        while (j >= num.length() - dec && num.charAt(j) == '0') {
            j--;
        }

        String s = "";      //initializing substring

        //make substring from i to j.
        for (int k = i; k <= j; k++) {
            s += num.charAt(k);
        }

        //if the string is empty means it contained only 0's so return 0.
        if (s.length() == 0) {
            return "0";
        }

        String ans = "";    //initializing ans string

        //New position of decimal as xzremoving 0's can change it.
        int decnew = s.length() - (num.length() - dec - i);
        //if it is greater than the substring length then we need to add 0's after decimal and before ans string
        if (decnew >= s.length()) {
            ans = "0.";     //starting the string with "0."

            //adding required number of 0's after decimal.
            for (int z = s.length(); z < decnew; z++) {
                ans += "0";
            }
            //Appending the main ans string
            for (int k = 0; k < s.length(); k++) {
                ans += s.charAt(k);
            }
        } 
        //if not place the decimal after decnew digits are traversed
        else {
            for (int k = 0; k < s.length(); k++) {
                if (k == s.length() - decnew)//At decnew from back position add a '.'.
                {
                    ans += ".";
                }
                ans = ans + s.charAt(k);    //Append the char at kth index in main string.
            }
        }

        //If last element is '.' then make a new string and dont include the last decimal symbol in it
        if (ans.charAt(ans.length() - 1) == '.') {
            String tmp = "";
            //Iterating only till n-2.
            for (int k = 0; k < ans.length() - 1; k++) {
                tmp += ans.charAt(k);
            }
            ans = tmp;
        }

        //If string starts with '.' add a 0 before it.
        if (ans.charAt(0) == '.') {
            ans = "0" + ans;
        }

        //Add '-' at start of string if negative
        if (sign) {
            ans = "-" + ans;
        }
        return ans;     //return the modified string after conversion
    }
}
