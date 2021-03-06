package labs_ch4;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

public class Square {

	private int size, x, y;
	private final int MAX = 16777215;
	private Color c;
	private Random r = new Random();
	
	public Square() {
		size = r.nextInt(100) + 100;
		x = r.nextInt(600);
		y = r.nextInt(400);
		c = new Color(r.nextInt(MAX));
		//c = new Color(r.nextFloat() * 255,
		//			  r.nextFloat() * 255,
		//			  r.nextFloat() * 255);
	}
	
	public void draw(Graphics g) {
		g.setColor(c);
		g.fillRect(x, y, size, size);
	}
}
