package algorithms;

import java.util.PriorityQueue;

import game.Board;
import game.EightPuzzle;
import game.SearchNode;

public class UniformCost extends SearchAlgorithm{
	public void search(Board[] board,int h) {
		SearchNode root = new SearchNode(new EightPuzzle(board));
		p = new PriorityQueue<SearchNode>(new gComparator());
		p.add(root);
		explored.add(root.toString());
		runSearch(h);
	};
	@Override
	public void runSearch(int h) {
		while(!p.isEmpty()){
			SearchNode currentNode = p.remove();
			time++;
			if(!currentNode.getCurrentState().isGoal()){
				successors = currentNode.getCurrentState().genSuccessors();
				for(EightPuzzle e: successors){
					SearchNode child = new SearchNode(currentNode, e, e.getCost(), 0);
					if(!explored.contains(child.toString())){
						explored.add(child.toString());
						p.add(child);
						maxPQueueSize();
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
