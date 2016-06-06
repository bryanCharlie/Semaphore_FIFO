import java.util.LinkedList;
import java.util.Queue;

public class FIFO{
	private Queue<Semaphore> queue = new LinkedList<Semaphore>();
	private Semaphore mutex = new Semaphore(1);
	
	public void add(Semaphore s){
		mutex.acquire();
		queue.add(s);
		mutex.release(); //immediatly release mutex to allow other waiting threads to add their Semaphore to queue.
		s.acquire();
	}
	
	public void remove(){
		mutex.acquire();
		Semaphore s = queue.remove();
		mutex.release();
		s.release();
	}
	
	public void remove(int num){
		mutex.acquire();
		Semaphore s = null;
		for(int i = 0; i < num; i++){
			s = queue.poll();
			s.release();
		}
		mutex.release();
	}
	
	public int size(){
		mutex.acquire();
		int s = queue.size();
		mutex.release();
		return s; 
	}
}
