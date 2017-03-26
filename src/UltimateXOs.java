import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class UltimateXOs extends Application {
	public void init(){
		sp_mainlayout = new StackPane();
		cc_custom = new CustomControl();
		sp_mainlayout.getChildren().add(cc_custom);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("XO Ultimate game");
		primaryStage.setScene(new Scene(sp_mainlayout, 400, 300));
		primaryStage.show();
	}
	
	
	public void stop()
	{
		
	}
	
	public static void main(String[] args){
		launch(args);
	}
	
	private StackPane sp_mainlayout;
	private CustomControl cc_custom;
}