package daybreak;

public class Crossbow extends Weapon
{
	public Crossbow()
	{
		damage = 5;
		reloadTime = 1;
		ammoCapacity = 1;
		isMelee = false;
		twoHanded = true;
		int maxRange = 5;
	}
}
