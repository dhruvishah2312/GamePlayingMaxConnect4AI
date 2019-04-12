import java.io.*;

//reference github

public class maxconnect4
{
  public static void main(String[] args) throws Exception
  {
    // check for the correct number of arguments
    if( args.length != 4 ) 
    {
      System.out.println("Four command-line arguments are needed:\n"
                         + "Usage: java [program name] interactive [input_file] [computer-next / human-next] [depth]\n"
                         + " or:  java [program name] one-move [input_file] [output_file] [depth]\n");

      exit_function( 0 );
     }
		
    // parse the input arguments
    String game_mode = args[0].toString();				// the game mode
    String input = args[1].toString();					// the input game file
    int depthLevel = Integer.parseInt( args[3] );  		// the depth level of the ai search
		
    // create and initialize the game board
    GameBoard currentGame = new GameBoard( input );
    
    // create the Ai Player
    AiPlayer calculon = new AiPlayer();
		
    //  variables to keep up with the game
    int playColumn = 99;				//  the players choice of column to play
    boolean playMade = false;			//  set to true once a play has been made

    if( game_mode.equalsIgnoreCase( "interactive" ) ) {
		String nextTurn = args[2].toString();
		if(nextTurn.equalsIgnoreCase("computer-next")){
			String output = "computer.txt";
    	    System.out.print("\nMaxConnect-4 game\n");
			System.out.print("Game state before next move:\n");
    	    currentGame.printGameBoard();
			System.out.println( "Score: Player 1 = " + currentGame.getScore( 1 ) +  ", Player2 = " + currentGame.getScore( 2 ) + "\n " );
			
			if( currentGame.getPieceCount() < 42 ) 
    	    {
    	        int current_player = currentGame.getCurrentTurn();
				playColumn = calculon.findBestPlay( currentGame );
				
    	        System.out.println("Move " + currentGame.getPieceCount() + ": Player " + current_player + ", Column " + playColumn);
    	        System.out.print("Game state after move:\n");
    	        currentGame.printGameBoard();
    	        System.out.println( "Score: Player 1 = " + currentGame.getScore( 1 ) + ", Player2 = " + currentGame.getScore( 2 ) + "\n " );
    	        currentGame.printGameBoardToFile( output );
				return;
    	    } 
    	    else 
    	    {
				System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over");
				return;
    	    }


			
		
		}else if(nextTurn.equalsIgnoreCase("human-next")){
			System.out.println("Enter the column value between 0 to 6");
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

			int humanMove = Integer.parseInt(bufferRead.readLine());
			if (humanMove<0||humanMove>6){
				System.out.println("enter values between 0 to 6 please");
				return;
			}
			else if(currentGame.isValidPlay(humanMove)){
				currentGame.playPiece(humanMove);
				currentGame.printGameBoard();
				String output = "human.txt";
				currentGame.printGameBoardToFile(output);
				return;
			}else{
				System.out.println("column is full try again");
				return;
			}
		}
    } 
	else if(game_mode.equalsIgnoreCase( "one-move" )){
		//  one-move mode ->
		// get the output file name
		String output = args[2].toString();				// the output game file
		
		System.out.print("\nMaxConnect-4 game\n");
		System.out.print("game state before move:\n");
		
		//print the current game board
		currentGame.printGameBoard();
		// print the current scores
		System.out.println( "Score: Player 1 = " + currentGame.getScore( 1 ) +
				", Player2 = " + currentGame.getScore( 2 ) + "\n " );
		
		// ****************** this chunk of code makes the computer play
		if( currentGame.getPieceCount() < 42 ) 
		{
			int current_player = currentGame.getCurrentTurn();
			playColumn = calculon.findBestPlay( currentGame );
			
			// display the current game board
			System.out.println("move " + currentGame.getPieceCount() 
							   + ": Player " + current_player
							   + ", column " + playColumn);
			System.out.print("game state after move:\n");
			currentGame.printGameBoard();
		
			// print the current scores
			System.out.println( "Score: Player 1 = " + currentGame.getScore( 1 ) + ", Player2 = " + currentGame.getScore( 2 ) + "\n " );
			
			currentGame.printGameBoardToFile(output);
		} 
		else 
		{
			System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over");
		}		
		return;
	}else{
		System.out.println( "\n" + game_mode + " is an unrecognized game mode \n try again. \n" );
		return;
    }
} // end of main()
	
  /**
   * This method is used when to exit the program prematurly.
   * @param value an integer that is returned to the system when the program exits.
   */
  private static void exit_function( int value )
  {
      System.out.println("exiting from MaxConnectFour.java!\n\n");
      System.exit( value );
  }
} // end of class connectFour
