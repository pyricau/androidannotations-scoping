package app;

import aa.GeneratedCode;
import aa.Injectable;
import aa.Singleton;

public final class MotherBean_ extends MotherBean implements Injectable {

	public static MotherBean getInstance() {
		return GeneratedCode.getInstanceInScopeOrNew(Singleton.class, MotherBean_.class);
	}

	@Override
	public void inject() {
		mySingleton = MySingleton_.getInstance();
	}

}
