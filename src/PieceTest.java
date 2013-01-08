import org.junit.Test;


public class PieceTest {
	
	@Test
	public void testRotate(){
		int[][] matrix = new int[4][4];
		matrix[0][0] = 1;
		matrix[0][1] = 2;
		matrix[0][2] = 3;
		matrix[0][3] = 4;
		matrix[1][0] = 5;
		matrix[1][1] = 6;
		matrix[1][2] = 7;
		matrix[1][3] = 8;
		matrix[2][0] = 9;
		matrix[2][1] = 10;
		matrix[2][2] = 11;
		matrix[2][3] = 12;
		matrix[3][0] = 13;
		matrix[3][1] = 14;
		matrix[3][2] = 15;
		matrix[3][3] = 0;
		
		Piece p = new Piece(matrix);
		p.toString();
		
		p.rotate(1);
		p.toString();
		
		p.rotate(2);
		p.toString();
	}

	@Test
	public void testToString() {
		int[][] matrix = new int[2][2];
		matrix[0][0] = 1;
		matrix[0][1] = 0;
		matrix[1][0] = 4;
		matrix[1][1] = 10;
		
		Piece p = new Piece(matrix);
		p.toString();
		
		p.rotate(1);
		p.toString();
		
		p.rotate(2);
		p.toString();
	}
}
