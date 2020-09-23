package mechanics;

import java.util.Vector;

import snake.Main;

public class Map {

	public Vector<int[]> v = new Vector<int[]>();
	public int[][] grid;
	public int tail = 1;
	int a, b, c, d;
	int Width, Height;
	int scale;
	private Main main;

	public Map(Main main, int Width, int Height, int scale) {
		this.main = main;
		this.scale = scale;
		this.Width = Width;
		this.Height = Height;
		grid = new int[Width][Height];
		a = Width / 2;
		b = Height / 2;
		grid[a][b] = 1;
		v.add(0, new int[] { a, b });
		makeFood();
	}

	public void tick(int direction) {
		if (a == c && b == d) {
			makeFood();
			tail++;
		}

		if (direction == 1) {
			if (a == Width - 1) {
				//a = 1;
				main.setAlive(false);
			}
			a++;
		}
		if (direction == 2) {
			if (b == 1) {
				//b = Height - 1;
				main.setAlive(false);
			}
			b--;
		}
		if (direction == 3) {
			if (a == 1) {
				//a = Width - 1;
				main.setAlive(false);
			}
			a--;
		}
		if (direction == 4) {
			if (b == Height - 1) {
				//b = 1;
				main.setAlive(false);
			}
			b++;
		}
		v.add(0, new int[] { a, b });
		if (v.size() > tail + 1) {
			v.remove(tail + 1);
		}
		for (int i = 1; i < v.size(); i++) {
			if (v.get(i)[0] == a && v.get(i)[1] == b) {
				main.setAlive(false);
			}
		}

	}

	public void makeFood() {
		c = (int) (Math.random() * (Width - 2) + 1);
		d = (int) (Math.random() * (Height - 2) + 1);
		if (grid[c][d] != 1) {
			grid[c][d] = 2;
		} else {
			makeFood();
		}
	}

	public int getA() {
		return a * scale;
	}

	public int getB() {
		return b * scale;
	}

	public int getC() {
		return c * scale;
	}

	public int getD() {
		return d * scale;
	}
	
	public int getTail() {
		return tail;
	}

}
