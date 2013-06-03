
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * CodeVS 2013
 */
public class Main {
	
	public static void main(String[] arg) throws IOException{
		//System.setIn(new FileInputStream("~/Documents/workspace20120106/codevs/src/sample_input.txt"));
		//System.setIn(new FileInputStream("/Users/takenojun/Documents/workspace20120106/codevs/src/sample_input2.txt"));
		//System.setIn(new FileInputStream("/Users/takenojun/Documents/workspace20120106/codevs/src/sample_inputl.txt"));
		Scanner scan = new Scanner(System.in);

		int wid = scan.nextInt();
		int hei = scan.nextInt();
		int size = scan.nextInt();
		int sum = scan.nextInt();
		int step = scan.nextInt();

		int[][][] pack = new int[step][size][size];
		String endstr = null;

		for(int i=0;i<step;i++){
			for(int j=0;j<size;j++){
				for(int k=0;k<size;k++){
					pack[i][j][k] = scan.nextInt();
				}
			}
			endstr = scan.next();
		}

		Stage stage = new Stage(wid, hei, size, sum, step);

		printStrategy(getBestResult(new Stage(stage), wid, size, step, pack).getNormalizedDpList(step, size, wid));
	}

	public static Result getBestResult(Stage stage , int wid , int size ,  int step , int[][][] pack){
		Result rs = getResult(new Stage(stage), wid, size, step, pack);
		//System.out.println(rs.chainCount);
		Result tmp;
		int sum = rs.chainCount;
		for(int i = 0; i < 3000; i++){
			tmp = getResult(new Stage(stage), wid, size, step, pack);
			//System.out.println("i:" + i + ":::" + tmp.turnCount);
			if(rs.chainCount < tmp.chainCount){
			//if(rs.turnCount < tmp.turnCount){
				rs = tmp;
				//System.out.println("turm:" + tmp.turnCount);
				//System.out.println("chain:" + tmp.chainCount);
			}
			sum+=tmp.chainCount;
		}

		//System.out.println("max:" + rs.turnCount + " avg:" + (double)sum/1000);
		return rs;
	}

	public static void printStrategy(List<DropPosition> list){
		for(DropPosition dp:list){
			System.out.printf("%d %d\n",dp.xpos,dp.rot);
			System.out.flush();
		}
	}

	public static Result getResult(Stage stage , int wid , int size ,  int step , int[][][] pack){
		List<DropPosition> list = new ArrayList<DropPosition>();
		int turn = 0;

		Random random = new Random((int)(1000*Math.random()));
		//stage.toString();
		for(int i=0;i<step;i++){
			turn = i;
			if(stage.isFinish()){
				break;
			}

			int xpos=random.nextInt(wid-size+1);
			int rot=random.nextInt(4);
			Piece piece = new Piece(pack[i]);

			piece.rotate(rot);
			DropPosition dp = new DropPosition(xpos, rot);
			stage.turn(piece , dp);
			list.add(dp);
			
			//stage.ShowStage();
		}

		return new Result(list, turn , stage.maxchain);
	}
}
