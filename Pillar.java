import java.awt.Color;

public class Pillar extends Piece {
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

	@Override
	public void act() {
		int c = Piece.rand(0, 2);
		if (c == 0)
			super.setColor(Color.RED);
		else if (c == 1)
			super.setColor(Color.GREEN);
		else if (c == 2)
			super.setColor(Color.YELLOW);

	}
	
	public String toString(){
		String color = "";
		return getColorWord() + " Pillar at (" + getX() + ", " + getY() + ")";
	}

}
