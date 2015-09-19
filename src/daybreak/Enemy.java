package daybreak;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents an enemy (a vampire). 
 */
public class Enemy extends Entity
{
	//Time since path was last calculated
	private long timeSinceCalculation;
	
	//Time since last move
	private long timeSinceMove;
	
	//How long to wait between calculations and moves in milliseconds
	private static final int CALCULATION_TIME = 3000;
	private static final int MOVE_TIME = 100;
	
	//List of movements to take in (x,y) format
	private Queue<int[]> movements;
	
	public Enemy(Tile[][] map)
	{
		super(map);
		
		timeSinceCalculation = 0;
		timeSinceMove = 0;
		
		movements = new LinkedList<int[]>();
	}

	@Override
	public void update(int deltaTime)
	{
		timeSinceCalculation += deltaTime;
		timeSinceMove += deltaTime;
		
		//Is it time to move?
		if(timeSinceMove >= MOVE_TIME)
		{
			timeSinceMove = 0;
			
			//TODO Move
		}
		
		//Is it time to recalculate the path?
		if(timeSinceCalculation >= CALCULATION_TIME)
		{
			timeSinceCalculation = 0;
			
			//TODO Calculate path
		}
	}
}
