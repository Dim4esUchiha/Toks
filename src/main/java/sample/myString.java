package sample;

public class myString {
    String startFlag = "01111110";
    private String str;
    static int i = 0;
    myString(String s){
        this.str = s;
    }

    void codeStr(){
        byte[] bytes = this.str.getBytes();
        StringBuilder binary = new StringBuilder();
        for(byte b : bytes){
            int val = b;
            for(int i=0;i<8;++i) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            //binary.append(' ');
        }

        //1
        System.out.println("String to code:  " + this.str);
        //
        StringBuilder strData = new StringBuilder();
        strData.append(binary.toString());
        this.str = strData.toString();

        //2
        System.out.println("Binary coded string:   " + this.str);
        //
        //coding str (if flag("0111111x") find x = 1)

        StringBuffer sb = new StringBuffer(this.str);
        int index = 0;
        while((index = sb.indexOf("0111111",index))!=-1){
            //System.out.println(index);
            index+=7;
            sb.insert(index,'1');
        }

        this.str = sb.toString();
        //3
        System.out.println("Coded string to send:  " + this.str);
        //
    }

    void unCodeStr(String st){
        //delete 1 if need
        StringBuffer sb = new StringBuffer(st);
        int index =0;
        while((index = sb.indexOf("0111111",index))!=-1){
            index+=7;
            sb.deleteCharAt(index);
        }


        st = sb.toString();
        //4
        System.out.println("Uncode(readyToBUnCo):  " + st);
        //
        StringBuilder stringBuilder = new StringBuilder();
        String string = new String();
        for(int i = 0;i<st.length();i+=8){
            string = st.substring(i,i+8);
            stringBuilder.append((char) Integer.parseInt(string,2));
        }

        st = stringBuilder.toString();
        this.str = st;
        //5
        System.out.println("Uncoded string from com1:  "+st);
        //
    }

    String getStringToPrint(){
        return ("Packet " + i++ + ":\n" + startFlag + "| " + str + "\n");
    }

    String getStr(){ return str; }

}
