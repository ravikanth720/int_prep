
// Old google interview question
package algorithms;
import java.util.Stack;
import datastructures.FileTree;
public class FindImage {
	public int imagesPathLength(String S){
		String[] inp = S.split("\n");
		int len = inp.length;
		Stack<FileTree> stk = new Stack<FileTree>();
		FileTree ft = new FileTree("/");
		FileTree root = ft;
		int res = 0;
		FileTree node = null;
		for(int i=0; i<len;i++){
			int count = inp[i].indexOf(inp[i].trim());
			if(count == ft.getLevel()){
				node = new FileTree(inp[i].trim(), ft.getLevel()+1);
				ft.getChilds().add(node);
			}else if(count > ft.getLevel()){
				stk.push(ft);
				ft = node;
				node = new FileTree(inp[i].trim(), ft.getLevel()+1);
				ft.getChilds().add(node);
			}else{
				int n = ft.getLevel() - count;
				for(int j=0; j<n;j++){
					
					int mf = ft.getMf();
					System.out.println(ft.getName() + " --- "+mf);
					ft = stk.pop();
					ft.setMf(ft.getMf()+mf);
				}
				node = new FileTree(inp[i].trim(), ft.getLevel()+1);
				ft.getChilds().add(node);
			}
			if(inp[i].trim().matches("^[a-zA-Z0-9]*[.](jpg|png)$")){
				//node.setMf(node.getMf()+1);
				ft.setMf(1);
			}	
		}
		//root.print();
		res = root.getImagePathLength();
		System.out.println("res ="+res);
		return res;
	}
}