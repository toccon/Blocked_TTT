/*#################################### Assignment 2: BlockedTicTacToe  #############################################
# Nicholas Tocco
# Student Number: 250909544
# CS2210
# October 19, 2017
#
# This class implements all the methods needed by algorithm computerPlay to play BlockedTicTacToe
###################################################################################################################*/
public class BlockedTicTacToe {
	// instance variables
	private int board_size;
	private int inline;
	private int max_levels;
	private char[][] gameboard;

	/**
	 * constructor
	 * 
	 * @param board_size
	 *            integer size of the n x n board
	 * @param inline
	 *            number of elements you must get in a line to win
	 * @param max_levels
	 *            maximum amount of levels the computer will simulate up to
	 */
	public BlockedTicTacToe(int board_size, int inline, int max_levels) {
		this.board_size = board_size;
		this.inline = inline;
		this.max_levels = max_levels;
		gameboard = new char[board_size][board_size];

		// fill the gameboard with empty spaces
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				gameboard[i][j] = ' ';
			}
		}

	}

	/**
	 * method create dictionary returns a new TTTDictionary of default size
	 * 
	 * @return
	 */
	public TTTDictionary createDictionary() {
		return new TTTDictionary();

	}

	/**
	 * checks whether the string representing the content of the gameboard is in
	 * the configurations dictionary or not
	 * 
	 * @param configurations
	 *            dictionary containing gameboard configurations
	 * @return associated score if the entry is in the dictionary, -1 if it is
	 *         not
	 */
	public int repeatedConfig(TTTDictionary configurations) {
		String totalstring = "";
		// append every character in the board to one total string
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				totalstring += gameboard[j][i];
			}
		}
		// if the configuration is in the dictionary returns the score else
		// returns -1
		if (configurations.get(totalstring) != null) {
			return configurations.get(totalstring).getScore();
		} else {
			return -1;
		}
	}

	/**
	 * inserts the given configuration score and level into the configurations
	 * dictionary
	 * 
	 * @param configurations
	 * @param score
	 * @param level
	 */
	public void insertConfig(TTTDictionary configurations, int score, int level) {
		String totalstring2 = "";
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				totalstring2 += gameboard[j][i];
			}
		}
		configurations.put(new TTTRecord(totalstring2, score, level));
	}

	/**
	 * stores the character symbol in gameboard[row][col]
	 * 
	 * @param row
	 *            row index
	 * @param col
	 *            column index
	 * @param symbol
	 *            symbol
	 */
	public void storePlay(int row, int col, char symbol) {
		gameboard[row][col] = symbol;
	}

	/**
	 * returns true if gameboard[row][col] is empty otherwise returns false
	 * 
	 * @param row
	 *            row index
	 * @param col
	 *            column index
	 * @return true if the square is empty, false otherwise
	 */
	public boolean squareIsEmpty(int row, int col) {
		if (gameboard[row][col] == ' ') {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * returns true if there are 'inline' number of adjacent occurrences of
	 * symbol in the same row, column, or diagonal
	 * 
	 * @param symbol
	 * @return true if it is a winning gameboard, false if not
	 */
	public boolean wins(char symbol) {
		if (checkrows(symbol) || checkcols(symbol) || checkforwarddiag(symbol) || checkbackwardsdiag(symbol)) {
			return true;
		}
		return false;
	}

	/**
	 * returns true if the gameboard has no empty positions left and no player
	 * has won the game
	 * 
	 * @return true if draw, false if not
	 */
	public boolean isDraw() {
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				if (gameboard[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * evaluates the board and returns an integer value from 0-3 depending on
	 * the conditions
	 * 
	 * @return 0 if human wins, 1 if game is a draw, 2 if the game is undecided,
	 *         3 if the computer has won
	 */
	public int evalBoard() {
		if (wins('o')) {
			return 3;
		} else if (wins('x')) {
			return 0;
		} else if (isDraw()) {
			return 1;
		} else {
			return 2;
		}

	}

	/**
	 * checks rows for winning conditions
	 * 
	 * @param symbol
	 *            symbol to check for
	 * @return true if there is a winning row of symbols, false otherwise
	 */
	public boolean checkrows(char symbol) {
		int inarow = 0;

		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < (board_size - (inline - 1)); j++) {
				while (gameboard[i][j] == symbol) {
					j++;
					inarow++;
					if (inarow == inline) {
						return true;
					}
				}
				inarow = 0;
			}
		}
		return false;
	}

	/**
	 * checks columns for winning conditions
	 * 
	 * @param symbol
	 * @return true if win in a column, false if not
	 */
	public boolean checkcols(char symbol) {
		int inarow = 0;

		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < (board_size - (inline - 1)); j++) {
				while (gameboard[j][i] == symbol) {
					j++;
					inarow++;
					if (inarow == inline) {
						return true;
					}
				}
				inarow = 0;
			}
		}
		return false;
	}

	/**
	 * checks forward diagonal lines for winning conditions
	 * 
	 * @param symbol
	 * @return true if won, false if not
	 */
	public boolean checkforwarddiag(char symbol) {
		int inarow = 0;

		for (int i = 0; i < (board_size - (inline - 1)); i++) {
			for (int j = 0; j < (board_size - (inline - 1)); j++) {
				while (gameboard[j][i] == symbol) {
					i++;
					j++;
					inarow++;
					if (inarow == inline) {
						return true;
					}
				}
				inarow = 0;
			}
		}
		return false;
	}

	/**
	 * checks backwards diagonal lines for winning conditions
	 * 
	 * @param symbol
	 * @return true if game has been won, false if not
	 */
	public boolean checkbackwardsdiag(char symbol) {
		int inarow = 0;

		for (int i = board_size - 1; i > 0 + (inline - 2); i--) {
			for (int j = 0; j < (board_size - (inline - 1)); j++) {
				while (gameboard[j][i] == symbol) {
					j++;
					i--;
					inarow++;
					if (inarow == inline) {
						return true;
					}
				}
				inarow = 0;
			}
		}
		return false;
	}

}
