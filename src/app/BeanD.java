package app;

import android.Context;

// @EBean(Activity.class)
public class BeanD {

	/*
	 * The injection of this field will fail at runtime, because FragmentBean is
	 * lives in scope Fragment, and BeanD lives in the wider scope Activity.
	 */
	// @Inject
	FragmentBean fragmentBean;

	final Context context;

	public BeanD(Context context) {
		this.context = context;
	}

}
