
public class KnapsackSolver {

	public static int[][] buildTable(Order[] orders, int costLimit, int timeLimit){
	// Problem #1
	// Fill in this method to create a (costLimit + 1) by (timeLimit + 1) table
	// that for each (i, j) stores the maximum number of cookies that can be
	// produced with cost at most i in time at most j.

		//Knapsack has weight and value
		//Making cookies, time = weight, numberOfCookies = value 
	
		//Declare table
		int[][] table = new int[costLimit+1][timeLimit+1];
		int numberOfCookieTypes = orders.length;
		int minCostCookies = 99999;
		int minTimeCookies = 99999;


		//Build table with a knapsack of timeLimit as capacity
		for(int i = 0; i < costLimit+1; i++)
		{
			for(int j = 0; j < timeLimit+1; j++)
			{
				if(i == 0 || j == 0)
				{
					table[i][j] = 0;
				}
				else
				{
					int max = 0;
					for(int k = 0; k < numberOfCookieTypes; k++)
					{
						if(orders[k].cost < minCostCookies)
						{
							minCostCookies = orders[k].cost;
						}
						if(orders[k].time < minTimeCookies)
						{
							minTimeCookies = orders[k].time;
						}
						
						int value = 0;
						Order temp = orders[k];
						//System.out.println("k: " + k);

						//#1
						if((i - temp.cost) > 0 && (j - temp.time) > 0)
						{
							value = table[i-temp.cost][j-temp.time] + temp.numberOfCookies;
							//System.out.println("#1 value: " + value);
						}
						//#2
						else if((i < minCostCookies) || (j < minTimeCookies) || ((i - temp.time) == 0) || ((j - temp.cost) == 0))
						{
							value = 0;
							//System.out.println("#2 value: " + value);
						}
						//#3
						else if((i >= minCostCookies) && (j >= minTimeCookies))
						{
							value = Math.max(table[i-1][j], table[i][j-1]);
							//System.out.println("#3 value: " + value);
						}
						
						if(value > max)
						{
							max = value;
						}
					}
					//System.out.println("i: " + i + ", j: " + j + ", max:" + max);
					table[i][j] = max;
				}
			}
		} 

		//System.out.println("minCostCookies: " + minCostCookies);
		//System.out.println("minTimeCookies: " + minTimeCookies);

		//System.out.println(table[costLimit][timeLimit]);

		//Returns answer to problem #1
		return table;
	 }

	public static Multiset solve(Order[] orders, int costLimit, int timeLimit){
	// Problem #2
	// Fill in this method to create a multiset that represents a combination of
	// cookie choices that maximizes the number of cookies with cost at most 
	// costLimit in time at most timeLimit.  Note: You can call buildTable as
	// a subroutine.
	
		int[][] table = buildTable(orders, costLimit, timeLimit);
		Multiset set = new Multiset();
		int costRemaining = costLimit;
		int timeRemaining = timeLimit;

		int value = table[costLimit][timeLimit];

		for(int k = 0; k < orders.length; k++)
		{
			for(int i = costLimit; i >= 0; i--)
			{
				for(int j = timeLimit; j >= 0; j--)
				{
					
					Order temp = orders[k];
					if(costRemaining > 0 && timeRemaining > 0) 
					{
						if((costRemaining - temp.cost) > 0 && ((timeRemaining - temp.time) > 0))
						{
							costRemaining = costRemaining - temp.cost;
							timeRemaining = timeRemaining - temp.time;
							set.add(temp);
						}
					}

				}
			}
		}
		return set;	
	}

}