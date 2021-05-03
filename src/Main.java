import java.rmi.Naming;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application 
{
	static String url = "//localhost/Server";

    @Override
    public void start(Stage stage) throws Exception 
    {    
		IServerImplementation serverImplementation = (IServerImplementation) Naming.lookup(url);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("testui.fxml"));
        loader.setControllerFactory(c -> 
        {
            return new BasicFXMLController(serverImplementation);
        });

        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.setRoot(root);
        stage.setScene(scene);
        stage.show();

        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("testui.fxml"));        
        //Scene scene = new Scene(root);
        //stage.setScene(scene);
        //stage.show();
    }
    
    public static void main(String[] args) 
    {   
        launch(args);		
    }      
}