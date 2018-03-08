import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashSet;


public class Dijkstra {

	// Returns the set of destinations that can be reached without going over budget
	public static Set<Location> getDestinationSet(WeightedGraph graph, Location start, Integer budget){
	// Problem #2
	// Fill in this method to compute the set of all possible destinations 
	// that can be reached while staying within the travel budget. You must
	// use Dijkstra's algorithm to get full credit.  We encourage you to
	// implement the |V|^2 time version of Dijkstra's algorithm.  You are
	// free to add helper methods. 

/*		Set<Location> vertices = new HashSet<Location>();

		Location next = reach.temp;

		Integer distance = graph.getWeight(start, next); */

		//initialize values
		Set<Location> reach = new LinkedHashSet<Location>();
		Set<Location> adjacent = new LinkedHashSet<Location>();

		Location low = null;

		Integer presentBudget = 0;
		Integer lowCost = Integer.MAX_VALUE;
		Integer presentCost = 0;
		reach.add(start);


		while(presentBudget.compareTo(budget) < 0)
		{
			//System.out.println("Calling graph.getNeighbors(start) : " + start.name);
			adjacent = graph.getNeighbors(start);
			adjacent.removeAll(reach);

			Iterator<Location> it = adjacent.iterator();

			while (it.hasNext())
			{
				Location edge = it.next();
				//System.out.println("Calling Location edge : " + edge.name);
				boolean shortestFound = false;
				/*while(!shortestFound)
				{
					Integer shortest = Integer.MAX_VALUE;
					Location short = null;
					Set<Location> vertices
				}*/
				presentCost = graph.getWeight(start, edge);
				if(presentCost.compareTo(lowCost) < 0)
				{
					lowCost = presentCost;
					low = edge;
				}
			}

			reach.add(low);

			
			//reset the init values
			start = low;
			presentBudget = Integer.valueOf(new Integer(presentBudget).intValue() + new Integer(lowCost).intValue());
			lowCost = Integer.MAX_VALUE;
		}

		return reach;
	}

	//get distance = graph.getWeight(start, target);
	//get neighbors = graph.getNeighbors(node);

}
