import java.util.Scanner;


public class BoardRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String[] COLORS = {"Red", "Blue", "Green","Yellow","Orange", "Purple"};
		final int GUESSES = 8;
		final int SIZEOFGUESS = 4;
		Code secret = Code.randomCode(COLORS, SIZEOFGUESS);
		boolean win = false;
		boolean playAgain = true;
		
		//System.out.println(secret.toString() + secret.getLength());
		System.out.println("   _____                   __                       .__            .___");
		System.out.println("  /     \\ _____    _______/  |_  ___________  _____ |__| ____    __| _/");
		System.out.println(" /  \\ /  \\\\__  \\  /  ___/\\   __\\/ __ \\_  __ \\/     \\|  |/    \\  / __ | ");
		System.out.println("/    Y    \\/ __ \\_\\___ \\  |  | \\  ___/|  | \\/  Y Y  \\  |   |  \\/ /_/ | ");
		System.out.println("\\____|__  (____  /____  > |__|  \\___  >__|  |__|_|  /__|___|  /\\____ | ");
		System.out.println("        \\/     \\/     \\/            \\/            \\/        \\/      \\/" );

		Board game = new Board(secret, GUESSES);
		String strOfColors = "";
		for(int i = 0; i< COLORS.length; i++){
			strOfColors += COLORS[i] + ", ";
		}
		strOfColors = strOfColors.substring(0,strOfColors.length()-2);//Cuts out last comma and space.
		
		
		//User interface loop:
		System.out.println("Welcome to MasterMind!" +
							"\nEnter one color at a time. To restart your guess type \"cancel\".\n"
							+ "The input can be the name of a color (i.e. Red or red) or can be the first letter (i.e. R or r).");		// or \"red, blue, green\" or \"r,b,g\""); // this became hard and was set aside for later
		Scanner reader = new Scanner(System.in);
			while(playAgain){
	
		for(int i = 0; i< GUESSES; i++){
			String[] guesses = new String[SIZEOFGUESS];
			System.out.println("The colors are: " + strOfColors + ".");
			boolean realColor = false;
				for(int k = 0; k<SIZEOFGUESS; k++){
					realColor = false;
					System.out.println("Input the " + (k+1) + " dot in the your guess: ");
					String guess = reader.nextLine();
					for(int f =0 ; f<COLORS.length && guess.length() >= 1; f++){
						if(guess.equalsIgnoreCase(COLORS[f]) || (guess.length()==1 && guess.substring(0,1).equalsIgnoreCase(COLORS[f].toString().substring(0,1)))){
							realColor = true;
							guesses[k]=guess;
							break;
						}
					}
					if(!realColor){System.out.println("Error with the input: " + guess); i--;break;}
				}
			if(realColor){
				win = game.makeAGuess(guesses);
				System.out.println(game.toString());
				if(win){
					System.out.println("	  ___    ___  ________      ___  ___          ___       __       ___      ________       ___        ___        ___  ");     
					System.out.println("         |\\  \\  /  /||\\   __  \\    |\\  \\|\\  \\        |\\  \\     |\\  \\    |\\  \\    |\\   ___  \\    |\\  \\      |\\  \\      |\\  \\    ");   
					System.out.println("         \\ \\  \\/  / /\\ \\  \\|\\  \\   \\ \\  \\\\\\  \\       \\ \\  \\    \\ \\  \\   \\ \\  \\   \\ \\  \\\\ \\  \\   \\ \\  \\     \\ \\  \\     \\ \\  \\    ");  
					System.out.println("          \\ \\    / /  \\ \\  \\\\\\  \\   \\ \\  \\\\\\  \\       \\ \\  \\  __\\ \\  \\   \\ \\  \\   \\ \\  \\\\ \\  \\   \\ \\  \\     \\ \\  \\     \\ \\  \\    "); 
					System.out.println("	   \\/  /  /    \\ \\  \\\\\\  \\   \\ \\  \\\\\\  \\       \\ \\  \\|\\__\\_\\  \\   \\ \\  \\   \\ \\  \\\\ \\  \\   \\ \\__\\     \\ \\__\\     \\ \\__\\   "); 
					System.out.println("  	 __/  / /       \\ \\_______\\   \\ \\_______\\       \\ \\____________\\   \\ \\__\\   \\ \\__\\\\ \\__\\   \\|__|      \\|__|      \\|__|   "); 
					System.out.println("	|\\___/ /         \\|_______|    \\|_______|        \\|____________|    \\|__|    \\|__| \\|__|       ___        ___        ___ "); 
					System.out.println("	\\|___|/                                                                                       |\\__\\      |\\__\\      |\\__\\ "); 
					System.out.println("	                                                                                              \\|__|      \\|__|      \\|__|");
					break;
				}
			}
		}
		if(!win){
		System.out.println("YOU LOSE\n"
				+ "The answer was " + secret.toString());
		}
		System.out.println("Would You like to Play again??? enter 1 to play again or 2 to exit.");
		int again = reader.nextInt();
		reader.nextLine();
		playAgain = again == 1;
		
		if(playAgain){
			secret = Code.randomCode(COLORS, SIZEOFGUESS);
			game = new Board(secret, GUESSES);
		}
		}
		reader.close();
		
	}
}

