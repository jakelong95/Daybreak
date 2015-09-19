package daybreak.gametype;

import daybreak.Tile;

public class TestGameType extends GameType
{
	public TestGameType(int mapWidth, int mapHeight)
	{
		super(mapWidth, mapHeight);
	}

	@Override
	public void init()
	{		
		for(int n = 0; n < 11; ++n)
		{
			for(int i = 0; i < 11; ++i)
			{
				map[n][i] = new Tile();
				map[n][i].setTileType(Tile.FLOOR);
			}
		}
		
		for(int n = 0; n < 11; ++n)
		{
			map[n][0] = new Tile();
			map[n][0].setTileType(Tile.WALL);
			map[n][0].isBlocked = true;
			
			map[n][10] = new Tile();
			map[n][10].setTileType(Tile.WALL);
			map[n][10].isBlocked = true;
		}
		
		for(int i = 1; i < 10; ++i)
		{
			map[0][i] = new Tile();
			map[0][i].setTileType(Tile.WALL);
			map[0][i].isBlocked = true;
			
			map[10][i] = new Tile();
			map[10][i].setTileType(Tile.WALL);
			map[10][i].isBlocked = true;
		}
		
		player.setPosition(5, 5);
	}

	@Override
	public void update(int deltaTime)
	{
	}

	@Override
	public int getID()
	{
		return 0;
	}
}
