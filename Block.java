import java.awt.Color;

/**
 * An instance of this class is a Block There should at most one Block in a game
 * board at a time
 * 
 * @author Wisarut Boonnumma
 */
public class Block extends Piece {
	/**
	 * Constructor of block: a new block at position (x,y) on Map color white.
	 */
	public Block(int x, int y, Map m) {
		super(Piece.BLOCK, m);
		super.setX(x);
		super.setY(y);
		super.setColor(Color.WHITE);
	}

	public void act() {
		return;
	}

	/** = representation of this piece */
	public String toString() {
		String color = "";
		return getColorWord() + " Block at (" + getX() + ", " + getY() + ")";
	}
}
