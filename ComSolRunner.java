import java.util.ArrayList;


public class ComSolRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Make the Board
		final String[] COLORS = {"Red", "Blue", "Green","Yellow","Orange", "Purple"};
		final int GUESSES = 8;
		final int SIZEOFGUESS = 4;
		Code secret = Code.randomCode(COLORS, SIZEOFGUESS);
		boolean win = false;
		
		System.out.println("Solution: " +  secret.toString() + "\n");

		Board game = new Board(secret, GUESSES);
		
		//Make the computer solver
		ArrayList<Answer> preAns = new ArrayList<Answer>();
		ArrayList<Code> preCodes = new ArrayList<Code>();
		Dot[] colorArray = new Dot[COLORS.length];
		for(int i = 0; i<COLORS.length ; i++){
			colorArray[i] = new Dot(COLORS[i]);
		}
		int sizeOfGuesses = 4;
		ComputerSolver Milo = new ComputerSolver(preAns, preCodes , colorArray,sizeOfGuesses);
		int numberOfTimesWon = 0;
		int numberOfTimesPlayed = 1;
		
		for(int timesToRun = 0; timesToRun<numberOfTimesPlayed; timesToRun++){
			//Play
			Code winningCode = new Code(new Dot[] {new Dot("nothing")});
			for(int i = 0; i<10 ; i++){
				Code guess = Milo.getGuess();
				win = game.makeACodeGuess(guess);
				preAns = game.getAnswers();
				preCodes = game.getCodes();	
				winningCode =  preCodes.get(preCodes.size()-1);
				Answer winningAnswer = preAns.get(preAns.size()-1);
				System.out.println(winningCode + "\t" + winningAnswer.toString());
				if(win){
					break;
				}
				else{
					Milo = new ComputerSolver(preAns , preCodes , colorArray , sizeOfGuesses);
				}
			}
			System.out.println("\nWin is " + win + "\nWinning Code: " + winningCode.toString());
			numberOfTimesWon++;
			secret = Code.randomCode(COLORS, SIZEOFGUESS);
			game = new Board(secret, GUESSES);
			preAns = new ArrayList<Answer>();
			preCodes = new ArrayList<Code>();
			Milo = new ComputerSolver(preAns, preCodes , colorArray,sizeOfGuesses);
		}
		System.out.println("The computer won " + numberOfTimesWon + "/" + numberOfTimesPlayed + " times!");
	}
}
