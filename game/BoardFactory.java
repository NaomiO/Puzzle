package game;
/*
 * Static factory that generates the types of boards that will be needed 
 * to run the puzzle.
 */
public class BoardFactory {
	private static final int SIZE = 9;
	
	public static Board[] newBoard(String boardType){
		
		if(boardType == "winState"){
			Board[] winState = new Board[SIZE];
			winState[0] = new Board(1, 1);
			winState[1] = new Board(0, 0);
			winState[2] = new Board(0, 1);
			winState[3] = new Board(0, 2);
			winState[4] = new Board(1, 2);
			winState[5] = new Board(2, 2);
			winState[6] = new Board(2, 1);
			winState[7] = new Board(2, 0);
			winState[8] = new Board(1, 0);
			return winState;
		}
		else if (boardType == "easy") {
			Board[] easy = new Board[SIZE];
			easy[0] = new Board(2,1);
			easy[1] = new Board(0,0);
			easy[2] = new Board(1,2);
			easy[3] = new Board(0,1);
			easy[4] = new Board(0,2);
			easy[5] = new Board(2,2);
			easy[6] = new Board(1,1);
			easy[7] = new Board(2,0);
			easy[8] = new Board(1,0);
			return easy;
		}
		else if (boardType == "medium") {
			Board[] medium = new Board[SIZE];
			medium[0] = new Board(1, 0);
			medium[1] = new Board(0, 2);
			medium[2] = new Board(0, 0);
			medium[3] = new Board(1, 2);
			medium[4] = new Board(1, 1);
			medium[5] = new Board(2, 2);
			medium[6] = new Board(2, 1);
			medium[7] = new Board(2, 0);
			medium[8] = new Board(0, 1);
			return medium;
		}
		else {
			Board[] hard = new Board[SIZE];
			hard[0] = new Board(1, 1);
			hard[1] = new Board(2, 2);
			hard[2] = new Board(2, 1);
			hard[3] = new Board(2, 0);
			hard[4] = new Board(1, 0);
			hard[5] = new Board(0, 0);
			hard[6] = new Board(0, 1);
			hard[7] = new Board(0, 2);
			hard[8] = new Board(1, 2);
			return hard;

		}
	}
}
