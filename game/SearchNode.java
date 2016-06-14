package game;
/*
 * Data structure used to store the eight puzzle states 
 * along with keeping track of the parent node, its depth, 
 * its "id" and the bookkeeping info. 
 */


public class SearchNode {
	
	private EightPuzzle currentState;
	private SearchNode parent;
	private int gCost; 
	private int hCost;
	private int fCost;
	private String nodeID;
	private int depth = 0;
	
	/*
	 * Constructor for root node
	 */
	public SearchNode(EightPuzzle s)
	{
		currentState = s;
		parent = null;
		gCost = 0;
		hCost = 0;
		fCost = 0;
		nodeID = currentState.stateToString();
	}

	/*
	 * Standard constructor
	 */
	public SearchNode(SearchNode prev, EightPuzzle s, int gCost, int hCost) {
		
		parent = prev;
		currentState = s; 
		this.gCost = gCost;
		this.hCost = hCost;
		fCost = gCost + hCost;
		nodeID = currentState.stateToString();
	}
	/*
	 * Constructor for depth if needed (Ex. IDDFS)
	 */
	public SearchNode(SearchNode prev, EightPuzzle s, int gCost, int hCost, int depth) {
		
		parent = prev;
		currentState = s; 
		this.gCost = gCost;
		this.hCost = hCost;
		fCost = gCost + hCost;
		nodeID = currentState.stateToString();
		this.depth = depth;
	}

	public EightPuzzle getCurrentState() {
		return currentState;
	}

	public SearchNode getParent() {
		return parent;
	}

	public int getgCost() {
		return gCost;
	}

	public int gethCost() {
		return hCost;
	}

	public int getfCost() {
		return fCost;
	}
	public String toString(){
		return nodeID;
	}
	public int getDepth(){
		return depth;
	}
}
