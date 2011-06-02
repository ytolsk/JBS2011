
public class Test {
	public static void main(String args[]){
		BaseMaze maze = new BaseMaze(4,4);
		
		maze.load("111111111100010001111010101100010101101110101100000101111011101100000101111111111");
		maze.display(); 
		outputArray(maze.getCell(0,0));
		outputArray(maze.getCell(0,1));
		System.out.println(maze.moveNext(1,0,1,1));
		System.out.println("I am solving the maze!");
		System.out.println(maze.Solve(0,0,1,1));
		
	}
	public static void outputArray(char[][] array) {
		 int rowSize = array.length;
		 int columnSize = array[0].length;
		 for(int i = 0; i <columnSize; i++) {
		 System.out.print("[");
		 for(int j = 0; j <rowSize; j++) {
		 System.out.print(" " + array[i][j]);
		 }
		 System.out.println(" ]");
		 }
		 System.out.println();
		 }
		

}
