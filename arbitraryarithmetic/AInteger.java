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
            return new AInteger(a.subing(this)); // If both a -ve substract first from second
        }
        if (this.sign && !a.sign) {
            return new AInteger("-" + a.adding(this)); // If opoosite sign add them.
        }
        if (!this.sign && a.sign) {
            return (a.adding(this)); // If opoosite sign add them.
        } else {
            return new AInteger(this.subing(a)); // If both a +ve substract second from first
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

        if (this.num.length() < a.num.length()) {
            ans.num = substr(a.num, this.num);      //Substracting bigger number from smaller and changinf the value of sign if swapped
            ans.sign = true;
        } else if (this.num.length() > a.num.length()) {
            ans.num = substr(this.num, a.num);
            ans.sign = false;
        } else {
            int i = 0;
            //chechking similarity of digits from left hand side
            while (i < this.num.length() && this.num.charAt(i) == a.num.charAt(i)) {
                i++;
            }
            //if all the digits matched return 0
            if (i == this.num.length()) {
                ans.num = "0";
                ans.sign = false;
            } else if (this.num.charAt(i) < a.num.charAt(i)) {
                ans.num = substr(a.num, this.num);      //substract the lexicographical larger from the smaller and updating the sign if swapped again
                ans.sign = true;
            } else {
                ans.num = substr(this.num, a.num);
                ans.sign = false;
            }
        }
        return ans; //return ans AInteger
    }

    private static String addstr(String a, String b) {
        String ans = "";
        int carry = 0;
        int size1 = a.length() - 1; // store lengths

        int size2 = b.length() - 1;

        int d1, d2, sum;

        if (size1 < size2) {
            return addstr(b, a); // Keeping the larger of both as the first parameter (in terms of length of string)
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
        int temp = 0;
        int size1 = a.length();
        int size2 = b.length();
        int[] result = new int[size1 + size2];
        for (int i = size1 - 1; i >= 0; i--) {
            for (int j = size2 - 1; j >= 0; j--) {
                temp = result[i + j + 1] + (a.charAt(i) - '0') * (b.charAt(j) - '0');
                result[i + j + 1] = temp % 10;
                result[i + j]     += temp / 10;
            }
        }
        String ans = "";
        for (int i = 0; i < result.length; i++) {
            ans += result[i];
        }
        return removezero(ans);
    }

    private static String divstr(String a, String b) {
        if (b.equals("0")) {
            return "Division by zero error";
        }
        if (a.equals("0")) {
            return "0";
        }
        int size1 = a.length();
        int size2 = b.length();
        if (size1 < size2) {
            return "0";
        }

        int[] remainder = new int[b.length()];
        for (int i = 0; i < size2; i++) {
            remainder[i] = a.charAt(i) - '0';
        }

        int[] result = new int[size1 - size2 + 1];
        for (int i = 0; i < result.length; i++) {
            int count = 0;
            String rem = "";
            for (int k = 0; k < remainder.length; k++) {
                rem += (char)(remainder[k] + '0');
            }

            while (rem.length() > size2 || (rem.length() == size2 && rem.compareTo(b) >= 0)) {
                rem = substr(rem, b);
                rem = removezero(rem);
                count++;
            }

            if (i + size2 < size1) {
                rem += a.charAt(i + size2);
            }

            rem = removezero(rem);
            remainder = new int[rem.length()];
            for (int j = 0; j < rem.length(); j++) {
                remainder[j] = rem.charAt(j) - '0';
            }

            result[i] = count;
        }

        String ans = "";
        for (int i = 0; i < result.length; i++) {
            ans += (char)(result[i] + '0');
        }
        return removezero(ans);
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
