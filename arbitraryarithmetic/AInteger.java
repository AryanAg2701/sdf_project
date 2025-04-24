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
        if (a.charAt(0) == '-') {
            this.sign = true;
            String x = "";
            for (int j = 0; j <= a.length() - 2; j++) {
                x += a.charAt(j + 1);
            }
            a = x;
        }
        this.num = removezero(a);

        if (this.num.length() == 0) {
            this.num = "0";
        }
        AInteger ans=new AInteger();
        ans.num=this.num;
        ans.sign=this.sign;
        return ans;
    }

    //Contructor to assign a AInteger using other predefined.
    public AInteger(AInteger a) {
        this.num = a.num;
        this.sign = a.sign;
    }

    //Addition Logic
    public AInteger add(AInteger a) {
        if (this.sign && a.sign) {
            return new AInteger("-" + adding(a));
        }
        if (this.sign && !a.sign) {
            return new AInteger(a.subing(this));
        }
        if (!this.sign && a.sign) {
            return new AInteger(this.subing(a));
        }
        return new AInteger(adding(a));
    }

    //Adding the AInteger values only when both the strings are positive
    public AInteger adding(AInteger a) {
        String x = a.num;
        String y = this.num;
        AInteger ans = new AInteger();
        ans.num = addstr(x, y);
        ans.sign = false;
        return ans;
    }

    public AInteger subing(AInteger a) {

    }

    private static String addstr(String a,String b) {

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
