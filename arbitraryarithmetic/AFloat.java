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

        // remove all decimal points
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
        // skip leading zeros
        while (i < pos && s.charAt(i) == '0') {
            i++;
        }

        int j = s.length() - 1;
        // skip trailing zeros
        while (j > pos && s.charAt(j) == '0') {
            j--;
        }

        // use only significant digits
        this.num = "";
        for (int k = i; k <= j; k++) {
            this.num += s.charAt(k);
        }

        this.dec = pos - i; // set decimal count

        //add 0 at start
        if (this.dec == 0) {
            this.num = "0" + this.num;
            this.dec = 1;
        }
        //add 0 after decimal
        if (dec == this.num.length() - 1) {
            this.num = this.num + "0";
        }

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
}
