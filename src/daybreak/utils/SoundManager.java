package daybreak.utils;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;
 
//Supports Ogg, WAV, XM, and AIF formats. At least two of those are real formats.
public class SoundManager {
	//Declare your sounds ahead of time here.
	/** A wav sound effect */
	private Audio sound1;
	/** The wav backgroudn music */
	private Audio background;

 
	/**
	 * Start the test 
	 */
	public void start() {
		initGL(800,600);
		init();
		//Just like, make a blank screen or something. It's for an example.
		//(obviously change this for the real one; we'll just have a soundmanager exist
		//in the background).
		while (true) {
			update();
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			render();
 
			Display.update();
			Display.sync(100);
 
			if (Display.isCloseRequested()) {
				Display.destroy();
				AL.destroy();
				System.exit(0);
			}
		}
	}
 
	/**
	 * Initialise the GL display
	 * 
	 * @param width The width of the display
	 * @param height The height of the display
	 */
	private void initGL(int width, int height) {
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.create();
			Display.setVSyncEnabled(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
 
		//Not sure what it does, but we can't run without it.
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);        
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);                    
 
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);                
        GL11.glClearDepth(1);                                       
 
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
 
        GL11.glViewport(0,0,width,height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
 
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
 
    /**
    * Initialise resources
    */
    public void init() {
    	//Load sounds in here.
        try {
	    // you can play oggs by loading the complete thing into 
	    // a sound
	    sound1 = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("aud/Background sounds/day 11.wav"));
 
	    background = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("aud/Flesh sounds/ya died.wav"));
        } catch (IOException e) {
	    e.printStackTrace();
	}
    }
 
	/**
	 * Game loop update
	 */
	public void update() {
		//Obviously there's less contrived times to play a sound.
		while (Keyboard.next()) {//Apparently keyboard.next is a thing.
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_Q) {
					// play as a one off sound effect
					sound1.playAsSoundEffect(1.0f, 1.0f, false);
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_W) {
					// replace the music thats curretly playing with 
					// the wav
					background.playAsMusic(1.0f, 1.0f, true);
				}
			}
		}
 
		// Polling is required to allow streaming to get a chance to
		// queue buffers.
		SoundStore.get().poll(0);
	}
 
	/**
	 * Game loop render
	 */
	public void render() {
 
	}
 
	/**
	 * Main method
	 * This is just a sample of how we'd want to use this.
	 * We should have a global SoundManager that can invoke
	 * sounds based on events.
	 */
	public static void main(String[] argv) {
		SoundManager soundExample = new SoundManager();
		soundExample.start();
	}
}