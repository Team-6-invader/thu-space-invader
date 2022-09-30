package entity;

import java.awt.Color;

import engine.DrawManager.SpriteType;

/*기본적인 총알 세팅. 변수들 대다수가 final로 선언되어 있기 때문에, 변경하기 힘듦.
 * 총알 속도를 담당하는 사람은 Bullet 클래스를 건드는 것보다는 BulletPool의 Bullet getBullet에 있는 speed에 숫자를 더하거나 곱해주는
 * 방식으로 하는게 훨씬 간단함. 아이템팀이 총알 속도가 빨라지는 아이템을 만든다고 하면, 따로 변수 선언해서 합의해서 하는게 좋을 것 같습니다.*/
/**
 * Implements a bullet that moves vertically up or down.
 * 
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 * 
 */
public class Bullet extends Entity {

	/**
	 * Speed of the bullet, positive or negative depending on direction -
	 * positive is down.
	 */
	private int speed;

	/**
	 * Constructor, establishes the bullet's properties.
	 * 
	 * @param positionX
	 *            Initial position of the bullet in the X axis.
	 * @param positionY
	 *            Initial position of the bullet in the Y axis.
	 * @param speed
	 *            Speed of the bullet, positive or negative depending on
	 *            direction - positive is down.
	 */
	public Bullet(final int positionX, final int positionY, final int speed) {
		super(positionX, positionY, 3 * 2, 5 * 2, Color.WHITE);

		this.speed = speed;
		setSprite();
	}

	/**
	 * Sets correct sprite for the bullet, based on speed.
	 */
	public final void setSprite() {
		if (speed < 0)
			this.spriteType = SpriteType.Bullet;
		else
			this.spriteType = SpriteType.EnemyBullet;
	}

	/**
	 * Updates the bullet's position.
	 */
	public final void update() {
		this.positionY += this.speed;
	}

	/**
	 * Setter of the speed of the bullet.
	 * 
	 * @param speed
	 *            New speed of the bullet.
	 */
	public final void setSpeed(final int speed) {
		this.speed = speed;
	}

	/**
	 * Getter for the speed of the bullet.
	 * 
	 * @return Speed of the bullet.
	 */
	public final int getSpeed() {
		return this.speed;
	}
}
