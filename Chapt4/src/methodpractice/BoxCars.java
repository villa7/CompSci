package methodpractice;
import villa7.Print;

public class BoxCars {
	
	private static PairOfDice pair = new PairOfDice();
	private static Print p = new Print();
	private static final int ROLLS = 1000;
	private static int boxCars = 0;
	public static void main(String[] args) {
		
		for (int i = 0; i < ROLLS; i++) {
			if (pair.roll(1) == 6 && pair.roll(2) == 6) {
				boxCars++;
			}
		}
		
		p.nl("Boxcars: " + boxCars);
		
	}
	
}
