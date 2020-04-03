package LowLevelDesign;
import java.util.HashMap;

// In order to understand factory pattern we are considering an example in which we have to validate  the address given by client
interface validator{
	public void validate();
}

class InValidator implements validator{
	
	public void validate() {
		System.out.println("Indian Address");
	}
}

class UsValidator implements validator{
	
	public void validate() {
		System.out.println("Us Address");
	}
}

class UkValidator implements validator{
	
	public void validate() {
		System.out.println("Uk Address");
	}
}

class MyException extends Exception{
	public MyException(String s) {
		super(s);
	}
}

// 99% FACTORY CLASSES are singleton as well.
class AddressValidatorsFactory{
	
	private HashMap<String,validator> map  = new HashMap<>();
	private AddressValidatorsFactory() {
		map.put("In", new InValidator());
		map.put("Us", new UsValidator());
		map.put("Uk", new UkValidator());
	}
	public validator getValidator (String code) throws MyException{
			if(map.containsKey(code)) {
				return map.get(code);
			}else {
				throw new MyException("Their is no validator corresponding to the entered county code");
			}
		
	}
	static AddressValidatorsFactory X;
	public static AddressValidatorsFactory getInstance() {
		if(X == null) {
			X = new AddressValidatorsFactory();
		}
		return X;
	}
}



public class FactoryPattern {

	public static void main(String[] args) {
		AddressValidatorsFactory obj = AddressValidatorsFactory.getInstance();
		try {
			obj.getValidator("In").validate();
			obj.getValidator("Uk").validate();
			obj.getValidator("Us").validate();
			obj.getValidator("NK").validate();
			obj.getValidator("Us").validate();
		}catch(MyException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
