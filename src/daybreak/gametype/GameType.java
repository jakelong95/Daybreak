package daybreak.gametype;

import java.util.AbstractSequentialList;
import java.util.Iterator;
import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import daybreak.Daybreak;
import daybreak.Entity;
import daybreak.Player;
import daybreak.Tile;
import daybreak.utils.SoundManager;

/**
 * Represents a game type in Daybreak.
 */
public abstract class GameType extends BasicGameState
{
	//List of entities in the game
	protected volatile LinkedList<Entity> entities;

	//Array containing the map
	protected Tile[][] map;

	protected Player player;

	public GameType(int mapWidth, int mapHeight)
	{
		map = new Tile[mapHeight][mapWidth];
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		Tile.loadTiles();
		SoundManager.loadSound();
		SoundManager.background1.playAsMusic(1f, 1f, true);

		player = new Player(map);

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
				Entity entity = null; //If there's an entity, we render it here

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
					//Keep a reference to the image and entity of the tile
					Tile tile = map[playerY + y][playerX + x];
					img = tile.tileImage;
					entity = tile.entity;
				}

				graphics.drawImage(img, (x + 4) * Daybreak.TILE_SIZE, (y + 4) * Daybreak.TILE_SIZE);
				
				//If there was an entity on the tile, render it
				if(entity != null)
				{
					entity.render((x + 4) * Daybreak.TILE_SIZE, (y + 4) * Daybreak.TILE_SIZE);
				}
			}
		}

		player.render(0, 0); //The coordinates aren't used because the player is centered
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(GameContainer container, StateBasedGame game, int deltaTime) throws SlickException
	{
		player.update(deltaTime);


		//Update each entity
		Iterator<Entity> iter = ((LinkedList<Entity>) entities.clone()).iterator();
		while(iter.hasNext())
		{
			Entity e = iter.next();
			e.update(deltaTime);
		}


		//Did the player die?
		if(player.getHealth() <= 0)
		{
			player.playDeathSound();

			game.enterState(Daybreak.GAMEOVER);
		}


		//Loop through each entity to check if they're still alive
		iter = ((LinkedList<Entity>) entities.clone()).iterator();
		while(iter.hasNext())
		{
			Entity e = iter.next();
			if(e.getHealth() <= 0)
			{
				map[e.getPosY()][e.getPosX()].entity = null;
				entities.remove(e);
				e.playDeathSound();
			}
		}

		update(deltaTime);

		SoundStore.get().poll(0);
	}

	@Override
	public void keyReleased(int key, char c)
	{
		if(key == Input.KEY_SPACE)
		{
			player.attack();
		}
	}

	public abstract void update(int deltaTime);
}
