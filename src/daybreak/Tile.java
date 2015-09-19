package daybreak;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Tile
{
	//Tile types
	public static final int FLOOR = 0; //Walkable background
	public static final int WALL = 1;  //Wall that cannot be passed through
	public static final int EXTERIOR_DOOR = 2;  //Closed door that player cannot pass through but enemies can enter. Can shoot through.
	public static final int INTERIOR_DOOR = 3; //Closed door that player can pass through once it opens.
	
	public int terrainType; //The terrain type
	public Image tileImage; 
	//public Rectangle SrcRect; //The source from which we get the tile (may be unecessary?)
	//public Vector2d Location //Where we will draw it on screen.
	public boolean canEnemyPass; //If true, the enemy can pass through the tile
	public boolean canPlayerPass; //If true, the player cannot pass through the tile
	public boolean canShootThrough;//If true, the player can shoot through the tile
	
	public static Image[] tileGFX; //Array of tiles to populate base.
	public static Image blackTile; //Just a black image
	
	//The entity that is in the tile
	public Entity entity;
	
	public Tile()
	{
		//Default to a passable tile
		canEnemyPass = false;
		canPlayerPass = true;
		canShootThrough = true;
		
		entity = null;
	}
	
	//There is no error handling here
	public Tile(int tileType)
	{
		System.out.println(tileType);
		terrainType = tileType;
		tileImage = tileGFX[tileType];
	
		switch(tileType)
		{
		case FLOOR:
			canEnemyPass = true;
			canPlayerPass = true;
			canShootThrough = true;
			break;
		case WALL:
			canEnemyPass = false;
			canPlayerPass = false;
			canShootThrough = false;
		case EXTERIOR_DOOR://Enemies can pass, player cannot. 
			canEnemyPass = true;
			canPlayerPass = false;
			canShootThrough = true;
		case INTERIOR_DOOR:
			canEnemyPass = false;
			canPlayerPass = false;
			canShootThrough = false;
		}
		
		entity = null;
	}
	
	public void setTileType(int tileType)
	{
		terrainType = tileType;
		tileImage = tileGFX[tileType];
	}
	
	public static void loadTiles() throws SlickException
	{
		//Create the array of tile images
		tileGFX = new Image[4]; 
		
		//Load the tile images
		tileGFX[FLOOR] = new Image("gfx/Floor.bmp");
		tileGFX[WALL] = new Image("gfx/Wall.bmp");
		tileGFX[INTERIOR_DOOR] = new Image("gfx/Door.bmp");
		tileGFX[EXTERIOR_DOOR] = tileGFX[INTERIOR_DOOR];
		
		blackTile = new Image("gfx/Black.bmp");
	}
}