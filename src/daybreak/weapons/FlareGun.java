package daybreak.weapons;

public class FlareGun extends Weapon
{
	public FlareGun()
	{
		damage = 10 * BASE_DAMAGE;
		reloadTime = 10;
		ammoCapacity = 1;
		isMelee = false;
		twoHanded = true;
		maxRange = 5;
	}
	
}
