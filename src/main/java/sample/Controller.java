package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jssc.SerialPort;
import jssc.SerialPortException;

public class Controller {
    @FXML
    private TextArea textAreaCom1;

    @FXML
    private TextArea textAreaCom2;

    @FXML
    private TextField com1TextField;

    @FXML
    private TextField com2TextField;

    @FXML
    private Button toCom2Btn;

    @FXML
    private Button toCom1Btn;


    private SerialPort serialPort1 = new SerialPort("COM1");
    private SerialPort serialPort2 = new SerialPort("COM2");

    @FXML
    void initialize(){
        //text Areas init
        textAreaCom1.setWrapText(true);//\n
        textAreaCom2.setWrapText(true);
        //
        textAreaCom1.setEditable(false);
        textAreaCom2.setEditable(false);
        //toCom2Btn pressed
        toCom2Btn.setOnAction(event -> {
            try {
                //open ports
                serialPort1.openPort();
                serialPort2.openPort();
                //set ports params
                serialPort1.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                serialPort2.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                //get string from com1TextField and send to com2
                String str = com1TextField.getText();
                if(str.length()>0) {
                    //code string
                    myString myString = new myString(str);
                    myString.codeStr();
                    textAreaCom1.appendText(myString.getStringToPrint());
                    //write to port
                    serialPort1.writeString(myString.getStr());

                    //com2 read
                    String strFromCom1 =serialPort2.readString();
                    //uncode
                    myString.unCodeStr(strFromCom1);
                    textAreaCom2.appendText("COM1:"+myString.getStr()+'\n');
                }

                com1TextField.clear();
                serialPort1.closePort();
                serialPort2.closePort();
            } catch (SerialPortException e){
                e.printStackTrace();
            }
        });



        //toCom1Btn pressed
        toCom1Btn.setOnAction(event -> {
            try {
                //open ports
                serialPort1.openPort();
                serialPort2.openPort();
                //set ports params
                serialPort1.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                serialPort2.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                //get string from com1TextField and send to com2
                String str = com2TextField.getText();
                if(str.length()>0) {
                    //code string
                    myString myString = new myString(str);
                    myString.codeStr();
                    textAreaCom2.appendText(myString.getStringToPrint());
                    //write to port
                    serialPort2.writeString(myString.getStr());

                    //com2 read
                    String strFromCom2 =serialPort1.readString();
                    //uncode
                    myString.unCodeStr(strFromCom2);
                    textAreaCom1.appendText("COM2:"+myString.getStr()+'\n');
                }

                com2TextField.clear();
                serialPort1.closePort();
                serialPort2.closePort();
            } catch (SerialPortException e){
                e.printStackTrace();
            }
        });
    }
}
