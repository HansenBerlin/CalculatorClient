import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class BasicFXMLController
{
    static int recSteps = 0;
    
    @FXML
    private Text textOutputField;  

    @FXML
    public void initialize() 
    {
        //loggedInAsTextUpperRight.setText("nicht eingeloggt");
        //dropdownCustomerAccounts.setVisible(false);   
        //textAccountBalance.setVisible(false);
        //textAccountType.setVisible(false);
    }
    @FXML
    private void onKeyReleased(ActionEvent e)
    {
    }

    @FXML
    private void numberButtonPressed(ActionEvent e) throws ScriptException
    {
        Button btn = (Button) e.getSource();
        String id = btn.getId();
        switch (id) 
        {
            case "buttonOne":
                textOutputField.setText(textOutputField.getText() + "1");                
                break;
            case "buttonTwo":
                textOutputField.setText(textOutputField.getText() + "2");                
                break;
            case "buttonThree":
                textOutputField.setText(textOutputField.getText() + "3");                
                break;
            case "buttonFour":
                textOutputField.setText(textOutputField.getText() + "4");                
                break;
            case "buttonFive":
                textOutputField.setText(textOutputField.getText() + "5");                
                break;
            case "buttonSix":
                textOutputField.setText(textOutputField.getText() + "6");                
                break;
            case "buttonSeven":
                textOutputField.setText(textOutputField.getText() + "7");                
                break;
            case "buttonEight":
                textOutputField.setText(textOutputField.getText() + "8");                
                break;
            case "buttonNine":
                textOutputField.setText(textOutputField.getText() + "9"); 
                break;
            case "buttonZero":
                textOutputField.setText(textOutputField.getText() + "0");                
                break; 
            case "buttonPlus":
                textOutputField.setText(textOutputField.getText() + "+");                
                break; 
            case "buttonMultiply":
                textOutputField.setText(textOutputField.getText() + "*");                
                break; 
            case "buttonMinus":
                textOutputField.setText(textOutputField.getText() + "-");                
                break; 
            case "buttonDivide":
                textOutputField.setText(textOutputField.getText() + "/");                
                break; 
            case "buttonParaOpen":
                textOutputField.setText(textOutputField.getText() + "(");                
                break; 
            case "buttonParaClose":
                textOutputField.setText(textOutputField.getText() + ")");                
                break; 
            case "buttonCE":
                textOutputField.setText("");                
                break;                   
            case "buttonEquals":
                textOutputField.setText(textOutputField.getText() + "=" + createClearString(textOutputField.getText()));   
                break;  
            default:
                break;
        }
    }  

    private String createClearString(String input) throws ScriptException
    {
        // (77+7*8(6+7))

        while(input.contains("("))
        {
            String replace = "(" + replaceParanthesis(input) + ")";
            String result = calculateTest(replace);
            input = input.replace(replace, result);
        }
        return calculateTest(input);
    }

    private String replaceParanthesis(String input)
    { 
        int paranthesisCount = 0;
        int tempIteratorVariable = 0;
        
        for (int i = 0; i < input.length(); i++) 
        {
            if (input.charAt(i) == '(')
            {
                paranthesisCount++;
                tempIteratorVariable = i+1;

                while (paranthesisCount != 0) 
                {
                    if (input.charAt(tempIteratorVariable) == '(')                    
                        paranthesisCount++;
                    
                    else if (input.charAt(tempIteratorVariable) == ')')                    
                        paranthesisCount--;                        
                                         
                    tempIteratorVariable++;                                
                }
                return replaceParanthesis(input.substring(i+1, tempIteratorVariable-1));                
            }
        }
        return input;
    }    

    private String calculateTest(String evaluate) throws ScriptException
    {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String testReturn = engine.eval(evaluate).toString();
        System.out.println(testReturn);
        return testReturn;
    }

    private void calculateWithoutParanthesis(String input)
    {
        String tempSequence = "";
        int tempFirst = 0;
        int tempSecond = 0;        

        for (int i = 0; i < input.length(); i++) 
        {
            if (input.charAt(i) == '*' || input.charAt(i) == '/' )
            {
                if(tempFirst == 0)
                    tempFirst = Integer.parseInt(tempSequence);
                else
                {
                    tempSecond = Integer.parseInt(tempSequence);

                }
                tempSequence = "";
            }
            else if ((input.charAt(i) == '-' || input.charAt(i) == '+') && !input.contains("*") && !input.contains("/"))
            {
                tempFirst = Integer.parseInt(tempSequence);
                tempSequence = "";
            }
            else
            {
                tempSequence += input.charAt(i);
            }
        }
    }    
}