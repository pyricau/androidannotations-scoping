package android;

public class Context {

	private static final Context APPLICATION_CONTEXT = new Context();

	public Context getApplicationContext() {
		return APPLICATION_CONTEXT;
	}

}
