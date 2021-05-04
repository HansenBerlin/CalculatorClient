import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.script.ScriptException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class BasicFXMLController
{
    IServerImplementation serverImplementation;  
    //static String url = "//localhost/Calculator";
    
    @FXML
    private Text textOutputField; 
    @FXML
    private Text textResult;
    @FXML
    private Text textServerTime;
    @FXML
    private Text textClientTime;
    @FXML
    private Text textServerIP;
    @FXML
    private Text textClientIP;
    @FXML
    private Text textResponseTime;   

    public BasicFXMLController(IServerImplementation serverImplementation, String serverUrl)
    {
        this.serverImplementation = serverImplementation;        
    }

    @FXML
    public void initialize() throws MalformedURLException, RemoteException, NotBoundException, ServerNotActiveException 
    {
        serverImplementation.printEstablishConnectionMessage();          
    }
   

    @FXML
    private void getKeyEvents(KeyEvent key) throws RemoteException
    {
        String operators = "+-/*().";
        String input = key.getText();
        if (key.isShiftDown() && input.equals("7"))
            textOutputField.setText(textOutputField.getText() + "/");  
        else if (key.isShiftDown() && input.equals("+"))
            textOutputField.setText(textOutputField.getText() + "*");  
        else if (key.isShiftDown() && input.equals("8"))
            textOutputField.setText(textOutputField.getText() + "(");
        else if (key.isShiftDown() && input.equals("9"))
            textOutputField.setText(textOutputField.getText() + ")");          
        else if (input.matches("[0-9]+") || operators.contains(input))
            textOutputField.setText(textOutputField.getText() + input);  
        else if (key.getCode() == KeyCode.ENTER || (key.isShiftDown() && input.equals("0")))
        {
            long start = new Date().getTime();                
            textResult.setText(serverImplementation.calculateUserInput(textOutputField.getText()));      
            long end = new Date().getTime();  
            textResponseTime.setText(Long.toString(end-start) + "ms");
        }
    }


    private void compareIP() throws RemoteException, ServerNotActiveException
    {       
        String[] ips = serverImplementation.getServerAndClientIP();  
        textServerIP.setText(ips[0]);
        textClientIP.setText(ips[1]);    
    }


    private void compareTime() throws RemoteException
    {        
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
        long start = new Date().getTime();                
        textServerTime.setText(serverImplementation.getServerDateAndTime());
        long end = new Date().getTime();  
        textClientTime.setText(formatter.format(new Date()));    
        textResponseTime.setText(Long.toString(end-start) + "ms");        
    }
    

    @FXML
    private void getButtonEvents(ActionEvent e) throws ScriptException, RemoteException, ServerNotActiveException
    {
        Button btn = (Button) e.getSource();
        String id = btn.getId();

        if (id.equals("btnCompareIPs"))
            compareIP();
        else if (id.equals("btnCompareTime"))
            compareTime();
        else
            updateInputFieldValue(id);
    }     


    private void updateInputFieldValue(String buttonId) throws RemoteException
    {
        String userInput = textOutputField.getText();
        switch (buttonId) 
        {
            case "buttonOne":
                textOutputField.setText(userInput + "1");
                break;
            case "buttonTwo":
                textOutputField.setText(userInput + "2");                
                break;
            case "buttonThree":
                textOutputField.setText(userInput + "3");                
                break;
            case "buttonFour":
                textOutputField.setText(userInput + "4");                
                break;
            case "buttonFive":
                textOutputField.setText(userInput + "5");                
                break;
            case "buttonSix":
                textOutputField.setText(userInput + "6");                
                break;
            case "buttonSeven":
                textOutputField.setText(userInput + "7");                
                break;
            case "buttonEight":
                textOutputField.setText(userInput + "8");                
                break;
            case "buttonNine":
                textOutputField.setText(userInput + "9"); 
                break;
            case "buttonZero":
                textOutputField.setText(userInput + "0");                
                break; 
            case "buttonPlus":
                textOutputField.setText(userInput + "+");                
                break; 
            case "buttonMultiply":
                textOutputField.setText(userInput + "*");                
                break; 
            case "buttonMinus":
                textOutputField.setText(userInput + "-");                
                break; 
            case "buttonDivide":
                textOutputField.setText(userInput + "/");                
                break; 
            case "buttonParaOpen":
                textOutputField.setText(userInput + "(");                
                break; 
            case "buttonParaClose":
                textOutputField.setText(userInput + ")");                
                break; 
            case "buttonDecimalPoint":
                textOutputField.setText(userInput + ".");                
                break; 
            case "buttonCE":
                textOutputField.setText("");  
                textResult.setText("");              
                break;                   
            case "buttonEquals":
                long start = new Date().getTime();                
                textResult.setText(serverImplementation.calculateUserInput(userInput));   
                long end = new Date().getTime();  
                textResponseTime.setText(Long.toString(end-start) + "ms");
                break;  
            default:
                break;
        }
    }     
}