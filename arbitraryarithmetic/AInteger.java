package arbitraryarithmetic;

public class AInteger {
    // Defining member variables of a and sign of input
    public String num;// number without sign.
    public boolean sign;// true if negative

    // AInteger Contructorfor Defualt value
    public AInteger() {
        this.num = "0";
        this.sign = false;
    }

    // Contructor for a to AInteger
    public AInteger(String string) {
        parse(string); // parsing to AInteger
    }

    // Parse function
    private AInteger parse(String string) {
        // if first char is -
        if (string.charAt(0) == '-') {
            this.sign = true; // marking true to sign variable for the AInteger
            String temp_1 = "";
            // Shifting each character by one place to right after removing first character.
            for (int j = 0; j <= string.length() - 2; j++) {
                temp_1 += string.charAt(j + 1);
            }
            string = temp_1;
        }
        // emove zeros at start
        this.num = removezero(string);

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
    public AInteger(AInteger string) {
        this.num = string.num;
        this.sign = string.sign;
    }

    // Addition Logic
    public AInteger add(AInteger string) {
        if (this.sign && string.sign) {
            return new AInteger("-" + this.adding(string)); // If both umbers were -ve add them and prefix "-"
        }
        if (this.sign && !string.sign) {
            return new AInteger(string.subing(this)); // If first number us -ve second is -ve
        }
        if (!this.sign && string.sign) {
            return new AInteger(this.subing(string)); // If first is+ve and second is -ve
        } else {
            return new AInteger(this.adding(string)); // Both Positive
        }
    }

    public AInteger sub(AInteger string) {
        if (this.sign && string.sign) {
            return new AInteger(string.subing(this)); // If both a -ve substract first from second
        }
        if (this.sign && !string.sign) {
            return new AInteger("-" + string.adding(this)); // If opoosite sign add them.
        }
        if (!this.sign && string.sign) {
            return new AInteger(string.adding(this)); // If opoosite sign add them.
        } else {
            return new AInteger(this.subing(string)); // If both a +ve substract second from first
        }
    }

    public AInteger mul(AInteger string) {
        AInteger ans = new AInteger();

        ans.num = mulstr(this.num, string.num); // call the mul a function

        if (this.sign == string.sign) {
            ans.sign = false; // if same sign then the resukt will have +ve sign
        }

        else {
            ans.sign = true; // else negtive
        }
        return ans;
    }

    public AInteger div(AInteger string) {
        // Handling the division by zero case if the second element is 0;
        if (string.num.equals("0")) {
            System.out.println("Division by zero error");
            System.exit(0);
            return new AInteger();

        } else {
            AInteger ans = new AInteger();
            ans.num = divstr(this.num, string.num); // call function

            if (this.sign == string.sign)
                ans.sign = false; // same sign gives positive result

            else {
                ans.sign = true; // else negative result
            }
            return ans;
        }
    }

    // Adding the AInteger values only when both the strings are positive
    private AInteger adding(AInteger string) {
        // Defining to strings to pass to the helper functions
        String temp_1 = string.num;
        String temp_2 = this.num;
        AInteger ans = new AInteger(); // to store the ouput

        ans.num = addstr(temp_1, temp_2); // calling helper func addstr

        ans.sign = false; // for adding always positive.
        return ans;
    }

    private AInteger subing(AInteger string) {
        String temp_1 = string.num;
        String temp_2 = this.num;
        AInteger ans = new AInteger(); // to store the ouput

        if (this.num.length() < string.num.length()) {
            ans.num = substr(string.num, this.num); // Substracting bigger number from smaller and changinf the value of sign if swapped
            ans.sign = true;
        } else if (this.num.length() > string.num.length()) {
            ans.num = substr(this.num, string.num);
            ans.sign = false;
        } else {
            int i = 0;
            // chechking similarity of digits from left hand side
            while (i < this.num.length() && this.num.charAt(i) == string.num.charAt(i)) {
                i++;
            }
            // if all the digits matched return 0
            if (i == this.num.length()) {
                ans.num = "0";
                ans.sign = false;
            } else if (this.num.charAt(i) < string.num.charAt(i)) {
                ans.num = substr(string.num, this.num); // substract the lexicographical larger from the smaller and updating the sign if swapped again
                ans.sign = true;
            } else {
                ans.num = substr(this.num, string.num);
                ans.sign = false;
            }
        }
        return ans; // return ans AInteger
    }

    private static String addstr(String string, String string_2) {
        String ans = "";
        int carry = 0;
        int size1 = string.length() - 1; // store lengths

        int size2 = string_2.length() - 1;

        int ele1, ele2, sum;

        if (size1 < size2) {
            return addstr(string_2, string); // Keeping the larger of both as the first parameter (in terms of length of string)
        }

        // while loop for smaller number
        while (size2 >= 0) {
            ele1 = string.charAt(size1) - '0'; // element of first a
            size1--;

            ele2 = string_2.charAt(size2) - '0'; // element of second a
            size2--;
            sum = ele1 + ele2 + carry; // adding them with carry

            ans = (sum % 10) + ans; // if it exceeds 10 then takes the unit digit and add it to the start of string
            carry = sum / 10; // the other tens digit is carry
        }

        // Rest of string in larger string
        while (size1 >= 0) {
            ele1 = string.charAt(size1) - '0';
            size1--;
            sum = ele1 + carry;
            ans = (sum % 10) + ans; // add the unit digit directly after adding the carry
            carry = sum / 10; // tens digit as carry

        }

        // At End if caryy still remains add it to start of string
        if (carry != 0) {
            ans = carry + ans;
        }

        return ans; // return string
    }

    private static String substr(String string, String string_2) {
        String ans = "";
        int borrow = 0;
        boolean negative = false;

        int size1 = string.length() - 1; // storing lengths
        int size2 = string_2.length() - 1;

        // if second string is larger swap the strings and assign the sign as negative
        // true
        if (size1 < size2) {
            String temp = string;
            string = string_2;
            string_2 = temp;
            negative = true;
        }

        // iterating in smaller string
        while (size2 >= 0) {
            int ele1 = string.charAt(size1) - '0' - borrow; // substract borrow from char of first string
            int ele2 = string_2.charAt(size2) - '0'; // second string element
            int diff = ele1 - ele2;

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
            int ele1 = string.charAt(size1) - '0' - borrow;
            int diff = ele1;

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

    private static String mulstr(String string, String string_2) {
        int temp = 0;
        int size1 = string.length(); // storing lengths
        int size2 = string_2.length();
        int[] result = new int[size1 + size2];
        // runming for loop for each element as we do in elementary multiplication
        for (int i = size1 - 1; i >= 0; i--) {
            for (int j = size2 - 1; j >= 0; j--) {
                temp = result[i + j + 1] + (string.charAt(i) - '0') * (string_2.charAt(j) - '0');// multiply the single char
                result[i + j + 1] = temp % 10;// add the unit digit
                result[i + j] += temp / 10;// add carry to next element
            }
        }
        String ans = "";
        for (int i = 0; i < result.length; i++) {
            ans += result[i]; // add result in the ans from char to string
        }
        return removezero(ans);// return after removingzeros
    }

    private static String divstr(String string, String string_2) {
        if (string_2.equals("0")) {
            return "Division by zero error"; // rechecking division by zero error
        }
        if (string.equals("0")) {
            return "0"; // if first element is 0 dont proceed
        }
        int size1 = string.length(); // storing lengths
        int size2 = string_2.length();
        if (size1 < size2)// if smaller first element then return 0;
        {
            return "0";
        }

        // initialize remainder with first digits of string
        int[] remainder = new int[size2];
        for (int i = 0; i < size2; i++) {
            remainder[i] = string.charAt(i) - '0';
        }

        // make array for result digits
        int[] result = new int[size1 - size2 + 1];
        for (int i = 0; i < result.length; i++) {
            int count = 0;
            // build current remainder string
            String rem = "";
            for (int k = 0; k < remainder.length; k++) {
                rem += (char) (remainder[k] + '0');// add character found after calculation after parsing to char
            }

            // subtract divisor repeatedly
            while (rem.length() > size2 || (rem.length() == size2 && rem.compareTo(string_2) >= 0)) {
                rem = substr(rem, string_2); // subtract string_2 from rem till rem becomes smaller
                rem = removezero(rem); // remove leading zeros in each iteration
                count++;
            }

            if (i + size2 < size1) {
                rem += string.charAt(i + size2);
            }
            rem = removezero(rem);

            // update remainder array for next iteration
            remainder = new int[rem.length()];
            for (int j = 0; j < rem.length(); j++) {
                remainder[j] = rem.charAt(j) - '0';// remainder is element at string for computation
            }
            result[i] = count;
        }

        // initialize finak output stirng
        String ans = "";
        for (int i = 0; i < result.length; i++) {
            ans += (char) (result[i] + '0'); // add in the final string after parsing to char
        }
        return removezero(ans);// return after removing zeros
    }

    // Removing zeros at start of string if any
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
        if (num.equals("0"))
            return num; // if zero is encountered return the 0 string
        String ans = "";
        if (sign) {
            ans += "-"; // Add - sign at start if sign is true
        }
        ans += num; // Add main string to ans
        return ans;
    } //
}
