package weapons;

public class Crossbow extends Weapon
{
	public Crossbow()
	{
		damage = 5 * BASE_DAMAGE;
		reloadTime = 5;
		ammoCapacity = 1;
		isMelee = false;
		twoHanded = true;
		maxRange = 5;
	}
}
