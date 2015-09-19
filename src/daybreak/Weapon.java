package daybreak;
import org.newdawn.slick.Image;
import org.newdawn.slick.openal.Audio;

abstract class Weapon {
    public int FireTime; //The amount of time between a trigger press and a shot fired
    public int RelaodTime; //The amount of time between expending a full clip and being able to fire again
    public int AmmoCapacity; //The amount of ammo in one full clip
    public int RemainingAmmo; //The amount of ammo until next reload, we never run out.
    public boolean IsMelee; //If false, is ranged weapon
    public boolean TwoHanded; //If false, one handed
    public int MaxRange; //How far will a projectile go?
    public int Damage; //How much damage will getting hit cost me?
    public Audio SoundEffect;  //The soud the weapon will make when it swings (not when it hits)
    public Image graphic; //The image of the weapon sitting on the ground.
	
}
