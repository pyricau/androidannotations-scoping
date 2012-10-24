package android;

import java.util.ArrayList;
import java.util.List;

public class View {

	private final int id;

	private final List<View> children = new ArrayList<View>();

	public View(int id) {
		this.id = id;
	}

	public void addChild(View child) {
		children.add(child);
	}

	public View findViewById(int viewId) {
		if (viewId == id) {
			return this;
		}

		for (View child : children) {
			View found = child.findViewById(viewId);
			if (found != null) {
				return found;
			}
		}
		return null;
	}

	public int getId() {
		return id;
	}

}
