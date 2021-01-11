package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		int x = randGen.nextInt(w);
		int y = randGen.nextInt(h);
		Cell cell = maze.getCell(x, y);
				
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(cell);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);

		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		ArrayList<Cell> unvisited = getUnvisitedNeighbors(currentCell);
		
		//C. if has unvisited neighbors,
		if (unvisited.size() > 0) {
			//C1. select one at random.
			int idx = randGen.nextInt(unvisited.size());
			Cell nbr = unvisited.get(idx);
			//C2. push it to the stack
			uncheckedCells.push(nbr);
			//C3. remove the wall between the two cells
			removeWalls(currentCell, nbr);
			//C4. make the new cell the current cell and mark it as visited
			currentCell = nbr;
			currentCell.setBeenVisited(true);
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
		//D. if all neighbors are visited
		else {
			//D1. if the stack is not empty
			if (uncheckedCells.size() > 0) {
				// D1a. pop a cell from the stack
				Cell popped = uncheckedCells.pop();
				// D1b. make that the current cell
				currentCell = popped;
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if (c1.getX() == c2.getX()) {
			if (c1.getY() == c2.getY() - 1) {
				// Remove south wall of c1 and north wall of c2
				c1.setSouthWall(false);
				c2.setNorthWall(false);
			}
			else if (c1.getY() == c2.getY() + 1) {
				// Remove north wall of c1 and south wall of c2
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			}
		}
		else if (c1.getY() == c2.getY()) {
			if (c1.getX() == c2.getX() - 1) {
				// Remove east wall of c1 and west wall of c2
				c1.setEastWall(false);
				c2.setWestWall(false);
			}
			else if (c1.getX() == c2.getX() + 1) {
				// Remove west wall of c1 and east wall of c2
				c1.setWestWall(false);
				c2.setEastWall(false);
			}
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> unvisitedNeighbors = new ArrayList<Cell>();
		// Check north:
		if (c.getY() > 0) {
			Cell nbr = maze.getCell(c.getX(), c.getY() - 1);
			if (!nbr.hasBeenVisited()) {
				unvisitedNeighbors.add(nbr);
			}
		}
		// Check south:
		if (c.getY() < height - 1) {
			Cell nbr = maze.getCell(c.getX(), c.getY() + 1);
			if (!nbr.hasBeenVisited()) {
				unvisitedNeighbors.add(nbr);
			}
		}
		// Check west:
		if (c.getX() > 0) {
			Cell nbr = maze.getCell(c.getX() - 1, c.getY());
			if (!nbr.hasBeenVisited()) {
				unvisitedNeighbors.add(nbr);
			}
		}
		// Check east:
		if (c.getX() < width - 1) {
			Cell nbr = maze.getCell(c.getX() + 1, c.getY());
			if (!nbr.hasBeenVisited()) {
				unvisitedNeighbors.add(nbr);
			}
		}
		return unvisitedNeighbors;
	}
}
