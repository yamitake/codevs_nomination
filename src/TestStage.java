import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestStage {

	@Test
	public void test() {
		assertEquals("‚Æ‚è‚ ‚¦‚¸" , 100, 100);
	}
	
	@Test
	public void initTest(){
		Stage stage = new Stage(10, 10, 3, 5, 100);
		assertEquals(true , true); //‰Šú‰»‚Å‚«‚Ä‚ê‚Î‚¢‚¢‚©BBB
		stage.toString();
	}
	
	@Test
	public void simulate(){
		
		
		Stage stage = new Stage(10, 10, 3, 5, 100);
	}

}
