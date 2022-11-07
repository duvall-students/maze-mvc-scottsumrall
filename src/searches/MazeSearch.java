package searches;

import java.awt.Point;
import java.util.Collection;
import java.util.PriorityQueue;

import application.Maze;

public abstract class MazeSearch 
{
	private Maze maze;					// The maze being solved
	private Point goal;	
	private Point current;	
	private Collection<Point> data;
	private boolean searchOver = false;
	private boolean searchResult = false;
	
	public MazeSearch(Maze mazeBlocks, Point startPoint, Point goalPoint)
	{
		maze = mazeBlocks;
		goal = goalPoint;
		current = startPoint;
		maze.markPath(current);
	}

}
