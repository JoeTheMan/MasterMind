import java.util.ArrayList;


public class Board {
	//field
	Code secret;
	ArrayList<Code> guesses;
	ArrayList<Answer> answers;
	int triesLeft;
	//constructors
	public Board(Code aCode, int numOfTries){
		secret = new Code(aCode.getCode());
		guesses = new ArrayList<Code>(numOfTries);
		answers = new ArrayList<Answer>(numOfTries);
		triesLeft = numOfTries;
		
	}
	//methods
	public boolean makeAGuess(String[] colors){
		Dot[] dots = new Dot[colors.length];
		for(int i = 0; i<colors.length; i++){
			dots[i] = new Dot(colors[i]);
		}
		inputGuess(new Code(dots));
		return win();
	}
	public boolean makeACodeGuess(Code c){
		inputGuess(c);
		return win();
	}
	private void inputGuess(Code guess){
		guesses.add(guess);
		Answer response =  secret.checkCode(guess);
		answers.add(response);
		triesLeft--;
	}
	private boolean win(){
		boolean retBoo = true;
		if(answers.size()<1){return false;}
		int[] answer = answers.get(answers.size()-1).getAnswer();
		for(int c = 0; c< answer.length ; c++){
			if(answer[c] != 2){
				retBoo = false;
			}
		}
		return retBoo;
	}
	public ArrayList<Answer> getAnswers(){
		return answers;
	}
	public ArrayList<Code> getCodes(){
		return guesses;
	}
	public String toString(){
		String str = "";
		for(int i = 0; i<guesses.size(); i++){
			str += (i+1) + " try: \t" + guesses.get(i).toString() +
					"\t" + answers.get(i).toString() + "\n\n";
		}
		str += "You have " + triesLeft + " tries left.";
		return str;
	}
}
