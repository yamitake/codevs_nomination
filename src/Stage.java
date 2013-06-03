import java.util.ArrayList;
import java.util.List;

/**
 * ステージ
 * 
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
	private List<List> cols;// 縦

	/**
	 * 10 16 4 10 1000
	 */
	@SuppressWarnings("rawtypes")
	public Stage(int width, int height, int size, int sum, int step) {
		this.width = width;
		this.height = height;
		this.size = size;
		this.sum = sum;
		this.step = step;

		cols = new ArrayList<List>();
		for (int i = 0; i < width; i++) {
			cols.add(new ArrayList<Block>());
		}
	}

	public Stage(Stage stage) {
		width = stage.width;
		height = stage.height;
		size = stage.size;
		sum = stage.sum;
		step = stage.step;
		cols = new ArrayList<List>();
		for (int i = 0; i < width; i++) {
			cols.add(new ArrayList<Block>(stage.cols.get(i)));
		}
	}

	public void turn(Piece piece, DropPosition dp) {
		// piece.toString();
		drop(piece, dp);
		chaincount = 0;
		chain();
		if (maxchain < chaincount) {
			maxchain = chaincount;
		}
	}

	public void drop(Piece piece, DropPosition dp) {
		Logger.log(piece);
		Logger.log(dp);
		for (int i = size - 1; i >= 0; i--) {
			for (int j = 0; j < size; j++) {
				int num = piece.matrix[i][j];
				if (num != 0) {
					Logger.log("drop:" + num);
					cols.get(dp.xpos + j).add(new Block(num));
				}
			}
		}
	}

	public boolean isFinish() {
		boolean isFinish = false;
		for (List<Integer> list : cols) {
			if (list.size() > height)
				isFinish = true;
		}

		return isFinish;
	}

	/**
	 * 端からチェック 上 上右 右
	 * 
	 * @return
	 */
	public int chain() {
		chaincount++;
		boolean isLoop = false;
		for (int i = cols.size() - 1; i >= 0; i--){
			List<Block> col = cols.get(i);
			for (int j = col.size() - 1; j >= 0; j--) {
				Logger.log(i + ":" + col.get(j).getVal());
				//その場
				if (col.get(j).getVal() == sum) {
					isLoop = true;
					col.get(j).setDelete(true);
				}else{
				
					isLoop = isLoop || checkBlocks(col.get(j) , i , j , 0 , 1);
					isLoop = isLoop || checkBlocks(col.get(j) , i , j , 1 , 1);
					isLoop = isLoop || checkBlocks(col.get(j) , i , j , 1 , 0);
					isLoop = isLoop || checkBlocks(col.get(j) , i , j , 1 , -1);
					
				}
			}
		}

		// 消える物とおぷよの削除
	//	System.out.println("--before");
	//	ShowStage();
		removeBlocks();
	//	System.out.println("--after");
//		ShowStage();
//		System.out.println("------------");

		// 一回でも消えていたらループする
		if (isLoop) {
			chain();
		}

		return 1;
	}
	
	public boolean checkBlocks(Block targetBlock , int i , int j , int diffx , int diffy){
		boolean isLoop = false;
		int total = targetBlock.getVal();
		int count = 1;
		List<Block> calcBlockList = new ArrayList<Block>();
		//calcBlockList.add(targetBlock);
		while (total < sum) {
			try {
				Block block = (Block) cols.get(i + count*diffx).get(j + count*diffy);
				total += block.getVal();
				//System.out.println("total" + total + " val" + block.getVal());
				//calcBlockList.add(targetBlock);
			} catch (IndexOutOfBoundsException e) {
				break;
			}
			
			if(total > sum){
				break;
			}

			if (total == sum) {
				Logger.log("sum" + sum);
//				for(Block block:calcBlockList){
//					block.setDelete(true);
//				}
				for(int k = 0; k <= count; k++){
					((Block) cols.get(i + k*diffx).get(j + k*diffy)).setDelete(true);
					//System.out.println("del x:" + (i + k*diffx) + "del y:" + (j + k*diffy) );
				}

				isLoop = true;
				break;
			}
			
			count++;
		}
		
//		System.out.println("i:" + i + " j:" + j + " val:" + targetBlock.getVal());
//		System.out.println("isloop" + isLoop);
//		System.out.println(calcBlockList);
//		System.out.println("000000000000");
		
		return isLoop;
	}
	
	/**
	 * x yは消えるブロックの座標
	 * @param x
	 * @param y
	 */
	public void removeOjamaBlocks(int x , int y){
		//上 右上 右 右下 左下 左 左上
		// 左下 左 左上
		try{
			List<Block> bl = cols.get(x - 1);
			ojamaJadge(bl.get(y - 1));
			ojamaJadge(bl.get(y));
			ojamaJadge(bl.get(y + 1));
		}catch (IndexOutOfBoundsException e) {
			//e.printStackTrace();
		}
		
		//右上
		try{
			List<Block> bl = cols.get(x + 1);
			ojamaJadge(bl.get(y - 1));
			ojamaJadge(bl.get(y));
			ojamaJadge(bl.get(y + 1));
		}catch (IndexOutOfBoundsException e) {
			//e.printStackTrace();
		}
		
		//上下
		try{
			List<Block> bl = cols.get(x);
			ojamaJadge(bl.get(y - 1));
			ojamaJadge(bl.get(y + 1));
		}catch (IndexOutOfBoundsException e) {
			//e.printStackTrace();
		}
	}
	
	public void ojamaJadge(Block bl){
		if(bl.getVal() > sum){
			bl.setDelete(true);
		}
	}

	public void removeBlocks() {
		for (int i = cols.size() - 1; i >= 0; i--){
			List<Block> col = cols.get(i);
			for (int j = col.size() - 1; j >= 0; j--) {
				if(col.get(j).isDelete()){
					removeOjamaBlocks(i , j);
//					System.out.println("xy" + i + ":" + j);
					cols.get(i).remove(j);
				}
			}
		}
	}

	@Override
	public String toString() {
		System.out.println("width:" + width + "height:" + height + "size:"
				+ size + "sum:" + sum + "step:" + step);

		return super.toString();
	}

	public int getMinWidth() {
		// ShowStage();

		int xpos = (int) Math.random() * (width - size + 1);
		double avg = 0;
		int min = 0;

		for (int i = 0; i < cols.size() - size + 1; i++) {
			int sum = 0;

			for (int k = 0; k < size; k++) {
				sum += cols.get(i + k).size();
			}
			double currentAvg = (double) sum / size;

			if (i == 0) {
				avg = currentAvg;
				min = cols.get(i).size();
			}

			// System.out.println("i:" + i + " avg:" + avg +" (sum/size):" +
			// currentAvg + " xpos:" + xpos);

			if (avg > currentAvg) {
				avg = currentAvg;
				xpos = i;
			}

			// if(min > cols.get(i).size()){
			// min = cols.get(i).size();
			// xpos = i;
			// }
		}

		return xpos;
	}

	public String ShowStage() {
		String str = "";

		for (int j = height - 1; j >= 0; j--) {
			for (int i = 0; i < width; i++) {
				// System.out.println("--i" + i + "--j" + j);
				try {
					Block a = (Block) cols.get(i).get(j);
					System.out.print(Integer.toHexString((a.getVal())));
				} catch (IndexOutOfBoundsException e) {
					System.out.print("○");
				}
			}
			System.out.println("");
		}

		System.out.println("------------------------------");
		return str;
	}

	public int getBlockCount() {
		int total = 0;
		for (int i = 0; i < cols.size() - size + 1; i++) {
			total += cols.get(i).size();
		}
		return total;
	}
}
