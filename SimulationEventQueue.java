import java.util.List;
import java.util.Vector;

import QueuePackage.SimulationEvent;
import QueuePackage.SimulationEventQueueInterface;

public class SimulationEventQueue implements SimulationEventQueueInterface {
    private double currentTime = 0;
    private List<SimulationEvent> queue = new Vector<SimulationEvent>();

    @Override
    public void add(SimulationEvent newEntry) {
    	if(newEntry.getTime() < currentTime) {
    		return;
    	}
    	if(queue.isEmpty() || (newEntry.getTime() >= queue.get(queue.size() - 1).getTime())) {
    		queue.add(newEntry);
    	}
    	else {
    		for(int i = 0; i < queue.size(); i++) {
    			if(queue.get(i).getTime() > newEntry.getTime()) {
    			queue.add(i, newEntry);
    			return;
    			}
    		}
    	}
    }

    @Override
    public SimulationEvent remove() {
    	SimulationEvent removed = queue.remove(0);
    	currentTime = removed.getTime();
        return removed;
    }

    @Override
    public SimulationEvent peek() {
    	SimulationEvent peeked = queue.get(0);
    	currentTime = peeked.getTime();
        return peeked;
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public int getSize() {
        return queue.size();
    }

    @Override
    public void clear() {
    	queue.clear();
    }

    @Override
    public double getCurrentTime() {
        return currentTime;
    }

}
