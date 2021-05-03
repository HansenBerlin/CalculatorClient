package Server;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import Interfaces.IServerImplementation;

public class ServerImplementation extends UnicastRemoteObject implements IServerImplementation
{

	private static final long serialVersionUID = -6488630703870051651L;

	public ServerImplementation() throws RemoteException 
    {
		super();
	}


	public String createClearStringWithoutParanthesis(String input) throws ScriptException
    {
		System.out.println("Request to calculate: " + input);
        while(input.contains("("))
        {
            String replace = "(" + replaceParanthesis(input) + ")";
            String result = calculateArrayWithoutParanthesis(replace);
            input = input.replace(replace, result);
        }
        return calculateArrayWithoutParanthesis(input);
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


    private String calculateArrayWithoutParanthesis(String evaluate) throws ScriptException
    {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String testReturn = engine.eval(evaluate).toString();
        System.out.println(testReturn);
        return testReturn;
    }  

    
    public boolean checkForValidUserInput(String input)
    {
        int paranthesis = 0;

        for (int i = 0; i < input.length(); i++) 
        {
            if (input.charAt(i) == '(')
                paranthesis++;
            else if (input.charAt(i) == ')')
                paranthesis--;            
        }

        if (paranthesis == 0)
            return true;
        else
            return false;
    }

}
