

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InfiniteIntegerInterface gd1 = new LInfiniteInteger("500");
		System.out.println(gd1.toString());
		InfiniteIntegerInterface gd2 = new LInfiniteInteger("499");
		//InfiniteIntegerInterface gd3 = new LInfiniteInteger("563");
		//System.out.println(gd2.minus(gd3));
		
		//System.out.println(((LInfiniteInteger) gd1).getBackValue(1));
		//System.out.println(((LInfiniteInteger) gd2).getBackValue(3)); 
		//System.out.println("Minus Result: " +gd1.minus(gd2));
		
		System.out.println("Multiply Result: "+(gd1.multiply(gd2).toString()));
		int division = 500*499;
		if(gd1.multiply(gd2).equals(division) == false){
			System.out.println("fail");
		}
		division = 4678 % 1000;
		System.out.println(division);
	}
}