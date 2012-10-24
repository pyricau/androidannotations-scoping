package app;

import aa.GeneratedCode;
import aa.Injectable;
import aa.Singleton;

public final class MySingleton_ extends MySingleton implements Injectable {

	public static MySingleton getInstance() {
		return GeneratedCode.getInstanceInScopeOrNew(Singleton.class, MySingleton_.class);
	}

	@Override
	public void inject() {
		motherBean = MotherBean_.getInstance();
		try {
			// Illegal
			beanC = BeanC_.getInstance();
			throw new IllegalStateException("injection should fail");
		} catch (IllegalArgumentException expected) {
		}

	}

}
