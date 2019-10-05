package algorithms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class PartitionScheduling {
	
	
	public class Location {
		int location_id;
		int max_size;
		Map<Integer, Content> contents = new HashMap<Integer, Content>();
		Queue<Content> contentQ = new PriorityQueue<Content>(
				new Comparator<Content>() {
					public int compare(Content c1, Content c2) {
						return c1.end_time - c2.end_time;
					}
				});
		
		public Location(int location, int max){
			location_id = location;
			max_size = max;
		}

		public void schedule(Content currAd) {
			if (!contents.containsKey(currAd.content_id)) {
				if(contentQ.size() == max_size){
					if (contentQ.peek().end_time < currAd.start_time) {
						int oldContentId = contentQ.poll().content_id;
						contents.remove(oldContentId);
						currAd.scheduled = true;
						contents.put(currAd.content_id, currAd);
						contentQ.add(currAd);
					}
				}else if(contentQ.size() < max_size){
					currAd.scheduled = true;
					contents.put(currAd.content_id, currAd);
					contentQ.add(currAd);
				}
			}else{
				if(contents.get(currAd.content_id).end_time < currAd.start_time){
					Content oldContent = contents.get(currAd.content_id);
					currAd.scheduled = true;
					contents.put(currAd.content_id, currAd);
					contentQ.remove(oldContent);
					contentQ.add(currAd);
				}
			}
		}

	}
	

	public class Content {
		int content_id;
		int start_time;
		int end_time;
		int location_id;
		boolean scheduled = false;

		public Content(int content, int start, int end, int location) {
			content_id = content;
			start_time = start;
			end_time = end;
			location_id = location;
		}
	}

	public void scheduleAds(String input, String accepted, String rejected) throws IOException {
		File inputFile = new File(input);
		File acceptedFile = new File(accepted);
		File rejectedFile = new File(rejected);
		if(!acceptedFile.exists()){
			acceptedFile.createNewFile();
		}
		if(!rejectedFile.exists()){
			rejectedFile.createNewFile();
		}
		Scanner sc = new Scanner(inputFile);
		FileWriter accWriter = new FileWriter(accepted);
		FileWriter rejWriter = new FileWriter(rejected);
		BufferedWriter aWriter = new BufferedWriter(accWriter);
		BufferedWriter rWriter = new BufferedWriter(rejWriter);
		
		Map<Integer, Location> locations = new HashMap<Integer, Location>();
		
		while (sc.hasNextLine()) {
			String currLine = sc.nextLine();
			String[] currJob = currLine.split(",");
			int content = Integer.parseInt(currJob[0]);
			int start = Integer.parseInt(currJob[1]);
			int end = Integer.parseInt(currJob[2]);
			int location = Integer.parseInt(currJob[3]);
			Content currContent = new Content(content, start, end, location);
			if(!locations.containsKey(location)){
				Location currLoc = new Location(location, 3);
				currLoc.schedule(currContent);
			}else{
				Location currLoc = locations.get(location);
				currLoc.schedule(currContent);
			}
			
			if(currContent.scheduled){
				aWriter.write(currLine);
			}else{
				rWriter.write(currLine);
			}
			
		}
		sc.close();
		aWriter.close();
		rWriter.close();
	}

	public static void main(String args[]) {
	}
}
