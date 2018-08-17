/*#################################### Assignment 2: BlockedTicTacToe  #############################################
# Nicholas Tocco
# Student Number: 250909544
# CS2210
# October 19, 2017
#
# This program was designed to represent one of the configurations that will be stored in the dictionary along with its
# integer score and level.
###################################################################################################################*/
public class TTTRecord {
	// instance variables
	private String config;
	private int level;
	private int score;

	/**
	 * creates a TTTRecord with given parameters
	 * 
	 * @param config
	 *            string configuration of gameboard
	 * @param score
	 *            associated score
	 * @param level
	 */
	public TTTRecord(String config, int score, int level) {
		this.level = level;
		this.score = score;
		this.config = config;

	}

	/**
	 * 
	 * @return string configuration
	 */
	public String getConfiguration() {
		return this.config;
	}

	/**
	 * 
	 * @return integer score
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * 
	 * @return integer level
	 */
	public int getLevel() {
		return this.level;
	}

}
