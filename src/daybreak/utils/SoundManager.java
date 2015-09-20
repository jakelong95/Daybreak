package daybreak.utils;

import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

//Supports Ogg, WAV, XM, and AIF formats. At least two of those are real formats.
public class SoundManager {
	//Declare your sounds ahead of time here.
	/** A wav sound effect */
	private Audio sound1;
	/** The wav backgroudn music */
	public static Audio background1;
	public static Audio background2;
	public static Audio background3;
	public static Audio background4;
	public static Audio background5;
	public static Audio background6;
	public static Audio playerDeath;
	public static Audio mobDeath;
	public static Audio playerDamage;
	public static Audio mobDamage;
	public static Audio doorOpen;
	public static Audio crossbowShoot;
	public static Audio sword;

	/**
	 * Load the sound files.
	 */
	public static void loadSound() {
		//Load sounds in here.
		try {
			background1 = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("aud/Background sounds/day 11.wav"));
			background2 = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("aud/Background sounds/day 20.wav"));
			background3 = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("aud/Background sounds/day 24.wav"));
			background4 = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("aud/Background sounds/day 35.wav"));
			background5 = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("aud/Background sounds/day 44.wav"));
			background6 = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("aud/Background sounds/day 50 1.wav"));


			playerDeath = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("aud/Flesh sounds/ya died.wav"));
			mobDeath = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("aud/Flesh sounds/they died.wav"));

			playerDamage =AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("aud/Flesh sounds/Damage-us.wav"));
			mobDamage = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("aud/Flesh sounds/Damage-them.wav"));

			doorOpen = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("aud/Other/Wall Crumbling.wav"));

			crossbowShoot = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("aud/Weapon sounds/Crossbow shoot.wav"));
			sword = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("aud/Weapon sounds/Sword shing.wav"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}