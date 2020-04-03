package LowLevelDesign;


// Builder pattern basically used when we want to initialize the class properties once and dont want to change it again,
// and also the number of such properties must be large then only builder pattern is helpfull.

// For the sake of simplicity here we are taking 4 properties only.
class leopard{
	private int weight;
	private String color;
	private String gender;
	private String name;
	
//	if we want to initialize this properties once then we must have to pass it to the constructor but since its number are large, 
//	so we used builder here.
	
	
	//Builder Class
	static class Builder{
		private int weight;
		private String color;
		private String gender;
		private String name;
		
		public  Builder(String name){              // In the constructor we will get the parameter that should be initialize first, 
			this.name = name;                     // rest the remaining one are not that much compulsory.
		}
		
		
		public Builder setWeight(int weight){        // Setter for weight
			this.weight = weight;
			return this;
		}
		public Builder setName(String name){         // Setter for name (In case client want to change it later on.)
			this.name = name;
			return this;
		}
		public Builder setGender(String gender){     // Setter for gender
			this.gender = gender;
			return this;
		}
		public Builder setColor(String color){       // Setter for color 
			this.color = color;
			return this;
		}
		
		
		public leopard Build(){
			leopard l = new leopard(this);
			return l;
		}
		
	}
	
	
	// Constructor of leopard class
	public leopard(Builder obj) {  
		
		// here we can have ,multiple validators and checks
		this.color = obj.color;
		this.name = obj.name;
		this.gender = obj.gender;
		this.weight = obj.weight;
		
	}
	
	
	public int getWeight(){        // getter for weight
		return this.weight;
	}
	public String getName(){         // getter for name 
		return this.name;
	}
	public String getGender(){     // getter for gender
		return this.gender;
	}
	public String getColor(){       // getter for color 
		return this.color;
	}
	
}
public class BuilderPattern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		leopard obj = new leopard.Builder("Indian").setColor("black").setGender("Male").setWeight(110).Build();
		System.out.println(obj.getName()+" "+obj.getGender()+" "+obj.getColor()+" "+obj.getWeight());
	}

}
