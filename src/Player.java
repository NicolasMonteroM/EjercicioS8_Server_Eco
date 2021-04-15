import processing.core.PApplet;
import processing.core.PConstants;

public class Player {

	private int posX;
	private int posY;
	private int color;
	private int size;
	private int dir;
	private int speed;
	private PApplet app;

	public Player(PApplet app, int posX, int posY, int color, int dir, int speed) {

		this.posX = posX;
		this.posY = posY;
		this.size = 40;
		this.speed = 5;
		this.dir = 0;
		this.color = color;
		this.app = app;

	}

	public void drawPlayer() {

		app.fill(color);
		app.ellipseMode(PConstants.CENTER);
		app.ellipse(posX, posY, size, size);

	}

	public void movePlayer() {

		if (dir != 0) {
			this.posY += (speed * dir);
		}

	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

}
