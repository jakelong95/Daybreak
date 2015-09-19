
/**
 * @author flavia
 * Crappy Pair class - will keep track of two numbers at a time
 */
public class Pair {
	private int T1;
	private int T2;
	
	public Pair (int T1, int T2){
		this.T1 = T1;
		this.T2 = T2;
	}
	
	public int retFirst(){
		return T1;
	}
	
	public int retSecond(){
		return T2;
	}
	
	public void printPair(){
		System.out.println(T1 + " " + T2);
	}
}
