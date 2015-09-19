
/**
 * @author flavia
 * Tester class
 */
public class tester {
	
	public static void main (String[] args){
		Map m = new Map(20, 20);
		Room r = new Room(1, 15, 2);
		m.genRoom(r);
		System.out.println(m.toString());
	}
}
