package searchesAbstraction;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;

import application.Maze;

public abstract class MazeSearch 
{
	protected Maze maze;					// The maze being solved
	protected Point goal;	
	protected Point current;	
	protected Collection<Point> data;
	protected boolean searchOver = false;
	protected boolean searchResult = false;
	
	public MazeSearch(Maze mazeBlocks, Point startPoint, Point goalPoint)
	{
		maze = mazeBlocks;
		goal = goalPoint;
		current = startPoint;
		maze.markPath(current);
		
	}
	
	public boolean step()
	{
		// Don't keep computing after goal is reached or determined impossible.
		if(searchOver)
		{
			return searchResult;
		}
		else
		{
			return continueStep();
		}
	}
	
	protected boolean continueStep()
	{
		Collection<Point> neighbors = getNeighbors();
		
		// Choose one to be a part of the path
		Point next = chooseNeighbor(neighbors);
		
		// mark the next step
		if(next!=null)
		{
			maze.markPath(next);
			recordLink(next);
		}
		else
		{
			visitPoint();
		}
		
		//pre-existing methods
		resetCurrent();
		checkSearchOver();
		return searchResult;	
	}
	




	
	protected abstract void visitPoint();
	
	protected Collection<Point> getNeighbors(){
		List<Point> maybeNeighbors = new ArrayList<>();
		maybeNeighbors.add(new Point(current.x-1,current.y));
		maybeNeighbors.add(new Point(current.x+1,current.y));
		maybeNeighbors.add(new Point(current.x,current.y+1));
		maybeNeighbors.add(new Point(current.x,current.y-1));
		List<Point> neighbors = new ArrayList<>();
		for(Point p: maybeNeighbors){
			if(maze.inBounds(p)){
				neighbors.add(p);
			}
		}
		return neighbors;
	}
	
	//different for random
	protected Point chooseNeighbor(Collection<Point> neighbors)
	{
		for(Point p: neighbors){
			if(maze.get(p)==Maze.EMPTY){
				return p;
			}
		}
		return null;
	}
	
	protected abstract void recordLink(Point next);
	
	protected abstract void resetCurrent();
	
	protected void checkSearchOver(){
		if(data!= null && data.isEmpty()) {
			searchOver = true;
			searchResult = false;
		}
		if(isGoal(current)){
			searchOver = true;
			searchResult = true;
		}
	}
	
	protected boolean isGoal(Point square){
		return square!= null && square.equals(goal);
	}

}
