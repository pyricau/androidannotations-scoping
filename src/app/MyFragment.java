package app;

import android.Fragment;
import android.View;

// @EFragment
public class MyFragment extends Fragment {

	// @Inject
	BeanA beanA;

	// @Inject
	BeanC beanC;

	// @Inject
	BeanD beanD;

	// @Inject
	FragmentBean fragmentBean;

	@Override
	public View onCreateView() {
		View root = new View(36);

		root.addChild(new View(20));
		return root;
	}

}
