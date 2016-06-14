package game;

import java.util.Scanner;
import algorithms.*;
/**
 * 
 * @author Marco Mera
 * Credit: https://github.com/gferrer/8-Puzzle-Solver
 * Big thanks for helping with BFS and other optimizations
 *
 */
public class Main {
	/*
	 * Standard UI/Driver class.
	 */
	private static Board[] startBoard = new Board[9];

	public static void main(String[] args){
		SearchAlgorithm algorithm;
		boolean goAgain = true;
		Scanner in = new Scanner(System.in);   
		while(goAgain != false){
			System.out.println("Select Difficulty:");
			System.out.println("1. Easy\n2. Medium\n3. Hard");		
			while (!in.hasNextInt()) 
			{        
				in.next(); 
				System.out.print("Please enter an integer!\n"); 
			}
			int input = in.nextInt(); 

			while (input<1 || input>3){
				System.out.print("Please enter an integer between 1 and 3!\n");
				input = in.nextInt();
			}

			switch(input){
			case 1: 
				System.out.println("Easy\n");
				startBoard = BoardFactory.newBoard("easy");
				break;
			case 2: 
				System.out.println("Medium\n");
				startBoard = BoardFactory.newBoard("medium");
				break;
			case 3:  
				System.out.println("Hard\n");
				startBoard = BoardFactory.newBoard("hard");
				break;
			}

			System.out.println("Select Algorithm: ");
			System.out.println("1. BFS\n2. DFS\n3. IDDFS\n4. UCS\n5. GBFS\n6. A*");		
			while (!in.hasNextInt()) 
			{        
				in.next(); 
				System.out.print("Please enter an integer!\n"); 
			}
			input = in.nextInt(); 

			while (input<1 || input >6){
				System.out.print("Please enter an integer between 1 and 6!\n");
				input = in.nextInt();
			}

			switch(input){
			case 1: 
				algorithm = new BreadthFirst();
				System.out.println();
				System.out.println("Breadth-First Search");
				System.out.println("____________________");
				algorithm.search(startBoard,0);
				break;
			case 2: 
				algorithm = new DepthFirst();
				System.out.println();
				System.out.println("Depth-First Search");
				System.out.println("____________________");
				algorithm.search(startBoard,0);

				break;
			case 3: 
				algorithm = new IterativeDeepening();
				System.out.println();
				System.out.println("Iterative Deepening Depth-First Search");
				System.out.println("____________________");
				algorithm.search(startBoard,0);
				break;
			case 4: 
				algorithm = new UniformCost();
				System.out.println();
				System.out.println("Uniform-Cost Search");
				System.out.println("____________________");
				algorithm.search(startBoard,0);
				break;
			case 5: 
				algorithm = new BestFirst();
				System.out.println("Select Heuristic: ");
				System.out.println("1. Tiles Out Of Place\n2. Manhattan Distance\n3. Sum Of Tiles Out Of Place\n");
				int heuristic = in.nextInt();
				System.out.println();
				System.out.println("Best-First Search");
				System.out.println("____________________");
				algorithm.search(startBoard,heuristic);
				break;
			case 6: 
				algorithm = new AStar();
				System.out.println("Select Heuristic:");
				System.out.println("1. Tiles Out Of Place\n2. Manhattan Distance\n3. Sum Of Tiles Out Of Place\n");
				heuristic = in.nextInt();
				System.out.println();
				System.out.println("A* Search");
				System.out.println("____________________");
				algorithm.search(startBoard,heuristic);
				break;
			}
			System.out.println();
			System.out.println("Would you like to play again? ");
			System.out.println("1. Yes\n2. No\n");
			
			input = in.nextInt();
			
			switch(input){
			case 1:
				continue;
			case 2:
				goAgain = false;
				System.out.print("Thanks for playing!");
			}
			
		}
		in.close();

	}
}
