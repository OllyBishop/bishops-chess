package chess;

public class Play {

	static boolean gameOver;
	static int numberOfLegalMoves;
	static String listOfLegalMoves;

	public static void newGame() {
		for (Player player : Player.players) {
			Player.resetPlayerVariables(player);
		}
		Board.resetBoard();
		Player.currentPlayer = Player.White;
		Player.opposingPlayer = Player.Black;
		gameOver = false;
	}

}
