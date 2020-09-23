package snake;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import mechanics.Keys;
import mechanics.Map;

public class Main extends Canvas implements Runnable {
	boolean running = false;
	private Thread thread;

	public int scale = 25;
	public int direction = 1;
	public Map map;
	public boolean alive = false, updateScore = true;
	public int[] highscore = { 0, 0, 0, 0, 0 };
	public Keys keys = new Keys(this);

	public static void main(String[] args) {
		openWindow w = new openWindow("GAME", new Main(), 1200, 600);
	}

	public synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(Color.WHITE);
		if (alive) {
			for (int i = 0; i < map.tail; i++) {
				g.fillRect(map.v.get(i)[0] * scale, map.v.get(i)[1] * scale, scale, scale);
			}
			g.setColor(Color.RED);
			g.fillRect(map.getC(), map.getD(), scale, scale);
		} else {
			if (updateScore) {
				updateScore=false;
				int a = map.getTail();
				for (int i = 0; i < highscore.length; i++) {
					if (a > highscore[i]) {
						int b = highscore[i];
						highscore[i] = a;
						a = b;
					}
				}
			}

			g.setColor(Color.RED);
			g.setFont(new Font(g.getFont().toString(), Font.BOLD, 100));
			g.drawString("YOU ARE DEAD", getWidth() / 3, getHeight() / 3);

			g.setColor(Color.WHITE);
			g.setFont(new Font(g.getFont().toString(), Font.BOLD, 50));
			g.drawString("SPACE to START", getWidth() / 3, getHeight() / 3 + 100);
			g.drawString("HIGHSCORE", getWidth() / 3, getHeight() / 2);

			g.setFont(new Font(g.getFont().toString(), Font.BOLD, 25));
			for (int i = 0; i < highscore.length; i++) {
				g.drawString("" + highscore[i], getWidth() / 3, getHeight() / 2 + 30 * (i + 1));
			}

		}

		g.dispose();
		bs.show();
	}

	@Override
	public void run() {
		init();
		long lastTime = System.nanoTime();
		double amountOfTicks = 32.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS:" + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;

			}
		}

	}

	public void init() {
		this.requestFocus();
		this.map = new Map(this, getWidth() / scale, getHeight() / scale, scale);

		this.addKeyListener(keys);

	}

	public void tick() {
		map.tick(direction);
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}
