package aa;

import java.util.HashSet;
import java.util.Set;

public class ViewListeners {

	private final Set<AfterSetContentViewListener> listeners = new HashSet<AfterSetContentViewListener>();

	public void registerListener(AfterSetContentViewListener listener) {
		listeners.add(listener);
	}

	public void afterSetContentView(HasContentView hasContentView) {
		for (AfterSetContentViewListener listener : listeners) {
			listener.afterSetContentView(hasContentView);
		}
	}

}
