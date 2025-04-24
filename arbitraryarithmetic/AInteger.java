package arbitraryarithmetic;

public class AInteger {
    // Defining member variables of string and sign of input
    public String num;// number without sign.
    public boolean sign;// true if negative

    // AInteger Contructorfor Defualt value
    public AInteger() {
        this.num = "0";
        this.sign = false;
    }

    // Contructor for string to AInteger
    public AInteger(String a) {
        parse(a); // parsing to AInteger
    }

    // Parse function
    private AInteger parse(String a) {
        // if first char is -
        if (a.charAt(0) == '-') {
            this.sign = true; // marking true to sign variable for the AInteger
            String x = "";
            // Shifting each character by one place to right after removing first character.
            for (int j = 0; j <= a.length() - 2; j++) {
                x += a.charAt(j + 1);
            }
            a = x;
        }
        // emove zeros at start
        this.num = removezero(a);

        // Edge case if the number was zero
        if (this.num.length() == 0) {
            this.num = "0";
        }
        // Making a new variable to return;
        AInteger ans = new AInteger();
        ans.num = this.num;
        ans.sign = this.sign;
        return this;
    }

    // Contructor to assign a AInteger using other predefined.
    public AInteger(AInteger a) {
        this.num = a.num;
        this.sign = a.sign;
    }

    // Addition Logic
    public AInteger add(AInteger a) {
        if (this.sign && a.sign) {
            return new AInteger("-" + this.adding(a)); // If both umbers were -ve add them and prefix "-"
        }
        if (this.sign && !a.sign) {
            return new AInteger(a.subing(this)); // If first number us -ve second is -ve
        }
        if (!this.sign && a.sign) {
            return new AInteger(this.subing(a)); // If first is+ve and second is -ve
        } else {
            return new AInteger(this.adding(a)); // Both Positive
        }
    }

    public AInteger sub(AInteger a) {
        if (this.sign && a.sign) {
            return new AInteger(this.subing(a)); // If both a -ve substract first from second
        }
        if (this.sign && !a.sign) {
            return new AInteger("-" + a.adding(this)); // If opoosite sign add them.
        }
        if (!this.sign && a.sign) {
            return (a.adding(this)); // If opoosite sign add them.
        } else {
            return new AInteger(a.subing(this)); // If both a +ve substract second from first
        }
    }

    public AInteger mul(AInteger a) {
        AInteger ans = new AInteger();

        ans.num = mulstr(this.num, a.num);

        if (this.sign == a.sign) {
            ans.sign = false;
        }

        else {
            ans.sign = true;
        }
        return ans;
    }

    public AInteger div(AInteger a) {
        if (a.num.equals("0")) {
            System.out.println("Division by zero error");
            System.exit(0);
            return new AInteger();

        } else {
            AInteger ans = new AInteger();
            ans.num = divstr(this.num, a.num);

            if (this.sign == a.sign)
                ans.sign = false;

            else {
                ans.sign = true;
            }
            return ans;
        }
    }

    // Adding the AInteger values only when both the strings are positive
    public AInteger adding(AInteger a) {
        // Defining to strings to pass to the helper functions
        String x = a.num;
        String y = this.num;
        AInteger ans = new AInteger(); // to store the ouput

        ans.num = addstr(x, y); // calling helper func addstr
        ans.sign = false; // for adding always positive.
        return ans;
    }

    public AInteger subing(AInteger a) {
        String x = a.num;
        String y = this.num;
        AInteger ans = new AInteger(); // to store the ouput

        ans.num = substr(x, y); // sending to helper substr fn
        ans.sign = false; // for adding always positive.
        return ans;

    }

    private static String addstr(String a, String b) {
        String ans = "";
        int carry = 0;
        int size1 = a.length() - 1; // store lengths

        int size2 = b.length() - 1;

        int d1, d2, sum;

        if (size1 < size2) {
            return addstr(b, a); // Keeping the larger of both as the first parameter (in terms of length of
                                 // string)
        }

        // while loop for smaller number
        while (size2 >= 0) {
            d1 = a.charAt(size1) - '0'; // element of first string
            size1--;

            d2 = b.charAt(size2) - '0'; // element of second string
            size2--;
            sum = d1 + d2 + carry; // adding them with carry

            ans = (sum % 10) + ans; // if it exceeds 10 then takes the unit digit and add it to the start of string
            carry = sum / 10; // the other tens digit is carry
        }

        // Rest of string in larger string
        while (size1 >= 0) {
            d1 = a.charAt(size1) - '0';
            size1--;
            sum = d1 + carry;
            ans = (sum % 10) + ans; // add the unit digit directly after adding the carry
            carry = sum / 10; // tens digit as carry

        }

        // At End if caryy still remains add it to start of string
        if (carry != 0) {
            ans = carry + ans;
        }

        return ans; // return string
    }

    private static String substr(String a, String b) {
        String ans = "";
        int borrow = 0;
        boolean negative = false;

        int size1 = a.length() - 1; // storing lengths
        int size2 = b.length() - 1;

        // if second string is larger swap the strings and assign the sign as negative
        // true
        if (size1 < size2) {
            String temp = a;
            a = b;
            b = temp;
            negative = true;
        }

        // iterating in smaller string
        while (size2 >= 0) {
            int d1 = a.charAt(size1) - '0' - borrow; // substract borrow from char of first string
            int d2 = b.charAt(size2) - '0'; // second string element
            int diff = d1 - d2;

            if (diff < 0) {
                diff += 10; // If ele1 is less than ele2 add 10 to diff and assign a borrow
                borrow = 1;
            } else {
                borrow = 0; // else no borrow
            }
            ans = diff + ans; // append in the ans string
            size1--;
            size2--;
        }
        // iterating in larger string which is left with same logics
        while (size1 >= 0) {
            int d1 = a.charAt(size1) - '0' - borrow;
            int diff = d1;

            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }

            ans = diff + ans;
            size1--;
        }
        // if the elements were swapped add - at start
        if (negative) {
            ans = "-" + ans;
        }
        return removezero(ans); // return after removing the zeros at start
    }

    private static String mulstr(String a, String b) {
        return "0";
    }

    private static String divstr(String a, String b) {
        return "0";
    }

    // Removinf zeros at start of string if any
    private static String removezero(String s) {
        int size1 = 0;
        // break when first non zero element is encountered
        while (s.charAt(size1) == '0' && size1 < s.length() - 1) {
            size1++; // Incrementing till the char is 0 from right hand side.
        }

        String num = "";// output substring
        for (int size2 = 0; size2 < s.length() - size1; size2++) {
            num += s.charAt(size1 + size2);// adding all the elements after first non zero element
        }
        return num;// return substring
    }

    @Override
    public String toString() {
        if(num.equals("0"))return num;  // if zero is encountered return the 0 string
        String ans = "";
        if (sign) {
            ans += "-";                         //Add - sign at start if sign is true
        }
        ans += num;                             //Add main string to ans
        return ans;
    } //
}
