package algorithms;

import game.EightPuzzle;
import game.SearchNode;

public class SumStrategy implements HeuristicStrategy{
	private SearchAlgorithm algorithm;

	SumStrategy(SearchAlgorithm b){
		this.algorithm = b;
	}
	public void search(){
		while(!algorithm.p.isEmpty()){
			SearchNode currentNode = algorithm.p.remove();
			algorithm.time++;

			if(!currentNode.getCurrentState().isGoal()){
				algorithm.successors = currentNode.getCurrentState().genSuccessors();
				for(EightPuzzle e: algorithm.successors){
					SearchNode child = new SearchNode(currentNode, e, e.getCost(), e.getSumOfTilesOutOfPlace());
					if(!algorithm.explored.contains(child.toString())){
						algorithm.explored.add(child.toString());
						algorithm.p.add(child);
						algorithm.maxPQueueSize();
					}
				}
			}
			else{
				algorithm.getSolution(currentNode);
				break;
			}
		}
	}
}
