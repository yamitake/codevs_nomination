import java.util.Arrays;


public class Piece {
	public int[][] matrix;
	
	public Piece(int[][] piece) {
		matrix = piece;
	}
	
	public Piece(Piece piece){
		int size = piece.matrix.length;
		matrix = new int[size][size];
		for(int i = 0; i < matrix.length; i++){
			matrix[i] = Arrays.copyOf(piece.matrix[3] , size);
		}
	}
	
	public int[][] rotate(int rot){
		for(int i = 0; i < rot; i++){
			matrix = oneRotate();
		}
		
		return matrix;
	}
	
	public int[][] oneRotate(){
		int size = matrix.length;
		int[][] temp = new int[size][size];
		
		for(int i = 0;i < size; i++){
			for(int k = 0; k < size; k++){
				temp[i][k] = matrix[k][(size - 1) - i];
			}
		}
		
		return temp;
	}
	
	@Override
	public String toString() {
		int size = matrix.length;
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				System.out.print(Integer.toHexString(matrix[i][j]));
			}
			System.out.println();
		}
		
		System.out.println("-------");
		
		return super.toString();
	}
}
