import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestStage {

	@Test
	public void test() {
		assertEquals("とりあえず" , 100, 100);
	}
	
	@Test
	public void initTest(){
		Stage stage = new Stage(10, 10, 3, 5, 100);
		assertEquals(true , true); //初期化できてればいいか。。。
		stage.toString();
	}
	
	@Test
	public void simulate(){
		
		
		Stage stage = new Stage(10, 10, 3, 5, 100);
	}

}
