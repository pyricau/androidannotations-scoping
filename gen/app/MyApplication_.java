package app;

import aa.GeneratedCode;
import aa.Injectable;
import aa.Scopes;
import android.Context;

public final class MyApplication_ extends MyApplication implements Injectable {

	public static MyApplication getInstance() {
		return GeneratedCode.getInstanceInSelfScope(MyApplication.class);
	}

	@Override
	public void onCreate() {

		Scopes.get().enterScope(MyApplication.class);
		Scopes.get().putInCurrentScope(Context.class, getApplicationContext());
		Scopes.get().putInCurrentScope(MyApplication.class, this);
		inject();

		/*
		 * We do not exit the Application scope
		 */

		super.onCreate();
	}

	@Override
	public void inject() {

	}

}
