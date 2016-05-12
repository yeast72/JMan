import java.awt.Color;

/**
 * An instance of this class is a Pillar There should at most one Pillar in a
 * game board at a time.
 * 
 * @author Wisarut Boonnumma.
 */
public class Pillar extends Piece {
	/**
	 * Constructor of Pillar: a new block at position (x,y) on Map color red if
	 * c = 0, green if c = 1, and yellow if c = 2..
	 */
	public Pillar(int x, int y, int c, Map m) {
		super(Piece.PILLAR, m);
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
	 * Action of Pillar randomly change color.
	 */
	public void act() {
		int c = Piece.rand(0, 2);
		if (c == 0)
			super.setColor(Color.RED);
		else if (c == 1)
			super.setColor(Color.GREEN);
		else if (c == 2)
			super.setColor(Color.YELLOW);

	}

	public String toString() {
		String color = "";
		return getColorWord() + " Pillar at (" + getX() + ", " + getY() + ")";
	}

}
