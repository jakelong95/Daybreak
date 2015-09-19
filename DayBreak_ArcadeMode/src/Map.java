import java.util.Random;


/**
 * @author flavia
 * The map of our game
 */
public class Map {

	private int width;
	private int height;
	private Object[][] map;
	
	
	/**
	 * @param width - width of the map
	 * @param height - height of the map
	 * 
	 * Initialize our map to the given width and height
	 */
	public Map(int width, int height){
		this.width = width;
		this.height = height;
		this.map = new Object[height][width];
		initializeMap();
	}
	
	/**
	 * Initializes our map with Integer 0s
	 */
	public void initializeMap(){
		Integer init = 0;
		for (int i = 0; i < map.length; i ++){
			for (int j = 0; j < map[0].length; j++){
				map[i][j] = init;
			}
		}
	}
	
	/**
	 * @param r - a room object
	 * @return a random 
	 * 
	 * generates num openings in the given room
	 */
	public void genRandomOpen(Room r){
		int numOpenings = r.getNumOpenings();
		Random rand = new Random();
		int pos = 0;
		
		for (int i = 0; i < numOpenings; i++){
			pos = rand.nextInt(r.getIndexes().length);
			int k = r.getIndexes()[pos].retFirst();
			int l = r.getIndexes()[pos].retSecond(); 
			
			map[k][l] = new Opening();
			r.getOpeningIndexes()[i] = new Pair(k, l);
		}
	}
	
	
	/**
	 * @param r - the given room
	 * Adds the given room to the map space
	 */
	public void genRoom(Room r){
		int roomW = r.getWidth();
		int roomH = r.getHeight();
		int pairIndex = 0; //the position of this room's pair array 
		
		//Our room height and width can't be larger than our map
		if (r.getWidth() >= this.width)  roomW = this.width - 1;
		if (r.getHeight() >= this.height) roomH = this.height - 1;
		
		int startX = this.width - roomW;
		int startY = this.height - roomH;
		Integer setTile = 1; //set element to be a tile if equal to 1
		
		//Tile the outer indices of the array
		for (int i = startY; i < map.length; i++){
			for (int j = startX; j < map[0].length; j++){
				if (i == startY || i == (map.length - 1) || j == startX || j == (map[0].length - 1)){
					
					setTile = 1;
					map[i][j] = setTile;
					r.setPair(i, j, pairIndex);
					pairIndex++;
				}
				else{
					setTile = 0;
					map[i][j] = setTile;
				}
			}
		}
		genRandomOpen(r);
	}
	
	
	/**
	 * @param r - the given room
	 * 
	 * Differently from the genRoom() function, this method is only to be called after a room
	 * already exists in the map
	 * It adds a small corridor between the rooms
	 */
	public void addNewRoom(Room r){
		
	}
	
	/**
	 * Print out our current map - just for testing purposes 
	 */
	public String toString(){
		String ret = "";
		for (int i = 0; i < map.length; i++){
			for (int j = 0; j < map[0].length; j++){
				if (map[i][j].equals(0)) ret += "X";
				else if (map[i][j] instanceof Opening) ret += "/";
				else if (map[i][j].equals(1)) ret += "-";	
			}
			ret += "\n";
		}
		return ret;
		
	}
}
