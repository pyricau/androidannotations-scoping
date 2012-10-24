package app;

import aa.GeneratedCode;
import aa.Injectable;
import android.Context;

public final class BeanD_ extends BeanD implements Injectable {

	public static BeanD getInstance() {
		return GeneratedCode.getInstanceInScopeOrNewWithContext(MyActivity.class, BeanD_.class);
	}

	public BeanD_(Context context) {
		super(context);
	}

	@Override
	public void inject() {
		try {
			// Illegal
			fragmentBean = FragmentBean_.getInstance();
		} catch (IllegalArgumentException expected) {
			return;
		}
		throw new IllegalStateException("Injection should fail");
	}

}
