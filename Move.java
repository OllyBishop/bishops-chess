package chess;

import java.util.Arrays;

import javax.swing.JOptionPane;

public class Move {

	static int fileFrom, rankFrom, fileTo, rankTo;
	static int enPassantFile, enPassantRank;

	public static boolean testCurrentSelection(int test) {
		int squareFromIndex = Arrays.asList(GUI.squaresArray).indexOf(GUI.squareFrom),
				squareToIndex = Arrays.asList(GUI.squaresArray).indexOf(GUI.squareTo);
		fileFrom = squareFromIndex / 8;
		rankFrom = squareFromIndex % 8;
		fileTo = squareToIndex / 8;
		rankTo = squareToIndex % 8;
		return validateCommand(test);
	}

	public static boolean validateCommand(int test) {

		if (!Board.validRange(fileFrom, rankFrom)) {
			if (test == 0) {
				GUI.output(
						String.format("You must select a square containing a %s piece.", Player.currentPlayer.colour));
			}
			return false;
		}

		if (!Board.validRange(fileTo, rankTo)) {
			if (test == 0) {
				GUI.output(String.format("You must select a destination square.", Player.currentPlayer.colour));
			}
			return false;
		}

		if (!Board.squareIsOccupied(fileFrom, rankFrom, Player.currentPlayer)) {
			if (test == 0) {
				GUI.output(
						String.format("You must select a square containing a %s piece.", Player.currentPlayer.colour));
			}
			return false;
		}

		if (fileFrom == fileTo && rankFrom == rankTo) {
			if (test == 0) {
				GUI.output("You cannot move a piece to its current position.");
			}
			return false;
		}

		int pieceType = Board.pieceType(fileFrom, rankFrom);
		int fileDisplacement = fileTo - fileFrom;
		int fileDistance = Math.abs(fileDisplacement);
		int fileSign = (int) Math.signum(fileDisplacement);
		int rankDisplacement = rankTo - rankFrom;
		int rankDistance = Math.abs(rankDisplacement);
		int rankSign = (int) Math.signum(rankDisplacement);

		switch (pieceType) {

		case Board.KING:
			int fileRook = (fileTo - 2) * 7 / 4;
			if (fileFrom == 4 && rankFrom == Player.currentPlayer.homeRankKing && fileDistance == 2
					&& rankDistance == 0) {
				if (!Board.kingIsInCheck()
						&& ((Player.currentPlayer.queensideCastlingPermitted && fileRook == 0)
								|| (Player.currentPlayer.kingsideCastlingPermitted && fileRook == 7))
						&& Board.pathIsClear(fileRook, fileSign, rankSign) && !moveResultsInCheck(test)) {
					if (test == 0 || test == 1) {
						Board.chessboard[fileTo / 2 + 2][rankTo] = Player.currentPlayer.pieces[2];
						Board.vacateSquare(fileRook, rankFrom);
						enPassant(false);
					}
					return true;
				}
				if (test == 0) {
					GUI.output(
							"Castling is only allowed if the king is not currently or subsequently in check, neither the king nor the rook it is castling with have left their starting positions and there is a clear path between the two pieces.");
				}
				return false;
			}
			if (fileDistance > 1 || rankDistance > 1) {
				if (test == 0) {
					GUI.output(
							"The king can only move one square vertically, horizontally or diagonally (when not castling) or two squares horizontally (when castling).");
				}
				return false;
			}
			break;

		case Board.QUEEN:
			if (fileDisplacement != 0 && rankDisplacement != 0 && fileDistance != rankDistance) {
				if (test == 0) {
					GUI.output("A queen can only move vertically, horizontally or diagonally.");
				}
				return false;
			}
			break;

		case Board.ROOK:
			if (fileDisplacement != 0 && rankDisplacement != 0) {
				if (test == 0) {
					GUI.output("A rook can only move vertically or horizontally.");
				}
				return false;
			}
			break;

		case Board.BISHOP:
			if (fileDistance != rankDistance) {
				if (test == 0) {
					GUI.output("A bishop can only move diagonally.");
				}
				return false;
			}
			break;

		case Board.KNIGHT:
			if (fileDistance * rankDistance != 2) {
				if (test == 0) {
					GUI.output("A knight can only move in an L-shaped pattern.");
				}
				return false;
			}
			break;

		case Board.PAWN:
			if ((Player.currentPlayer == Player.White && rankDisplacement <= 0)
					|| (Player.currentPlayer == Player.Black && rankDisplacement >= 0)) {
				if (test == 0) {
					GUI.output("A pawn must move at least one square forwards.");
				}
				return false;
			}
			if (fileDisplacement == 0) {
				if (rankFrom == Player.currentPlayer.homeRankPawn) {
					if (rankDistance > 2) {
						if (test == 0) {
							GUI.output("A pawn cannot move more than two squares from its starting position.");
						}
						return false;
					}
				} else if (rankDistance > 1) {
					if (test == 0) {
						GUI.output("A pawn can only move one square after it has left its starting position.");
					}
					return false;
				}
				if (Board.squareIsOccupied(fileTo, rankTo, Player.opposingPlayer)) {
					if (test == 0) {
						GUI.output("A pawn can only capture an opponent's piece when it is moving diagonally.");
					}
					return false;
				}
			} else {
				if (fileDistance > 1 || rankDistance > 1) {
					if (test == 0) {
						GUI.output("A pawn can only move up to two squares vertically or one square diagonally.");
					}
					return false;
				}
				if (fileTo == enPassantFile && rankTo == enPassantRank && !moveResultsInCheck(test)) {
					if (test == 0 || test == 1) {
						Player.currentPlayer.piecesCaptured += Board.chessboard[fileTo][rankTo - rankSign];
						Board.vacateSquare(fileTo, rankTo - rankSign);
						enPassant(false);
					}
					return true;
				}
				if (!Board.squareIsOccupied(fileTo, rankTo, Player.opposingPlayer)) {
					if (test == 0) {
						GUI.output("A pawn can only move diagonally when it is capturing an opponent's piece.");
					}
					return false;
				}
			}
			break;

		}

		// test if path is clear
		if (pieceType != Board.KNIGHT && !Board.pathIsClear(fileTo, fileSign, rankSign)) {
			if (test == 0) {
				GUI.output("Path is blocked.");
			}
			return false;
		}

		// test if square to contains own piece
		if (Board.squareIsOccupied(fileTo, rankTo, Player.currentPlayer)) {
			if (test == 0) {
				GUI.output("Another of your pieces is occupying that square.");
			}
			return false;
		}

		// test if move places current player in check
		if (moveResultsInCheck(test)) {
			return false;
		}

		// if 'test' equals 0 or 1, allow modification of en passant variables
		if (test == 0 || test == 1) {
			// if move is that of a pawn traveling two squares from player's
			// home rank, allow en passant on next move
			enPassant(pieceType == Board.PAWN && rankDistance == 2);
		}

		return true;

	}

	public static boolean moveResultsInCheck(int test) {
		boolean kingWasPreviouslyInCheck = Board.kingIsInCheck();
		for (int file = 0; Board.validRange(file); file++) {
			for (int rank = 0; Board.validRange(rank); rank++) {
				Board.chessboardCopy[file][rank] = Board.chessboard[file][rank];
			}
		}
		Board.chessboard[fileTo][rankTo] = Board.chessboard[fileFrom][rankFrom];
		Board.vacateSquare(fileFrom, rankFrom);
		boolean kingIsCurrentlyInCheck = Board.kingIsInCheck();
		for (int file = 0; Board.validRange(file); file++) {
			for (int rank = 0; Board.validRange(rank); rank++) {
				Board.chessboard[file][rank] = Board.chessboardCopy[file][rank];
			}
		}
		if (kingIsCurrentlyInCheck) {
			if (test == 0) {
				GUI.output(String.format("%s", kingWasPreviouslyInCheck ? "Your king is still in check."
						: "That move would place your king in check."));
			}
			return true;
		}
		return false;
	}

	public static void executeCommand() {

		// if capturing, add captured piece to pieces captured
		// move piece from square from to square to
		if (Board.squareIsOccupied(fileTo, rankTo, Player.opposingPlayer)) {
			Player.currentPlayer.piecesCaptured += Board.chessboard[fileTo][rankTo];
		}
		Board.chessboard[fileTo][rankTo] = Board.chessboard[fileFrom][rankFrom];
		Board.vacateSquare(fileFrom, rankFrom);

		// pawn promotion
		if (Board.pieceType(fileTo, rankTo) == Board.PAWN && rankTo == Player.opposingPlayer.homeRankKing) {
			Board.chessboard[fileTo][rankTo] = Player.currentPlayer.pieces[JOptionPane.showOptionDialog(null,
					"Please select a piece to promote your pawn to.", "Pawn promotion", JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, new String[] { "Queen", "Rook", "Bishop", "Knight" }, null) + 1];
		}

		// disable castling right(s)
		if (rankFrom == Player.currentPlayer.homeRankKing) {
			switch (fileFrom) {
			case 0:
				Player.currentPlayer.queensideCastlingPermitted = false;
				break;
			case 4:
				Player.currentPlayer.queensideCastlingPermitted = false;
				Player.currentPlayer.kingsideCastlingPermitted = false;
				break;
			case 7:
				Player.currentPlayer.kingsideCastlingPermitted = false;
				break;
			}
		}
		if (rankTo == Player.opposingPlayer.homeRankKing) {
			switch (fileTo) {
			case 0:
				Player.opposingPlayer.queensideCastlingPermitted = false;
				break;
			case 7:
				Player.opposingPlayer.kingsideCastlingPermitted = false;
				break;
			}
		}

	}

	public static void enPassant(boolean enPassantPermitted) {
		enPassantFile = enPassantPermitted ? fileFrom : -1;
		enPassantRank = enPassantPermitted ? (rankFrom + rankTo) / 2 : -1;
	}

}
