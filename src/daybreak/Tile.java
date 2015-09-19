package daybreak;

import org.newdawn.slick.Image;


public class Tile {

	public enum TileType
	{
		Cobble, //Walkable background
		Wall, //Cannot be destroyed. Always exterior.
		CrackedWall //Cracked wall can be destroyed with rocket launcher. 
	}

	public TileType TerrainType; //The terrain type
	public Image TileGFX; 
	//public Rectangle SrcRect; //The source from which we get the tile (may be unecessary?)
	//public Vector2d Location //Where we will draw it on screen.
	public boolean isBlocked; //If true, we cannot walk or shoot through this.
	
	static Image[] tileGFX; //Array of tiles to populate base.
	
}

