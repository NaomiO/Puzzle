package algorithms;
import game.Board;
import game.EightPuzzle;
import game.SearchNode;

public class BreadthFirst extends SearchAlgorithm{

	public void search(Board[] board,int h) {
		SearchNode root = new SearchNode(new EightPuzzle(board));
		q.addLast(root);//Add at the end of the line per Queue data structure
		explored.add(root.toString());//keep track of all the explored nodes
		runSearch(h);
	}
	@Override
	protected void runSearch(int h) {
		while(!q.isEmpty()){
			SearchNode currentNode = q.remove();
			time++;//number of nodes popped off the queue
			if(!currentNode.getCurrentState().isGoal()){
				successors = currentNode.getCurrentState().genSuccessors();
				for(EightPuzzle e: successors){
					SearchNode child = new SearchNode(currentNode, e, e.getCost(), 0);
					if(!explored.contains(child.toString())){
						explored.add(child.toString());
						q.addLast(child);
						maxQueueSize();//If the q.size>space then space = q.size
					}
				}
			}
			else{
				getSolution(currentNode);//Follows the stack back to the root node and returns path
				break;
			}

		}
	}
}
