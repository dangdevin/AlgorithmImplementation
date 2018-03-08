import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFS {

	// Returns the set of reachable locations using breadth first search
	public static Set<Location> getReachableSet(WeightedGraph graph, Location start){
	// Problem #1
	// Fill in this method to compute the set of all possible reachable 
	// locations (ignoring costs and budget).  You must use Breadth
	// First Search to get full credit.
	
	Set<Location> reach = new HashSet<Location>();

	Queue<Location> search = new LinkedList<Location>(graph.getNeighbors(start));
	//Queue<Location> search = new Queue<Location>(graph.vertices.get(start));

	while(!(search.peek() == null))
	{
		Location temp = search.remove();
		boolean newLocation = reach.add(temp);
		boolean containsNext = graph.adjacencyList.containsKey(temp);

		if(newLocation && containsNext)
		{
			for(Location loc : graph.getNeighbors(temp))
			{
				search.add(loc);
			}
		}
	}

	return reach;

	}

}
