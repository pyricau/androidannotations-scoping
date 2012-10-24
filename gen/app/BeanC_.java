package app;

import aa.AfterSetContentViewListener;
import aa.GeneratedCode;
import aa.HasContentView;
import aa.Injectable;
import aa.Scopes;
import aa.ViewListeners;
import android.Activity;

public final class BeanC_ extends BeanC implements Injectable, AfterSetContentViewListener {

	public static BeanC getInstance() {
		return GeneratedCode.getInstanceInScopeOrNew(Activity.class, BeanC_.class);
	}

	@Override
	public void inject() {
		ViewListeners viewListeners = Scopes.get().findThroughScopesOrNull(ViewListeners.class);
		if (viewListeners != null) {
			viewListeners.registerListener(this);
		}
	}

	@Override
	public void afterSetContentView(HasContentView hasContentView) {
		view36 = hasContentView.findViewById(36);
	}

}
