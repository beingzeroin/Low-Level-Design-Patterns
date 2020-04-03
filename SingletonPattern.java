package LowLevelDesign;


// SINGLETON CLASS
class resourceIntializer{
	int A;
	int B;
	int C;
	static resourceIntializer R;
	private resourceIntializer(int A,int B, int C) {
		// create Connection
		// db query
		// close connection
		//sample:-
		this.A = A;
		this.B = B;
		this.C = C;
	}
	public static resourceIntializer getInstance(int A,int B, int C) {
		if(R == null) {
			R = new resourceIntializer(A,B,C);
		}
		return R;
	}
}


public class SingletonPattern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		resourceIntializer object_1 = resourceIntializer.getInstance(10, 11, 13);
		resourceIntializer object_2 = resourceIntializer.getInstance(12, 14, 15);
		System.out.println((object_2.A));					// this shows that only 1 object is created of class resourceIntializer
		System.out.println(object_1.equals(object_2));		// this shows that only 1 object is created of class resourceIntializer
	}

}
