package arena;

import java.util.Random;

/**
 * @author flavia
 * The map of arcade mode
 */
public class Map {

	private int width; 
	private int height;
	private Object[][] map;
	private Room lastAddedRoom;
	String lastDirection = "None"; //the direction the last room was added in the map
	
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
	 * Since this map only accepts Objects, we have to initialize our map with Integers -> 0
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
	 * @param num - the given randomly generated number
	 * @param r - the given room 
	 * @return true if any of the following conditions are met: door is generated in one of the room corners or a door is generated in a direction with no space for a new room addition
	 */
	public boolean invalidRand(int num, Room r){
		int set = r.getWidth()%2 == 0 ? 1 : 0; //setter bit 

		if (num == 0 || num == r.getWidth() - 1 || num == r.getIndexes().length - r.getWidth() || num == r.getIndexes().length - r.getWidth()){
			return true;
		}
		//lastDirection is only None for the first room
		//in this case, no doors can be added to the bottom or right direction
		if (lastDirection == "None" && (num >= r.getIndexes().length - r.getWidth() + 1 && num <= r.getIndexes().length - 1)) return true; //Down
		if (lastDirection == "None" && (num > r.getWidth() && num < r.getIndexes().length - r.getWidth() + 1 && num%2 == set))	return true; //Right

		return false;
	}
	
	/**
	 * @param r - a room object
	 * @return a random 
	 * 
	 * Generates n openings in the given room; n = numOpenings in a room
	 * One thing that this method has to be careful with is to never generate doors in the corners of a room
	 * as monsters won't be able to pass through them && to never generate doors in a direction where there isn't enough
	 * space for a new room to be added
	 */
	public void genRandomOpen(Room r){
		int numOpenings = r.getNumOpenings();
		Random rand = new Random();
		int pos = 0;
		
		for (int i = 0; i < numOpenings; i++){
			pos = rand.nextInt(r.getIndexes().length - 1);
			
			while(invalidRand(pos, r)){
					pos = rand.nextInt(r.getIndexes().length - 1);
			}

			//BUG: random numbers can be generated twice in small rooms -- shouldn't be a problem for the actual game map
			//	   sometimes there are some null pointer exceptions?
			
			int k = r.getIndexes()[pos].retX();
			int l = r.getIndexes()[pos].retY(); 	
						
			map[k][l] = new Opening();
			r.getOpeningIndexes()[i] = new Pair(k, l);
		}
	}
	
	
	/**
	 * @param r - the given room
	 * Adds the first room to the map space in the lower left corner of the map
	 */
	public void addFirstRoom(Room r){
		int roomW = r.getWidth();
		int roomH = r.getHeight();
		int pairIndex = 0; //the position of this room's pair array 
		lastAddedRoom = r;
	
		//Our room height and width can't be larger than our map - give it a smaller value
		//nor can it be smaller than or equal to 2
		roomW = (r.getWidth() >= this.width) ? this.width/2 : roomW;
		roomH = (r.getHeight() >= this.height) ? this.height/2 : roomH;
		roomW = (r.getWidth() <= 2) ? 3 : roomW;
		roomH = (r.getWidth() <= 2) ? 3 : roomH;

		int startX = this.width - roomW; //will initialize the room in the lower left corner of the map
		int startY = this.height - roomH;
		
		r.setIndexes(new Pair[2*(roomW) + 2*(roomH - 2)]);
		r.setOrigin(new Pair(startX, startY));
		
		
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
					setTile = 2;
					map[i][j] = setTile;
				}
			}
		}
		genRandomOpen(r);
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
	 * @param p - a given index pair for an opening
	 * @return a string that syas what the orientation of the given opening is 
	 * up, down, left, right
	 */
	public String detOrientation(Pair p){
		int x = p.retX();
		int y = p.retY();
		int startX = lastAddedRoom.getOrigin().retX();
		int startY = lastAddedRoom.getOrigin().retY();
		int height = lastAddedRoom.getHeight() - 1;
		int width = lastAddedRoom.getWidth() - 1;
		
//		System.out.println(startX + " " + startY + " " + height + " " + width);
		
		if (x == startY)  return "UP";
		else if (y == startX) return "LEFT";
		else if (x == startY + width) return "DOWN";
		else if (y == startX + height) return "RIGHT";
		return "NONE";
	}
	
	
	/**
	 * @param p
	 * @return the space from a given point
	 */
	public int detSpace(Pair p){
		int startX = lastAddedRoom.getOrigin().retX();
		int startY = lastAddedRoom.getOrigin().retY();
		int endX = startX + lastAddedRoom.getWidth() - 1;
		int endY = startY + lastAddedRoom.getHeight() - 1;
		String s = detOrientation(p);
		
//		System.out.println("endX " + endX);
//		System.out.println("endY " + endY);
//		System.out.println("retX " + p.retX());
//		System.out.println("retY " + p.retY());

		if(s.equals("UP")) return startY;
		else if (s.equals("LEFT")) return startX;
		else if (s.equals("DOWN")) return this.height - endY - 1;
		else if (s.equals("RIGHT")) return this.width - endX - 1;
		return 0;
	}
	
	
	/**
	 * @param p a given pair of points for the opening
	 * Fills a given room with floor tiles
	 */
	public void fillMap(Pair p, Room r){
		int startX = lastAddedRoom.getOrigin().retX();
		int startY = lastAddedRoom.getOrigin().retY();
		 lastAddedRoom.getOrigin().printPair();
		int endX = startX + lastAddedRoom.getWidth() - 1;
		int endY = startY + lastAddedRoom.getHeight() - 1;
		int size = detSpace(p);
		int pairIndex = 0; //the position of this room's pair array 

		r.setIndexes(new Pair[2*(4) + 2*(4 - 2)]);
		if (startY - 3 < 0) r.setOrigin(new Pair(startY, startY));
		else  r.setOrigin(new Pair(startY - 3, startY));
		
		if (size > 3){
			if (detOrientation(p).equals("UP")){ 
				for (int i = startY; i > startY - 4; i--){
					for (int j = startX; j <= endX; j++){
						if (map[i][j] instanceof Opening) continue;
						map[i][j] = 2;
						if (i == startY || i == startY - 3 || j == endX || j == startX){ 
							map[i][j] = 1;
							r.setPair(i, j, pairIndex);
							pairIndex++;
						}
					}
				}
			}
			
			else if (detOrientation(p).equals("LEFT")){
				for (int i = startY; i <= endY; i++){
					for (int j = startX; j > startX - 4; j--){
						if (map[i][j] instanceof Opening) continue; 
						
						map[i][j] = 2;
						
						if (i == startY || i == endY || j == startX - 3 || j == startX){ 
							map[i][j] = 1;	
							r.setPair(i, j, pairIndex);
							pairIndex++;
						}

					}
				}
			}
		
			else if (detOrientation(p).equals("DOWN")){
				for (int i = endY; i < endY + 4; i++){
					for (int j = startX; j <= endX; j++){
						if (map[i][j] instanceof Opening) continue;  

						map[i][j] = 2;

						if (i == endY || i == endY + 3 || j == endX || j == startX){ 
							map[i][j] = 1;
							r.setPair(i, j, pairIndex);
							pairIndex++;
						}

					}

			}
		}
			
			else if (detOrientation(p).equals("RIGHT")){
				for (int i = startY; i <= endY; i++){
					for (int j = endX; j < endX + 4; j++){
						if (map[i][j] instanceof Opening) continue;
						
						map[i][j] = 2;

						if (i == startY || i == endY|| j == endX + 3 || j == endX){ 
							System.out.println(endY);
							map[i][j] = 1;
							r.setPair(i, j, pairIndex);
							pairIndex++;
						}
					}
				}
			}
			genRandomOpen(r);
		}
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
		Opening opening = (Opening) this.map[p.retX()][p.retY()]; //select a random opening to join a new room
		Room r = new Room(lastAddedRoom.getWidth(), lastAddedRoom.getHeight(), 2);

		System.out.println(detOrientation(p));
		System.out.println(detSpace(p));	
		
		fillMap(p, r);
		lastAddedRoom = r;
		lastDirection = detOrientation(p);
		opening.deactivateSpawnPnt(); //this specific opening is now just a passageway and not a spawn point
		
		//p.printPair();
	}
	
	/**
	 * Print out our current map - just for testing purposes 
	 */
	public String toString(){
		String ret = "";
		for (int i = 0; i < map.length; i++){
			for (int j = 0; j < map[0].length; j++){
				if (map[i][j].equals(0)) ret += "X";
				else if (map[i][j] instanceof Opening && ((Opening) map[i][j]).isSpawnPnt()) ret += "S";
				else if (map[i][j] instanceof Opening && !((Opening) map[i][j]).isSpawnPnt()) ret += "D";
				else if (map[i][j].equals(1)) ret += "-";	
				else if (map[i][j].equals(2)) ret += "F";

			}
			ret += "\n";
		}
		return ret;
		
	}
}