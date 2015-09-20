
/**
 * @author flavia
 * Crappy Pair class - will keep track of two numbers at a time
 */
public class Pair {
	private int x;
	private int y;
	
	public Pair (int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int retX(){
		return x;
	}
	
	public int retY(){
		return y;
	}
	
	public void printPair(){
		System.out.println(x + " " + y);
	}
}
