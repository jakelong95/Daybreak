package daybreak;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import daybreak.utils.SoundManager;

/**
 * Represents an enemy (a vampire). 
 */
public class Enemy extends Entity
{
	public static final int DEFAULT_HEALTH = 10;
	
	//Damage to deal to the player
	public static final int DAMAGE = 1;
	
	private long timeSinceCalculation;	
	private long timeSinceMove;
	private long timeSinceAttack;
	
	//How long to wait between calculations and moves in milliseconds
	private static final int CALCULATION_TIME = 500;
	private static final int MOVE_TIME = 300;
	private static final int ATTACK_TIME = 1000;
	
	//List of movements to take in (x,y) format
	private Stack<Coordinate> movements;
	
	//Reference to the player
	private Player player;
	
	/**
	 * Creates a new Enemy.
	 * @param map Reference to the game map.
	 * @param player Reference to the player.
	 */
	public Enemy(Tile[][] map, Player player)
	{
		super(map, "gfx/Antagonist.png");
		
		timeSinceCalculation = 0;
		timeSinceMove = 0;
		
		hurtSound = SoundManager.mobDamage;
		deathSound = SoundManager.mobDeath;
		
		movements = new Stack<Coordinate>();
		
		this.player = player;
		
		calculatePath();
		
		setHealth(DEFAULT_HEALTH);
	}

	@Override
	public void update(int deltaTime)
	{
		timeSinceCalculation += deltaTime;
		timeSinceMove += deltaTime;
		timeSinceAttack += deltaTime;
		
		//Is it time to recalculate the path?
		if(timeSinceCalculation >= CALCULATION_TIME)
		{
			timeSinceCalculation = 0;
			
			calculatePath();
		}
		
		
		//Is it time to move?/ is there a movement calculated?
		if(timeSinceMove >= MOVE_TIME && movements.size() != 0)
		{
			timeSinceMove = 0;
			
			//Move will be the new coordinates in (x,y) format
			Coordinate move = movements.pop();

			setPosition(move.x, move.y);
		}
		
		if(isAdjacentToPlayer())
		{			
			//First, make the enemy face the player
			if(player.getPosX() > posX)
			{
				direction = Entity.DIRECTION_RIGHT;
			}
			else if(player.getPosX() < posX)
			{
				direction = Entity.DIRECTION_LEFT;
			}
			else if(player.getPosY() > posY)
			{
				direction = Entity.DIRECTION_DOWN;
			}
			else if(player.getPosY() < posY)
			{
				direction = Entity.DIRECTION_UP;
			}
			
			if(timeSinceAttack >= ATTACK_TIME)
			{
				timeSinceAttack = 0;
				player.updateHealth(-DAMAGE);
			}
		}
	}

	/**
	 * Calculates a path to the player.
	 */
	private void calculatePath()
	{
		//Previous coordinate used to reach each coordinate along the path
		Map<Coordinate, Coordinate> pred = new HashMap<Coordinate, Coordinate>();
		
		//Whether or not a coordinate set has been visited
		boolean[][] visited = new boolean[map.length][map[0].length];
		
		//Default to no coordinates having been visited
		for(int n = 0; n < visited.length; ++n)
		{
			for(int i = 0; i < visited[0].length; ++i)
			{
				visited[n][i] = false;
			}
		}
		
		//Array of coordinates used to generate path
		Coordinate[][] coordinates = new Coordinate[map.length][map[0].length];
		
		for(int n = 0; n < coordinates.length; ++n)
		{
			for(int i = 0; i < coordinates[0].length; ++i)
			{
				Coordinate coord = new Coordinate();
				coord.x = i;
				coord.y = n;
				coordinates[n][i] = coord;
			}
		}
		
		//Destination coordinates (player's location)
		Coordinate dest = coordinates[player.getPosY()][player.getPosX()];
		
		//Coordinates to check
		Queue<Coordinate> toCheck = new LinkedList<Coordinate>();
		
		Coordinate cur = coordinates[posY][posX];
		
		visited[cur.y][cur.x] = true;
		pred.put(cur, null);
		pred.put(dest, null);
		
		toCheck.add(cur);
		
		boolean complete = false; //Whether or not the destination has been reached
				
		//Keep going until we run out of coordinates to check
		while(!toCheck.isEmpty() && !complete)
		{
			cur = toCheck.poll();
			
			//Check each orthogonal neighbor of cur
			for(int x = -1; x <= 1; ++x) //Only check -1 and 1
			{
				//Make sure nothing goes out of bounds
				if(cur.x + x >= map[0].length || cur.x + x < 0)
				{
					continue;
				}
				
				for(int y = -1; y <= 1; ++y) //Only check -1 and 1
				{
					if((cur.y + y >= map.length || cur.y + y < 0) || //Make sure nothing goes out of bounds
							visited[cur.y + y][cur.x + x] || //Skip anything that was already visited
							map[cur.y + y][cur.x + x] == null || //Make sure it's not a null tile
							!map[cur.y + y][cur.x + x].canEnemyPass || //Make sure the enemy can walk there
							Math.abs(x) == Math.abs(y)) //Only check the orthogonals
					{
						continue;
					}

					Coordinate newCoord = coordinates[cur.y + y][cur.x + x];
					visited[newCoord.y][newCoord.x] = true;
					pred.put(newCoord, cur);
					toCheck.add(newCoord);
					
					//Check if we're finished
					if(newCoord.equals(dest))
					{
						complete = true;
						break;
					}
				}
				
				if(complete)
				{
					break;
				}
			}
		} //end while
				
		//Construct the path as a stack
		movements.clear(); //Clear any previous data
		
		//Check if a path was actually constructed
		if(pred.get(dest) == null)
		{
			return;
		}
		
		cur = pred.get(dest); //Go to a square adjacent to the player
		
		while(pred.get(cur) != null)
		{
			movements.push(cur);
			cur = pred.get(cur);
		}
	}
	
	/**
	 * Checks whether this enemy is adjacent to the player.
	 * @return True if this enemy is adjacent to the player, false otherwise.
	 */
	private boolean isAdjacentToPlayer()
	{
		boolean adjacent = false;
		
		for(int x = -1; x <= 1; ++x)
		{
			for(int y = -1; y <= 1; ++y)
			{
				if(map[posY + y][posX + x] == null)
				{
					continue;
				}
				
				//Don't check the tile containing
				if(Math.abs(x) == Math.abs(y))
				{
					continue;
				}
				
				if(map[posY + y][posX + x].entity instanceof Player)
				{
					adjacent = true;
				}
			}
		}
		
		return adjacent;
	}
	
	/**
	 * Represents a coordinate in the map.
	 */
	private class Coordinate
	{
		public int x;
		public int y;
		
		/**
		 * Checks if this Coordinate is at the same location as the specified Coordinate.
		 * @param other Coordinate to compare to.
		 * @return True if the Coordinates are at the same location.
		 */
		public boolean equals(Coordinate other)
		{
			return (this.x == other.x) && (this.y == other.y);
		}
	}
}
