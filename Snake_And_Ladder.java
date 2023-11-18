/*This class runs a series of methods that create a game called Ladder And Snake. 2 players must play the game 
 *if user inputs 0 or 1, the program terminates. Once a valid integer input is prompted by the user, the game sets
 *the player count to 2 and each player rolls a die to determine the player order. In case of a tie, the players roll again.
 *Once the player order is decided, the players roll the dice until they reach the goal of square 100 and win the game.
 *Along the way, the players may land on a ladder or a snake and depending on which, they either go up or down in position.*/

package comp249_assignment1;

import java.util.Random; //class needs to import Random function

import java.util.Scanner; //class needs to import Scanner

public class LadderAndSnake {
	
	//Private attributes declared
	private int NumberOfPlayers;
	private int DiceNumber;
	private int[][] Board = new int[10][10];
	
	//Default Constructor that sets the integers to 0 and the 2d-array to a 10x10
	public LadderAndSnake() {
		NumberOfPlayers = 0;
		DiceNumber = 0;
		LadderAndSnake Board[][] = new LadderAndSnake[10][10];
	}
	
	//Constructor that takes an Integer Parameter
	public String LadderAndSnakeGame(int NumOfPlayers) {
		this.NumberOfPlayers = NumOfPlayers; //the attribute set to the value of the parameter
		String str = "";
	
		//if the number of players are 2 or more, the system will set the value to 2
		if (NumberOfPlayers >= 2) {
			str = "\nInitialization was attempted for " + NumberOfPlayers + " member of players; \nhowever, this is only expected for an extended version the game. Value will be set to 2.\n";
			NumberOfPlayers = 2;
		//otherwise if the number fo players is less than 2, then the program will terminate with an error message
		} else if (NumberOfPlayers < 2) {
			str = "\nError: Cannot execute the game with less than 2 players! Will exit";
		}
		
		return str;
	}
	
	//Method that returns an integer of a random value for a dice roll
	public int flipDice() {
		
		Random number = new Random();
		this.DiceNumber = number.nextInt(6);
		DiceNumber++;
		
		return DiceNumber;
	}
	
	//Method that returns the exact position of a player on the game board
	public int SquareLocation(int position) {	
		
		//loop looks through rows
		for(int i=0;i<Board.length;i++) {
			//if the parameter is located in a particular row
			if (i == position/10) {
				//loop looks through columns of a specific row
				for(int j=i;j<Board.length;j++) {
					//if the column is the same as the first digit of the parameter
					if (j == (position - (position/10)*10)) {
						Board[i][j] = position; //the location is set to the specific row and column
					}
				}
			}
		}
		return position;
	}
	
	/* public String toString(int position1, int position2) {	
		
		String str = "";
		for(int i=0;i<Board.length;i++) {
			for(int j=1;j<=Board.length;j++) {
				System.out.print("|");
				if ((i == position1/10) && (j == (position1 - (position1/10)*10))) {
					System.out.print("1");
				} else {
					System.out.print(" ");
				}
				
				if ((i == position2/10) && (j == (position2 - (position2/10)*10))) {
					System.out.print("2");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println("|");
		}
		return str;
	} */
	
	//Method that executes the game
	public void play(){
		Scanner NumberOfPlayers = new Scanner(System.in); //Scanner declared
		
		//Welcoming message
		System.out.println("===================================================");
		System.out.println("|           Welcome to Ladder And Snake           |");
		System.out.println("===================================================");
		
		System.out.print("\nPlease insert a positive integer of players: "); //prompts user to enter integer input
		int NumOfPlayers = NumberOfPlayers.nextInt(); //integer input from Scanner
		
		//if number of players is less than 2
		if (NumOfPlayers < 2) {
			System.out.print(LadderAndSnakeGame(NumOfPlayers)); //calls method LadderAndSnakeGame and prints error message
		//else if number of players is 2 or more
		} else if (NumOfPlayers >= 2) {
			//if number of players is more than 2
			if (NumOfPlayers > 2) {
				System.out.print(LadderAndSnakeGame(NumOfPlayers)); //calls method LadderAndSnakeGame and sets value to 2
			}	
			
			LadderAndSnake Player1 = new LadderAndSnake(); //instance object player1 is created
			LadderAndSnake Player2 = new LadderAndSnake(); //instance object player2 is created
			
			System.out.println("\n*** Players will now throw the dice to determine the player order. ***");
			
			boolean WhoGoesFirst = true; //boolean for which player goes first
			int AttemptCounter = 0; //integer that counts how many attempts are done before determining who goes first
			LadderAndSnake[] PlayerOrder = new LadderAndSnake[2]; //array determining player order is created
			
			while(WhoGoesFirst) {
				AttemptCounter++;
				
				int ResultPlayer1 = Player1.flipDice(); //player1 rolls a dice
				int ResultPlayer2 = Player2.flipDice(); //player2 rolls a dice
				
				System.out.println("\n| Zac got a dice value of " + ResultPlayer1 + " |"); //displays result of dice for first player
				System.out.println("| Julie 2 got a dice value of " + ResultPlayer2 + " |"); //displays result of dice for second player
				
				//if the dice roll of player1 is the same as player2
				if (ResultPlayer1 == ResultPlayer2) {
					System.out.println("| A tie was achieved between Player 1 and Player 2. Attempting to break the tie |"); //tie needs to be broken so players roll again
				} else {
					String PlayOrder[] = new String[2]; //string of player order declared
					//if dice roll of player1 is bigger than player2's dice roll
					if (ResultPlayer1 > ResultPlayer2) {
						PlayOrder[0] = "Zac"; //string of player 1
						PlayOrder[1] = "Julie"; //string of player 2
						PlayerOrder[0] = Player1; //player 1 goes first
						PlayerOrder[1] = Player2; //player 2 goes second
					//otherwise
					} else {
						PlayOrder[0] = "Julie"; //string of player 2
						PlayOrder[1] = "Zac"; //string of player 1
						PlayerOrder[0] = Player2; //player 2 goes first
						PlayerOrder[1] = Player1; //player 1 goes second
					}
					//prints the player order
					System.out.println("Reached final decision on order of playing: " + PlayOrder[0] + " then " + PlayOrder[1] + ". It took " + AttemptCounter + " attempt(s) before a decision could be made.");
					
					WhoGoesFirst = false; //first while loop condition is now set to false so after all statements below are executed, program will exit loop
					
					boolean GameContinues = true; //boolean for the game that goes on until one player wins
					int ResultFirstPlayer, ResultSecondPlayer, positionFirstPlayer = 0, positionSecondPlayer = 0; 
					
					while (GameContinues) {
						ResultFirstPlayer = PlayerOrder[0].flipDice(); //first player rolls dice
						ResultSecondPlayer = PlayerOrder[1].flipDice(); //second player rolls dice
						
						positionFirstPlayer = positionFirstPlayer + ResultFirstPlayer; //position of first player is increasing for each dice value
						positionSecondPlayer = positionSecondPlayer + ResultSecondPlayer; //position of second player is increasing for each dice value
						
						PlayerOrder[0].SquareLocation(positionFirstPlayer); //calls method to determine the location of first player on board
						PlayerOrder[1].SquareLocation(positionSecondPlayer); //calls method to determine the location of second player on board
						
						//if position of first player exceeds 100, the player goes backwards to satisfy dice value
						if (positionFirstPlayer > 100) {
							positionFirstPlayer = positionFirstPlayer - 2*(positionFirstPlayer -100);
						//else if position of second player exceeds 100, the player goes backwards to satisfy dice value
						} else if (positionSecondPlayer > 100) {
							positionSecondPlayer = positionSecondPlayer - 2*(positionSecondPlayer -100);
						}
						
						//prints dice value for first player and their position
						System.out.print("\n" + PlayOrder[0] + " got a dice value of " + ResultFirstPlayer + "; now in square " + positionFirstPlayer);
						
						//switch looks through specific positions and if the first player has the same position at that value, the player has either landed
						//on a snake or a ladder and either goes up or down in position
						switch (positionFirstPlayer) {
							case 1: 
								positionFirstPlayer = 38;
								PlayerOrder[0].SquareLocation(38);
								System.out.print(" then up to square 38");
								break;
							case 4:
								positionFirstPlayer = 14;
								PlayerOrder[0].SquareLocation(14);
								System.out.print(" then up to square 14");
								break;
							case 9:
								positionFirstPlayer = 31;
								PlayerOrder[0].SquareLocation(31);
								System.out.print(" then up to square 31");
								break;
							case 16:
								positionFirstPlayer = 6;
								PlayerOrder[0].SquareLocation(6);
								System.out.print(" then down to square 6");
								break;
							case 21:
								positionFirstPlayer = 42;
								PlayerOrder[0].SquareLocation(42);
								System.out.print(" then up to square 42");
								break;
							case 28:
								positionFirstPlayer = 84;
								PlayerOrder[0].SquareLocation(84);
								System.out.print(" then up to square 84");
								break;
							case 36:
								positionFirstPlayer = 44;
								PlayerOrder[0].SquareLocation(44);
								System.out.print(" then up to square 44");
								break;
							case 48:
								positionFirstPlayer = 30;
								PlayerOrder[0].SquareLocation(30);
								System.out.print(" then down to square 30");
								break;
							case 51:
								positionFirstPlayer = 67;
								PlayerOrder[0].SquareLocation(67);
								System.out.print(" then up to square 67");
								break;
							case 62:
								positionFirstPlayer = 19;
								PlayerOrder[0].SquareLocation(19);
								System.out.print(" then down to square 19");
								break;
							case 64:
								positionFirstPlayer = 60;
								PlayerOrder[0].SquareLocation(60);
								System.out.print(" then down to square 60");
								break;
							case 71:
								positionFirstPlayer = 91;
								PlayerOrder[0].SquareLocation(91);
								System.out.print(" then up to square 91");
								break;
							case 80:
								positionFirstPlayer = 100;
								PlayerOrder[0].SquareLocation(100);
								System.out.print(" then up to square 100");
								break;
							case 93:
								positionFirstPlayer = 68;
								PlayerOrder[0].SquareLocation(68);
								System.out.print(" then down to square 68");
								break;
							case 95:
								positionFirstPlayer = 24;
								PlayerOrder[0].SquareLocation(24);
								System.out.print(" then down to square 24");
								break;
							case 97:
								positionFirstPlayer = 76;
								PlayerOrder[0].SquareLocation(76);
								System.out.print(" then down to square 76");
								break;
							case 98:
								positionFirstPlayer = 78;
								PlayerOrder[0].SquareLocation(78);
								System.out.print(" then down to square 78");
								break;
						}
						
						//prints dice value for second player and their position
						System.out.print("\n" + PlayOrder[1] +" got a dice value of " + ResultSecondPlayer + "; now in square " + positionSecondPlayer);
						
						//switch looks through specific positions and if the second player has the same position at that value, the player has either landed
						//on a snake or a ladder and either goes up or down in position
						switch (positionSecondPlayer) {
							case 1: 
								positionSecondPlayer = 38;
								PlayerOrder[1].SquareLocation(38);
								System.out.print(" then up to square 38\n");
								break;
							case 4:
								positionSecondPlayer = 14;
								PlayerOrder[1].SquareLocation(14);
								System.out.print(" then up to square 14\n");
								break;
							case 9:
								positionSecondPlayer = 31;
								PlayerOrder[1].SquareLocation(31);
								System.out.print(" then up to square 31\n");
								break;
							case 16:
								positionSecondPlayer = 6;
								PlayerOrder[1].SquareLocation(6);
								System.out.print(" then down to square 6\n");
								break;
							case 21:
								positionSecondPlayer = 42;
								PlayerOrder[1].SquareLocation(42);
								System.out.print(" then up to square 42\n");
								break;
							case 28:
								positionSecondPlayer = 84;
								PlayerOrder[1].SquareLocation(84);
								System.out.print(" then up to square 84\n");
								break;
							case 36:
								positionSecondPlayer = 44;
								PlayerOrder[1].SquareLocation(44);
								System.out.print(" then up to square 44\n");
								break;
							case 48:
								positionSecondPlayer = 30;
								PlayerOrder[1].SquareLocation(30);
								System.out.print(" then down to square 30\n");
								break;
							case 51:
								positionSecondPlayer = 67;
								PlayerOrder[1].SquareLocation(67);
								System.out.print(" then up to square 67\n");
								break;
							case 62:
								positionSecondPlayer = 19;
								PlayerOrder[1].SquareLocation(19);
								System.out.print(" then down to square 19\n");
								break;
							case 64:
								positionSecondPlayer = 60;
								PlayerOrder[1].SquareLocation(60);
								System.out.print(" then down to square 60\n");
								break;
							case 71:
								positionSecondPlayer = 91;
								PlayerOrder[1].SquareLocation(91);
								System.out.print(" then up to square 91\n");
								break;
							case 80:
								positionSecondPlayer = 100;
								PlayerOrder[1].SquareLocation(100);
								System.out.print(" then up to square 100\n");
								break;
							case 93:
								positionSecondPlayer = 68;
								PlayerOrder[1].SquareLocation(68);
								System.out.print(" then down to square 68\n");
								break;
							case 95:
								positionSecondPlayer = 24;
								PlayerOrder[1].SquareLocation(24);
								System.out.print(" then down to square 24\n");
								break;
							case 97:
								positionSecondPlayer = 76;
								PlayerOrder[1].SquareLocation(76);
								System.out.print(" then down to square 76\n");
								break;
							case 98:
								positionSecondPlayer = 78;
								PlayerOrder[1].SquareLocation(78);
								System.out.print(" then down to square 78\n");
								break;
							default:
								System.out.print("\n");
						}
						
						//System.out.println(toString(positionFirstPlayer,positionSecondPlayer));
						
						//if first player lands on square 100, that player has won and game ends
						if (PlayerOrder[0].SquareLocation(positionFirstPlayer) == 100) {		
							System.out.println("Game Over! " + PlayOrder[0] + " won");
							GameContinues = false;
						//if second player lands on square 100, that player has won and game ends
						} else if (PlayerOrder[1].SquareLocation(positionSecondPlayer) == 100) {
							System.out.println("Game Over! " + PlayOrder[1] + " won");
							GameContinues = false;
						//otherwise game continues and loop is executed again
						} else {
							System.out.println("Game not over; flipping again");
						}
					}	
				}
			}
		}
		NumberOfPlayers.close(); //close Scanner
	}
}
