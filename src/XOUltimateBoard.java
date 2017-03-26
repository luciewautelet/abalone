import javax.swing.JOptionPane;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

class XOUltimateBoard extends Pane{
	public XOUltimateBoard(){
		board_basic = new XOBoard[3][3];
		gl = new GameLogic();
		getChildren().add(ver1);
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++){
				board_basic[i][j] = new XOBoard(this);
				getChildren().add(board_basic[i][j]);
			}
		}
		current_player = XPIECE;
		first = true;

	}
	
	
	public void resize(double width, double height)
	{
		super.resize(width, height);
		
		cell_width  = width / 3;
		cell_height = height / 3;

		
		ver1.setStartX(width / 3);
		ver1.setEndX(width / 3);
		ver1.setEndY(height);
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++)
			{
				board_basic[i][j].relocate(i * cell_width, j * cell_height);
				board_basic[i][j].resize(cell_width, cell_height);
			}
		}
	}
	
	public void resetGame(){
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++){
				board_basic[i][j].resetGame();
			}
		}
	}
	
	public void placePiece(final double x, final double y){
		int i = (int) (x / cell_width);
		int j = (int)(y / cell_height);
		
		int indexx = (int) (x % cell_width); 
		int indexy = (int) (y % cell_height);
		

		if (first == true || board_basic[i][j].case_left == 0 || gl.check_turn(i, j) )
		{
			first = false;
		board_basic[i][j].placePiece(indexx, indexy);
		gl.set_last_turn((int) (indexx / board_basic[i][j].cell_width), (int) (indexy / board_basic[i][j].cell_height));
		gl.check_win(board_basic[i][j]);
		win_final = gl.check_win_final(board_basic);
		if (win_final == 1 || win_final == 2)
		{
			JOptionPane.showMessageDialog(null,"player " + win_final + " won");
			resetGame();
		}
	}
}
	
	public int getCurrent_player(){
		
		return current_player;
	}
	
	public void setCurrentPlayer(int player){
		current_player = player;
	}
	
	private GameLogic gl;
	private double cell_width, cell_height;
	private XOBoard[][] board_basic;
	private int win_final;
	
	private int current_player;

	private final int XPIECE = 1;
	private Line ver1;
	private boolean first;
}
	