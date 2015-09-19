package weapons;

public class Stake extends Weapon
{
	public Stake()
	{
		damage = 1;
		reloadTime = 0;
		ammoCapacity = 0;//Or should this be int max...?
		isMelee = true;
		twoHanded = false;
		maxRange = 1;
	}
}
