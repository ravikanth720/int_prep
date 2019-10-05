package datastructures;
import java.util.ArrayList;
public class FileTree {
	private String name;
	private ArrayList<FileTree> childs;
	private int mf;
	private int level;
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getMf() {
		return mf;
	}
	public void setMf(int mf) {
		this.mf = mf;
	}
	public FileTree(String name){
		this.name = name;
		this.childs = new ArrayList<FileTree>();
		this.mf=0;
		this.level =0;
	}
	public FileTree(String name, int level) {
		this.name = name;
		this.childs = new ArrayList<FileTree>();
		this.level = level;
		this.mf = 0;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<FileTree> getChilds() {
		return childs;
	}
	public void setChilds(ArrayList<FileTree> childs) {
		this.childs = childs;
	}
	public void print(){
		for(int i=0; i<this.level; i++){
			System.out.print(" ");
		}
		System.out.print(this.name+"-"+this.mf+"\n");
		if(this.childs.size()>0)
			for(FileTree child: this.childs){
				child.print();
			}
	}
	public int getImagePathLength() {
		int len = this.mf*this.name.trim().length();
		if(this.childs.size()>0)
			for(FileTree child: this.childs){
				len += child.getImagePathLength();
			}
		if(this.level > 1 && len > 0)//Add a / before new level
			len +=1;
		System.out.println(this.name+" = "+len+" mf = "+this.mf);
		return len;
	}
}
