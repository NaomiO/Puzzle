package algorithms;

import game.Board;
import game.EightPuzzle;
import game.SearchNode;

public class IterativeDeepening extends SearchAlgorithm{
	private boolean cutOff;//flag to terminate the loop
	
	public void search(Board[] board,int h){
		SearchNode root = new SearchNode(new EightPuzzle(board));
		cutOff = false; 
		int depth = 0; //start at depth 0
		while(cutOff==false){
			runSearch(root, depth);
			depth++; //incrementally increase depth
		}
	}
	
	public void runSearch(SearchNode root, int depth){
		q.addFirst(root);//Start all over again
		while(!q.isEmpty()){
			SearchNode currentNode = q.pop();
			time++;
			if(currentNode.getCurrentState().isGoal()){//check to see if we found the goal
				cutOff = true;
				getSolution(currentNode);
				break;
			}
			if(currentNode.getDepth()==depth){//Continue popping all nodes at this depth 
				continue;
			}
			else{//When finished generate the next level of nodes
				successors = currentNode.getCurrentState().genSuccessors();
				for(EightPuzzle e: successors){
					SearchNode child = new SearchNode(currentNode,e,e.getCost(),0,currentNode.getDepth()+1);
						q.addFirst(child);
						maxQueueSize();
				}
			}
		}
	}
	@Override
	protected void runSearch(int h) {
		throw new UnsupportedOperationException();
	}
}
