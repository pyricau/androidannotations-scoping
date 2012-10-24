package app;

import aa.GeneratedCode;
import aa.Injectable;
import android.Context;

public final class BeanB_ extends BeanB implements Injectable {

	public static BeanB getInstance() {
		return GeneratedCode.getNewInstanceWithContext(BeanB_.class);
	}

	public BeanB_(Context context) {
		super(context);
	}

	@Override
	public void inject() {
		beanC = BeanC_.getInstance();
	}

}
