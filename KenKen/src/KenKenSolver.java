
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;


public class KenKenSolver {
	private static Coordinate [] coor;
	private static Cage [] cages;
//	private static int [] [] board = {{3,2,1},{1,3,2},{2,1,3}};
	private static int [] [] temp = {{5,4,6,8,2,7,1,3},{4,7,1,3,8,2,6,5},{8,3,7,5,1,4,2,6},{2,8,4,7,6,3,5,1},{1,5,8,4,7,6,3,2},{3,6,2,1,4,5,7,8},{6,1,5,2,3,8,4,7},{7,2,3,6,5,1,8,4}};
	private static int [] [] board;
	private static int size;
	private static  JFrame frame;
	private static KenKenComponent kc;
	private static int cageNum;
	
	public static void main (String[] args) {
		System.out.println("Hello World");
		fileReader();
	   System.out.println(solveKenKen(0,0));

		kc.setNumber(board);
		
	}
	public void setUpGui(){
		
	}
	public static boolean fileReader(){
		try{
			Scanner inScan = new Scanner(System.in);
			
			System.out.print("Enter KenKen file name :");
			String tempFileName= inScan.next();
		File KenKenFile = new File(tempFileName);
		String fileName = KenKenFile.getAbsolutePath();
		inScan.close();
		Scanner KenKenScanner = new Scanner (KenKenFile);
		size = KenKenScanner.nextInt();
		board = new int [size][size];
		int cageNum=KenKenScanner.nextInt();
		frame = new JFrame();
		kc = new KenKenComponent(fileName , frame);
		cages = new Cage[cageNum];
		coor = new Coordinate [size*size];
		int index =0;
		for(int i = 0; i < cageNum; i++ ){
			String line = KenKenScanner.nextLine();
			if (line.equals(""))
				line = KenKenScanner.nextLine();
			String [] stringSplit = line.split(",");
			Integer tar = Integer.valueOf(stringSplit[0]);
			int targetNum = tar.intValue();
			//System.out.println("targetNumber: " +targetNum);
			
			String Op = stringSplit[1];
			Integer squares = Integer.valueOf(stringSplit[2]);
			cages[i] = new Cage(tar,Op,squares);
			for (int l = 0; l < squares; l++){
				String tempString = KenKenScanner.nextLine();
				String [] tempStringSplit = tempString.split(",");
				int x = Integer.parseInt(tempStringSplit[0]);
				int y = Integer.parseInt(tempStringSplit[1]);
				coor[index]= new Coordinate();
				coor[index].setR(x);
				coor[index].setC(y);
				coor[index].setCageNumber(i);
				index ++;
			}
		}
		
	}catch(FileNotFoundException e){
		System.out.println("Invalid File Name");
		return false;
		}
	 catch(NumberFormatException e){System.out.println("Invalid numbers");
	 return false;
	 }
	 catch (IOException e){System.out.println("IOE");}
		return true;
	}

	public static boolean isLegal(int r, int c){
		int [] [] temp = board;
		ArrayList<Integer> values = new ArrayList <Integer>();
		if (checkForRowDuplicates (temp))
			return false;
		if (checkForColumnDuplicates (temp))
			return false;

		for (int k = 0 ; k < size*size ; k++)
			if(r == coor[k].getR() && c == coor[k].getC()){
				cageNum = coor[k].getCage();
				break;
			}
		int tmep = coor.length;
		for (int m = 0; m < coor.length; m++){
			if (cageNum ==coor[m].getCage() ){
				int r1= coor[m].getR();
				int c1= coor[m].getC();
				if(temp[r1][c1] != 0)
					values.add(temp[r1][c1]);
			}
		}
		String op= cages[cageNum].getOperator();
		int tv =cages[cageNum].returnTargetValue();
		int n =cages[cageNum].getSquares();
		if(!addToCage(values,op,tv,n))
			return false;
		return true;
	}
	
	public static boolean checkForColumnDuplicates(int[][] inArray)
	{
	    for (int col = 0; col < inArray.length; col++)
	    {
	        for (int row = 0; row < inArray[col].length; row++)
	        {
	            int num = inArray[row][col];
	            for (int otherRow = row + 1; otherRow < inArray.length; otherRow++)
	            {
	                if (num == inArray[otherRow][col] && num != 0)
	                    return true;
	            }    	            
	        }
	    }
	    return false;
	}
	public static boolean addToCage(ArrayList <Integer> values, String operator, int targetValue, int numOfSquares){
		Permutation p = new Permutation();


		if (operator.equals("+") ){
			int sum =0;
				for (int i = 0; i< values.size() ; i++){
					sum += values.get(i);
				}
					
					if (values.size() == numOfSquares){
						if (sum == targetValue)
							return true;
					}
					if(values.size() < numOfSquares){
						if(sum < targetValue)
							return true;
					}
				
		}
		
		
		if (operator.equals("*") ){
			int sum =1;
				for (int i = 0; i< values.size() ; i++){
					sum *= values.get(i);
				}
					
					if (values.size() == numOfSquares){
						if (sum == targetValue)
							return true;
					}
					if(values.size() < numOfSquares){
						if(sum <= targetValue)
							return true;
					}
				
		}
		
		
		if (operator.equals("/") ){
			ArrayList <ArrayList <Integer>> perm = p.permutation(values);
			if (values.size() < numOfSquares)
				return true;
			for (int i = 0; i< perm.size() ; i++){
				ArrayList<Integer> tempAr = perm.get(i);
				int sum = tempAr.get(0);
				for (int l =1; l < tempAr.size(); l ++ ){
					sum = sum /tempAr.get(l);						
				}
				if (values.size() == numOfSquares){
					if (sum == targetValue){
						return true;
					}
				}	
				

			}
	}
		if (operator.equals("-") ){
			ArrayList <ArrayList <Integer>> perm = p.permutation(values);
			if (values.size() <numOfSquares)
				return true;
			for (int i = 0; i< perm.size() ; i++){
				ArrayList<Integer> tempAr = perm.get(i);
				int sum = tempAr.get(0);
				for (int l =1; l < tempAr.size(); l ++ ){
					sum = sum - tempAr.get(l);						
				}
				if (values.size() == numOfSquares){
					if (sum == targetValue){
						return true;
					}
				}	
			}
		}

		if (operator.equals(" ")){
			if (values.get(0) == targetValue)
				return true;
		}

		return false;	
	}
	
	public static boolean checkForRowDuplicates(int[][] inArray)
	{
	    for (int row = 0; row < inArray.length; row++)
	    {
	        for (int col = 0; col < inArray[row].length; col++)
	        {
	            int num = inArray[row][col];
	            for (int otherCol = col + 1; otherCol < inArray.length; otherCol++)
	            {
	                if (num == inArray[row][otherCol] && num != 0)
	                    return true;
	            }
	        }
	    }

	    return false;
	}
	public static boolean solveKenKen(int r, int c){
	 if (c == size){
		 c= 0;
		 r =r +1;
	 }
	 
	 //System.out.println("(" + r + "," + c + ")");
	if (r == size)
		return true;
	 for (int i = 1; i <= size ;i++){
		 board [r][c] = i;
		 boolean p = isLegal(r,c);
		 if (p){
			 boolean q =solveKenKen (r, c+1);
			 if (q)
				 return true;

		 }
	 }
	
	board[r][c]= 0;
	 return false;
	}

}

