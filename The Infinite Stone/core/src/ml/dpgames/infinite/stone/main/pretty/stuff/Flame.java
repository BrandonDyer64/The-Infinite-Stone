package ml.dpgames.infinite.stone.main.pretty.stuff;

import java.util.LinkedList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Flame {
	private static final TextureRegion fireParticleTex = new TextureRegion(new Texture("fire_particle.png"));
	private final LinkedList<FireParticle> particles = new LinkedList<FireParticle>();
	public static final Random random = new Random();
	public float x, y;

	public Flame(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void render(SpriteBatch batch) {
		for (int i = 0; i < 10; i++) {
			particles.add(new FireParticle(x, y));
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(batch);
		}
	}

	public class FireParticle {
		public float x, y, size;

		public FireParticle(float x, float y) {
			size = random.nextFloat() * 5f;
			this.x = x + 5 - random.nextInt(10);
			this.y = y + 5 - random.nextInt(10);
		}

		public void render(SpriteBatch batch) {
			y += Gdx.graphics.getDeltaTime() * 160;
			this.x = x + 1 - random.nextInt(3);
			this.y = y + 1 - random.nextInt(3);
			batch.draw(fireParticleTex, x, y, 4, 4, 8, 8, size, size, (float) Math.toRadians(random.nextInt(360)));
			size *= 0.94f;
			if (size < 0.1f) {
				particles.remove(this);
			}
		}
	}
}
