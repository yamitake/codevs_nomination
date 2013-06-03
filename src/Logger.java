
public class Logger {
	public static final boolean isDebug = false;
	
	static void log(Object log) {
		if(isDebug){
			System.out.println(log);	
		}
	}
}
