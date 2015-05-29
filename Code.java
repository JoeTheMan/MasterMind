//import java.util.Arrays;

public class Code {
	//Fields
	private Dot[] code;
	//Construcors
	public Code(Dot[] aCode){
		code = new Dot[aCode.length];
		for(int i = 0; i < aCode.length; i++ ){
			code[i] = aCode[i];
		}
	}
	//Methods
	//Pre: codes must be the same length
	public Answer checkCode(Code other){
		int[] usedDots = new int[other.getLength()];
		int[] myUsedDots = new int[getLength()];
		Answer retAns = new Answer(code.length);
		for(int i = 0; i<other.getLength() ; i++){
			if(usedDots[i]==0 && code[i].equals(other.get(i))){
				retAns.addBlack();
				usedDots[i] = 1;
				myUsedDots[i] = 1;
				//System.out.println(Arrays.toString(usedDots) + "\n" + Arrays.toString(myUsedDots));
			}
		}
		for(int i = 0; i<other.getLength() ; i++){
			int loc = indexOf(other.get(i), i , usedDots, myUsedDots);
			if(loc > -1){
				//System.out.println(other.get(i) + " from " + i + " gives a 1");
				retAns.addWhite();
				usedDots[loc] = 1;
				myUsedDots[i] = 1;
				//System.out.println(Arrays.toString(usedDots) + "\n" + Arrays.toString(myUsedDots));
			}
		}
		return retAns;
	}
	private int indexOf(Dot a,int indexOfDot, int[] usedColors, int[] myUsedColors){
		int retVal = -1;
		for(int i = 0; i<code.length; i++){
			if(usedColors[i]+myUsedColors[indexOfDot] == 0 && code[i].equals(a)){
				retVal = i;
				//System.out.println(a.toString() + " is shown at " + i);
				break;
			}
		}
		return retVal;
	}
	public int getLength(){
		return code.length;
	}
	public Dot get(int loc){
		return code[loc];
	}
	public Dot[] getCode(){
		return code;
	}
	public static Code randomCode(String[] colors ,int sizeOfCode){
		Dot[] random = new Dot[sizeOfCode];
		for(int i = 0; i < sizeOfCode; i++){
			random[i] = new Dot(colors[(int) (Math.random()*colors.length)]);
		}
		return new Code(random);
	}
	public String toString(){
		String str = "Code: \t";
		for(Dot e : code){
			str += e.toString() + "\t";
		}
		return str;
	}
	public static void main(String[] args){
		Dot[] aDot = {new Dot("Blue"),new Dot("Blue"),new Dot("Blue"), new Dot ("Blue")};
		Dot[] bDot = {new Dot("Blue"),new Dot("Blue"),new Dot("Green"), new Dot ("Green")};
		Code a = new Code(aDot);
		Code b = new Code(bDot);
		System.out.println(a.checkCode(b).toString());
		Dot[] aDot1 = {new Dot("Yellow"),new Dot("Blue"),new Dot("Blue"), new Dot ("Yellow")};
		Dot[] bDot1 = {new Dot("Blue"),new Dot("Blue"),new Dot("Green"), new Dot ("Green")};
		Code a1 = new Code(aDot1);
		Code b1 = new Code(bDot1);
		System.out.println(a1.checkCode(b1).toString());
		Dot[] aDot2 = {new Dot("Yellow"),new Dot("Blue"),new Dot("Blue"), new Dot ("Blue")};
		Dot[] bDot2 = {new Dot("Blue"),new Dot("Blue"),new Dot("Green"), new Dot ("Green")};
		Code a2 = new Code(aDot2);
		Code b2 = new Code(bDot2);
		System.out.println(a2.checkCode(b2).toString());
		Dot[] aDot3 = {new Dot("Blue"),new Dot("Blue"),new Dot("Blue"), new Dot ("Yellow")};
		Dot[] bDot3 = {new Dot("Blue"),new Dot("Blue"),new Dot("Green"), new Dot ("Green")};
		Code a3 = new Code(aDot3);
		Code b3 = new Code(bDot3);
		System.out.println(a3.checkCode(b3).toString());
	}
}
