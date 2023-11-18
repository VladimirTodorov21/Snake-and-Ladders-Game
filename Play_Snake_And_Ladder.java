/*This class runs a series of methods that create a game called Ladder And Snake. 2 players must play the game 
 *if user inputs 0 or 1, the program terminates. Once a valid integer input is prompted by the user, the game sets
 *the player count to 2 and each player rolls a die to determine the player order. In case of a tie, the players roll again.
 *Once the player order is decided, the players roll the dice until they reach the goal of square 100 and win the game.
 *Along the way, the players may land on a ladder or a snake and depending on which, they either go up or down in position.*/

package comp249_assignment1;

public class PlayLadderAndSnake {
	
	public static void main(String[] args) {
		
		LadderAndSnake Game = new LadderAndSnake();

		Game.play();
	}
}
