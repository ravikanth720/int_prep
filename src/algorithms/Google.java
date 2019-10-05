package algorithms;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Google {
	
	public void treeMapOrdering(){
		Map<Integer, Integer> sample = new TreeMap<Integer, Integer>(new Comparator<Integer>(){
			public int compare(Integer arg0, Integer arg1) {
				// TODO Auto-generated method stub
				return 0;
			}
		});
	}
	public void function(int n){
		String nn = Integer.toString(n);
        boolean f=false;
        int i=0;
        for( i=0;i<nn.length()-1;i++){
            if(nn.charAt(i)==nn.charAt(i+1)){
                f=true;
            }else if(f && (nn.charAt(i)<=nn.charAt(i+1))){
                System.out.println(nn.charAt(i-1)+" "+i);
                break;
            }
            else{
                f=false;
            }
        }
        if(f){
            int res = Integer.parseInt(nn.substring(0, i) + nn.substring(i+1));
            System.out.println(res);
        }else {
            int res = Integer.parseInt(nn);
        System.out.println(res);
        }
	}
	
	public static void main(String[] args) {
		Google g  = new Google();
		g.function(887766033);
	}
}
