import java.awt.Color;

/**
 * An instance of this class is a Walker There should at most one Walker in a
 * game board at a time.
 * 
 * @author Wisarut Boonnumma
 */
public class Walker extends Piece {
	/**
	 * Constructor of Walker: a new block at position (x,y) on Map color red if
	 * c = 0, green if c = 1, and yellow if c = 2..
	 */
	public Walker(int x, int y, int c, Map m) {
		super(Piece.WALKER, m);
		if (c == 0)
			super.setColor(Color.RED);
		else if (c == 1)
			super.setColor(Color.GREEN);
		else if (c == 2)
			super.setColor(Color.YELLOW);
		setX(x);
		setY(y);

	}

	/**
	 * action of Walker approximately 1/3 (move up,down left or right) or
	 * randomly choose a color :red ,green or yellow.
	 */
	public void act() {
		if (Piece.rand(0, 2) == 2) {
			int action = Piece.rand(0, 1);
			if (action == 0) {
				int c = Piece.rand(0, 2);
				if (c == 0)
					super.setColor(Color.RED);
				else if (c == 1)
					super.setColor(Color.GREEN);
				else if (c == 2)
					super.setColor(Color.YELLOW);
			} else if (action == 1) {
				int move = Piece.rand(0, 3);
				if (move == 0 && getMap().isEmpty(getX(), getY() + 1))
					getMap().move(getX(), getY(), getX(), getY() + 1);
				else if (move == 1 && getMap().isEmpty(getX(), getY() - 1))
					getMap().move(getX(), getY(), getX(), getY() - 1);
				else if (move == 2 && getMap().isEmpty(getX() + 1, getY()))
					getMap().move(getX(), getY(), getX() + 1, getY());
				else if (move == 3 && getMap().isEmpty(getX() - 1, getY()))
					getMap().move(getX(), getY(), getX() - 1, getY());
			}
		}

	}

	/** = representation of this piece */
	public String toString() {
		String color = "";
		return getColorWord() + " Walker at (" + getX() + ", " + getY() + ")";
	}

}
