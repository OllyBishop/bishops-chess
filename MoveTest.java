package chess;

import static org.junit.Assert.*;
import org.junit.Test;

public class MoveTest {

	@Test
	public void whitePawnCanMoveOneSquareFromStartingPosition() {
		// Arrange
		Play.newGame();
		submitCommand(0, "0102");

		// Act
		char expected = Player.White.pieces[Board.PAWN];
		char actual = Board.chessboard[0][2];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void blackPawnCanMoveOneSquareFromStartingPosition() {
		// Arrange
		Play.newGame();
		submitCommand(1, "0605");

		// Act
		char expected = Player.Black.pieces[Board.PAWN];
		char actual = Board.chessboard[0][5];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void whitePawnCanMoveTwoSquaresFromStartingPosition() {
		// Arrange
		Play.newGame();
		submitCommand(0, "0103");

		// Act
		char expected = Player.White.pieces[Board.PAWN];
		char actual = Board.chessboard[0][3];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void blackPawnCanMoveTwoSquaresFromStartingPosition() {
		// Arrange
		Play.newGame();
		submitCommand(1, "0604");

		// Act
		char expected = Player.Black.pieces[Board.PAWN];
		char actual = Board.chessboard[0][4];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void whitePawnCannotMoveMoreThanTwoSquaresFromStartingPosition() {
		// Arrange
		Play.newGame();
		submitCommand(0, "0104");

		// Act
		char expected = Player.White.pieces[Board.PAWN];
		char actual = Board.chessboard[0][1];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void blackPawnCannotMoveMoreThanTwoSquaresFromStartingPosition() {
		// Arrange
		Play.newGame();
		submitCommand(1, "0603");

		// Act
		char expected = Player.Black.pieces[Board.PAWN];
		char actual = Board.chessboard[0][6];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void whitePawnCannotMoveTwoSquaresHavingLeftStartingPosition() {
		// Arrange
		Play.newGame();
		submitCommand(0, "0102");
		submitCommand(0, "0204");

		// Act
		char expected = Player.White.pieces[Board.PAWN];
		char actual = Board.chessboard[0][2];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void blackPawnCannotMoveTwoSquaresHavingLeftStartingPosition() {
		// Arrange
		Play.newGame();
		submitCommand(1, "0605");
		submitCommand(1, "0503");

		// Act
		char expected = Player.Black.pieces[Board.PAWN];
		char actual = Board.chessboard[0][5];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void whitePawnIsBlockedByOtherPiece() {
		// Arrange
		Play.newGame();
		submitCommand(0, "0103");
		submitCommand(1, "0604");
		submitCommand(0, "0304");

		// Act
		char expected = Player.White.pieces[Board.PAWN];
		char actual = Board.chessboard[0][3];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void blackPawnIsBlockedByOtherPiece() {
		// Arrange
		Play.newGame();
		submitCommand(1, "0604");
		submitCommand(0, "0103");
		submitCommand(1, "0403");

		// Act
		char expected = Player.Black.pieces[Board.PAWN];
		char actual = Board.chessboard[0][4];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void whitePawnCannotMoveDiagonallyIntoSquareNotContainingBlackPiece() {
		// Arrange
		Play.newGame();
		submitCommand(0, "0112");

		// Act
		char expected = Player.White.pieces[Board.PAWN];
		char actual = Board.chessboard[0][1];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void blackPawnCannotMoveDiagonallyIntoSquareNotContainingBlackPiece() {
		// Arrange
		Play.newGame();
		submitCommand(1, "0615");

		// Act
		char expected = Player.Black.pieces[Board.PAWN];
		char actual = Board.chessboard[0][6];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void whitePawnCanMoveDiagonallyIntoSquareContainingBlackPiece() {
		// Arrange
		Play.newGame();
		submitCommand(0, "0103");
		submitCommand(1, "1614");
		submitCommand(0, "0314");

		// Act
		char expected = Player.White.pieces[Board.PAWN];
		char actual = Board.chessboard[1][4];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void blackPawnCanMoveDiagonallyIntoSquareContainingWhitePiece() {
		// Arrange
		Play.newGame();
		submitCommand(1, "0604");
		submitCommand(0, "1113");
		submitCommand(1, "0413");

		// Act
		char expected = Player.Black.pieces[Board.PAWN];
		char actual = Board.chessboard[1][3];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void whitePawnCannotMoveSideways() {
		// Arrange
		Play.newGame();
		submitCommand(0, "0102");
		submitCommand(0, "0212");

		// Act
		char expected = Player.White.pieces[Board.PAWN];
		char actual = Board.chessboard[0][2];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void blackPawnCannotMoveSideways() {
		// Arrange
		Play.newGame();
		submitCommand(1, "0605");
		submitCommand(1, "0515");

		// Act
		char expected = Player.Black.pieces[Board.PAWN];
		char actual = Board.chessboard[0][5];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void whitePawnCannotMoveBackwards() {
		// Arrange
		Play.newGame();
		submitCommand(0, "0102");
		submitCommand(0, "0201");

		// Act
		char expected = Player.White.pieces[Board.PAWN];
		char actual = Board.chessboard[0][2];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void blackPawnCannotMoveBackwards() {
		// Arrange
		Play.newGame();
		submitCommand(1, "0605");
		submitCommand(1, "0506");

		// Act
		char expected = Player.Black.pieces[Board.PAWN];
		char actual = Board.chessboard[0][5];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void whiteRookCanMoveLegally() {
		// Arrange
		Play.newGame();
		submitCommand(0, "0102");
		submitCommand(0, "0001");

		// Act
		char expected = Player.White.pieces[Board.ROOK];
		char actual = Board.chessboard[0][1];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void blackRookCanMoveLegally() {
		// Arrange
		Play.newGame();
		submitCommand(1, "0605");
		submitCommand(1, "0706");

		// Act
		char expected = Player.Black.pieces[Board.ROOK];
		char actual = Board.chessboard[0][6];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void whiteRookCannotMoveDiagonally() {
		// Arrange
		Play.newGame();
		submitCommand(0, "1112");
		submitCommand(0, "0011");

		// Act
		char expected = Player.White.pieces[Board.ROOK];
		char actual = Board.chessboard[0][0];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void blackRookCannotMoveDiagonally() {
		// Arrange
		Play.newGame();
		submitCommand(1, "1615");
		submitCommand(1, "0716");

		// Act
		char expected = Player.Black.pieces[Board.ROOK];
		char actual = Board.chessboard[0][7];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void whiteRookIsBlockedByOtherPiece() {
		// Arrange
		Play.newGame();
		submitCommand(0, "0002");

		// Act
		char expected = Player.White.pieces[Board.ROOK];
		char actual = Board.chessboard[0][0];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void blackRookIsBlockedByOtherPiece() {
		// Arrange
		Play.newGame();
		submitCommand(1, "0705");

		// Act
		char expected = Player.Black.pieces[Board.ROOK];
		char actual = Board.chessboard[0][7];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void whiteRookCapturesBlackPiece() {
		// Arrange
		Play.newGame();
		submitCommand(0, "0103");
		submitCommand(0, "0002");
		submitCommand(0, "0212");
		submitCommand(0, "1216");

		// Act
		char expected = Player.White.pieces[Board.ROOK];
		char actual = Board.chessboard[1][6];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void blackRookCapturesWhitePiece() {
		// Arrange
		Play.newGame();
		submitCommand(1, "0604");
		submitCommand(1, "0705");
		submitCommand(1, "0515");
		submitCommand(1, "1511");

		// Act
		char expected = Player.Black.pieces[Board.ROOK];
		char actual = Board.chessboard[1][1];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void whiteRookCannotBypassBlockingPieceByCapturingAnother() {
		// Arrange
		Play.newGame();
		submitCommand(0, "0006");

		// Act
		char expected = Player.White.pieces[Board.ROOK];
		char actual = Board.chessboard[0][0];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void blackRookCannotBypassBlockingPieceByCapturingAnother() {
		// Arrange
		Play.newGame();
		submitCommand(1, "0701");

		// Act
		char expected = Player.Black.pieces[Board.ROOK];
		char actual = Board.chessboard[0][7];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void whiteQueensideCastlingSuccessful() {
		// Arrange
		Play.newGame();
		submitCommand(0, "1002");
		submitCommand(0, "3132");
		submitCommand(0, "2042");
		submitCommand(0, "3031");
		submitCommand(0, "4020");

		// Act
		char expected1 = Player.White.pieces[Board.KING];
		char actual1 = Board.chessboard[2][0];
		char expected2 = Player.White.pieces[Board.ROOK];
		char actual2 = Board.chessboard[3][0];

		// Assert
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	@Test
	public void whiteKingsideCastlingSuccessful() {
		// Arrange
		Play.newGame();
		submitCommand(0, "6072");
		submitCommand(0, "4142");
		submitCommand(0, "5032");
		submitCommand(0, "4060");

		// Act
		char expected1 = Player.White.pieces[Board.KING];
		char actual1 = Board.chessboard[6][0];
		char expected2 = Player.White.pieces[Board.ROOK];
		char actual2 = Board.chessboard[5][0];

		// Assert
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	@Test
	public void blackQueensideCastlingSuccessful() {
		// Arrange
		Play.newGame();
		submitCommand(1, "1705");
		submitCommand(1, "3635");
		submitCommand(1, "2745");
		submitCommand(1, "3736");
		submitCommand(1, "4727");

		// Act
		char expected1 = Player.Black.pieces[Board.KING];
		char actual1 = Board.chessboard[2][7];
		char expected2 = Player.Black.pieces[Board.ROOK];
		char actual2 = Board.chessboard[3][7];

		// Assert
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	@Test
	public void blackKingsideCastlingSuccessful() {
		// Arrange
		Play.newGame();
		submitCommand(1, "6775");
		submitCommand(1, "4645");
		submitCommand(1, "5735");
		submitCommand(1, "4767");

		// Act
		char expected1 = Player.Black.pieces[Board.KING];
		char actual1 = Board.chessboard[6][7];
		char expected2 = Player.Black.pieces[Board.ROOK];
		char actual2 = Board.chessboard[5][7];

		// Assert
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	@Test
	public void whiteQueensideCastlingUnsuccessfulDueToKingHavingLeftStartingPosition() {
		// Arrange
		Play.newGame();
		submitCommand(0, "1002");
		submitCommand(0, "3132");
		submitCommand(0, "2042");
		submitCommand(0, "3031");
		submitCommand(0, "4030");
		submitCommand(0, "3040");
		submitCommand(0, "4020");

		// Act
		char expected1 = Player.White.pieces[Board.KING];
		char actual1 = Board.chessboard[4][0];
		char expected2 = Player.White.pieces[Board.ROOK];
		char actual2 = Board.chessboard[0][0];

		// Assert
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	@Test
	public void whiteQueensideCastlingUnsuccessfulDueToQueensideRookHavingLeftStartingPosition() {
		// Arrange
		Play.newGame();
		submitCommand(0, "1002");
		submitCommand(0, "3132");
		submitCommand(0, "2042");
		submitCommand(0, "3031");
		submitCommand(0, "0010");
		submitCommand(0, "1000");
		submitCommand(0, "4020");

		// Act
		char expected1 = Player.White.pieces[Board.KING];
		char actual1 = Board.chessboard[4][0];
		char expected2 = Player.White.pieces[Board.ROOK];
		char actual2 = Board.chessboard[0][0];

		// Assert
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	@Test
	public void whiteKingsideCastlingUnsuccessfulDueToKingHavingLeftStartingPosition() {
		// Arrange
		Play.newGame();
		submitCommand(0, "6072");
		submitCommand(0, "4142");
		submitCommand(0, "5032");
		submitCommand(0, "4050");
		submitCommand(0, "5040");
		submitCommand(0, "4060");

		// Act
		char expected1 = Player.White.pieces[Board.KING];
		char actual1 = Board.chessboard[4][0];
		char expected2 = Player.White.pieces[Board.ROOK];
		char actual2 = Board.chessboard[7][0];

		// Assert
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	@Test
	public void whiteKingsideCastlingUnsuccessfulDueToKingsideRookHavingLeftStartingPosition() {
		// Arrange
		Play.newGame();
		submitCommand(0, "6072");
		submitCommand(0, "4142");
		submitCommand(0, "5032");
		submitCommand(0, "7060");
		submitCommand(0, "6070");
		submitCommand(0, "4060");

		// Act
		char expected1 = Player.White.pieces[Board.KING];
		char actual1 = Board.chessboard[4][0];
		char expected2 = Player.White.pieces[Board.ROOK];
		char actual2 = Board.chessboard[7][0];

		// Assert
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	@Test
	public void whiteKingsideCastlingUnsuccessfulDueToKingsideRookHavingBeenCaptured() {
		// Arrange
		Play.newGame();
		submitCommand(0, "7173");
		submitCommand(1, "6664");
		submitCommand(0, "7364");
		submitCommand(1, "6755");
		submitCommand(0, "7076");
		submitCommand(1, "5766");
		submitCommand(0, "7677");
		submitCommand(1, "6677");
		submitCommand(1, "4767");

		// Act
		char expected1 = Player.Black.pieces[Board.KING];
		char actual1 = Board.chessboard[4][7];
		char expected2 = Player.Black.pieces[Board.BISHOP];
		char actual2 = Board.chessboard[7][7];

		// Assert
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	@Test
	public void blackQueensideCastlingUnsuccessfulDueToKingHavingLeftStartingPosition() {
		// Arrange
		Play.newGame();
		submitCommand(1, "1705");
		submitCommand(1, "3635");
		submitCommand(1, "2745");
		submitCommand(1, "3736");
		submitCommand(1, "4737");
		submitCommand(1, "3747");
		submitCommand(1, "4727");

		// Act
		char expected1 = Player.Black.pieces[Board.KING];
		char actual1 = Board.chessboard[4][7];
		char expected2 = Player.Black.pieces[Board.ROOK];
		char actual2 = Board.chessboard[0][7];

		// Assert
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	@Test
	public void blackQueensideCastlingUnsuccessfulDueToQueensideRookHavingLeftStartingPosition() {
		// Arrange
		Play.newGame();
		submitCommand(1, "1705");
		submitCommand(1, "3635");
		submitCommand(1, "2745");
		submitCommand(1, "3736");
		submitCommand(1, "0717");
		submitCommand(1, "1707");
		submitCommand(1, "4727");

		// Act
		char expected1 = Player.Black.pieces[Board.KING];
		char actual1 = Board.chessboard[4][7];
		char expected2 = Player.Black.pieces[Board.ROOK];
		char actual2 = Board.chessboard[0][7];

		// Assert
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	@Test
	public void blackKingsideCastlingUnsuccessfulDueToKingHavingLeftStartingPosition() {
		// Arrange
		Play.newGame();
		submitCommand(1, "6775");
		submitCommand(1, "4645");
		submitCommand(1, "5735");
		submitCommand(1, "4757");
		submitCommand(1, "5747");
		submitCommand(1, "4767");

		// Act
		char expected1 = Player.Black.pieces[Board.KING];
		char actual1 = Board.chessboard[4][7];
		char expected2 = Player.Black.pieces[Board.ROOK];
		char actual2 = Board.chessboard[7][7];

		// Assert
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	@Test
	public void blackKingsideCastlingUnsuccessfulDueToKingsideRookHavingLeftStartingPosition() {
		// Arrange
		Play.newGame();
		submitCommand(1, "6775");
		submitCommand(1, "4645");
		submitCommand(1, "5735");
		submitCommand(1, "7767");
		submitCommand(1, "6777");
		submitCommand(1, "4767");

		// Act
		char expected1 = Player.Black.pieces[Board.KING];
		char actual1 = Board.chessboard[4][7];
		char expected2 = Player.Black.pieces[Board.ROOK];
		char actual2 = Board.chessboard[7][7];

		// Assert
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	@Test
	public void blackKingsideCastlingUnsuccessfulDueToKingsideRookHavingBeenCaptured() {
		// Arrange
		Play.newGame();
		submitCommand(1, "7674");
		submitCommand(0, "6163");
		submitCommand(1, "7463");
		submitCommand(0, "6052");
		submitCommand(1, "7771");
		submitCommand(0, "5061");
		submitCommand(1, "7170");
		submitCommand(0, "6170");
		submitCommand(0, "4060");

		// Act
		char expected1 = Player.White.pieces[Board.KING];
		char actual1 = Board.chessboard[4][0];
		char expected2 = Player.White.pieces[Board.BISHOP];
		char actual2 = Board.chessboard[7][0];

		// Assert
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	@Test
	public void blackPiecesCapturedProperlyRecorded() {
		// Arrange
		Play.newGame();
		submitCommand(0, "0103");
		submitCommand(1, "1614");
		submitCommand(0, "0314");
		submitCommand(1, "1725");
		submitCommand(0, "1425");
		submitCommand(1, "2716");
		submitCommand(0, "2516");
		submitCommand(1, "3727");
		submitCommand(0, "1627");

		// Act
		String expected = Character.toString(Player.Black.pieces[Board.PAWN])
				+ Character.toString(Player.Black.pieces[Board.KNIGHT])
				+ Character.toString(Player.Black.pieces[Board.BISHOP])
				+ Character.toString(Player.Black.pieces[Board.QUEEN]);
		String actual = Player.White.piecesCaptured;

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void whitePiecesCapturedProperlyRecorded() {
		// Arrange
		Play.newGame();
		submitCommand(1, "0604");
		submitCommand(0, "1113");
		submitCommand(1, "0413");
		submitCommand(0, "1022");
		submitCommand(1, "1322");
		submitCommand(0, "2011");
		submitCommand(1, "2211");
		submitCommand(0, "3020");
		submitCommand(1, "1120");

		// Act
		String expected = Character.toString(Player.White.pieces[Board.PAWN])
				+ Character.toString(Player.White.pieces[Board.KNIGHT])
				+ Character.toString(Player.White.pieces[Board.BISHOP])
				+ Character.toString(Player.White.pieces[Board.QUEEN]);
		String actual = Player.Black.piecesCaptured;

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void whiteEnPassantSuccessful() {
		// Arrange
		Play.newGame();
		submitCommand(0, "0103");
		submitCommand(0, "0304");
		submitCommand(1, "1614");
		submitCommand(0, "0415");

		// Act
		char expected1 = Player.White.pieces[Board.PAWN];
		char actual1 = Board.chessboard[1][5];
		char expected2 = 0;
		char actual2 = Board.chessboard[1][4];

		// Assert
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	@Test
	public void whiteEnPassantUnsuccessful() {
		// Arrange
		Play.newGame();
		submitCommand(0, "0103");
		submitCommand(1, "1614");
		submitCommand(0, "0304");
		submitCommand(0, "0415");

		// Act
		char expected1 = Player.White.pieces[Board.PAWN];
		char actual1 = Board.chessboard[0][4];
		char expected2 = Player.Black.pieces[Board.PAWN];
		char actual2 = Board.chessboard[1][4];

		// Assert
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	@Test
	public void blackEnPassantSuccessful() {
		// Arrange
		Play.newGame();
		submitCommand(1, "0604");
		submitCommand(1, "0403");
		submitCommand(0, "1113");
		submitCommand(1, "0312");

		// Act
		char expected1 = Player.Black.pieces[Board.PAWN];
		char actual1 = Board.chessboard[1][2];
		char expected2 = 0;
		char actual2 = Board.chessboard[1][3];

		// Assert
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	@Test
	public void blackEnPassantUnsuccessful() {
		// Arrange
		Play.newGame();
		submitCommand(1, "0604");
		submitCommand(0, "1113");
		submitCommand(1, "0403");
		submitCommand(1, "0312");

		// Act
		char expected1 = Player.Black.pieces[Board.PAWN];
		char actual1 = Board.chessboard[0][3];
		char expected2 = Player.White.pieces[Board.PAWN];
		char actual2 = Board.chessboard[1][3];

		// Assert
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	@Test
	public void whiteMoveResultingInCheckByBlackKingRejected() {
		// Arrange
		Play.newGame();
		submitCommand(1, "4645");
		submitCommand(0, "4142");
		submitCommand(1, "4746");
		submitCommand(0, "4041");
		submitCommand(1, "4655");
		submitCommand(0, "4152");
		submitCommand(1, "5554");
		submitCommand(0, "5253");

		// Act
		char expected = Player.White.pieces[Board.KING];
		char actual = Board.chessboard[5][2];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void whiteMoveResultingInCheckByBlackQueenVerticallyRejected() {
		// Arrange
		Play.newGame();
		submitCommand(0, "4143");
		submitCommand(1, "3634");
		submitCommand(1, "3735");
		submitCommand(1, "3545");
		submitCommand(0, "4334");

		// Act
		char expected = Player.White.pieces[Board.PAWN];
		char actual = Board.chessboard[4][3];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void whiteMoveResultingInCheckByBlackRookVerticallyRejected() {
		// Arrange
		Play.newGame();
		submitCommand(0, "4143");
		submitCommand(1, "3634");
		submitCommand(1, "0604");
		submitCommand(1, "0705");
		submitCommand(1, "0545");
		submitCommand(0, "4334");

		// Act
		char expected = Player.White.pieces[Board.PAWN];
		char actual = Board.chessboard[4][3];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void whiteMoveResultingInCheckByBlackQueenDiagonallyRejected() {
		// Arrange
		Play.newGame();
		submitCommand(1, "2625");
		submitCommand(1, "3704");
		submitCommand(0, "3132");

		// Act
		char expected = Player.White.pieces[Board.PAWN];
		char actual = Board.chessboard[3][1];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void whiteMoveResultingInCheckByBlackBishopRejected() {
		// Arrange
		Play.newGame();
		submitCommand(1, "4645");
		submitCommand(1, "5713");
		submitCommand(0, "3132");

		// Act
		char expected = Player.White.pieces[Board.PAWN];
		char actual = Board.chessboard[3][1];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void whiteMoveResultingInCheckByBlackKnightRejected() {
		// Arrange
		Play.newGame();
		submitCommand(1, "1725");
		submitCommand(0, "4142");
		submitCommand(1, "2533");
		submitCommand(0, "4041");

		// Act
		char expected = Player.White.pieces[Board.KING];
		char actual = Board.chessboard[4][0];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void whiteMoveResultingInCheckByBlackPawnRejected() {
		// Arrange
		Play.newGame();
		submitCommand(0, "4142");
		submitCommand(1, "4644");
		submitCommand(0, "4041");
		submitCommand(1, "4443");
		submitCommand(0, "4132");

		// Act
		char expected = Player.White.pieces[Board.KING];
		char actual = Board.chessboard[4][1];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void blackMoveResultingInCheckByWhiteKingRejected() {
		// Arrange
		Play.newGame();
		submitCommand(0, "4142");
		submitCommand(1, "4645");
		submitCommand(0, "4041");
		submitCommand(1, "4746");
		submitCommand(0, "4152");
		submitCommand(1, "4655");
		submitCommand(0, "5253");
		submitCommand(1, "5554");

		// Act
		char expected = Player.Black.pieces[Board.KING];
		char actual = Board.chessboard[5][5];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void blackMoveResultingInCheckByWhiteQueenVerticallyRejected() {
		// Arrange
		Play.newGame();
		submitCommand(1, "4644");
		submitCommand(0, "3133");
		submitCommand(0, "3032");
		submitCommand(0, "3242");
		submitCommand(1, "4433");

		// Act
		char expected = Player.Black.pieces[Board.PAWN];
		char actual = Board.chessboard[4][4];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void blackMoveResultingInCheckByWhiteRookVerticallyRejected() {
		// Arrange
		Play.newGame();
		submitCommand(1, "4644");
		submitCommand(0, "3133");
		submitCommand(0, "0103");
		submitCommand(0, "0002");
		submitCommand(0, "0242");
		submitCommand(1, "4433");

		// Act
		char expected = Player.Black.pieces[Board.PAWN];
		char actual = Board.chessboard[4][4];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void blackMoveResultingInCheckByWhiteQueenDiagonallyRejected() {
		// Arrange
		Play.newGame();
		submitCommand(0, "2122");
		submitCommand(0, "3003");
		submitCommand(1, "3635");

		// Act
		char expected = Player.Black.pieces[Board.PAWN];
		char actual = Board.chessboard[3][6];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void blackMoveResultingInCheckByWhiteBishopRejected() {
		// Arrange
		Play.newGame();
		submitCommand(0, "4142");
		submitCommand(0, "5014");
		submitCommand(1, "3635");

		// Act
		char expected = Player.Black.pieces[Board.PAWN];
		char actual = Board.chessboard[3][6];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void blackMoveResultingInCheckByWhiteKnightRejected() {
		// Arrange
		Play.newGame();
		submitCommand(0, "1022");
		submitCommand(1, "4645");
		submitCommand(0, "2234");
		submitCommand(1, "4746");

		// Act
		char expected = Player.Black.pieces[Board.KING];
		char actual = Board.chessboard[4][7];

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void blackMoveResultingInCheckByWhitePawnRejected() {
		// Arrange
		Play.newGame();
		submitCommand(1, "4645");
		submitCommand(0, "4143");
		submitCommand(1, "4746");
		submitCommand(0, "4344");
		submitCommand(1, "4635");

		// Act
		char expected = Player.Black.pieces[Board.KING];
		char actual = Board.chessboard[4][6];

		// Assert
		assertEquals(expected, actual);
	}

	public static void submitCommand(int playerId, String command) {
		Player.currentPlayer = Player.players[playerId];
		Player.updatePlayer();
		Player.updatePlayer();
		Move.fileFrom = Integer.parseInt(command.substring(0, 1));
		Move.rankFrom = Integer.parseInt(command.substring(1, 2));
		Move.fileTo = Integer.parseInt(command.substring(2, 3));
		Move.rankTo = Integer.parseInt(command.substring(3, 4));
		if (Move.validateCommand(1)) {
			Move.executeCommand();
		}
	}

}
