package game;

import java.util.ArrayList;
import java.util.Arrays;

public class EightPuzzle {

	/*
	 * The SIZE of the puzzle.
	 * The hole will be represented by the number 0.
	 * The index of the array will be the number of the tile but the array itself will consist of coordinates.
	 */
	private static final int SIZE  = 9; 
	private static final int HOLE  = 0; 
	private Board[] currentBoard   = new Board[SIZE];  
	private final Board[] winState = BoardFactory.newBoard("winState");

	private int manhattanDistance     = 0;
	private int tilesOutOfPlace       = 0; 
	private int sumOfTilesOutOfPlace  = 0;
	private int costOfMoving  		  = 0;

	private String move = "START";
	/*
	 *  Constructor for root node 
	 */
	public EightPuzzle(Board[] newBoard){
		this.currentBoard = newBoard;
		findTilesOutOfPlace();
		findManDist();
		sumOfTilesOutOfPlace();
	}
	/*
	 * Constructor for child nodes
	 */
	public EightPuzzle(Board[] newBoard,int cost, String move){
		this.currentBoard = newBoard;
		findTilesOutOfPlace();
		findManDist();
		sumOfTilesOutOfPlace();
		costOfMoving = cost;
		this.move = move;
	}

	/*
	 * Copying the current board so that I can return an updated new
	 * puzzle when the move is made. 
	 */
	private EightPuzzle moveTile(int tile){
		Board[] newBoard = new Board[SIZE];
		newBoard = Arrays.copyOf(currentBoard, SIZE);
		Board temp = newBoard[HOLE];
		newBoard[HOLE] = newBoard[tile];
		newBoard[tile] = temp;
		return new EightPuzzle(newBoard,tile, setMove(newBoard, HOLE, tile));
	}
	
	/*
	 * Helper method that'll eventually print out the move made for a given state.
	 */
	private String setMove(Board[] temp, int hole, int tile){
		int result = 0;
		String nextMove;
		if (temp[hole].row != temp[tile].row ){
			result = temp[hole].row - temp[tile].row;  
			if (result>0) nextMove = "DOWN";
			else nextMove = "UP";
		}
		else{
			result =temp[hole].column - temp[tile].column;
			if (result >0) nextMove = "RIGHT";
			else nextMove = "LEFT";
		}
		return nextMove;
	}
	/*
	 * Checks to see if tile is moveable by seeing where it is in relation to the HOLE.
	 * 1. Checks if they are adjacent 
	 * 2. Checks if they are on the same column/row
	 */
	private boolean isMoveable(Board tile){
		if(currentBoard[HOLE].row == tile.row){
			return Math.abs(currentBoard[HOLE].column-tile.column) == 1;
		}
		else if(currentBoard[HOLE].column == tile.column){
			return Math.abs(currentBoard[HOLE].row-tile.row) == 1;
		}
		else return false;
	}

	/*
	 * Move all tiles that are moveable and return their respective states
	 */
	public ArrayList<EightPuzzle> genSuccessors(){
		ArrayList<EightPuzzle> states = new ArrayList<EightPuzzle>();
		for(int i=1; i<SIZE;i++){
			if(isMoveable(currentBoard[i])){
				states.add(moveTile(i));
			}
		}
		return states;
	}
	/*
	 * Standard algo for finding the Manhattan distance
	 */
	private void findManDist(){
		for(int i=0; i<SIZE; i++){
			manhattanDistance += Math.abs(currentBoard[i].row-winState[i].row) + Math.abs(currentBoard[i].column-winState[i].column);
		}
	}
	/*
	 * Method for finding all the tiles out of place in the current state of the puzzle
	 */
	private void findTilesOutOfPlace(){
		for(int i=0; i<SIZE; i++){
			if(!currentBoard[i].equals(winState[i])){
				tilesOutOfPlace++;
			}
		}
	}
	/*
	 * Since the total cost g(n) goes up by the value of the tile moved, 
	 * then keeping track of a total sum should be an admissible heuristic.
	 */
	private void sumOfTilesOutOfPlace(){
		for(int i=0; i<SIZE; i++){
			if(!currentBoard[i].equals(winState[i])){
				sumOfTilesOutOfPlace += i;
			}

		}
	}
	/*
	 * Compares this board (currentBoard) with the winning state to see if this is the one.
	 */
	public boolean isGoal(){
		for (int i=0; i<9; i++)
			if (!currentBoard[i].equals(winState[i])) return false;
		return true;
	}
	
	public int getManhattanDistance() {
		return manhattanDistance;
	}

	public int getTilesOutOfPlace() {
		return tilesOutOfPlace;
	}

	public int getSumOfTilesOutOfPlace() {
		return sumOfTilesOutOfPlace;
	}
	public int getCost(){
		return costOfMoving;
	}
	public String getMove(){
		return move;
	}
	/*
	 * Represents puzzle in a string format: Example "123804765"
	 */
	public String stateToString(){
		
		StringBuilder s = new StringBuilder();
		int location[][] = new int[3][3];
		for (int i=0; i<9; i++)
			location[currentBoard[i].row][currentBoard[i].column] = i;
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++)
				s.append(location[i][j]);
		}
		return s.toString();
		
	}
	/*
	 * Represents puzzle in a 2 dimensional string format:
	 * Example:
	 * 1 2 3
	 * 8 0 4
	 * 7 6 5 
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("\n");
		int location[][] = new int[3][3];
		for (int i=0; i<9; i++)
			location[currentBoard[i].row][currentBoard[i].column] = i;
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++)
				s.append(location[i][j] + " ");
			s.append("\n");
		}
		return s.toString();
	}
}
