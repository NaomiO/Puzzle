package algorithms;

import game.Board;
import game.EightPuzzle;
import game.SearchNode;

public class DepthFirst extends SearchAlgorithm{

	public void search(Board[] board,int h) {
		SearchNode root = new SearchNode(new EightPuzzle(board));
		q.addFirst(root);//Add first to simulate stack data structure 
		runSearch(h);
	}
	@Override
	protected void runSearch(int h) {
		while(!q.isEmpty()){
			SearchNode currentNode = q.pop();//pop off the top of the "stack"
			time++;//Total number of nodes popped off
			if(!currentNode.getCurrentState().isGoal()){
				if(!explored.contains(currentNode.toString())){
					explored.add(currentNode.toString());
					successors = currentNode.getCurrentState().genSuccessors();
					for(EightPuzzle e: successors){
						SearchNode child = new SearchNode(currentNode, e, e.getCost(), 0);
						q.addFirst(child);
						maxQueueSize();
					}
				}
			}
			else{
				getSolution(currentNode);
				break;
			}

		}
	}
}
