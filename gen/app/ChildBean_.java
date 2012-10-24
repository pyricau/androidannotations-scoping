package app;

import aa.GeneratedCode;
import aa.Injectable;
import android.Activity;

public final class ChildBean_ extends ChildBean implements Injectable {

	public static ChildBean getInstance() {
		return GeneratedCode.getInstanceInScopeOrNew(Activity.class, ChildBean_.class);
	}

	@Override
	public void inject() {
		mySingleton = MySingleton_.getInstance();
		beanC = BeanC_.getInstance();
	}

}
