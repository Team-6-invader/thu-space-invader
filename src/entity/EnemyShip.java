package entity;

import java.awt.Color;

import engine.Cooldown;
import engine.Core;
import engine.DrawManager.SpriteType;

/**
 * Implements a enemy ship, to be destroyed by the player.
 * 
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 * 
 */
public class EnemyShip extends Entity {

	/** Point value of a type A enemy. */
	private static final int A_TYPE_POINTS = 10;
	/** Point value of a type B enemy. */
	private static final int B_TYPE_POINTS = 20;
	/** Point value of a type C enemy. */
	private static final int C_TYPE_POINTS = 30;
	/** Point value of a bonus enemy. */
	private static final int BONUS_TYPE_POINTS = 100;

	/** Cooldown between sprite changes. */
	private Cooldown animationCooldown;
	/** Checks if the ship has been hit by a bullet. */
	private boolean isDestroyed;
	/** Values of the ship, in points, when destroyed. */
	private int pointValue;

	/**
	 * Constructor, establishes the ship's properties.
	 * 
	 * @param positionX
	 *            Initial position of the ship in the X axis.
	 * @param positionY
	 *            Initial position of the ship in the Y axis.
	 * @param spriteType
	 *            Sprite type, image corresponding to the ship.
	 */
	/*여기에 존재하는 width와 height는 박스이다. 가로의 길이를 길게 설정하면 분명 총알이 적을 맞추지 않았는데도 부숴질 것이다. 총알이 해당
	* 박스에 맞추면 적이 파괴되었다고 인식하는 것 같음. 흰색 적은 따로 커지지는 않기 때문. 이걸 건든다면 총알이 적을 맞추지 않았음에도 부숴지니,
	* 총알의 화력을 높이는 부분으로 사용해도 상당히 괜찮을 부분임.*/
	public EnemyShip(final int positionX, final int positionY,
			final SpriteType spriteType) {
		super(positionX, positionY, 12 * 2, 8 * 2, Color.WHITE);

		this.spriteType = spriteType;
		this.animationCooldown = Core.getCooldown(500);
		this.isDestroyed = false;

		switch (this.spriteType) {
		case EnemyShipA1:
		case EnemyShipA2:
			this.pointValue = A_TYPE_POINTS;
			break;
		case EnemyShipB1:
		case EnemyShipB2:
			this.pointValue = B_TYPE_POINTS;
			break;
		case EnemyShipC1:
		case EnemyShipC2:
			this.pointValue = C_TYPE_POINTS;
			break;
		default:
			this.pointValue = 0;
			break;
		}
	}

	/*게임 내에서 빨간색으로 한번씩 지나가는 우주선. 새로 보스 클래스를 만드는 것보다 여기를 건드는게 더 나을 수 있음. 단, 해당 우주선은 게임
	* 사이즈를 벗어나면 바로 사라져버리기 때문에, 일반 적들처럼 계속 왔다갔다 할 수 있도록 만드는 것이 관건임. 이게 힘들다면 새로 만드는 걸 추천.*/
	/**
	 * Constructor, establishes the ship's properties for a special ship, with
	 * known starting properties.
	 */
	public EnemyShip() {
		super(-32, 60, 16 * 2, 7 * 2, Color.RED);

		this.spriteType = SpriteType.EnemyShipSpecial;
		this.isDestroyed = false;
		this.pointValue = BONUS_TYPE_POINTS;
	}
	/*새로운 적 유형을 만들어서 추가적인 점수를 주고 싶다면, 이부분을 건들거나, 위에서 적의 종류에 따라 점수를 다르게 설정한 지점에서 변경해도
	괜찮다. 다만, 점수부분은 다른 클래스에서도 같이 관여하는 부분이기 때문에, 어느 부분을 건들은 아래 메소드도 같이 건들여야함. 참고.
	 */
	/**
	 * Getter for the score bonus if this ship is destroyed.
	 * 
	 * @return Value of the ship.
	 */
	public final int getPointValue() {
		return this.pointValue;
	}

	/**
	 * Moves the ship the specified distance.
	 * 
	 * @param distanceX
	 *            Distance to move in the X axis.
	 * @param distanceY
	 *            Distance to move in the Y axis.
	 */
	public final void move(final int distanceX, final int distanceY) {
		this.positionX += distanceX;
		this.positionY += distanceY;
	}

	/**
	 * Updates attributes, mainly used for animation purposes.
	 */
	public final void update() {
		if (this.animationCooldown.checkFinished()) {
			this.animationCooldown.reset();

			switch (this.spriteType) {
			case EnemyShipA1:
				this.spriteType = SpriteType.EnemyShipA2;
				break;
			case EnemyShipA2:
				this.spriteType = SpriteType.EnemyShipA1;
				break;
			case EnemyShipB1:
				this.spriteType = SpriteType.EnemyShipB2;
				break;
			case EnemyShipB2:
				this.spriteType = SpriteType.EnemyShipB1;
				break;
			case EnemyShipC1:
				this.spriteType = SpriteType.EnemyShipC2;
				break;
			case EnemyShipC2:
				this.spriteType = SpriteType.EnemyShipC1;
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Destroys the ship, causing an explosion.
	 */
	public final void destroy() {
		this.isDestroyed = true;
		/*여기를 false로 바꾸면 적은 죽었다고 뜨지만, 폭발하는 이미지에서 넘어가지 않는다. 즉, 2번 맞는 적은 다른 곳의 코드를 만져야함.*/
		this.spriteType = SpriteType.Explosion;
	}

	/**
	 * Checks if the ship has been destroyed.
	 * 
	 * @return True if the ship has been destroyed.
	 */
	public final boolean isDestroyed() {
		return this.isDestroyed;
	}
}
