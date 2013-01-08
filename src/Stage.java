import java.util.ArrayList;
import java.util.List;

/**
 * ステージ
 * @author takenojun
 */
public class Stage {
	private int width;
	private int height;
	private int size;
	private int sum;
	private int step;
	
	int maxchain = 0;
	int chaincount = 0;
	
	@SuppressWarnings("rawtypes")
	private List<List> cols;//縦
	
	/**
	 * 10 16 4 10 1000
	 */
	@SuppressWarnings("rawtypes")
	public Stage(int width , int height , int size , int sum , int step){
		this.width = width;
		this.height = height;
		this.size = size;
		this.sum = sum;
		this.step = step;
		
		cols = new ArrayList<List>();
		for(int i=0;i<width;i++){
			cols.add(new ArrayList<Integer>());
		}
	}
	
	public Stage(Stage stage){
		width = stage.width;
		height = stage.height;
		size = stage.size;
		sum = stage.sum;
		step = stage.step;
		cols = new ArrayList<List>();
		for(int i=0;i<width;i++){
			cols.add(new ArrayList<Integer>(stage.cols.get(i)));
		}
	}
	
	public void turn(Piece piece , DropPosition dp){
		//piece.toString();
		drop(piece , dp);
		chaincount = 0;
		chain();
		if(maxchain < chaincount){
			maxchain = chaincount;
		}
	}
	
	public void drop(Piece piece , DropPosition dp){
		for(int i = size - 1; i >= 0; i--){
			for(int j = 0; j < size; j++){
				int num = piece.matrix[i][j];
				if(num != 0){
					cols.get(dp.xpos + j).add(num);
				}
			}
		}
	}
	
	public boolean isFinish(){
		boolean isFinish = false;
		for(List<Integer> list:cols){
			if(list.size() > height)isFinish = true;
		}
		
		return isFinish;
	}
	
	/**
	 * 端からチェック 上 上右 右
	 * @return
	 */
	public int chain(){
		chaincount++;
		boolean isLoop = false;
		
		for(int i = cols.size() - 1; i  >= 0 ; i--){
			List<Integer> col = cols.get(i);
			for(int j = 0; j < col.size() - 1; j++){
				//単一マスで消える数字だったら消す。
				if(col.get(j) == sum){
					col.remove(j);
				}
				
				//上
				int total = col.get(j);
				int count = 1;
				while(total < sum){
					try{
						total+=col.get(j+count);
					}catch(IndexOutOfBoundsException e){
						break;
					}
					
					if(total == sum){
						for(int k = 0; k < count; k++){
							col.remove(j);
						}
						
						isLoop = true;
						break;
					}
				}
				
				//右上
				total = col.get(j);
				count = 1;
				while(total < sum){
					try{
						Integer val = (Integer) cols.get(i+count).get(j+count);
						total += val;
					}catch(IndexOutOfBoundsException e){
						break;
					}
					
					if(total == sum){
						for(int k = 0; k < count; k++){
							col.remove(j);
						}
						
						isLoop = true;
						break;
					}
				}
			}
		}
		
		//一回でも消えていたらループする
		if(isLoop){
			chain();
		}
			
		return 1;
	}
	
	@Override
	public String toString() {
		System.out.println("width:" + width + 
							"height:" + height +
							"size:" + size + 
							"sum:" + sum +
							"step:" + step);
		
		return super.toString();
	}
	
	public int getMinWidth(){
		//ShowStage();
		
		int xpos = (int)Math.random()*(width-size+1);
		double avg = 0;
		int min = 0;
		
		for(int i = 0; i < cols.size() - size + 1;i++){
			int sum = 0;
			
			
			for(int k = 0; k < size; k++){
				sum+=cols.get(i+k).size();
			}
			double currentAvg = (double)sum/size;
			
			if(i==0){
				avg = currentAvg;
				min = cols.get(i).size();
			}
			
			//System.out.println("i:" + i + " avg:" + avg +" (sum/size):" + currentAvg + " xpos:" + xpos);
			
			if(avg > currentAvg){
				avg = currentAvg;
				xpos = i;
			}
			
//			if(min > cols.get(i).size()){
//				min = cols.get(i).size();
//				xpos = i;
//			}
		}
		
		return xpos;
	}
	
	public String ShowStage(){
		String str = "";
		
			for(int j = height - 1; j >= 0 ; j--){
				for(int i = 0; i < width; i++){
				//System.out.println("--i" + i + "--j" + j);
				try{
					Integer a = (Integer)cols.get(i).get(j);
					System.out.print(Integer.toHexString((a.intValue())));
				}catch(IndexOutOfBoundsException e){
					System.out.print("○");
				}
			}
			System.out.println("");
		}
		
		System.out.println("------------------------------");
		return str;
	}
	
	public int getBlockCount(){
		int total = 0;
		for(int i = 0; i < cols.size() - size + 1;i++){
			total += cols.get(i).size();
		}
		return total;
	}
}
