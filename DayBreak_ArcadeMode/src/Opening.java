
/**
 * @author flavia
 *
 * The room's opening object. An object can either be a spawning point for monsters
 * or simply a passage way 
 */
public class Opening {
	boolean spawnPnt; //whether or not this opening is a spawn point or not
	
	/**
	 * Make this opening be a spawning point
	 */
	public void makeSpawnPnt(){
		spawnPnt = true;
	}
	
	
	/**
	 * Deactivate this opening as a spawning point. 
	 * When this function is called this opening becomes a simple passage way
	 */
	public void deactivateSpawnPnt(){
		spawnPnt = false;
	}
	
	
	/**
	 * @return whether this opening is a spawning point or not.
	 * If false, this opening is just a passage way
	 */
	public boolean isSpawnPnt(){
		return spawnPnt;
	}
}
