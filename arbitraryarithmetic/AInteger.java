package arbitraryarithmetic;
public class AInteger {
    //Defining member variables of string and sign of input
    public String num;//number without sign.
    public boolean sign;//true if negative

    //AInteger Contructorfor Defualt value
    public AInteger() {
        this.num = "0";
        this.sign = false;
    }

    // Contructor for string to AInteger
    public AInteger(String a) {
        parse(a);       //parsing to AInteger
    }
    
    //Parse function
    private AInteger parse(String a){
        //if first char is -
        if (a.charAt(0) == '-') {
            this.sign = true;   //marking true to sign variable for the AInteger 
            String x = "";
            //Shifting each character by one place to right after removing first character.
            for (int j = 0; j <= a.length() - 2; j++)
            {
                x += a.charAt(j + 1);
            }
            a = x;
        }
        //emove zeros at start
        this.num = removezero(a);

        //Edge case if the number was zero
        if (this.num.length() == 0)
        {
            this.num = "0";
        }
        //Making a new variable to return;
        AInteger ans=new AInteger();
        ans.num=this.num;
        ans.sign=this.sign;
        return this;
    }

    //Contructor to assign a AInteger using other predefined.
    public AInteger(AInteger a) {
        this.num = a.num;
        this.sign = a.sign;
    }

    //Addition Logic
    public AInteger add(AInteger a) {
        if (this.sign && a.sign) {
            return new AInteger("-" + this.adding(a));   //If both umbers were -ve add them and prefix "-"
        }
        if (this.sign && !a.sign) {
            return new AInteger(a.subing(this));    //If first number us -ve second is -ve
        }
        if (!this.sign && a.sign) {
            return new AInteger(this.subing(a));    //If first is+ve and second is -ve
        }
        else{
        return new AInteger(this.adding(a));        //Both Positive
        }             
    }
    public AInteger sub(AInteger a) {
        if (this.sign && a.sign) {
            return new AInteger(this.subing(a));    //If both a -ve substract first from second
        }
        if (this.sign && !a.sign) {
            return new AInteger("-"+a.adding(this));    //If opoosite sign add them.
        }
        if (!this.sign && a.sign) {
            return (a.adding(this));            //If opoosite sign add them.
        }
        else{
        return new AInteger(a.subing(this));    //If both a +ve substract second from first
        }
    }

    //Adding the AInteger values only when both the strings are positive
    public AInteger adding(AInteger a) {
        //Defining to strings to pass to the helper functions
        String x = a.num;       
        String y = this.num;
        AInteger ans = new AInteger();      //to store the ouput

        ans.num = addstr(x, y);             //calling helper func addstr
        ans.sign = false;                   //for adding always positive.
        return ans;
    }

    public AInteger subing(AInteger a) {
        String x = a.num;
        String y = this.num;
        AInteger ans = new AInteger();      //to store the ouput

        ans.num = substr(x, y);             //sending to helper substr fn
        ans.sign = false;                   //for adding always positive.
        return ans;

    }

    private static String addstr(String a,String b) {
        String ans = "";
        int carry = 0;
        int i = a.length() - 1;     //store lengths

        int j = b.length() - 1;

        int d1, d2, sum;

        if (i < j) {
            return addstr(b, a);    //Keeping the larger of both as the first parameter (in terms of length of string)
        }

        //while loop for smaller number
        while (j >= 0) {
            d1 = a.charAt(i) - '0';     //element of first string
            i--;

            d2 = b.charAt(j) - '0';     //element of second string
            j--;
            sum = d1 + d2 + carry;      //adding them with carry

            ans = (sum % 10) + ans;     //if it exceeds 10 then takes the unit digit and add it to the start of string
            carry = sum / 10;           //the other tens digit is carry
        }

        //Rest of string in larger string
        while (i >= 0) {
            d1 = a.charAt(i) - '0';
            i--;
            sum = d1 + carry;       
            ans = (sum % 10) + ans;     //add the unit digit directly after adding the carry
            carry = sum / 10;           //tens digit as carry

        }
        //At End if caryy still remains add it to start of string
        if (carry != 0) 
        {
            ans = carry + ans;
        }

        return ans;         //return string
    }
    private static String substr(String a,String b) {

    }
    
    //Removinf zeros at start of string if any
    private static String removezero(String s) {
        int i = 0;
        //break when first non zero element is encountered
        while (s.charAt(i) == '0' && i < s.length() - 1) 
        {
            i++; //Incrementing till the char is 0 from right hand side.
        }

        String num = "";//output substring
        for (int j = 0; j < s.length() - i; j++) {
            num += s.charAt(i + j);//adding all the elements after first non zero element
        }
        return num;//return substring
    }
}
