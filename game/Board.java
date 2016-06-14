package game;
/*
 * Board class used to represent coordinates 
 * (x,y) or (row,column) on a N x N matrix
 */
public class Board{
	public int row;
	public int column;
	
	public Board(int row, int column){
		this.row = row;
		this.column = column;
	}
	public String toString(){
		StringBuilder b = new StringBuilder();
		b.append('(');
		b.append(row);
		b.append(", ");
		b.append(column);
		b.append(')');
		return b.toString();
	}
	public boolean equals(Board o){
		return this.row == o.row && 
				this.column == o.column;
	}
}
