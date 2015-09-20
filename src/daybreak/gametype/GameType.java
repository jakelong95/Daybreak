package daybreak.gametype;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import daybreak.Daybreak;
import daybreak.Entity;
import daybreak.Player;
import daybreak.Tile;

/**
 * Represents a game type in Daybreak.
 */
public abstract class GameType extends BasicGameState
{
	//List of entities in the game
	protected LinkedList<Entity> entities;

	//Array containing the map
	protected Tile[][] map;

	protected Player player;

	public GameType(int mapWidth, int mapHeight)
	{
		map = new Tile[mapHeight][mapWidth];

		player = new Player(map);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		Tile.loadTiles();
		entities = new LinkedList<Entity>();

		init();

		player.setInput(container.getInput());		
		player.setMap(map);
	}

	public abstract void init();

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics graphics) throws SlickException
	{
		//Render 100 tiles with the player at the center
		int playerX = player.getPosX();
		int playerY = player.getPosY();

		//Render each tile around the player, and any entities on the tile
		for(int x = -5; x <=  5; ++x)
		{
			for(int y = -5; y <= 5; ++y)
			{
				Image img = null;
				Image entityImage = null; //If there's an entity, render it here

				//Make sure the tile exists
				if(playerY + y < 0 || playerY + y >= map.length ||
						playerX + x < 0 || playerX + x >= map[0].length
						|| map[playerY + y][playerX + x] == null)
				{
					//If it doesn't, use a black tile
					img = Tile.blackTile;
				}
				else
				{
					img = map[playerY + y][playerX + x].tileImage;

					//Check if there's an entity in the tile
					if(map[playerY + y][playerX + x].entity != null)
					{
						entityImage = map[playerY + y][playerX + x].entity.getImage();
					}
				}

				graphics.drawImage(img, (x + 4) * Daybreak.TILE_SIZE, (y + 4) * Daybreak.TILE_SIZE);
				if(entityImage != null)
				{
					graphics.drawImage(entityImage, (x + 4) * Daybreak.TILE_SIZE, (y + 4) * Daybreak.TILE_SIZE);
				}
			}
		}

		player.render();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int deltaTime) throws SlickException
	{
		player.update(deltaTime);

		//Update each entity
		for(Entity e : entities)
		{
			e.update(deltaTime);
		}

		update(deltaTime);
	}

	public abstract void update(int deltaTime);
}
