package daybreak.gametype;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import daybreak.Entity;
import daybreak.Tile;

/**
 * Represents a game type in Daybreak.
 */
public abstract class GameType extends BasicGameState
{
	//List of entities in the game
	protected LinkedList<Entity> entities;
	
	public GameType()
	{
		entities = new LinkedList<Entity>();
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		Tile.loadTiles();
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics graphics) throws SlickException
	{
		//Render 100 tiles with the player at the center
		//TODO See line 26
		
		//Draw each entity
		for(Entity e : entities)
		{
			e.render();
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int deltaTime) throws SlickException
	{
		//Update each entity
		for(Entity e : entities)
		{
			e.update(deltaTime);
		}
	}
}
