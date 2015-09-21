package daybreak;

import org.newdawn.slick.opengl.Texture;

public interface ITextureLoader
{

	public abstract void init();

	public abstract Texture getTexture(int textureID);

	public abstract Texture[] getAnimation(int animationID);

}