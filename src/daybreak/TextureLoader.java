package daybreak;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

import daybreak.entity.IEntity;

public class TextureLoader implements ITextureLoader 
{
	private static Texture[] textures = new Texture[1024];
	private static Texture[][] animations = new Texture[1024][1024];
	
	
	public TextureLoader()
	{		
    }


	public void init()
	{
		textures[IEntity.FLOOR] = loadTexture("gfx/tiles/floor.bmp",0,0,64,64);
		textures[IEntity.WALL] = loadTexture("gfx/tiles/wall.bmp",0,0,64,64);
		textures[IEntity.DOOR] = loadTexture("gfx/tiles/door.bmp",0,0,64,64);
		
		animations[IEntity.PLAYER] = loadAnimation("gfx/Player1.png",0,0,64,64);
	}
	
	/* (non-Javadoc)
	 * @see rtype.ITextureLoader#getTexture(int)
	 */
	public  Texture getTexture(int textureID)
	{
		return textures[textureID];
	}
	
	/* (non-Javadoc)
	 * @see rtype.ITextureLoader#getAnimation(int)
	 */
	public  Texture[] getAnimation(int animationID)
	{
		return animations[animationID];
	}
	
	Hashtable imageCache = new Hashtable();
	
	// Offset are in term off pixel, not byte, the image loader figure out alone what is the bytesPerPixel
	private  Texture loadTexture(String path,int xOffSet, int yOffSet, int textWidth, int textHeight) {
		
		
		BufferedImage buffImage = null;
		try
		{
			if (imageCache.get(path) != null)
				buffImage = (BufferedImage)imageCache.get(path);
			else
			{
				System.out.println("Loading image:"+path);
				buffImage = ImageIO.read(this.getClass().getResource(path));
				
				//Fix for JAVA 1.6
				byte[] data = ((DataBufferByte) buffImage.getRaster().getDataBuffer()).getData();
				switch (buffImage.getType()) 
				{
					case BufferedImage.TYPE_4BYTE_ABGR:  convertFromARGBToBGRA(data);break;
					case BufferedImage.TYPE_3BYTE_BGR:  convertFromBGRToRGB(data);   break;
					default: System.out.println("Unknown type:"+buffImage.getType()); break;
				}
				
				
				
				imageCache.put(path, buffImage);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	        
		int bytesPerPixel = buffImage.getColorModel().getPixelSize() / 8;
		
		ByteBuffer scratch = ByteBuffer.allocateDirect(textWidth*textHeight*bytesPerPixel).order(ByteOrder.nativeOrder());	
		
		
		DataBufferByte dataBufferByte = ((DataBufferByte) buffImage.getRaster().getDataBuffer());
		
		for (int i = 0 ; i < textHeight ; i++)
			scratch.put(dataBufferByte.getData(),(xOffSet+(yOffSet+i)*buffImage.getWidth())*bytesPerPixel, textWidth * bytesPerPixel);
		
        scratch.rewind();

        
        // Create A IntBuffer For Image Address In Memory
        IntBuffer buf = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asIntBuffer();
        GL11.glGenTextures(buf); // Create Texture In OpenGL

         // Create Nearest Filtered Texture
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, buf.get(0));
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0,GL11.GL_RGBA,textWidth,textHeight, 0,GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, scratch);

        Texture newTexture = new Texture()
        newTexture.textureId = buf.get(0);     // Return Image Addresses In Memory
        newTexture.textureHeight = textHeight;
        newTexture.textureWidth = textWidth;
        
        return newTexture;
    }

	

	
	private  Texture[] loadAnimation(String path,int cols, int rows, int textWidth, int textHeight) 
	{
		return loadAnimation(path,cols,rows,textWidth,textHeight,0,0);
	}

	private  Texture[] loadAnimation(String path,int cols, int rows, int textWidth, int textHeight, int xOffSet, int yOffSet) 
	{
		Texture[] toReturntextures = new Texture[cols*rows];

               
        		
		for (int i=0;i< rows ; i++)
			for (int j=0;j< cols ; j++)
			{
				toReturntextures[i*cols+j] = loadTexture(path,j*textWidth+xOffSet,i*textHeight+yOffSet,textWidth,textHeight);
			}
		
		return toReturntextures;
	}

	//February 2nd, 2011: Fix for JAVA 1.6 thanks to sonicWonder for the fix
	private static void convertFromARGBToBGRA(final byte[] data) {
		for (int i = 0; i <  data.length; i += 4) 
		{
			/*
			final int a = data[i] & 0x000000FF;
			final int r = data[i + 1] & 0x000000FF;
			final int g = data[i + 2] & 0x000000FF;
			final int b = data[i + 3] & 0x000000FF;
			data[i] = (byte) b;
			data[i + 1] = (byte) g;
			data[i + 2] = (byte) r;
			data[i + 3] = (byte) a;
			*/
			
			// A small optimisation
			
			data[i] ^= data[i + 3];
			data[i+3] ^= data[i];
			data[i] ^= data[i + 3];
			
			data[i + 1] ^= data[i + 2];
			data[i + 2] ^= data[i + 1];
			data[i + 1] ^= data[i + 2];
			
		}
	}

	private static void convertFromBGRToRGB(final byte[] data) 
	{
		for (int i = 0; i < data.length; i += 3) 
		{
			/*
			final int b = data[i] & 0xFF;
			final int g = data[i + 1] & 0xFF;
			final int r = data[i + 2] & 0xFF;
			data[i] = (byte) r;
			data[i + 1] = (byte) g;
			data[i + 2] = (byte) b;
			*/
			
			// A small optimisation
			data[i] ^= data[i + 2];
			data[i + 2 ] ^= data[i ];
			data[i] ^= data[i + 2];
			
		}
	}
		//End February 2nd, 2011: Fix for JAVA 1.6 thanks to sonicWonder for the fix
}