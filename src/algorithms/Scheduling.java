
import java.util.LinkedList;
import java.util.Queue;

public class Scheduling {
	
	public float waitingTimeRobin(int[] jobArr, int[] rt, int q, int n){
		
		while(n>0){
			int totalTime = jobArr[0];
			Queue<Job> waitQ = new LinkedList<Job>();
			int i =1;
			int idletime = 0;
			float waittime = 0;
			Job pendingJob = null;
			waitQ.add(new Job(jobArr[0], rt[0]));
			while(i<n || !waitQ.isEmpty()){
				
				while(i<n && jobArr[i] <= totalTime){
					waitQ.add(new Job(jobArr[i], rt[i]));
					i++;
				}
				if(pendingJob != null){
					waitQ.add(pendingJob);
				}
				if(!waitQ.isEmpty()){
					Job currJob = waitQ.poll();
					currJob.rt = currJob.rt - q;
					
					if(currJob.rt > 0){
						pendingJob = currJob;
						totalTime += q; 
					}else{
						pendingJob = null;
						totalTime += q + currJob.rt;
						waittime += totalTime - currJob.at - currJob.ort;
					}
				}else{
					if(i<n){
						idletime += jobArr[i]- totalTime; 
						totalTime = jobArr[i];
					}
				}
			}
			return (waittime)/n;
		}
		return 0;
	}
	
	public class Job{
		int at;
		int rt;
		int ort;
		Job(int at, int rt){
			this.at = at;
			this.ort = rt;
			this.rt = rt;
		}
	
	}
	
	public static void main(String[] args) {
		Scheduling s = new Scheduling();
		int[] rt = {3, 4, 1, 3, 1};
		int[] at = {0, 2, 4, 5, 10};
		System.out.println(s.waitingTimeRobin(at, rt, 3, 5));
	}
}
