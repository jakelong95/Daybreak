package daybreak.entity;

import org.newdawn.slick.opengl.Texture;

import daybreak.Daybreak;
import daybreak.Tile;

public abstract class AnimatedEntity extends Entity
{

	public AnimatedEntity(Tile[][] map)
	{
		super(map);
		// TODO Auto-generated constructor stub
	}

	protected Texture[] animationTextures;
	
	public void init()
	{
		this.animationTextures = Daybreak.textureLoader.getAnimation(this.type);
		this.original_width = animationTextures[0].getTextureWidth();
		this.original_height = animationTextures[0].getTextureHeight();
	
	}
	
	public abstract void draw();
	
	// This field set if player is charging a blast
	public boolean displayAnimation;
	
	// This field hold the blast charge..
	protected float animationCursor;
	
	protected float animationSpeed = 4.4f;
	

	public void startAnimation()
	{
		this.displayAnimation = true;
		
	}
	public void stopAnimation()
	{
		this.displayAnimation = false;
		this.animationCursor = 0;
		
	}
}