import javax.swing.JOptionPane;



public class GameLogic{
	
	
	public void check_win(XOBoard b)
	{
		check_win_sec(XPIECE, 1, b);
		check_win_sec(OPIECE, 2, b);
	}
	

	public void check_win_sec(int play, int win, XOBoard b)
	{
		int align = 0;
		int tmpx, tmpy;
		for(int i = 0; i < 3; i +=1)
		{
			for (int j = 0; j < 3; j += 1)
			{
				if (b.board[i][j] == play)
				{

					if (i == 0)
					{
						tmpx = i;
						tmpy = j;
						while (b.board[tmpx][tmpy] == play && tmpx < 3)
						{
							align += 1;
							tmpx += 1;
							if (align == 3)
							{
								b.winner = win;
								b.change_color();
								return;
							}
						}
						align = 0;
					}
					if (j == 0)
					{
						tmpx = i;
						tmpy = j;
						while (b.board[tmpx][tmpy] == play && tmpy < 3)
						{
							align += 1;
							tmpy += 1;
							if (align == 3)
							{
								b.winner = win;
								b.change_color();
								return;
							}
						}
						align = 0;
					}
					if (i == 0 && j == 0)
					{
						tmpx = i;
						tmpy = j;
						while (b.board[tmpx][tmpy] == play && tmpy < 3)
						{
							align += 1;
							tmpy += 1;
							tmpx += 1;
							if (align == 3)
							{
								b.winner = win;
								b.change_color();
								return;
							}
						}
						align = 0;	
					}
					if (i == 2 && j == 0)
					{
						tmpx = i;
						tmpy = j;
						while (b.board[tmpx][tmpy] == play && tmpy < 3)
						{
							align += 1;
							tmpx -= 1;
							tmpy += 1;
							if (align == 3)
							{
								b.winner = win;
								b.change_color();
								return;
							}
						}
						align = 0;
					}
				}
			}
		}
	}
	

	public int check_win_final(XOBoard[][] board)
	{
		int tmpwin;
		int align = 0;
		int tmpx, tmpy;
		for(int i = 0; i < 3; i +=1)
		{
			for (int j = 0; j < 3; j += 1)
			{
				if (board[i][j].winner != 0)
				{
					tmpwin = board[i][j].winner;
					if (i == 0)
					{
						tmpx = i;
						tmpy = j;
						while (board[tmpx][tmpy].winner == tmpwin && tmpx < 3)
						{
							align += 1;

							if (align == 3)
							{
								return board[tmpx][tmpy].winner;

							}
							tmpx += 1;
						}
						align = 0;
					}
					if (j == 0)
					{
						tmpx = i;
						tmpy = j;
						while (board[tmpx][tmpy].winner == tmpwin && tmpy < 3)
						{
							align += 1;
							if (align == 3)
							{
								return board[tmpx][tmpy].winner;
							}

							tmpy += 1;
						}
						align = 0;
					}
					if (i == 0 && j == 0)
					{
						tmpx = i;
						tmpy = j;
						while (board[tmpx][tmpy].winner == tmpwin && tmpy < 3)
						{
							align += 1;
							if (align == 3)
							{
								return board[tmpx][tmpy].winner;
							}

							tmpy += 1;
							tmpx += 1;
						}
						align = 0;	
					}
					if (i == 2 && j == 0)
					{
						tmpx = i;
						tmpy = j;
						while (board[tmpx][tmpy].winner == tmpwin && tmpy < 3)
						{
							align += 1;
							tmpx -= 1;
							tmpy += 1;
							if (align == 3)
							{
								return board[tmpx][tmpy].winner;
							}
						}
						align = 0;
					}
				}
			}
		}
		return -1;
	}
	
	public boolean check_turn(int i, int j)
	{

		if (i == x_old && j == y_old)
		{
			return true;
		}
		else
		{
			JOptionPane.showMessageDialog(null,"you have to put a piece in the "+ x_old + ", " + y_old + "little boards");
			return false;
		}
	}
	
	public void set_last_turn(int i, int j)
	{
		x_old = i;
		y_old = j;
	}
	
	private int x_old;
	private int y_old;
	
	private final int XPIECE = 1;
	private final int OPIECE = 2;
}