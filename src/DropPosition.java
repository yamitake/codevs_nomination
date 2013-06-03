/**
 * —Ž‰ºˆÊ’u
 * @author takenojun
 */
public class DropPosition {
	public int xpos;
	public int rot;
	
	public DropPosition(int x , int r) {
		xpos = x;
		rot = r;
	}
	
	@Override
	public String toString() {
		return "xpos:" + xpos + " rot:" + rot;
	}
}
