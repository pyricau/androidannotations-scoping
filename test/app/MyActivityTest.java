package app;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertSame;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import android.Application;

public class MyActivityTest {

	private MyActivity activity;

	private static Application application;

	@BeforeClass
	public static void applicationSetup() {
		application = new MyApplication_();
		application.onCreate();
	}

	@Before
	public void setup() {
		activity = new MyActivity_();
		activity.onCreate();
	}

	@Test
	public void bean_a_is_injected() {
		assertNotNull(activity.beanA);
	}

	@Test
	public void bean_b1_is_injected() {
		assertNotNull(activity.beanB1);
	}

	@Test
	public void bean_b2_is_injected() {
		assertNotNull(activity.beanB2);
	}

	@Test
	public void bean_b1_is_not_same_as_b2() {
		assertNotSame(activity.beanB2, activity.beanB1);
	}

	@Test
	public void bean_c_is_injected_in_b1() {
		assertNotNull(activity.beanB1.beanC);
	}

	@Test
	public void bean_c_is_injected_in_b2() {
		assertNotNull(activity.beanB1.beanC);
	}

	@Test
	public void bean_c_is_same_in_b1_and_b2() {
		assertSame(activity.beanB2.beanC, activity.beanB1.beanC);
	}

	@Test
	public void view36_injected_in_bean_a() {
		assertEquals(36, activity.beanA.view36.getId());
	}

	@Test
	public void view20_injected_in_fragment_bean() {
		assertEquals(20, activity.myFragment.fragmentBean.view20.getId());
	}

	@Test
	public void view36_injected_in_fragment_bean_c() {
		assertEquals(36, activity.myFragment.beanC.view36.getId());
	}

	@Test
	public void not_same_view_is_injected_in_fragment_bean_a() {
		assertEquals(36, activity.myFragment.beanA.view36.getId());
		assertNotSame(activity.beanA.view36, activity.myFragment.beanA.view36);
	}

	@Test
	public void fragment_in_fragment_bean_is_parent_fragment() {
		assertSame(activity.myFragment, activity.myFragment.fragmentBean.myFragment);
	}

	@Test
	public void activity_in_fragment_bean_is_parent_activity() {
		assertSame(activity, activity.myFragment.fragmentBean.myActivity);
	}

	@Test
	public void resolves_cyclic_dependency() {
		assertSame(activity.mySingleton, activity.mySingleton.motherBean.mySingleton);
	}

	@Test
	public void singleton_is_injected_in_subscope() {
		assertSame(activity.mySingleton, activity.childBean.mySingleton);
	}

	@Test
	public void application_context_is_injected() {
		assertSame(application.getApplicationContext(), activity.beanA.context);
	}

	@Test
	public void application_is_injected() {
		assertSame(application, activity.beanA.myApplication);
	}

	@Test
	public void context_is_injected_in_beanB_constructor() {
		assertSame(application.getApplicationContext(), activity.beanB1.context);
	}

	@Test
	public void context_is_injected_in_beanD_constructor_in_scope_activity() {
		assertSame(application.getApplicationContext(), activity.myFragment.beanD.context);
	}
}
