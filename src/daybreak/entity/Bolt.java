package daybreak.entity;

import java.util.logging.Logger;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.geom.Vector2f;

public class Bolt extends Entity
{
	
	public Bolt()
	{
		super(map);//We don't have access to map
		init();
	}
	
	static BoltStrike hit = null;
	public boolean collided(Entity entity)
	{
			this.remove();//Do we have some kind of unspawn/stopExisting function?
			hit = new BOLTHIT(IEntity.CROSSBOW_HIT);//TODO
			hit.spawn(this.position,new Vector2f(0,0),Daybreak.fx);
			return true;
		}
		return false;
	}	
}
