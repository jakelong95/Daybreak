package daybreak;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


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
	public boolean canPlayerPass; //If true, the player cannot pass through the tile
	
	public static Image[] tileGFX; //Array of tiles to populate base.
	public static Image blackTile; //Just a black image
	
	public void setTileType(int tileType)
	{
		terrainType = tileType;
		tileImage = tileGFX[tileType];
	}
	
	public static void loadTiles() throws SlickException
	{
		//Create the array of tile images
		tileGFX = new Image[3]; 
		
		//Load the tile images
		tileGFX[FLOOR] = new Image("gfx/Floor.bmp");
		tileGFX[WALL] = new Image("gfx/Wall.bmp");
		tileGFX[DOOR] = new Image("gfx/Door.bmp");
		
		blackTile = new Image("gfx/Black.bmp");
	}
}

