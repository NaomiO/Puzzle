package algorithms;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import game.Board;
import game.EightPuzzle;
import game.SearchNode;

public abstract class SearchAlgorithm {

	protected Deque<SearchNode> q = new LinkedList<>(); //Double ended linked list used to implement both stack and queue
	protected PriorityQueue<SearchNode> p;				//Priority queue used to sort based on cost and/or heuristic estimate
	protected HashSet<String> explored = new HashSet<String>(); //Stores search nodes as strings 
	protected List<EightPuzzle> successors; //List of successor states 
	protected int time = 0;
	protected int cost = 0;
	protected int space=0;
	protected int size;//aka "Length" 

	public abstract void search(Board[] board,int h);
	protected abstract void runSearch(int h);
/*
 * Takes in a search node and traces the path back to the root node 
 * and prints out that path along with other benchmark data.
 * @param SearchNode 
 */
	protected void getSolution(SearchNode goalNode){
		Stack<SearchNode> path = new Stack<>();
		path.push(goalNode);
		while(goalNode.getParent()!= null){
			path.push(goalNode.getParent());
			goalNode = goalNode.getParent();
		}
		size = path.size();
		System.out.print("Moves: ");
		for(int i=0; i<size; i++){
			cost += path.peek().getgCost();
			System.out.print(path.pop().getCurrentState().getMove()+ " ");
		}
		System.out.println();
		System.out.println("Length: "+ size);
		System.out.println("Cost: "+ cost);
		System.out.println("Time: " +time);
		System.out.println("Space: " +space);
	}
	/*
	 * Used to find the max size of a queue
	 */
	protected void maxQueueSize(){
		if(q.size()>space){
			space = q.size();
		}
	}
	/*
	 * Used to find the max size of a priority queue
	 */
	protected void maxPQueueSize(){
		if(p.size()>space){
			space = p.size();
		}
	}
	/*
	 * Comparators for the Priority Queue to be used with 
	 * Best-First, Uniform Search Cost, and A* algorithms
	 */
	protected class hComparator implements Comparator<SearchNode>{

		@Override
		public int compare(SearchNode o1, SearchNode o2) {
			return o1.gethCost()-o2.gethCost();
		}
		
	}
	protected class gComparator implements Comparator<SearchNode>{

		@Override
		public int compare(SearchNode o1, SearchNode o2) {
			return o1.getgCost()-o2.getgCost();
		}
		
	}
	protected class fComparator implements Comparator<SearchNode>{

		@Override
		public int compare(SearchNode o1, SearchNode o2) {
			return o1.getfCost()-o2.getfCost();
		}
		
	}
}
