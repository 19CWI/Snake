package mechanics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import snake.Main;

public class Keys implements KeyListener {

	public Main main;

	public Keys(Main main) {
		super();
		this.main = main;

	}

	@Override
	public void keyPressed(KeyEvent r) {
		if (r.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (main.direction != 3) {
				main.direction = 1;
			}
		}
		if (r.getKeyCode() == KeyEvent.VK_UP) {
			if (main.direction != 4) {
				main.direction = 2;
			}
		}
		if (r.getKeyCode() == KeyEvent.VK_LEFT) {
			if (main.direction != 1) {
				main.direction = 3;
			}
		}
		if (r.getKeyCode() == KeyEvent.VK_DOWN) {
			if (main.direction != 2) {
				main.direction = 4;
			}
		}
		if (r.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		if (r.getKeyCode() == KeyEvent.VK_SPACE) {
			if(!main.alive) {
				main.alive=true;
				main.map = new Map(main, main.getWidth() / main.scale, main.getHeight() / main.scale, main.scale);
				main.updateScore=true;
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent r) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent r) {
		// TODO Auto-generated method stub

	}

}