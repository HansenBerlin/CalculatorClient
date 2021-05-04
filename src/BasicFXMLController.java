import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
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


    public BasicFXMLController(IServerImplementation serverImplementation)
    {
        this.serverImplementation = serverImplementation;
        
    }

    @FXML
    public void initialize() throws MalformedURLException, RemoteException, NotBoundException 
    {
        //serverImplementation = (IServerImplementation)Naming.lookup(url);
        //loggedInAsTextUpperRight.setText("nicht eingeloggt");
        //dropdownCustomerAccounts.setVisible(false);   
        //textAccountBalance.setVisible(false);
        //textAccountType.setVisible(false);           
    }
   

    @FXML
    private void getKeyEvents(KeyEvent key) throws RemoteException
    {
        String operators = "+-/*()";
        String input = key.getText();
        if (input.matches("[0-9]+") || operators.contains(input))
            textOutputField.setText(textOutputField.getText()+input);  
        else if (key.getCode() == KeyCode.ENTER)
            textResult.setText(serverImplementation.calculateUserInput(textOutputField.getText()));      
    }


    @FXML
    private void numberButtonPressed(ActionEvent e) throws ScriptException, RemoteException
    {
        Button btn = (Button) e.getSource();
        String id = btn.getId();
        String userInput = textOutputField.getText();

        switch (id) 
        {
            case "buttonOne":
                textOutputField.setText(userInput + "1");  

                long start = System.currentTimeMillis();
                long serverResponseAt = serverImplementation.measureResponseTime();
                long now = System.currentTimeMillis();

                System.out.println("Hinweg: " + (serverResponseAt - start));
                System.out.println("Rückweg: " + (now - serverResponseAt));

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
            case "buttonCE":
                textOutputField.setText("");                
                break;                   
            case "buttonEquals":                
                textResult.setText(serverImplementation.calculateUserInput(userInput));   
                break;  
            default:
                break;
        }
    }      
}