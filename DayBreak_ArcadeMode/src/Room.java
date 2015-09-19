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
	
	/**
	 * @param width - width of the room
	 * @param height - height of the room
	 * @param numOpenings - number of openings in the room
	 * 
	 * Initialize a room with the given parameters
	 */
	public Room(int width, int height, int numOpenings){
		this.width = width;
		this.height = height;
		
		if (width <= 2){
			this.width = 3;
		}
		if (height <= 2){
			this.height = 2;
		}

		this.numOpenings = numOpenings;
		setIndexes(new Pair[2*(this.width) + 2*(this.height - 2)]);
		setOpeningIndexes(new Pair[this.numOpenings]);
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
		if (pos > getIndexes().length) return;
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

}
