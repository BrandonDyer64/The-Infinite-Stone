package ml.dpgames.infinite.stone.main.pretty.stuff;

import java.util.LinkedList;
import java.util.Random;

import ml.dpgames.infinite.stone.main.Graphics;
import ml.dpgames.infinite.stone.main.screens.game.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Clicksplosion {
	public int size;
	public float x, y;
	private LinkedList<Particle> particles = new LinkedList<Particle>();
	public Random rand = new Random();
	public static final float gravity = -1;
	public float time;

	public Clicksplosion(float x, float y, int size) {
		this.size = size;
		for (int i = 0; i < size; i++) {
			particles.add(new Particle(x, y));
		}
		time = 0;
	}
	
	public void render(SpriteBatch batch) {
		time += Gdx.graphics.getDeltaTime();
		if (time > 2) {
			GameScreen.splodes.remove(this);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(batch);
		}
	}

	public class Particle {
		public float x, y, xVel, yVel;
		private float time;
		private TextureRegion tex;

		public Particle(float x, float y) {
			this.x = x;
			this.y = y;
			xVel = 50 - rand.nextInt(101);
			yVel = 50 - rand.nextInt(101);
			time = rand.nextFloat();
			tex = new TextureRegion(Graphics.spriteSheet, rand.nextInt(16), rand.nextInt(16), 16, 16);
		}

		public void render(SpriteBatch batch) {
			time += Gdx.graphics.getDeltaTime();
			if (time > 2) {
				particles.remove(this);
			}
			yVel += gravity;
			y += yVel;
			x += xVel;
			batch.draw(tex, x, y);
		}
	}

}
