import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

class CustomControl extends Control {
	public CustomControl()
	{
		setSkin(new CustomControlSkin(this));
		xoboard= new XOUltimateBoard();
		getChildren().add(xoboard);
		
		// add a mouse clicked listener that will try to place a piece 
		setOnMouseClicked(new EventHandler<MouseEvent>() {
			// overridden handle method 
			@Override public void handle(MouseEvent event)
			{ xoboard.placePiece(event.getX(), event.getY()); }
			});
		
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.SPACE)
					xoboard.resetGame();
			}
		});
	}

	public void resize(double width, double height){
		super.resize(width, height);
		xoboard.resize(width, height); 
	}
	
	private XOUltimateBoard xoboard;
}