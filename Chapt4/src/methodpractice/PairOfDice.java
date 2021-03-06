package methodpractice;

public class PairOfDice { //rename to Dice to make more sense
	
	public Die[] d;
	
	public PairOfDice() {
		d = new Die[2];
		makeDice(2);
	}
	public PairOfDice(int num) {
		d = new Die[num];
		makeDice(num);
	}
	public PairOfDice(int num, int dots) {
		d = new Die[num];
		makeDice(num, dots);
	}
	private void makeDice(int num) {
		for (int i = 0; i < num; i++) {
			d[i] = new Die();
		}
	}
	private void makeDice(int num, int dots) {
		for (int i = 0; i < num; i++) {
			d[i] = new Die(dots);
		}
	}
	public int roll(int diceID) {
		d[diceID - 1].roll();
		return d[diceID - 1].getFaceValue(); //roll(1) returns d[0] because it is first
	}

}
