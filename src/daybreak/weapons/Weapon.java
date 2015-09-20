package daybreak.weapons;
import org.newdawn.slick.Image;
import org.newdawn.slick.openal.Audio;

public abstract class Weapon {
	public int BASE_DAMAGE = 1;
    public int fireTime; //The amount of time between a trigger press and a shot fired
    public int reloadTime; //The amount of time between expending a full clip and being able to fire again
    public int ammoCapacity; //The amount of ammo in one full clip
    public int remainingAmmo; //The amount of ammo until next reload, we never run out.
    public boolean isMelee; //If false, is ranged weapon
    public boolean twoHanded; //If false, one handed
    public int maxRange; //How far will a projectile go in squares?
    public int damage; //How much damage will getting hit cost me?
    public Audio soundEffect;  //The soud the weapon will make when it swings (not when it hits)
    public Image graphic; //The image of the weapon sitting on the ground.
	
}
