import java.util.Random;


/**
 * @author flavia
 * The map of our game
 */
public class Map {

	private int width;
	private int height;
	private Object[][] map;
	private Room lastAddedRoom;
	
	
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
	public void genRandomOpen(Room r, boolean firstRoom){
		int numOpenings = r.getNumOpenings();
		Random rand = new Random();
		int pos = 0;
		
		for (int i = 0; i < numOpenings; i++){
			pos = rand.nextInt(r.getIndexes().length - 1);
			int k = r.getIndexes()[pos].retFirst();
			int l = r.getIndexes()[pos].retSecond(); 	
			
			if (firstRoom){ //the first room is in the corner of the map 
				while ((k == map.length -1 || l == map[0].length -1)){
					pos = rand.nextInt(r.getIndexes().length);
					k = r.getIndexes()[pos].retFirst();
					l = r.getIndexes()[pos].retSecond(); 	
				}
			}
						
			map[k][l] = new Opening();
			r.getOpeningIndexes()[i] = new Pair(k, l);
		}
	}
	
	
	/**
	 * @param r - the given room
	 * Adds the first room to the map space
	 */
	public void genRoom(Room r){
		int roomW = r.getWidth();
		int roomH = r.getHeight();
		int pairIndex = 0; //the position of this room's pair array 
		lastAddedRoom = r;
		Random rand = new Random();
	
		//Our room height and width can't be larger than our map - givet it a smaller value, nor can it be smaller or equal to 2
		roomW = (r.getWidth() >= this.width) ? this.width/2 : roomW;
		roomH = (r.getHeight() >= this.height) ? this.height/2 : roomH;
		roomW = (r.getWidth() <= 2) ? 3 : roomW;
		roomH = (r.getWidth() <= 2) ? 3 : roomH;

		int startX = rand.nextInt(this.width);
		int startY = rand.nextInt(this.height);
		
		if ((startX + roomH) > map.length){
			startX -= (roomH + 1);
			startX = (startX < 0) ?  0 : startX;
		}
		if ((startY + roomW) > map[0].length){
			startY -= (roomW + 1); 		
			startY = (startY < 0) ?  0 : startY;
		}
		
		r.setIndexes(new Pair[2*(roomW) + 2*(roomH - 2)]);
				
		Integer setTile = 1; //set element to be a tile if equal to 1

		//Tile the outer indices of the array
		for (int i = startY; i < (startY + roomW); i++){
			for (int j = startX; j < (startX + roomH); j++){
				
				if (i == startY || (i == startY + roomW - 1) || j == startX || (j == startX + roomH - 1)){
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
		genRandomOpen(r, true);
	}
	
	
	/**
	 * Return a random opening in the lastAddedRoom room
	 */
	public Pair getRandOpen(){
		Random rand = new Random();
		int pos = rand.nextInt(lastAddedRoom.getNumOpenings() - 1);
		return lastAddedRoom.getOpeningIndexes()[pos];		
	}
	
	/**
	 * @param r - the given room
	 * 
	 * Differently from the genRoom() function, this method is only to be called after a room
	 * already exists in the map
	 * It adds a small corridor between the rooms
	 */
	public void addNewRoom(){
		Pair p = getRandOpen();
		Opening opening = (Opening) this.map[p.retFirst()][p.retSecond()]; //select a random opening to join a new room
		Room r = new Room(lastAddedRoom.getWidth(), lastAddedRoom.getHeight(), 2);

		
		opening.deactivateSpawnPnt(); //this specific opening is now just a passageway and not a spawn point
		
		p.printPair();
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
