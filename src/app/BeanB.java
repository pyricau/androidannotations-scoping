package app;

import android.Context;

// @EBean
public class BeanB {

	// @Inject
	BeanC beanC;

	final Context context;

	public BeanB(Context context) {
		this.context = context;
	}

}
