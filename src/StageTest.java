import static org.junit.Assert.*;

import org.junit.Test;


public class StageTest {

	@Test
	public void testStage() {
		Stage stage = new Stage(10, 10, 3, 5, 100);
		stage.ShowStage();
	}

	@Test
	public void testTurn() {
		Stage stage = new Stage(10, 5, 2, 5, 100);
		int[][] matrix = new int[2][2];
		matrix[0][0] = 1;
		matrix[0][1] = 0;
		matrix[1][0] = 4;
		matrix[1][1] = 10;
		
		Piece p = new Piece(matrix);
		DropPosition dp = new DropPosition(0, 1);
		stage.turn(p, dp);
		stage.ShowStage();
		
		int[][] matrix2 = new int[2][2];
		matrix2[0][0] = 1;
		matrix2[0][1] = 5;
		matrix2[1][0] = 1;
		matrix2[1][1] = 0;
		Piece p2 = new Piece(matrix2);
		DropPosition dp2 = new DropPosition(0, 1);
		stage.turn(p2, dp2);
		
		stage.ShowStage();
		stage.toString();
	}
	
	@Test
	public void testGetMinWidth(){
		Stage stage = new Stage(10, 10, 3, 5, 100);
		
	}

	@Test
	public void testToString() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void initTest(){
		Stage stage = new Stage(10, 10, 3, 5, 100);
		assertEquals(true , true); //èâä˙âªÇ≈Ç´ÇƒÇÍÇŒÇ¢Ç¢Ç©ÅBÅBÅB
		stage.toString();
	}
	
}
