
public class Answer {
	//Fields
	private int[] answer;
	private int lastAnswerIndex;
	//constructors
	public Answer(int size){
		answer = new int[size];
		for(int i = 0; i<answer.length ; i++){
			answer[i]=0;
		}
		lastAnswerIndex = 0;
	}
	//methods
	public void addWhite(){
		answer[lastAnswerIndex] = 1;
		lastAnswerIndex++;
	}
	public void addBlack(){
		answer[lastAnswerIndex] = 2;
		lastAnswerIndex++;
	}
	public int[] getAnswer(){
		return answer;
	}
	public int getNumOf1(){
		int retVal = 0;
		for(int e: answer){
			if(e==1){
				retVal++;
			}
		}
		return retVal;
	}
	public int getNumOf2(){
		int retVal = 0;
		for(int e: answer){
			if(e==2){
				retVal++;
			}
		}
		return retVal;
	}
	public String toString(){
		String str = "Answer: ";
		for (int i: answer){
			str += i + "\t";
		}
		return str;
	}
}
