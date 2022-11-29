package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.util.Arrays;

public class GUI {

	final static int SIZE = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
			.getHeight() / 180;

	final static int WINDOW_WIDTH = 90 * SIZE, WINDOW_HEIGHT = 16 * WINDOW_WIDTH / 9, SQUARE_SIZE = WINDOW_WIDTH / 10,
			BOARD_SIZE = 8 * SQUARE_SIZE;

	final static int SMALL = 3 * SIZE, MEDIUM = 4 * SIZE, LARGE = 5 * SIZE, EXTRA_LARGE = 6 * SIZE;

	String font = null;
	Font small = new Font(font, Font.PLAIN, SMALL), medium = new Font(font, Font.PLAIN, MEDIUM),
			large = new Font(font, Font.PLAIN, LARGE), extraLarge = new Font(font, Font.PLAIN, EXTRA_LARGE);

	final static Color DARK_SQUARE = Color.decode("#D18B46"), LIGHT_SQUARE = Color.decode("#FECEA0"),
			DARK_SQUARE_SELECTED = Color.decode("#916133"), LIGHT_SQUARE_SELECTED = Color.decode("#B1906F"),
			DARK_GREEN = Color.decode("#008000"), TEAL = Color.decode("#008080");

	static JButton A1 = new JButton(), A2 = new JButton(), A3 = new JButton(), A4 = new JButton(), A5 = new JButton(),
			A6 = new JButton(), A7 = new JButton(), A8 = new JButton(), B1 = new JButton(), B2 = new JButton(),
			B3 = new JButton(), B4 = new JButton(), B5 = new JButton(), B6 = new JButton(), B7 = new JButton(),
			B8 = new JButton(), C1 = new JButton(), C2 = new JButton(), C3 = new JButton(), C4 = new JButton(),
			C5 = new JButton(), C6 = new JButton(), C7 = new JButton(), C8 = new JButton(), D1 = new JButton(),
			D2 = new JButton(), D3 = new JButton(), D4 = new JButton(), D5 = new JButton(), D6 = new JButton(),
			D7 = new JButton(), D8 = new JButton(), E1 = new JButton(), E2 = new JButton(), E3 = new JButton(),
			E4 = new JButton(), E5 = new JButton(), E6 = new JButton(), E7 = new JButton(), E8 = new JButton(),
			F1 = new JButton(), F2 = new JButton(), F3 = new JButton(), F4 = new JButton(), F5 = new JButton(),
			F6 = new JButton(), F7 = new JButton(), F8 = new JButton(), G1 = new JButton(), G2 = new JButton(),
			G3 = new JButton(), G4 = new JButton(), G5 = new JButton(), G6 = new JButton(), G7 = new JButton(),
			G8 = new JButton(), H1 = new JButton(), H2 = new JButton(), H3 = new JButton(), H4 = new JButton(),
			H5 = new JButton(), H6 = new JButton(), H7 = new JButton(), H8 = new JButton(), squareFrom, squareTo;

	static JButton[] squaresArray = { A1, A2, A3, A4, A5, A6, A7, A8, B1, B2, B3, B4, B5, B6, B7, B8, C1, C2, C3, C4,
			C5, C6, C7, C8, D1, D2, D3, D4, D5, D6, D7, D8, E1, E2, E3, E4, E5, E6, E7, E8, F1, F2, F3, F4, F5, F6, F7,
			F8, G1, G2, G3, G4, G5, G6, G7, G8, H1, H2, H3, H4, H5, H6, H7, H8 };

	static JLabel fileA1 = new JLabel(), fileA2 = new JLabel(), fileB1 = new JLabel(), fileB2 = new JLabel(),
			fileC1 = new JLabel(), fileC2 = new JLabel(), fileD1 = new JLabel(), fileD2 = new JLabel(),
			fileE1 = new JLabel(), fileE2 = new JLabel(), fileF1 = new JLabel(), fileF2 = new JLabel(),
			fileG1 = new JLabel(), fileG2 = new JLabel(), fileH1 = new JLabel(), fileH2 = new JLabel(),
			rank11 = new JLabel(), rank12 = new JLabel(), rank21 = new JLabel(), rank22 = new JLabel(),
			rank31 = new JLabel(), rank32 = new JLabel(), rank41 = new JLabel(), rank42 = new JLabel(),
			rank51 = new JLabel(), rank52 = new JLabel(), rank61 = new JLabel(), rank62 = new JLabel(),
			rank71 = new JLabel(), rank72 = new JLabel(), rank81 = new JLabel(), rank82 = new JLabel();

	static JLabel[] filesArray = { fileA1, fileA2, fileB1, fileB2, fileC1, fileC2, fileD1, fileD2, fileE1, fileE2,
			fileF1, fileF2, fileG1, fileG2, fileH1, fileH2 },
			ranksArray = { rank11, rank12, rank21, rank22, rank31, rank32, rank41, rank42, rank51, rank52, rank61,
					rank62, rank71, rank72, rank81, rank82 };

	JFrame frame;
	static JLabel WhitePiecesCaptured, BlackPiecesCaptured, gameStatus, legalMoves;
	static JButton submit = new JButton("\u2713");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {

		initialize();

		Play.newGame();
		updateGUI();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(32, 64);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		frame.pack();

		JLabel title = new JLabel("Welcome to Bishop's Chess!");
		title.setBounds(0, 0, WINDOW_WIDTH, SQUARE_SIZE);
		title.setFont(large);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(title);

		gameStatus = new JLabel();
		gameStatus.setBounds(0, 2 * SQUARE_SIZE, WINDOW_WIDTH, SQUARE_SIZE);
		gameStatus.setFont(medium);
		gameStatus.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(gameStatus);

		// create squares
		for (JButton square : squaresArray) {
			int index = Arrays.asList(squaresArray).indexOf(square), file = index / 8, rank = index % 8;
			square.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == square) {
						if (Play.gameOver) {
							square.setSelected(false);
							output("Game over! Click \'New Game\' to play again.");
						} else {
							updateSquares();
							setSelected(square, true);
							// if square clicked contains current player piece
							if (Board.squareIsOccupied(file, rank, Player.currentPlayer)) {
								// if such a square is already selected
								if (squareFrom != null) {
									// deselect first square
									setSelected(squareFrom, false);
								}
								// if a destination square is selected
								if (squareTo != null) {

									// deselect destination square
									setSelected(squareTo, false);
									squareTo = null;
								}
								squareFrom = square.isSelected() ? square : null;
							}
							// else (if square clicked does NOT contain current
							// player piece)
							else {
								// if such a square is already selected
								if (squareTo != null) {
									// deselect first square
									setSelected(squareTo, false);
								}
								// if NO square containing current player piece
								// is selected
								if (squareFrom == null) {
									// deselect square clicked
									setSelected(square, false);
								}
								squareTo = square.isSelected() ? square : null;
								if (squareFrom != null && squareTo != null && !Move.testCurrentSelection(2)) {
									setSelected(square, false);
									squareTo = null;
								}
							}
							for (JButton square : squaresArray) {
								int index = Arrays.asList(squaresArray).indexOf(square), file = index / 8,
										rank = index % 8;
								if (squareFrom != null && squareTo == null) {
									squareTo = square;
									if (Move.testCurrentSelection(2)) {
										square.setForeground(TEAL);
										square.setText(Board.squareIsOccupied(file, rank) ? square.getText()
												: Character.toString((char) (squareFrom.getText().charAt(0) - 6)));
									}
									squareTo = null;
								} else {
									square.setBackground((file + rank) % 2 == 0
											? square.isSelected() ? DARK_SQUARE_SELECTED : DARK_SQUARE
											: square.isSelected() ? LIGHT_SQUARE_SELECTED : LIGHT_SQUARE);
								}
							}
							submit.setVisible(squareTo != null);
							submit.requestFocus();
						}
					}
				}
			});
			square.setBackground((file + rank) % 2 == 0 ? DARK_SQUARE : LIGHT_SQUARE);
			square.setFont(extraLarge);
			square.setOpaque(true);
			square.setSize(SQUARE_SIZE, SQUARE_SIZE);
			frame.getContentPane().add(square);
		}

		// ImageIcon image = new ImageIcon("bin/file.jpg");
		// JLabel label = new JLabel("", image, JLabel.CENTER);
		// JPanel panel = new JPanel(new BorderLayout());
		// panel.add(label, BorderLayout.CENTER);
		// panel.setBounds(0, 0, 0, 0);
		// frame.getContentPane().add(panel);

		// create file labels
		{
			int twice = 0, number = 0;
			for (JLabel file : filesArray) {
				file.setFont(medium);
				file.setHorizontalAlignment(SwingConstants.CENTER);
				file.setSize(SQUARE_SIZE, SQUARE_SIZE);
				file.setText(Character.toString((char) (number + 65)));
				frame.getContentPane().add(file);
				if (++twice > 1) {
					twice = 0;
					if (!Board.validRange(++number)) {
						number = 0;
					}
				}
			}
		}

		// create rank labels
		{
			int twice = 0, number = 0;
			for (JLabel rank : ranksArray) {
				rank.setFont(medium);
				rank.setHorizontalAlignment(SwingConstants.CENTER);
				rank.setSize(SQUARE_SIZE, SQUARE_SIZE);
				rank.setText(Character.toString((char) (number + 49)));
				frame.getContentPane().add(rank);
				if (++twice > 1) {
					twice = 0;
					if (!Board.validRange(++number)) {
						number = 0;
					}
				}
			}
		}

		WhitePiecesCaptured = new JLabel();
		WhitePiecesCaptured.setFont(medium);
		WhitePiecesCaptured.setForeground(Color.BLACK);
		WhitePiecesCaptured.setSize(BOARD_SIZE, SQUARE_SIZE - SIZE);
		frame.getContentPane().add(WhitePiecesCaptured);

		BlackPiecesCaptured = new JLabel();
		BlackPiecesCaptured.setFont(medium);
		BlackPiecesCaptured.setForeground(Color.WHITE);
		BlackPiecesCaptured.setSize(BOARD_SIZE, SQUARE_SIZE - SIZE);
		frame.getContentPane().add(BlackPiecesCaptured);

		legalMoves = new JLabel();
		legalMoves.setBounds(WINDOW_WIDTH - 3 * SQUARE_SIZE, WINDOW_HEIGHT - SQUARE_SIZE, 3 * SQUARE_SIZE, SQUARE_SIZE);
		legalMoves.setFont(small);
		frame.getContentPane().add(legalMoves);

		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setSelected(squareFrom, false);
				setSelected(squareTo, false);
				submit.setVisible(false);
				if (!Play.gameOver) {
					if (Move.testCurrentSelection(0)) {
						Move.executeCommand();
						Player.updatePlayer();
						updateGUI();
						Board.countLegalMoves();
						if (Play.numberOfLegalMoves == 0) {
							Play.gameOver = true;
							if (Board.kingIsInCheck()) {
								gameStatus.setText(String.format("%s wins!", Player.opposingPlayer.colour));
								output(String.format("%s wins by checkmate!", Player.opposingPlayer.colour));
							} else {
								gameStatus.setText("It's a draw!");
								output(String.format("%s has no legal move. Both players draw by stalemate!",
										Player.currentPlayer.colour));
							}
						} else if (Board.insufficientMaterial()) {
							Play.gameOver = true;
							gameStatus.setText("It's a draw!");
							output("Insufficient material for either player to give checkmate. Both players draw by stalemate!");
						} else if (Board.kingIsInCheck()) {
							gameStatus.setText(String.format("Check on %s!", Player.currentPlayer.colour));
						}
					}
				}
				squareFrom = null;
				squareTo = null;
			}
		});
		submit.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent kE) {
				if (kE.getKeyCode() == KeyEvent.VK_ENTER) {
					submit.doClick();
				}
			}

			@Override
			public void keyPressed(KeyEvent kE) {
				if (kE.getKeyCode() == KeyEvent.VK_ENTER) {
					submit.doClick();
				}
			}

			@Override
			public void keyReleased(KeyEvent kE) {
				if (kE.getKeyCode() == KeyEvent.VK_ENTER) {
					submit.doClick();
				}
			}
		});
		submit.setBounds((WINDOW_WIDTH - SQUARE_SIZE) / 2, WINDOW_HEIGHT - 3 * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
		submit.setFocusPainted(false);
		submit.setFont(large);
		submit.setForeground(DARK_GREEN);
		submit.setVisible(false);
		frame.getContentPane().add(submit);

		for (boolean b = false; b == true; b = false) {
			title.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			gameStatus.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			WhitePiecesCaptured.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			BlackPiecesCaptured.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			fileA1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			fileH2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			rank11.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			rank82.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			submit.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			legalMoves.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}

	}

	public static void updateGUI() {
		gameStatus.setText(String.format("It's %s's turn.", Player.currentPlayer.colour));
		updateSquares();
		updateFileLabels();
		updateRankLabels();
		updatePiecesCaptured();
		Board.countLegalMoves();
		legalMoves.setText(String.format("Legal moves: %s", Integer.toString(Play.numberOfLegalMoves)));
	}

	public static void updateSquares() {

		int file = 0, rank = 0;

		for (JButton square : squaresArray) {

			square.setBorder(BorderFactory.createEmptyBorder());

			if (Board.squareIsOccupied(file, rank, Player.White)) {
				square.setForeground(Color.WHITE);
				square.setText(Character.toString((char) (Board.chessboard[file][rank] + 6)));
			} else {
				square.setForeground(Color.BLACK);
				square.setText(Character.toString(Board.chessboard[file][rank]));
			}

			if (Board.kingIsInCheck() && Board.chessboard[file][rank] == Player.currentPlayer.pieces[Board.KING]) {
				square.setBorder(BorderFactory.createLineBorder(Color.RED));
			}

			int fileVal = 0, rankVal = 0;
			if (Player.currentPlayer == Player.White) {
				fileVal = file;
				rankVal = 7 - rank;
			} else if (Player.currentPlayer == Player.Black) {
				fileVal = 7 - file;
				rankVal = rank;
			}
			square.setLocation(fileVal * SQUARE_SIZE + (WINDOW_WIDTH - BOARD_SIZE) / 2,
					rankVal * SQUARE_SIZE + (WINDOW_HEIGHT - BOARD_SIZE) / 2);

			if (!Board.validRange(++rank)) {
				file++;
				rank = 0;
			}

		}

	}

	public static void updateFileLabels() {
		int twice = 0, number = 0;
		for (JLabel file : filesArray) {
			file.setLocation(squaresArray[8 * number].getX(),
					2 * squaresArray[7 * twice].getY() - squaresArray[5 * twice + 1].getY());
			if (++twice > 1) {
				twice = 0;
				if (++number > 7) {
					number = 0;
				}
			}
		}
	}

	public static void updateRankLabels() {
		int twice = 0, number = 0;
		for (JLabel rank : ranksArray) {
			rank.setLocation(2 * squaresArray[8 * 7 * twice].getX() - (squaresArray[8 * (5 * twice + 1)].getX()),
					squaresArray[number].getY());
			if (++twice > 1) {
				twice = 0;
				if (++number > 7) {
					number = 0;
				}
			}
		}
	}

	public static void updatePiecesCaptured() {
		WhitePiecesCaptured.setLocation(SQUARE_SIZE, (WINDOW_HEIGHT - WhitePiecesCaptured.getHeight()) / 2
				+ 49 * SIZE * ((int) Math.signum(squaresArray[0].getY() - squaresArray[1].getY())));
		WhitePiecesCaptured.setText(Player.White.piecesCaptured);
		BlackPiecesCaptured.setLocation(SQUARE_SIZE, (WINDOW_HEIGHT - BlackPiecesCaptured.getHeight()) / 2
				+ 49 * SIZE * ((int) Math.signum(squaresArray[7].getY() - squaresArray[6].getY())));
		String BlackPiecesCapturedConverted = "";
		for (int i = 0; i < Player.Black.piecesCaptured.length(); i++) {
			BlackPiecesCapturedConverted += Character.toString((char) (Player.Black.piecesCaptured.charAt(i) + 6));
		}
		BlackPiecesCaptured.setText(BlackPiecesCapturedConverted);
	}

	public static void output(String message) {
		JOptionPane.showMessageDialog(null,
				String.format("%s (%d,%d;%d,%d)", message, Move.fileFrom, Move.rankFrom, Move.fileTo, Move.rankTo));
	}

	public static void setSelected(JButton square, boolean select) {
		int index = Arrays.asList(squaresArray).indexOf(square);
		square.setSelected(select);
		square.setBackground((index / 8 + index % 8) % 2 == 0 ? select ? DARK_SQUARE_SELECTED : DARK_SQUARE
				: select ? LIGHT_SQUARE_SELECTED : LIGHT_SQUARE);
	}
}
