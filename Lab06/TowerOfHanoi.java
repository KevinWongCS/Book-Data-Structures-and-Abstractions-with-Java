import java.util.ArrayList;


public class TowerOfHanoi
{
	// TO DO: Instance Variables
	ArrayList<Integer>[] towers = new ArrayList [3];
	ArrayList <Integer> tower0 = new ArrayList <Integer> ();
	ArrayList <Integer> tower1 = new ArrayList <Integer> ();
	ArrayList <Integer> tower2 = new ArrayList <Integer> ();
	int numOfDiscs;
	
	/* Construct the Towers of Hanoi (3 towers) with aNumDisc
	 * on the first tower. Each tower can be identified by an
	 * integer number (0 for the first tower, 1 for the second
	 * tower, and 2 for the third tower). Each disc can be identified
	 * by an integer number starting from 0 (for the smallest disc)
	 * and (aNumDisc - 1) for the largest disc.
	 */
	public TowerOfHanoi(int aNumDiscs)
	{
		numOfDiscs = aNumDiscs;
		for (int i = numOfDiscs - 1; i >= 0; i-- ){
			tower0.add(i);
		}
	}
	
	/* Returns an array of integer representing the order of
	 * discs on the tower (from bottom up). The bottom disc should
	 * be the first element in the array and the top disc should be
	 * the last element of the array. The size of the array MUST
	 * be the number of discs on the tower. For example, suppose
	 * the tower 0 contains the following discs 0,1,4,6,7,8 (from top
	 * to bottom). This method should return the array [8,7,6,4,1,0]
	 * (from first to last). 
	 * @param tower the integer identify the tower number.
	 * @return an array of integer representing the order of discs.
	 */
	public int [] getArrayOfDiscs(int tower)
	{

		int [] empty = new int [0];
		if (tower ==0){
			if(tower0.size() == 0)
				return empty;
			int [] temp = new int [tower0.size()];
			for(int i = 0; i < tower0.size(); i++){
				temp[i] = tower0.get(i).intValue();
			}
			return temp;
		}
		if (tower ==1){
			if(tower1.size() == 0)
				return empty;
			int [] temp = new int [tower1.size()];
			for(int i = 0; i < tower1.size(); i++)
				temp[i] = tower1.get(i).intValue();
			return temp;
		}
		if (tower == 2){
			if(tower2.size() == 0)
				return empty;
			int [] temp = new int [tower2.size()];
			for(int i = 0; i < tower2.size(); i++)
				temp[i] = tower2.get(i).intValue();
			return temp;
		}
		return empty;
	}
	
	/* Gets the total number of discs in this Towers of Hanoi
	 * @return the total number of discs in this Towers of Hanoi
	 */
	public int getNumberOfDiscs()
	{
		return numOfDiscs;
	}
	
	/* Gets the number of discs on a tower.
	 * @param tower the tower identifier (0, 1, or 2)
	 * @return the number of discs on the tower.
	 */
	public int getNumberOfDiscs(int tower)
	{
		if (tower ==0)
			return tower0.size();
		if (tower == 1)
			return tower1.size();
		if (tower == 2)
			return tower2.size();
		else
			return -1;
	}
	
	/* Moves the top disc from fromTower to toTower. Note that
	 * this operation has to follow the rule of the Tower of Hanoi
	 * puzzle. First fromTower must have at least one disc and second
	 * the top disc of toTower must not be smaller than the top disc
	 * of the fromTower.
	 * @param fromTower the source tower
	 * @param toTower the destination tower
	 * @return ture if successfully move the top disc from
	 *         fromTower to toTower.
	 */
	public boolean moveTopDisc(int fromTower, int toTower)
	{
		
		if (fromTower == 0){
			if(toTower ==0)
				return true;
			if (toTower == 1){
				if (tower1.size() !=0 && tower0.get(tower0.size()-1).intValue() > tower1.get(tower1.size()-1).intValue())
					return false;
				tower1.add(tower0.get(tower0.size()-1).intValue());
				tower0.remove((tower0.size()-1));
				return true;
			}
			if(toTower == 2){
				if (tower2.size() !=0 &&tower0.get(tower0.size()-1).intValue()> tower2.get(tower2.size()-1).intValue())
					return false;
				tower2.add(tower0.get(tower0.size()-1).intValue());
				tower0.remove((tower0.size()-1));
				return true;
			}
		}
		///finish changing this.getNumberOfDiscs to towersize;
		else if (fromTower == 1){
			if(toTower ==1)
				return true;
			if (toTower == 0){
				if (tower0.size() !=0 && tower1.get(tower1.size()-1).intValue() > tower0.get(tower0.size()-1).intValue())
					return false;
				tower0.add(tower1.get(tower1.size()-1).intValue());
				tower1.remove(tower1.size()-1);
				return true;
			}
			if(toTower == 2){
				if (tower2.size() !=0 && tower2.size() !=0 && tower1.get(tower1.size()-1).intValue() > tower2.get(tower2.size()-1).intValue() )
					return false;
				tower2.add(tower1.get(tower1.size()-1).intValue());
				tower1.remove(tower1.size()-1);
				return true;
			}
		}
		else if (fromTower == 2){
			if(toTower ==2)
				return true;
			if (toTower == 1){
				if (tower1.size() !=0 && tower2.get(tower2.size()-1).intValue() > tower1.get(tower1.size()-1).intValue())
					return false;
				tower1.add(tower2.get(tower2.size()-1).intValue());
				tower2.remove((tower2.size()-1));
				return true;
			}
			if(toTower == 0){
				if (tower0.size() !=0 && tower2.get(tower2.size()-1).intValue() > tower0.get(tower0.size()-1).intValue())
					return false;
				tower0.add(tower2.get(tower2.size()-1).intValue());
				tower2.remove((tower2.size()-1));
				return true;
			}
		}
		
			return false;
	}
}
