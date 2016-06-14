package algorithms;
import java.util.PriorityQueue;

import game.Board;
import game.EightPuzzle;
import game.SearchNode;

public class BestFirst extends SearchAlgorithm{
	private HeuristicStrategy strategy;
	/*
	 * Implements the abstract search() method in SearchAlgorithm.
	 */
	@Override
	public void search(Board[] board,int h) {
		SearchNode root = new SearchNode(new EightPuzzle(board));
		p = new PriorityQueue<SearchNode>(new hComparator());//comparator based on h(n)
		p.add(root);
		explored.add(root.toString());
		runSearch(h);
	};
	/**
	 * Implements the abstract runSearch() method in SearchAlgorithm.
	 * Based on the argument passed, initializes a heuristic, and uses it to 
	 * solve the algorithm.
	 * @param Integer 1-3 
	 */
	@Override
	protected void runSearch(int h) {
		switch(h){
		case 1:
			strategy = new TilesOutOfPlaceStrategy(this);
			strategy.search();
			break;
		case 2:
			strategy = new ManhattanStrategy(this);
			strategy.search();
			break;
		case 3:
			strategy = new SumStrategy(this);
			strategy.search();
			break;
		default:
			System.out.println("Invalid entry!");
			break;
		}

	}
}
