import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.awt.*;


public class BaseMaze {
	int width;
	int hight;
	int aw;
	int ah;
	char[][] maze; 
	ArrayList<int[]> visited = new ArrayList<int[]>(); // List of cells already visited;
	HashSet visit = new HashSet();
	
	public BaseMaze(int n, int m){
		width =n;
		hight= m;
		aw = 2*width+1;
		ah = 2*hight+1;
		maze = new char[aw][ah];		
		
	}
	
	public void load(String numbers){
		for (int y=0;y<ah; y++){
		
			for (int x = y*aw; x < y*aw +aw; x++){
				
				maze[y][x%aw]=(numbers.charAt(x));
			}
		}
	}
	//this method returns the wall matrix for a given cell;
	public char[][] getCell(int y, int x){
		char[][] cell = new char[3][3];
		int l =0; 
		for(int j =2*y; j<2*y+3; j++){
			int k=0;
			if(j<0){
				j=0;
			}
			for (int n = 2*x; n <2*x+3; n++){	
				if(n<0){
					n=0;
				}
				cell[l][k]= maze[j][n];
				k=k+1;
			}
			l=l+1;
		}
		
		return cell; 
	}
	
	
	public void display(){
		int rowSize = maze.length;
		 int columnSize = maze[0].length;
		 for(int i = 0; i <columnSize; i++) {
		 System.out.print("[");
		 for(int j = 0; j <rowSize; j++) {
			 
		 System.out.print(" " + maze[i][j]);
		 }
		 System.out.println(" ]");
		 }
		 System.out.println();
		 }
   // checks if we can move from one cell to the next cell;
   public boolean moveNext(int BegX, int BegY, int EndX, int EndY){
	   char[][] b= getCell(BegY,BegX);
	   char[][] e = getCell(EndY,EndX);
	   if(BegX==EndX){
		   if(BegY<EndY){
			   if(b[2][1]== '0' & e[0][1]== '0'){
				   return true;
			   }
		   }
		   else{
			   if(b[0][1]== '0' & e[2][1]== '0'){
				   return true;
			   }
		   }
	   }
	   else if( BegY==EndY){
		   if(BegX<EndX){
			   if(b[1][2]=='0' && e[1][0]=='0'){
				   
				   return true;
			   }
		   }
		   else{
			   if(b[1][0]== '0' & e[1][2]== '0'){
				   return true;
			   }
		   }
		   
	   }
	   else{System.out.println("These cells are not next to each other!");}
	   return false;
   }
   // helper method for Solve, checks if we can move right
   public boolean moveRight(int BegX, int BegY){
	   if(moveNext(BegX, BegY,BegX+1, BegY) & !visit.contains( (BegX+1)+"p"+ BegY)){
		   return true;
	   }
	   else{
		   return false;
	   }
   }
   //helper method for Solve, checks if we can move left
   public boolean moveLeft(int BegX, int BegY){
	   if(moveNext(BegX, BegY,BegX-1, BegY) & !visit.contains( (BegX-1)+"p"+ BegY)){
		   return true;
	   }
	   else{
		   return false;
	   }
   }
 //helper method for Solve, checks if we can move down
   public boolean moveDown(int BegX, int BegY){
	   if(moveNext(BegX, BegY,BegX, BegY+1) & !visit.contains( (BegX)+"p"+ (BegY+1))){
		   return true;
	   }
	   else{
		   return false;
	   }
   }
   public boolean moveUp(int BegX, int BegY){
	   if(moveNext(BegX, BegY,BegX, BegY-1) & !visit.contains( (BegX)+"p"+ (BegY-1))){
		   return true;
	   }
	   else{
		   return false;
	   }
   }
   
   

   
   
   public boolean Solve(int BegX, int BegY, int EndX, int EndY){
	   boolean solve = false;
	   
	   visit.add( BegX+"p"+BegY);
	   if(BegX==EndX & BegY==EndY){
		   solve = true;
	   }
	   
	   //check if we can move right, if cell was not visited before, and there's no wall, move right and solve from there;
	   
	   else{
		   if (moveRight(BegX,BegY)){
			   if(Solve(BegX+1, BegY, EndX, EndY)){
				   return (Solve(BegX+1, BegY, EndX, EndY));
			   }
			   else{
				   if(moveLeft(BegX,BegY)){
					   if(Solve(BegX-1, BegY, EndX, EndY)){
						   return (Solve(BegX-1, BegY, EndX, EndY));
					   }
					   else{
						   if(moveDown(BegX,BegY)){
							   if(Solve(BegX, BegY+1, EndX, EndY)){
								   return (Solve(BegX, BegY+1, EndX, EndY));
							   }
							   else{
								   if(moveUp(BegX,BegY)){
									   if(Solve(BegX, BegY-1, EndX, EndY)){
										   return (Solve(BegX, BegY-1, EndX, EndY));
									   }
									   else{
										   solve = false;
									   }
							   }
								   
							   }
						   
					   }
					   
				   }
			   }
		   }
	   }
	   
	   }
	   
	   return solve; 
   }
   
   
	
	
	public ArrayList makeMaze(){
		ArrayList maze = new ArrayList(); 
		for (int y=0;y<hight; y++){
			ArrayList row = new ArrayList();
			for (int x=0;x<width; x++){
				Point pt = new Point (x,y);
				row.add(pt);
			}
			maze.add(row);
		}
		return maze;
	}
	

}
