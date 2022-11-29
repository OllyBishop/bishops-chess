package chess;

public class Player {

	static Player White = new Player(0), Black = new Player(1), currentPlayer, opposingPlayer;
	static Player[] players = new Player[] { White, Black };

	String colour;
	char[] pieces = new char[6];
	int homeRankKing, homeRankPawn, advanceOneSquare;
	boolean queensideCastlingPermitted, kingsideCastlingPermitted;
	String piecesCaptured;

	public Player(int playerId) {
		this.colour = (playerId == 0) ? "White" : (playerId == 1) ? "Black" : null;
		for (int i = 0; i < this.pieces.length; i++) {
			this.pieces[i] = (char) (9812 + i + playerId * 6);
		}
		this.homeRankKing = playerId * 7;
		this.homeRankPawn = playerId * 5 + 1;
		this.advanceOneSquare = this.homeRankPawn - this.homeRankKing;
		resetPlayerVariables(this);
	}

	public static void resetPlayerVariables(Player player) {
		player.queensideCastlingPermitted = true;
		player.kingsideCastlingPermitted = true;
		player.piecesCaptured = "";
	}

	public static void updatePlayer() {
		opposingPlayer = currentPlayer;
		currentPlayer = currentPlayer == White ? Black : currentPlayer == Black ? White : null;
	}

}
