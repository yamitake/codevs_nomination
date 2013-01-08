import java.util.List;
import java.util.Random;


public class Result {

	public List<DropPosition> dpList;
	public int turnCount = 0;
	public int chainCount = 0;
	
	public Result(List<DropPosition> list , int count ,int chcount) {
		dpList = list;
		turnCount = count;
		chainCount = chcount;
	}
	
	public List<DropPosition> getNormalizedDpList(int step ,int size , int wid){
		Random random = new Random(1);
		while(step > dpList.size()){
			dpList.add(new DropPosition(random.nextInt(wid-size+1) , random.nextInt(4) ));
		}
		
		return dpList;
	}
}
