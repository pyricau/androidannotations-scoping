package app;

// @EBean(Singleton.class)
public class MySingleton {

	/*
	 * The injection of this field will fail at runtime, because BeanC is lives
	 * in scope Activity, and MySingleton lives in the wider scope Singleton.
	 */
	// @Inject
	BeanC beanC;

	/*
	 * motherBean has a dependency on MySingleton, and this works fine!
	 */
	// @Inject
	MotherBean motherBean;

}
