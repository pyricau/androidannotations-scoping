package app;

import aa.AfterSetContentViewListener;
import aa.GeneratedCode;
import aa.HasContentView;
import aa.Injectable;
import aa.Scopes;
import aa.ViewListeners;
import android.Fragment;

public final class FragmentBean_ extends FragmentBean implements Injectable, AfterSetContentViewListener {

	public static FragmentBean getInstance() {
		return GeneratedCode.getInstanceInScopeOrNew(Fragment.class, FragmentBean_.class);
	}

	@Override
	public void inject() {
		ViewListeners viewListeners = Scopes.get().findThroughScopesOrNull(ViewListeners.class);
		if (viewListeners != null) {
			viewListeners.registerListener(this);
		}
		myFragment = MyFragment_.getInstance();
		myActivity = MyActivity_.getInstance();
	}

	@Override
	public void afterSetContentView(HasContentView hasContentView) {
		view20 = hasContentView.findViewById(20);
	}

}
