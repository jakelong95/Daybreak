package daybreak;

import org.newdawn.slick.Image;


public class Tile
{
	//Tile types
	public static final int FLOOR = 0; //Walkable background
	public static final int WALL = 1;  //Wall that cannot be passed through
	public static final int DOOR = 2;  //Closed door that AI cannot pass through

	public int terrainType; //The terrain type
	public Image tileImage; 
	//public Rectangle SrcRect; //The source from which we get the tile (may be unecessary?)
	//public Vector2d Location //Where we will draw it on screen.
	public boolean isBlocked; //If true, we cannot walk or shoot through this.
	
	static Image[] tileGFX; //Array of tiles to populate base.
}

