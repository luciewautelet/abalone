import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;

class XOBoard extends Pane{
	public XOBoard(XOUltimateBoard ultboard){
		board = new int[3][3];
		renders = new XOPiece[3][3];
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++) {
			board[i][j] = EMPTY;
			renders[i][j] = null;
		}

		winner = 0;
		back = new Rectangle();
		back.setFill(Color.BLACK);
		h1 = new Line(); h2 = new Line();
		v1 = new Line(); v2 = new Line();
		h1.setStroke(Color.WHITE);
		h2.setStroke(Color.WHITE);
		v1.setStroke(Color.WHITE);
		v2.setStroke(Color.WHITE);
		
		h1.setStartX(0); h1.setStartY(0); h1.setEndY(0);
		h2.setStartX(0); h2.setStartY(0); h2.setEndY(0);
		
		v1.setStartX(0); v1.setStartY(0); v1.setEndX(0);
		v2.setStartX(0); v2.setStartY(0); v2.setEndX(0);
		
		ch_one = new Translate(0, 0);
		ch_two = new Translate(0, 0); 
		h1.getTransforms().add(ch_one);
		h2.getTransforms().add(ch_two); 
		cw_one = new Translate(0, 0); 
		cw_two = new Translate(0, 0); 
		v1.getTransforms().add(cw_one); 
		v2.getTransforms().add(cw_two); 
		
		getChildren().addAll(back, h1, h2, v1, v2); 
		
		xoboard = ultboard;
		case_left =9;
	}
	
	public void resize(double width, double height)
	{
		super.resize(width, height);
		
		cell_width = width / 3.0; 
		cell_height = height / 3.0;
		
		back.setWidth(width); back.setHeight(height);
		ch_one.setY(cell_height); ch_two.setY(2 * cell_height); 
		h1.setEndX(width); h2.setEndX(width); 
		
		cw_one.setX(cell_width); cw_two.setX(2 * cell_width); 
		v1.setEndY(height); v2.setEndY(height); 
		
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++)
			{
				if (board[i][j] != 0)
				{
					renders[i][j].relocate(i * cell_width, j * cell_height);
					renders[i][j].resize(cell_width, cell_height);
					
				}
			}
		}
	}
	
	public void resetGame(){
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++){
				board[i][j] = 0;
				getChildren().remove(renders[i][j]);
				renders[i][j] = null;
			}
		}
		winner = 0;
		change_color();
		case_left = 9;
	}

	
	
	public void placePiece(final double x, final double y){
		int indexx = (int) (x / cell_width); 
		int indexy = (int) (y / cell_height);

		
		if (board[indexx][indexy] == EMPTY && xoboard.getCurrent_player() == XPIECE)
		{
			board[indexx][indexy] = XPIECE;
			renders[indexx][indexy] = new XOPiece(XPIECE);
			renders[indexx][indexy].resize(cell_width , cell_height);
			renders[indexx][indexy].relocate(indexx * cell_width, indexy * cell_height);
			getChildren().add(renders[indexx][indexy]);
			xoboard.setCurrentPlayer(OPIECE);
			case_left -= 1;
		}
		else if (board[indexx][indexy] == EMPTY && xoboard.getCurrent_player() == OPIECE) {
			board[indexx][indexy] = OPIECE;
			renders[indexx][indexy] = new XOPiece(OPIECE);
			renders[indexx][indexy].resize(cell_width, cell_height);
			renders[indexx][indexy].relocate(indexx * cell_width, indexy * cell_height);
			getChildren().add(renders[indexx][indexy]);
			xoboard.setCurrentPlayer(XPIECE);
			case_left -= 1;
		}
	}
	

	
	public void change_color()
	{
		if (winner == 1)
		{
			h1.setStroke(Color.RED);
			h2.setStroke(Color.RED);
			v1.setStroke(Color.RED);
			v2.setStroke(Color.RED);
		}
		else if (winner == 2)
		{
			h1.setStroke(Color.GREEN);
			h2.setStroke(Color.GREEN);
			v1.setStroke(Color.GREEN);
			v2.setStroke(Color.GREEN);
		}
		else
		{
			h1.setStroke(Color.WHITE);
			h2.setStroke(Color.WHITE);
			v1.setStroke(Color.WHITE);
			v2.setStroke(Color.WHITE);
		}
	}
	
	public int case_left;;
	public boolean active;
	public int[][] board;
	private XOPiece[][] renders;
	private Rectangle back;
	private Line h1, h2, v1, v2;
	public double cell_width, cell_height;
	public int winner;
	
	private Translate ch_one, ch_two, cw_one, cw_two;
	
	private XOUltimateBoard xoboard;
	private final int EMPTY = 0;
	private final int XPIECE = 1;
	private final int OPIECE = 2;
}