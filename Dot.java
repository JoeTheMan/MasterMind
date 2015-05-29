
public class Dot {
	//Fields
	private String color;
	//Constructors
	public Dot(String aColor){
		color = aColor;
	}
	//Methods
	public String getColor(){
		return color;
	}
	public boolean equals(Dot other){
		return color.equalsIgnoreCase(other.toString()) ||
				color.substring(0, 1).equalsIgnoreCase(other.toString().substring(0, 1));//Allows User to only enter first letter of color.
	}
	public String toString(){
		return color;
	}
}
