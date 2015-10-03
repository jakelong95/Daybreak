package arena;

/**
 * @author flavia
 * Our room object for the arcade mode 
 */
public class Room {
	private int width; //width of the room
	private int height; //height of the room
	private int numOpenings; //number of openings in a given room
	private Pair[] indexes; //an array that contains the location of where the room was initialized in the map 
	private Pair[] openingIndexes; //an array that contains the location of every opening in the room
	private Pair origin;
	private Pair end; 
	
	/**
	 * @param width - width of the room
	 * @param height - height of the room
	 * @param numOpenings - number of openings in the room
	 * 
	 * Initialize a room with the given parameters
	 */
	public Room(int width, int height, int numOpenings){
		this.width = (width <= 2) ? 3 : width;
		this.height = (height <= 2) ? 3 : height;
		this.numOpenings = numOpenings;
	}
	
	/**
	 * @return this room's width
	 */
	public int getWidth(){
		return this.width;
	}
	
	
	/**
	 * @return this room's height
	 */
	public int getHeight(){
		return this.height;
	}
	
	/**
	 * Set this rooms width to the given width
	 */
	public void setWidth(int width){
		this.width = width;
	}
	
	
	/**
	 * Set this rooms height to the given height
	 */
	public void setHeight( int height){
		this.height = height;
	}
	
	/**
	 * @return this room's number of openings
	 */
	public int getNumOpenings(){
		return this.numOpenings;
	}
	
	/**
	 * @param i - element 1
	 * @param j - element 2
	 * @param pos - position in the array
	 * 
	 * Will set the indices array with the given pair values
	 */
	public void setPair(int i, int j, int pos){
		if (pos > getIndexes().length - 1) return;
		this.getIndexes()[pos] = new Pair(i, j);
	}

	/**
	 * @return return an array that contains this room's overall position in the map
	 */
	Pair[] getIndexes() {
		return indexes;
	}

	/**
	 * @param indexes - the given array of indexes
	 * Sets the array of indexes
	 */
	public void setIndexes(Pair[] indexes) {
		this.indexes = indexes;
		this.numOpenings = (numOpenings > indexes.length) ? 10 : numOpenings;
		setOpeningIndexes(new Pair[this.numOpenings]);
	}
	
	
	/**
	 * @return the values inside of the indexes array
	 */
	public String printIndexes(){
		String ret = "";
		for (int i = 0; i < indexes.length; i++){
			ret += ("[" + indexes[i].retX() + ", " + indexes[i].retY() + "]");
			ret += " ";
		}
		return ret;
	}
	
	/**
	 * @return an array that contains the position of every opening in this room
	 */
	Pair[] getOpeningIndexes() {
		return openingIndexes;
	}
	
	/**
	 * @param indexes - the given indexes array
	 * Sets the position of the every opening in this room
	 */
	public void setOpeningIndexes(Pair[] indexes) {
		this.openingIndexes = indexes;
	}
	
	public void setOrigin(Pair p){
		this.origin = p;
	}
	
	public Pair getOrigin(){
		return origin;
	}
	
	public void setEnd(Pair e){
		this.end = e;
	}
	
	public Pair getEnd(){
		return this.end;
	}

}