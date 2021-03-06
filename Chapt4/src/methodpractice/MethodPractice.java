package methodpractice;
import java.util.*;
import villa7.Print;

public class MethodPractice {
	private static Print p = new Print();
	
	public static void main(String[] args) {
		powersOfTwo();
		p.nl();
		alarm(4);
		sum100();
		p.nl(maxOfTwo(1,2));
		p.nl(sumRange(30, 10));
		p.nl(sumRange(40, 55));
		p.nl(larger(5.5, 3.4));
		p.nl("AASADBABSHiduisabhbfi: " + countA("AASADBABSHiduisabhbfi"));
		p.nl("div2, 5: " + evenlyDivisible(2,5));
		p.nl("div5, 10: " + evenlyDivisible(5,10));
		p.nl("avg2:10,12: " + average(10,12));
		p.nl("avg3:10,11,12: " + average(10,11,12));
		p.nl("avg4:9,10,12,13: " + average(9,10,12,13));
	}
	
	private static void powersOfTwo() {
		int a = 2;
		for (int i = 2; i < 12; i++) {
			p.l((int)Math.pow(a, i) + " ");
		}
	}
	private static void alarm(int times) {
		if (times < 1) {
			p.nl("NO");
			return;
		}
		for (int i = 0; i < times; i++) {
			p.nl("Alarm!");
		}
	}
	private static void sum100() {
		int a = 0;
		for (int i = 1; i <= 100; i++) {
			a += i;
		}
		p.nl(a);

	}
	private static int maxOfTwo(int x, int y) {
		if (x > y) {
			return x;
		} else {
			return y;
		}
	}
	private static int sumRange(int min, int max) {
		if (max < min) {
			p.nl("Max cannot be less than min");
			return 0;
		} else {
			int x = min;
			for (int i = x; i < max; i++) {
				x += i;
			}
			return x;
		}
	}
	private static boolean larger(double x, double y) {
		if (x > y) {
			return true;
		} else {
			return false;
		}
	}
	private static int countA(String a) {
		int b = a.length(),
			d = 0;
		char c = 'A';
		
		for (int i = 0; i < b; i++) {
			if (a.charAt(i) == c) {
				d++;
			}
		}
		return d;
	}
	private static boolean evenlyDivisible(int x, int y) {
		if (x == 0 || y == 0) {
			return false;
		} else {
			if (x % y == 0 || y % x == 0) {
				return true;
			} else {
				return false;
			}
		}
	}
	private static double average(int x, int y) {
		return ((x + y) / 2);
	}
	private static double average(int x, int y, int z) {
		return ((x + y + z) / 3);
	}
	private static double average(int x, int y, int z, int i) {
		return ((x + y + z + i) / 4);
	}
	private static double average(int num[]) {
		int a = 0;
		for (int i = 0; i < num.length; i++) {
			a += num[i];
		}
		return a;
	}
}
