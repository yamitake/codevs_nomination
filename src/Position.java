
public class Position {
	int x;
	int y;
	
	public Position(int x , int y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		
		return "pos(x:y) "+x+":" + y;
	}
}
