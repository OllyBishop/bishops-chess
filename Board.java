package chess;

import java.math.BigInteger;

public class Board {

	final static int KING = 0, QUEEN = 1, ROOK = 2, BISHOP = 3, KNIGHT = 4, PAWN = 5;

	static char[][] chessboard = new char[8][8], chessboardCopy = new char[8][8];

	static int[] plusMinusOne = { 1, -1 }, primeNumbers = { 2, 3, 5, 7, 11, 13 };

	public static void resetBoard() {
		for (Player player : Player.players) {
			chessboard[0][player.homeRankKing] = player.pieces[ROOK];
			chessboard[1][player.homeRankKing] = player.pieces[KNIGHT];
			chessboard[2][player.homeRankKing] = player.pieces[BISHOP];
			chessboard[3][player.homeRankKing] = player.pieces[QUEEN];
			chessboard[4][player.homeRankKing] = player.pieces[KING];
			chessboard[5][player.homeRankKing] = player.pieces[BISHOP];
			chessboard[6][player.homeRankKing] = player.pieces[KNIGHT];
			chessboard[7][player.homeRankKing] = player.pieces[ROOK];
			for (int file = 0; validRange(file); file++) {
				chessboard[file][player.homeRankPawn] = player.pieces[PAWN];
			}
		}
		for (int rank = 2; rank <= 5; rank++) {
			for (int file = 0; validRange(file); file++) {
				vacateSquare(file, rank);
			}
		}
	}

	public static void vacateSquare(int file, int rank) {
		chessboard[file][rank] = 0;
	}

	public static boolean squareIsOccupied(int file, int rank) {
		for (Player player : Player.players) {
			if (squareIsOccupied(file, rank, player)) {
				return true;
			}
		}
		return false;
	}

	public static boolean squareIsOccupied(int file, int rank, Player player) {
		return String.valueOf(player.pieces).contains(String.valueOf(chessboard[file][rank]));
	}

	public static boolean pathIsClear(int fileTo, int fileSign, int rankSign) {
		int fileTest = Move.fileFrom, rankTest = Move.rankFrom;
		while (!(fileTest + fileSign == fileTo && rankTest + rankSign == Move.rankTo)) {
			fileTest += fileSign;
			rankTest += rankSign;
			if (squareIsOccupied(fileTest, rankTest)) {
				return false;
			}
		}
		return true;
	}

	public static boolean kingIsInCheck() {

		boolean check = false;
		int fileKing = -1, rankKing = -1;

		// locate king
		outerLoop: for (int file = 0; validRange(file); file++) {
			for (int rank = 0; validRange(rank); rank++) {
				if (squareIsOccupied(file, rank, Player.currentPlayer) && pieceType(file, rank) == KING) {
					fileKing = file;
					rankKing = rank;
					break outerLoop;
				}
			}
		}

		for (int fileSign : plusMinusOne) {
			for (int rankSign : plusMinusOne) {
				// test surrounding squares for king
				check = directionContainsThreat(fileKing, rankKing, fileSign, 0, KING)
						|| directionContainsThreat(fileKing, rankKing, 0, rankSign, KING)
						|| directionContainsThreat(fileKing, rankKing, fileSign, rankSign, KING) ? true : check;
				// test horizontal and vertical lines for queens/rooks
				check = directionContainsThreat(fileKing, rankKing, fileSign, 0, QUEEN, ROOK)
						|| directionContainsThreat(fileKing, rankKing, 0, rankSign, QUEEN, ROOK) ? true : check;
				// test diagonal lines for queens/bishops
				check = directionContainsThreat(fileKing, rankKing, fileSign, rankSign, QUEEN, BISHOP) ? true : check;
				// test for knights
				check = directionContainsThreat(fileKing, rankKing, 2 * fileSign, rankSign, KNIGHT)
						|| directionContainsThreat(fileKing, rankKing, fileSign, 2 * rankSign, KNIGHT) ? true : check;
				// test for pawns
				check = directionContainsThreat(fileKing, rankKing, fileSign, Player.currentPlayer.advanceOneSquare,
						PAWN) ? true : check;
			}
		}

		return check;

	}

	public static boolean directionContainsThreat(int fileTest, int rankTest, int fileSign, int rankSign,
			int pieceType) {
		while (validRange(fileTest += fileSign, rankTest += rankSign)) {
			if (squareIsOccupied(fileTest, rankTest)) {
				return squareIsOccupied(fileTest, rankTest, Player.opposingPlayer)
						&& pieceType(fileTest, rankTest) == pieceType;
			}
			if (pieceType == KING || pieceType == KNIGHT || pieceType == PAWN) {
				break;
			}
		}
		return false;
	}

	public static boolean directionContainsThreat(int fileTest, int rankTest, int fileSign, int rankSign,
			int pieceType1, int pieceType2) {
		return directionContainsThreat(fileTest, rankTest, fileSign, rankSign, pieceType1)
				|| directionContainsThreat(fileTest, rankTest, fileSign, rankSign, pieceType2);
	}

	public static void countLegalMoves() {
		Play.numberOfLegalMoves = 0;
		Play.listOfLegalMoves = "";
		for (Move.fileFrom = 0; validRange(Move.fileFrom); Move.fileFrom++) {
			for (Move.rankFrom = 0; validRange(Move.rankFrom); Move.rankFrom++) {
				for (Move.fileTo = 0; validRange(Move.fileTo); Move.fileTo++) {
					for (Move.rankTo = 0; validRange(Move.rankTo); Move.rankTo++) {
						if (Move.validateCommand(2)) {
							Play.numberOfLegalMoves++;
							Play.listOfLegalMoves += (Play.listOfLegalMoves.equals("") ? "" : ", ")
									+ (char) (Move.fileFrom + 65) + (char) (Move.rankFrom + 49)
									+ (char) (Move.fileTo + 65) + (char) (Move.rankTo + 49);
						}
					}
				}
			}
		}
	}

	public static boolean insufficientMaterial() {
		BigInteger value = BigInteger.ONE;
		for (int file = 0; validRange(file); file++) {
			for (int rank = 0; validRange(rank); rank++) {
				if (squareIsOccupied(file, rank)) {
					value = value.multiply(new BigInteger(Integer.toString(primeNumbers[pieceType(file, rank)])));
				}
			}
		}
		System.out.println(value);
		return value == new BigInteger(Integer.toString(primeNumbers[KING] * primeNumbers[KING]))
				|| value == new BigInteger(
						Integer.toString(primeNumbers[KING] * primeNumbers[BISHOP] * primeNumbers[KING]))
				|| value == new BigInteger(
						Integer.toString(primeNumbers[KING] * primeNumbers[KNIGHT] * primeNumbers[KING]));
	}

	public static int pieceType(int file, int rank) {
		return (chessboard[file][rank] - 9812) % 6;
	}

	public static boolean validRange(int file, int rank) {
		return validRange(file) && validRange(rank);
	}

	public static boolean validRange(int coordinate) {
		return 0 <= coordinate && coordinate <= 7;
	}

}
