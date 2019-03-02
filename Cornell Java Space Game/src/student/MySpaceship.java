package student;

import controllers.Spaceship;
import models.Edge;
import models.Node;
import models.NodeStatus;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Map;

import controllers.RescueStage;
import controllers.ReturnStage;

/** An instance implements the methods needed to complete the mission. */
public class MySpaceship implements Spaceship {

    /** The spaceship is on the location given by parameter state.
     * Move the spaceship to Planet X and then return, while the spaceship is on
     * Planet X. This completes the first phase of the mission.
     * 
     * If the spaceship continues to move after reaching planet X, rather than
     * returning, it will not count. Returning from this procedure while
     * not on Planet X counts as a failure.
     * <p>
     * There is no limit to how many steps you can take, but your score is
     * directly related to how long it takes you to find Planet X.
     * <p>
     * At every step, you know only the current planet's ID, the IDs of
     * neighboring planets, and the strength of the ping from Planet X at
     * each planet.
     * <p>
     * In this rescueStage, parameter stage has useful methods:<br>
     * (1) In order to get information about the current state, use functions
     * getLocation(), neighbors(), getPing(), and foundSpaceship().
     * <p>
     * (2) You know you are on Planet X when foundSpaceship() is true.
     * <p>
     * (3) Use function moveTo(long id) to move to a neighboring planet
     * by its ID. Doing this will change state to reflect your new position.
     */
    @Override
    public void rescue(RescueStage state) {
    		HashSet<Long> visited = new HashSet<Long>();
    		
    		// you are not on Planet X
    		while(!state.foundSpaceship()) {
    			if(state.getPing() == 1) 
    				return;
    			
    			HashMap<Double,Long> nbInfo = new HashMap<Double,Long>();
    			
    			double largestPing = maxPing(state, nbInfo, visited);
    			double n = 0;
    			
    			// all neighbors have not already been visited
    			if(!nbInfo.isEmpty()) {
    				// store the id with largest ping
    				long pingId = nbInfo.get(largestPing);
    				visited.add(pingId);
    				state.moveTo((int) pingId);
    			}
    			
    			// all neighbors have been visited
    			else {
    				for(models.NodeStatus nb: state.neighbors()) {
    					double ping = nb.getPingToTarget();
    					long id = nb.getId();
    					nbInfo.put(ping, id);
    					
    					if (ping > n) {
    						n = ping;
    					}
    				}
    				
    				// store the id with largest ping
    				long pingId = nbInfo.get(n);
    				visited.add(pingId);
    				state.moveTo((int) pingId);
    			}
    		}
    }
    
    /** Helper method for rescue()
     * Finds the largest ping value among neighbors skipping the
     * nodes already visited
     * HashMap<Double,Long> nbInfo: id mapped to the ping
     * HashSet<Long> visited: nodes that were already visited
     */
    public double maxPing(RescueStage state, HashMap<Double,Long> nbInfo, 
    		HashSet<Long> visited) {
    		double n = 0;
    		for(models.NodeStatus nb : state.neighbors()) {
    			// does not contain the neighbor id
    			if(!visited.contains(nb.getId())) {
    				double ping = nb.getPingToTarget();
    				long id = nb.getId();
    				nbInfo.put(ping, id);
    				
    				if(ping > n) {
    					n = ping;
    				}
    			}
    		}
    		// returns largest n = largest ping
    		return n;
    }

    /** Return to Earth while collecting as many gems as possible.
     * The rescued spaceship has information on the entire galaxy, so you
     * now have access to the entire underlying graph. This can be accessed
     * through ReturnStage. getCurrentNode() and getEarth() will return Node
     * objects of interest, and getNodes() gives you a Set of all nodes
     * in the graph. 
     *
     * You must return from this function while on Earth. Returning from the
     * wrong location will be considered a failed run.
     *
     * You must make it back to Earth before running out of fuel.
     * state.getDistanceLeft() will tell you how far you can travel with your  
     * remaining fuel stores.
     * 
     * You can increase your score by collecting more gems on your way back to 
     * Earth. You should look for ways to optimize your return. The information 
     * from the rescued ship includes information on where gems are located. 
     * getNumGems() will give you the number of gems on a node. You will 
     * automatically collect any remaining gems when you move to a planet during 
     * the rescue stage.  */
//    @Override
    public void returnToEarth(ReturnStage state) {
//    		List<Node> pathBack = Paths.minPath(state.getCurrentNode(), state.getEarth());
//    		 
//    		for(int i = 1; i < pathBack.size(); i++) {
//    			state.moveTo(pathBack.get(i));
    		}
    		
    		
    		
    		
    		
    		
    		
    		
    }
