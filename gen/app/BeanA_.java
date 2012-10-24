package app;

import aa.AfterSetContentViewListener;
import aa.GeneratedCode;
import aa.HasContentView;
import aa.Injectable;
import aa.Scopes;
import aa.ViewListeners;
import android.Context;

public final class BeanA_ extends BeanA implements Injectable, AfterSetContentViewListener {

	public static BeanA getInstance() {
		return GeneratedCode.getNewInstance(BeanA_.class);
	}

	@Override
	public void inject() {
		beanC = BeanC_.getInstance();
		// Context is a special case
		context = Scopes.get().findThroughScopesOrNull(Context.class);
		myApplication = MyApplication_.getInstance();
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
