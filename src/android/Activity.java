package android;

public abstract class Activity extends Context {

	private View contentView;

	protected void onCreate() {

	}

	public void setContentView(View view) {
		contentView = view;
	}

	public View findViewById(int viewId) {
		return contentView.findViewById(viewId);
	}

}
