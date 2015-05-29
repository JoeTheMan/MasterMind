import java.util.ArrayList;


public class ComputerSolver {
	//Fields
	private ArrayList<Answer> previousAnswers;
	private ArrayList<Code> previousCodes;
	private Dot[] colors;
	private int sizeOfCodes;
	private Dot[][] posabilities;
	private int numOfPosabilities;
	//Constructors
	/*
	 * Precondition: colorArray must be even.
	 */
	public ComputerSolver(ArrayList<Answer> preAns, ArrayList<Code> preCod, Dot[] colorArray , int sizeOfGuesses ){
		previousCodes = preCod;
		previousAnswers = preAns;
		colors = new Dot[colorArray.length];
		for(int i = 0; i<colorArray.length ; i++){
			colors[i] = colorArray[i];
		}
		sizeOfCodes = sizeOfGuesses;
		numOfPosabilities = (int) Math.pow(colorArray.length, sizeOfGuesses);
		posabilities = new Dot[numOfPosabilities][sizeOfGuesses];
	}
	//Methods
	public Code[] generatePosabilities(){
		Code[] retCodes = new Code[numOfPosabilities];
		for(int i = 1; i< sizeOfCodes+1; i++){
			generateEachRound(i);
		}
		for(int i = 0; i<numOfPosabilities; i++){
			retCodes[i] = new Code(posabilities[i]);
		}
		
		return retCodes;
	}
	private void generateEachRound(int set){ 
		int section = numOfPosabilities / (int) Math.pow(colors.length,set);
		for(int i = 0; i<numOfPosabilities ; i++){
			//System.out.println(numOfPosabilities + " " + section + "  " + i + "  " + (set-1) + " " + i/section);
			posabilities[i][set-1] = colors[(i/section) % colors.length];
		}
	}
	private Code getBestChoice(Code[] posables){
		for(Code code: posables){
			if(passAll(code)){
				return code;
			}
		}
		return posables[0]; //should never be used....
	}
	private boolean passAll(Code guess){
		for(int i = 0; i<previousAnswers.size() ; i++){
			if(!pass(guess, previousCodes.get(i) ,previousAnswers.get(i))){
				return false;
			}
		}
		return true;
	}
	private boolean pass(Code guess, Code guessed, Answer answer){
		Answer calculatedAnswer = guess.checkCode(guessed);
		return (calculatedAnswer.getNumOf2() == answer.getNumOf2() && calculatedAnswer.getNumOf1() == answer.getNumOf1());
	}
	private ArrayList<Dot> getNotYetGuess(){
		ArrayList<Dot> notYetUsed = new ArrayList<Dot>(colors.length);
		for(int i = 0; i<colors.length; i++){
			notYetUsed.add(i,colors[i]);
		}
		for(int i =0; i< previousAnswers.size(); i++){
			Dot[] element = previousCodes.get(i).getCode();
			for(int j = 0 ; j< sizeOfCodes; j++){
				int index = notYetUsed.indexOf(element[j]);
				if(index>-1){
					notYetUsed.remove(index);
				}
			}
		}
		return notYetUsed;
	}
	public Code getGuess(){
		Code retCode;
		Dot[] retDots = new Dot[sizeOfCodes];
		if(previousAnswers.size() == 0){ 
			for(int i = 0; i<sizeOfCodes ; i++){
				retDots[i] = colors[i/2];
			}
			retCode = new Code(retDots);
		}
		else if(previousAnswers.size() <= (colors.length/2)){
			int dotsColorCorrect = 0;
			for(int i = 0; i<previousAnswers.size(); i++){
				for(int j = 0; j<sizeOfCodes ; j++){
					if(previousAnswers.get(i).getAnswer()[j] != 0){
						dotsColorCorrect++;
					}
				}
			}
			if(dotsColorCorrect >= sizeOfCodes){
				retCode = getBestChoice(generatePosabilities());
				
			}
			else{
				ArrayList<Dot> notUsed = getNotYetGuess();
				for(int i = 0; i<sizeOfCodes; i++){
					retDots[i] = notUsed.get(i/2);
				}
				retCode = new Code(retDots);
			}
		}
		else{
			retCode = getBestChoice(generatePosabilities());
		}
		return retCode;
	}
	public static void main(String[] args){
		ArrayList<Answer> preAns = new ArrayList<Answer>();
		ArrayList<Code> preCodes = new ArrayList<Code>();
		Dot[] colorArray = {new Dot("Red"), new Dot("Blue"), new Dot("Green"),new Dot("Yellow"),new Dot("Orange"), new Dot("Purple")};
		int sizeOfGuesses = 4;
		ComputerSolver Milo = new ComputerSolver(preAns, preCodes , colorArray,sizeOfGuesses);
		Code[] pos = Milo.generatePosabilities();
		for(Code e : pos){
			System.out.println(e.toString());
		}
		System.out.println(Milo.getGuess().toString());
		
	}
}

