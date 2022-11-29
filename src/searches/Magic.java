package searches;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import application.Maze;

public class Magic extends Greedy
{	
	// Keeps up with the child-parent trail so we can recreate the chosen path
		HashMap<Point,Point> childParent;

		private Maze maze;					// The maze being solved
		private Point goal;					// The goal Point - will let us know when search is successful
		private Collection<Point> data;		// Data structure used to keep "fringe" points
		private boolean searchOver = false;	// Is search done?
		private boolean searchResult = false;	// Was it successful?
		private Point current;				// Current point being explored


		public Magic(Maze mazeBlocks, Point startPoint, Point goalPoint)
		{
			super(mazeBlocks, startPoint, goalPoint);

		}

		
		/*
		 * Rather than choosing the (first) closest NON-wall, choose 
		 * any of the closest next squares.
		 */
		@Override
		protected Point chooseNeighbor(Collection<Point> neighbors){
			Point closest = closestToGoal(neighbors);
			List<Point> possibles = new ArrayList<>();
			for(Point p: neighbors){
				if(distanceToGoal(p) == distanceToGoal(closest)){
					possibles.add(p);
				}
			}
			if(!possibles.isEmpty()){
				int randIndex = (int)(Math.random()*possibles.size());
				return possibles.get(randIndex);
			}
			return null;
		}
}
