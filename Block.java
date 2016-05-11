import java.awt.Color;

public class Block extends Piece {
	public Block(int x, int y, Map m) {
		super(Piece.BLOCK, m);
		super.setX(x);
		super.setY(y);
		super.setColor(Color.WHITE);
	}

	public void act() {
		return;
	}
	public String toString(){
		String color = "";
		return getColorWord() + " Block at (" + getX() + ", " + getY() + ")";
	}
}
