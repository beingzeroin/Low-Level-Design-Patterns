package LowLevelDesign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
class MyException extends Exception{
	public MyException(String s) {
		super(s);
	}
}
/* USE CASE OF CASCADE PATTERN:-
Cascade pattern is used in the scenario when their are multiple clients and each of them require different type of operation to 
be done .
for ex:- Here we are considering an example of address validation, now their could be multiple clients who requires address validation.
         Like dispatch department require more fields then card department so we have to build a system in which their are cascading of 
         validators in the backend and cascading is different for different clients.*/

// Address class
class Address{
	private int Zip;
	private String AdressLine;
	private String State;
	private String Country;
	private String City;
	
	public void setZip(int Zip) { 
		this.Zip = Zip;
	}
	public int getZip() { 
		return Zip;
	}
	
	public void setAdressLine(String AdressLine) { 
		this.AdressLine = AdressLine;
	}
	public String getAdressLine() { 
		return AdressLine;
	}
	
	public void setState(String State) { 
		this.State = State;
	}
	public String getState() { 
		return State;
	}
	
	public void setCountry(String Country) { 
		this.Country = Country;
	}
	public String getCountry() { 
		return Country;
	}
	
	public void setCity(String City) { 
		this.City = City;
	}
	public String getCity() { 
		return City;
	}
}

// All different kinds of validators.
interface validators{
	public boolean validate(Address add);
}
class zipValidator implements validators{
	
	public boolean validate(Address add) {
//		here the zip validation algo will come.
		System.out.println("Zip validated");
		return true;
	}
}
class cityValidator implements validators{
	
	public boolean validate(Address add) {
//		here the city validation algo will come.
		System.out.println("City validated");
		return true;
	}
}
class stateValidator implements validators{
	
	public boolean validate(Address add) {
//		here the state validation algo will come.
		System.out.println("State validated");
		return true;
	}
}
class countryValidator implements validators{
	
	public boolean validate(Address add) {
//		here the country validation algo will come.
		System.out.println("Country validated");
		return true;
	}
}
class addressLineValidator implements validators{
	
	public boolean validate(Address add) {
//		here the Address Line validation algo will come.
		System.out.println("Address Line validated");
		return true;
	}
}

/*Class which actually perform validations and in this class priority of different validators can also be set suing different 
allignment in the validators list.*/

class MasterValidator{
	private ArrayList<validators> validators;                 // the order in this list is based on priority(refer FAIL FAST ALGO)
	public MasterValidator(ArrayList<validators> validator){
		this.validators = validator;
	}
	public boolean validate(Address add) {
		for(validators val : validators) {                // cascading of different validators are done here.
			if(!val.validate(add))
				return false;
		}
		return true;
	}
}




// this is singleton Factory class
class ValidatorCascadeFactory{                     // This class stores the relationship between client and validators.
	private HashMap<String,MasterValidator> map = new HashMap<>();
	static ValidatorCascadeFactory X;
	private ValidatorCascadeFactory(){
//		order of validators matters here.
		map.put("c1", new MasterValidator(new ArrayList<>(Arrays.asList(new zipValidator(),new countryValidator(),
				                                                        new stateValidator(),new cityValidator(),
				                                                        new addressLineValidator()))));
		map.put("c2", new MasterValidator(new ArrayList<>(Arrays.asList(new zipValidator(),new countryValidator()))));
		map.put("c3", new MasterValidator(new ArrayList<>(Arrays.asList(new countryValidator(),new stateValidator()))));
	}
	
	public static ValidatorCascadeFactory getInstance(){
		if(X == null) {
			X = new ValidatorCascadeFactory();
		}
		return X;
	}
	public MasterValidator getValidator(String code) throws MyException{
		if(map.containsKey(code)) {
			return map.get(code);
		}else {
			throw new MyException("Invalid Clients code code");
		}
	
	}
}

// clients code come under this section
public class CascadePattern {

	public static void main(String[] args) {
		
		/*Here, client1 have id c1,
		      client2 have id c2,
		      client3 have id c3*/
		ValidatorCascadeFactory obj = ValidatorCascadeFactory.getInstance();
		try {
			Address add = new Address();
			obj.getValidator("c1").validate(add);
			obj.getValidator("c2").validate(add);
			obj.getValidator("c3").validate(add);
			obj.getValidator("c4").validate(add);
		}catch(MyException ex){
			System.out.println(ex.getMessage());
		}
	}

}
