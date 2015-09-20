package daybreak.gametype;

import daybreak.Enemy;
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
				map[n][i] = new Tile(Tile.FLOOR);
			}
		}
		
		for(int n = 0; n < 11; ++n)
		{
			map[n][0] = new Tile(Tile.WALL);
			
			map[n][10] = new Tile(Tile.WALL);
		}
		
		for(int i = 1; i < 10; ++i)
		{
			map[0][i] = new Tile(Tile.WALL);
			
			map[10][i] = new Tile(Tile.WALL);
		}
		
		player.setPosition(5, 5);
		
		Enemy enemy = new Enemy(map, player);
		entities.add(enemy);
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
