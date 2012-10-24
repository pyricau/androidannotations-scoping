package app;

import aa.GeneratedCode;
import aa.HasContentView;
import aa.Injectable;
import aa.Scope;
import aa.Scopes;
import aa.ViewListeners;
import android.Context;
import android.View;

public final class MyActivity_ extends MyActivity implements Injectable, HasContentView {

	public static MyActivity getInstance() {
		return GeneratedCode.getInstanceInSelfScope(MyActivity.class);
	}

	private ViewListeners viewListeners;
	private Scope activityScope;

	@Override
	protected void onCreate() {

		viewListeners = new ViewListeners();

		activityScope = Scopes.get().enterScope(MyActivity.class);

		Scopes.get().putInCurrentScope(Context.class, getApplicationContext());
		Scopes.get().putInCurrentScope(MyActivity.class, this);
		Scopes.get().putInCurrentScope(ViewListeners.class, viewListeners);
		inject();
		Scopes.get().exitScope();

		super.onCreate();
	}

	@Override
	public void setContentView(View view) {
		Scopes.get().reenterScope(activityScope);
		super.setContentView(view);
		viewListeners.afterSetContentView(this);
		Scopes.get().exitScope();
	}

	@Override
	public void inject() {
		beanA = BeanA_.getInstance();
		beanB1 = BeanB_.getInstance();
		beanB2 = BeanB_.getInstance();
		mySingleton = MySingleton_.getInstance();
		childBean = ChildBean_.getInstance();
	}

}
