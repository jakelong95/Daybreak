package daybreak.weapons;

public class Stake extends Weapon
{
	public Stake()
	{
		damage = BASE_DAMAGE;
		reloadTime = 0;
		ammoCapacity = 0;//Or should this be int max...?
		isMelee = true;
		twoHanded = false;
		maxRange = 1;
	}
}
