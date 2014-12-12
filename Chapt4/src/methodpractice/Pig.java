package methodpractice;
import java.util.*;
import villa7.Print;
public class Pig {
	
	public static Print p = new Print();
	public static Scanner s = new Scanner(System.in);
	public static PairOfDice pair = new PairOfDice();
	public static boolean playerTurn = true,
						  computerTurn = false;
	public static int playerScore = 0,
					  computerScore = 0,
					  roundTotal = 0;
	public static final int GOAL = 100;
	
	public static void main(String[] args) {
		p.nl("GAME OF PIG");
		if (playPig()) {
			p.nl();
			p.nl("Player wins!");
		} else {
			p.nl();
			p.nl("Computer wins.");
		}
		
	}
	private static boolean playPig() {
		while(true) {
			playerTurn = true;
			computerTurn = true;
			
			do {
				roundTotal = 0;
				p.nl("Player's turn.");
				p.nl("Player has " + playerScore + " points.");
				p.nl("ROLL? y/n");
				if (s.next().equalsIgnoreCase("Y")) {
					int r1 = pair.roll(1);
					int r2 = pair.roll(2);
					
					roundTotal += (r1 + r2);
					if (r1 == 1 && r2 == 1) {
						roundTotal = 0;
						playerScore = 0;
						playerTurn = false;
						p.nl("LOSE ALL POINTS & END TURN");
					} else if (r1 == 1 || r2 == 1) {
						roundTotal = 0;
						playerTurn = false;
						p.nl("LOSE TURN POINTS");
					} else {
						playerScore += roundTotal;
						p.nl("GAINED " + roundTotal + " points.");
					}
				} else {
					playerTurn = false;
				}
			} while (playerTurn);
			
			if (playerScore > GOAL) {
				return true;
			} else if (computerScore > GOAL) {
				return false;
			} else {
				p.nl("moar");
			}
			
			do {
				roundTotal = 0;
				p.nl("Computer's turn.");
				p.nl("Computer has " + computerScore + " points.");
				
				if (roundTotal < 20) { //roll
					p.nl("Computer rolls.");
					int r1 = pair.roll(1);
					int r2 = pair.roll(2);
					
					roundTotal += (r1 + r2);
					if (r1 == 1 && r2 == 1) {
						roundTotal = 0;
						computerScore = 0;
						computerTurn = false;
						p.nl("COMPUTER LOSE ALL POINTS & END TURN");
					} else if (r1 == 1 || r2 == 1) {
						roundTotal = 0;
						computerTurn = false;
						p.nl("COMPUTER LOSE TURN POINTS");
					} else {
						computerScore += roundTotal;
						p.nl("COMPUTER GAINED " + roundTotal + " points.");
					}
				} else {
					computerTurn = false;
				}
			} while (computerTurn);
			
			if (playerScore > GOAL) {
				return true;
			} else if (computerScore > GOAL) {
				return false;
			} else {
				p.nl("moar");
			}
		}
	}	
}
