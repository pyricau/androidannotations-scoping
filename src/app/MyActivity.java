package app;

import android.Activity;
import android.View;

// @EActivity
public class MyActivity extends Activity {

	// @Inject
	BeanA beanA;

	// @Inject
	BeanB beanB1;

	// @Inject
	BeanB beanB2;

	// @Inject
	MySingleton mySingleton;

	// @Inject
	ChildBean childBean;

	// Visible for testing
	MyFragment myFragment;

	@Override
	protected void onCreate() {
		super.onCreate();
		setContentView(buildViewHierarchy());
	}

	private View buildViewHierarchy() {
		View root = new View(42);
		root.addChild(new View(36));
		root.addChild(new View(22));
		root.addChild(new View(69));
		return root;
	}

	@Override
	public void setContentView(View view) {
		super.setContentView(view);
		/*
		 * Simulating fragment creation
		 */
		myFragment = new MyFragment_();
		view.addChild(myFragment.onCreateView());
	}

}
