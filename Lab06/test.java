
public class test {
	public static void main(String[] args)
	{
		int numDiscs = 7;
		int point = 0;
		
		TowerOfHanoi t = new TowerOfHanoi(numDiscs);
		int [] returnva = t.getArrayOfDiscs(0);
		int [] test = new int [7];
		System.out.println("test[4]:" +test[5]);
	}
}
