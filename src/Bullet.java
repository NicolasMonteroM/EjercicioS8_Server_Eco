import processing.core.PApplet;
import processing.core.PConstants;

public class Bullet {

	private int posX;
	private int posY;
	private int size;
	private int dir;
	private int speed;
	private PApplet app;

	public Bullet(PApplet app, int posX, int posY, int dir) {

		this.posX = posX;
		this.posY = posY;
		this.speed = 5;
		this.size = 5;
		this.dir = dir;
		this.app = app;

	}

	public void drawBullet() {

		app.fill(255);
		app.ellipseMode(PConstants.CENTER);
		app.ellipse(posX, posY, size, size);

	}

	public void moveBullet() {

		this.posX += (speed * dir);

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
