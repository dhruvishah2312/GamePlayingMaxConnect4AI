import java.util.*;

/**
 * This is the AiPlayer class.  It simulates a minimax player for the max
 * connect four game.
 * The constructor essentially does nothing. 
 * 
 * @author james spargo
 *
 */

public class AiPlayer 
{
	int humanValue = 1;
	int botValue = 2;
    /**
     * The constructor essentially does nothing except instantiate an
     * AiPlayer object.
     *
     */
    public AiPlayer() 
    {
	// nothing to do here
    }

    /**
     * This method plays a piece randomly on the board
     * @param currentGame The GameBoard object that is currently being used to
     * play the game.
     * @return an integer indicating which column the AiPlayer would like
     * to play in.
     */
    public int findBestPlay(GameBoard curGame) 
    {
	// start random play code
	//Random randy = new Random();
	//int playChoice = 99;
	
	// playChoice = randy.nextInt( 7 );
	
	// while( !currentGame.isValidPlay( playChoice ) )
	    // playChoice = randy.nextInt( 7 );
	
	// end random play code
	int playChoice = 99;
	playChoice = minimaxdecision(curGame);
	
	
	return playChoice;
    }
	
	 public int minimaxdecision(GameBoard currentGame)
	{
		int randomColumn=0, bestCol=0, getScore1=0, getScore4=0;

		int getscore1=currentGame.getScore(currentGame.getCurrentTurn());
		for (int i=0;i<=6;i++) 	
		{
			if(currentGame.isValidPlay(i)==true)
			{
				currentGame.playPiece(i);
				int column=i;
				if(currentGame.isFinished()==2||currentGame.isFinished()==1){		getScore1=getscore1+1;
				}
				int utility=getScore1;
				if(utility>getscore1)
				{
					bestCol=column+1;	
					break;
				}else {
					currentGame.removePiece(i);
				}
			}
		}
		if(currentGame.isFinished()==0){
			Random r = new Random();
			int playChoice = r.nextInt( 7 );
			while( !currentGame.isValidPlay( playChoice ) ){
				playChoice = r.nextInt( 7 );
			}
			currentGame.playPiece(playChoice);
		}
		return bestCol;
	}
	
	public int miniMaxValue(GameBoard currentGame, int depth, int playerToMove, int alpha, int beta ) {
		if (playerToMove == humanValue){
			int[] max = {-1, Integer.MIN_VALUE};
			//int column = 0;
			for (int i = 0; i < 6 ; i++){
				currentGame.insert(i, humanValue);
				int value = miniMaxValue(currentGame, depth-1, botValue, alpha, beta);
				if (max[0] == -1 || max[1] < value) {
					max[0] = i;
					max[1] = value;
					alpha = value;
				}
				
				if(alpha >= beta) return max[1];
				currentGame.removePiece(i);
			}
			return max[1];
		}else {
			int[] minimum = {-1, Integer.MAX_VALUE};
			for (int i = 0; i < 6; i++) {
				currentGame.insert(i, botValue);
				int value = miniMaxValue(currentGame, depth-1, humanValue, alpha, beta);
				if (minimum[0] == -1 || minimum[1] > value){
					minimum[0] = i;
					minimum[1] = value;
					beta = value;
				}
				if (alpha >= beta) return minimum[1];
				currentGame.removePiece(i);
			}
			return minimum[1];
		}
	}
	
	
}
