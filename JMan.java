import java.awt.*;

/**
 * An instance of this class is a J*Man. There should at most one J*Man in a
 * game board at a time
 * @author Wisarut Boonnumma
 */
public class JMan extends Piece {

	/**
	 * Constructor: a new J*Man at position (x, y) on Map m with color red if c
	 * = 0, green if c = 1, and yellow if c = 2.
	 */
	public JMan(int x, int y, int c, Map m) {
		super(Piece.JMAN, m);
		super.setX(x);
		super.setY(y);
		if (c == 0)
			super.setColor(Color.RED);
		else if (c == 1)
			super.setColor(Color.GREEN);
		else if (c == 2)
			super.setColor(Color.YELLOW);
	}

	/**
	 * Constructor: a new J*Man at position (x, y) on Map m with color c.
	 * Precondition: c is one of Color.RED, Color.GREEN, and Color.YELLOW.
	 */
	public JMan(int x, int y, Color c, Map m) {
		super(Piece.JMAN, m);
		super.setX(x);
		super.setY(y);
		if (c.equals(Color.RED) || c.equals(Color.GREEN)
				|| c.equals(Color.YELLOW))
			super.setColor(c);
	}

	/**
	 * J*Man should move based on what button is pushed. This method is not
	 * used.
	 */
	public void act() {
		return;
	}

	/**
	 * Move J*Man one step based on the value of i: 0 = up, 1 = down, 2 = left,
	 * 3 = right.
	 */
	public void step(int i) {
		setActed(true);
		if (i == 0 && canMove(getX(), getY() - 1))
			getMap().move(getX(), getY(), getX(), getY() - 1);
		else if (i == 1 && canMove(getX(), getY() + 1))
			getMap().move(getX(), getY(), getX(), getY() + 1);
		else if (i == 2 && canMove(getX() - 1, getY()))
			getMap().move(getX(), getY(), getX() - 1, getY());
		else if (i == 3 && canMove(getX() + 1, getY()))
			getMap().move(getX(), getY(), getX() + 1, getY());
		// Complete this
	}

	/**
	 * check j*man can move next position if can move j*man and eat pillar or walker change color.
	 * @param x nextPosition x coordinate
	 * @param y nextPosition y coordinate
	 * @return true if this JMan can move next position either false.
	 */
	public boolean canMove(int x, int y) {
		Piece colorNext = getMap().pieceAt(x, y);
		if (getMap().pieceAt(x, y) == null && getMap().isInGrid(x, y))
			return true;
		else if (this.getColor().equals(Color.YELLOW)
				&& colorNext.getColor().equals(Color.GREEN)) {
			this.setColor(colorNext.getColor());
			return true;
		} else if (this.getColor().equals(Color.GREEN)
				&& colorNext.getColor().equals(Color.RED)) {
			this.setColor(colorNext.getColor());
			return true;
		} else if (this.getColor().equals(Color.RED)
				&& colorNext.getColor().equals(Color.YELLOW)) {
			this.setColor(colorNext.getColor());
			return true;
		}
		return false;

	}

	/** = representation of this piece */
	public String toString() {
		String color = "";
		return getColorWord() + " J*Man at (" + getX() + ", " + getY() + ")";
	}
}
