import java.util.ArrayList;

import processing.core.PApplet;

public class Main extends PApplet {

	private boolean GameOver = false;
	private int killedPlayer, killedBy;

	private TCPConnection1 P1conection;
	private TCPConnection2 P2conection;

	private ArrayList<Bullet> P1bullets;
	private ArrayList<Bullet> P2bullets;

	private Player P1;
	private Player P2;

	public static void main(String[] args) {
		PApplet.main(Main.class.getName());

	}

	public void settings() {

		size(500, 500);

	}

	public void setup() {

		P1conection = new TCPConnection1();
		P1conection.setMain(this);
		P1conection.start();

		P2conection = new TCPConnection2();
		P2conection.setMain(this);
		P2conection.start();

		P1 = new Player(this, 60, height / 2, color(212, 252, 121), 2, 0);
		P2 = new Player(this, width - 60, height / 2, color(102, 126, 234), 2, 0);

		P1bullets = new ArrayList<Bullet>();
		P2bullets = new ArrayList<Bullet>();

	}

	public void draw() {

		background(20);

		noStroke();
		P1.drawPlayer();
		P2.drawPlayer();

		drawBullets();

		P1.movePlayer();
		P2.movePlayer();

		killPlayer();
		gameOver();
	}

	public void drawBullets() {

		for (Bullet b : this.P1bullets) {
			b.drawBullet();
			b.moveBullet();
		}

		for (Bullet b : this.P2bullets) {
			b.drawBullet();
			b.moveBullet();
		}

	}

	public void notify(Message e, Object obj) {

		if (obj instanceof TCPConnection1) {

			System.out.println("Player 1: " + e.getMessage());

			switch (e.getMessage()) {

			case "UP_START":
				P1.setDir(-1);
				break;

			case "DOWN_START":
				P1.setDir(1);
				break;

			case "DOWN_END":
				P1.setDir(0);
				break;

			case "UP_END":
				P1.setDir(0);
				break;

			case "FIRE":
				P1bullets.add(new Bullet(this, P1.getPosX(), P1.getPosY(), 1));
				break;
			}
		}

		if (obj instanceof TCPConnection2) {

			System.out.println("Player 2: " + e.getMessage());

			switch (e.getMessage()) {

			case "UP_START":
				P2.setDir(-1);
				break;

			case "DOWN_START":
				P2.setDir(1);
				break;

			case "DOWN_END":
				P2.setDir(0);
				break;

			case "UP_END":
				P2.setDir(0);
				break;

			case "FIRE":
				P2bullets.add(new Bullet(this, P2.getPosX(), P2.getPosY(), -1));
				break;
			}
		}

	}

	public void gameOver() {

		if (GameOver) {

			P1bullets.clear();
			P2bullets.clear();

			if (killedBy == 1) {
				background(P1.getColor());
				fill(100);
			} else if (killedBy == 2) {
				background(P2.getColor());
				fill(255);
			}

			textAlign(CENTER);
			textSize(20);
			text("Ganaste, jugador " + killedBy, width / 2, height / 2);
			textSize(12);
			text("Presiona en la pantalla para volver a jugar", width / 2, (height / 2) + 30);

		}

		if (mousePressed) {
			P1.setPosY(height / 2);
			P2.setPosY(height / 2);
			GameOver = false;
		}

	}

	public void killPlayer() {

		for (int i = 0; i < P1bullets.size(); i++) {

			if (dist(P1bullets.get(i).getPosX(), P1bullets.get(i).getPosY(), P2.getPosX(), P2.getPosY()) < 35) {
				killedPlayer = 2;
				killedBy = 1;
				GameOver = true;
			}

		}

		for (int i = 0; i < P2bullets.size(); i++) {

			if (dist(P2bullets.get(i).getPosX(), P2bullets.get(i).getPosY(), P1.getPosX(), P1.getPosY()) < 35) {
				killedPlayer = 1;
				killedBy = 2;
				GameOver = true;
			}

		}

	}

}
