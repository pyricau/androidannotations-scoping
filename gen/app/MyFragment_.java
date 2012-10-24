package app;

import aa.GeneratedCode;
import aa.HasContentView;
import aa.Injectable;
import aa.Scopes;
import aa.ViewListeners;
import android.View;

public final class MyFragment_ extends MyFragment implements Injectable, HasContentView {

	public static MyFragment getInstance() {
		return GeneratedCode.getInstanceInSelfScope(MyFragment.class);
	}

	private ViewListeners viewListeners;

	private View root;

	public MyFragment_() {
		viewListeners = new ViewListeners();

		Scopes.get().enterScope(MyFragment.class);
		Scopes.get().putInCurrentScope(MyFragment.class, this);
		Scopes.get().putInCurrentScope(ViewListeners.class, viewListeners);

		inject();

		Scopes.get().exitScope();
	}

	@Override
	public View onCreateView() {
		root = super.onCreateView();

		viewListeners.afterSetContentView(this);

		return root;
	}

	@Override
	public View findViewById(int viewId) {
		return root.findViewById(viewId);
	}

	@Override
	public void inject() {
		beanA = BeanA_.getInstance();
		beanC = BeanC_.getInstance();
		beanD = BeanD_.getInstance();
		fragmentBean = FragmentBean_.getInstance();
	}

}
