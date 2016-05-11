import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Map implements ActionListener {
	// Window for game with graphics and buttons.
	public JFrame frame = new JFrame("J*Man!!!"); // The GUI window

	// The board of the game. Class JManPanel is an "inner class", defined
	// below.
	private JManPanel board;

	// Buttons on the GUI
	private JButton bUp = new JButton("Up");
	private JButton bDown = new JButton("Down");
	private JButton bLeft = new JButton("Left");
	private JButton bRight = new JButton("Right");
	private JButton bnewGame = new JButton("New Game");

	// Box to contain the direction buttons
	private Box buttonBox = new Box(BoxLayout.X_AXIS);

	// The box to contain buttonBox and the instructions
	Box instructBox = new Box(BoxLayout.Y_AXIS);

	private int height = 20; // height of the map in tiles.
	private int width = 20; // width of the map in tiles.
	private int tileWidth = 16; // width of a tile in pixels.
	private int tileHeight = 16; // height of a tile in pixels.

	private Piece[][] grid; // grid of Pieces that makes up the game.

	private JMan jMan; // the J*Man piece in this map.

	/** Start a game 20x20 game wih 10 walkers, 10 pillars, and 20 blocks. */
	public static void main(String[] pars) {
		Map m = new Map();
	}

	/**
	 * Constructor: a default 20 x 20 game with 10 walkers, 10 pillars, and 20
	 * blocks placed randomly throughout the game grid.
	 */
	public Map() {
		this(20, 20, 20, 10, 10);
	}

	/**
	 * Constructor: a game with an h x w grid with bl blocks, wa walkers, and pi
	 * pillars. J#Man is at position (0, 0), and all other pieces are placed
	 * randomly. Precondition: number of pieces specified is <= h*w.
	 */
	public Map(int h, int w, int bl, int wa, int pi) {
		this.height = h;
		this.width = w;

		// Set the preferred dimensions of the buttons
		Dimension buttondim = new Dimension(width * tileWidth / 4, 27);
		bUp.setPreferredSize(buttondim);
		bDown.setPreferredSize(buttondim);
		bLeft.setPreferredSize(buttondim);
		bRight.setPreferredSize(buttondim);
		bnewGame.setPreferredSize(new Dimension(width * tileWidth / 2, 27));

		// Add the direction buttons to buttonBox and set the buttonBox
		// alignment
		buttonBox.add(bUp);
		buttonBox.add(bDown);
		buttonBox.add(bLeft);
		buttonBox.add(bRight);
		buttonBox.setAlignmentX(0);

		// Register this as an action listener for all buttons.
		bUp.addActionListener(this);
		bDown.addActionListener(this);
		bLeft.addActionListener(this);
		bRight.addActionListener(this);
		bnewGame.addActionListener(this);

		// Set up the game board.
		board = new JManPanel(this);

		// Place the direction buttons and the instructions into instructBox and
		// set its alignment.
		instructBox.setAlignmentX(0);
		instructBox.add(buttonBox);
		addInstructions(instructBox);

		// Create the game grid, put J*Man in (0,0), and
		// put the rest of the pieces randomly on the map.
		grid = new Piece[width][height];
		putNew(1, 0, 0);
		putOnMap(bl, wa, pi);

		// Put the board and buttons and instructions into the frame.
		frame.getContentPane().add(bnewGame, BorderLayout.NORTH);
		frame.getContentPane().add(board, BorderLayout.CENTER);
		frame.getContentPane().add(instructBox, BorderLayout.SOUTH);

		frame.pack();
		frame.setLocation(5, 30);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	/** Add to instructBox the rules of the game (a sequence of JLabels). */
	private void addInstructions(Box instructBox) {
		instructBox.add(new JLabel(
				" Use the four buttons to direct J*Man (the star-",
				SwingConstants.LEFT));
		instructBox.add(new JLabel(
				" like piece) to capture the other colored pieces.",
				SwingConstants.LEFT));
		instructBox
				.add(new JLabel(" J*Man can capture: ", SwingConstants.LEFT));
		instructBox.add(new JLabel("    a green piece if he is yellow,",
				SwingConstants.LEFT));
		instructBox.add(new JLabel("    a yellow piece if he is red, ",
				SwingConstants.LEFT));
		instructBox.add(new JLabel("    a red piece if he is green.",
				SwingConstants.LEFT));
		instructBox.add(new JLabel(" Walkers (circles) wander randomly about.",
				SwingConstants.LEFT));
		instructBox.add(new JLabel(
				" Pillars (triangles) change color occasionally.",
				SwingConstants.LEFT));
		instructBox.add(new JLabel(
				" Nothing can enter a block (white square).",
				SwingConstants.LEFT));
		instructBox.add(new JLabel(
				" Be careful. With patience, you can always capture ",
				SwingConstants.LEFT));
		instructBox.add(new JLabel(
				" a pillar, but capturing all walkers requires ",
				SwingConstants.LEFT));
		instructBox.add(new JLabel(" thinking ahead. Good Luck!",
				SwingConstants.LEFT));
	}

	/**
	 * Put bl block, wa walkers, and pi pillars on the map. Precondition. The
	 * map must have enough empty spaces for all of them.
	 */
	private void putOnMap(int bl, int wa, int pi) {
		// Put the blocks on the map.
		int k = 0;
		// invariant: k blocks have been added.
		while (k < bl) {
			int xx = Piece.rand(0, width - 1);
			int yy = Piece.rand(0, height - 1);
			if (isEmpty(xx, yy)) {
				putNew(Piece.BLOCK, xx, yy);
				k = k + 1;
			}
		}

		// Put the walkers on the map.
		k = 0;
		// invariant: k walkers have been added.
		while (k < wa) {
			int xx = Piece.rand(0, width - 1);
			int yy = Piece.rand(0, height - 1);
			if (isEmpty(xx, yy)) {
				putNew(Piece.WALKER, xx, yy);
				k = k + 1;
			}
		}

		// Put the pillars on the map.
		k = 0;
		// invariant: k pillars have been added.
		while (k < pi) {
			int xx = Piece.rand(0, width - 1);
			int yy = Piece.rand(0, height - 1);
			if (isEmpty(xx, yy)) {
				putNew(Piece.PILLAR, xx, yy);
				k = k + 1;
			}
		}
	}

	/**
	 * If (x, y) is in the grid and empty, put a new piece of type typ in
	 * location (x, y); and, if the new piece is J*Man, store it in field jMan.
	 * Precondition: typ is one of the piece constants in class Piece.
	 */
	public void putNew(int typ, int x, int y) {
		if (typ >= 0 && typ < 4) {
			if (isInGrid(x, y) && (isEmpty(x, y))) {
				if (typ == 0) {
					grid[x][y] = new Block(x, y, this);
				} else if (typ == 1) {
					grid[x][y] = this.jMan = new JMan(x, y, Piece.rand(0, 2),
							this);

				} else if (typ == 2) {
					grid[x][y] = new Walker(x, y, Piece.rand(0, 2), this);
				} else if (typ == 3) {
					grid[x][y] = new Pillar(x, y, Piece.rand(0, 2), this);
				}

			}
		}
	}

	/** = "(x, y) is inside the grid". */
	public boolean isInGrid(int x, int y) {
		return x > -1 && y > -1 && x < width && y < height;
	}

	/** = "(x, y) is inside the grid and does not contain a piece'. */
	public boolean isEmpty(int x, int y) {
		return isInGrid(x, y) && grid[x][y] == null;
	}

	/**
	 * = the Piece on tile (x, y) (null if (x, y) is outside the grid or
	 * contains null).
	 */
	public Piece pieceAt(int x, int y) {
		if (isInGrid(x, y)) {
			return grid[x][y];
		}
		return null;
	}

	/**
	 * Move the Piece at (fromX, fromY) to (toX, toY) on the grid, changing the
	 * position at (fromX, fromY) to null; change the position in the Piece
	 * accordingly. The piece originall in (toX, toY) is permanently deleted.
	 * Precondition: 1. (toX, toY) is inside the grid. 2. The move is allowed by
	 * the game.
	 */
	public void move(int fromX, int fromY, int toX, int toY) {
		grid[toX][toY] = grid[fromX][fromY];
		grid[fromX][fromY] = null;
		grid[toX][toY].setX(toX);
		grid[toX][toY].setY(toY);
	}

	/**
	 * Make every piece in the map act. When all have acted, reset all their
	 * has-acted flags to false.
	 */
	public void act() {
		// Make every piece act.
		for (int i = 0; i < width; i = i + 1) {
			for (int j = 0; j < height; j = j + 1) {
				if (!isEmpty(i, j)) {
					Piece p = grid[i][j];
					p.act();
					p.setActed(true);
				}
			}
		}

		// Set all the act flags to false.
		for (int i = 0; i < width; i = i + 1) {
			for (int j = 0; j < height; j = j + 1) {
				if (!isEmpty(i, j)) {
					grid[i][j].setActed(false);
				}
			}
		}
	}

	/**
	 * Process a button push: call a function accordingly. Thus, if the button
	 * was Up, Down, Left, or Right, call function JMan.step with arg 0, 1, 2 or
	 * 3 respectively, and then call procedure act().
	 * 
	 * If the button newGame, open a dialog and ask whether a new game is
	 * desired and act accordingly.
	 * 
	 * After all is done, repaint the game board.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bUp) {
			jMan.step(0);
			act();
		} else if (e.getSource() == bDown) {
			jMan.step(1);
			act();
		} else if (e.getSource() == bLeft) {
			jMan.step(2);
			act();
		} else if (e.getSource() == bRight) {
			jMan.step(3);
			act();
		} else if (e.getSource() == bnewGame) {
			if (JOptionPane.showConfirmDialog(frame,
					"Start a new game of the standard size?", "New Game?",
					JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
				new Map();
			}
		}
		board.repaint();// repaint the game board
	}

	/* Inner class to take care of the graphics. */
	private class JManPanel extends JPanel {
		private Map m;

		/* Constructor: an instance with map m. */
		public JManPanel(Map m) {
			this.m = m;
			setPreferredSize(new Dimension(tileWidth * width, tileHeight
					* height));
		}

		/* Paint the game board. */
		public void paint(Graphics g) {
			// paint the background
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, m.width * m.tileWidth, m.height * m.tileHeight);

			for (int i = 0; i < m.width; i = i + 1) {
				for (int j = 0; j < m.height; j = j + 1) {
					int h = i * m.tileWidth;
					int h1 = (i + 1) * m.tileWidth;
					int v = j * m.tileHeight;
					int v1 = (j + 1) * m.tileHeight;

					// Paint tile (i, j). It is in pixels (h..h1-1, v..v1-1).
					if (m.grid[i][j] != null
							&& m.grid[i][j].getType() == Piece.BLOCK) {
						// Tile is a block; fill it with a white square.
						g.setColor(Color.WHITE);
						g.fillRect(h + 1, v, m.tileWidth - 2, m.tileHeight - 2);

					} else if (m.grid[i][j] != null
							&& m.grid[i][j].getType() == Piece.JMAN) {
						// Fill J*Man's square with J*Man's Asterix Icon.
						g.setColor(jMan.getColor());
						g.drawLine(h + 3, v + 2, h1 - 3, v1 - 2);
						g.drawLine(h + 3, v1 - 2, h1 - 3, v + 2);
						g.drawLine(h + 1, v + m.tileHeight / 2, h1 - 1, v
								+ m.tileHeight / 2);
						g.drawLine(h + m.tileWidth / 2, v + 1, h + m.tileWidth
								/ 2, v1 - 1);

					} else if (m.grid[i][j] != null
							&& m.grid[i][j].getType() == Piece.WALKER) {
						// Tile is a walker, fill it with an appropriate colored
						// circle.
						g.setColor(m.grid[i][j].getColor());
						g.fillOval(h + 1, v, m.tileWidth - 2, m.tileHeight - 2);

					} else if (m.grid[i][j] != null
							&& m.grid[i][j].getType() == Piece.PILLAR) {
						// Tile is a pillar, fill it with an appropriate colored
						// triangle.
						g.setColor(m.grid[i][j].getColor());
						g.fillPolygon(new int[] { h + 1, h1 - 1,
								h + m.tileWidth / 2 }, new int[] { v1 - 2,
								v1 - 2, v }, 3);
					}
				}
			}// end of for loops
		}
	}// end of inner class JManPanel
}