package aa;

import android.Context;

/**
 * This code will be generated code
 */
public class GeneratedCode {

	public static <T extends Injectable> T getNewInstance(Class<T> beanClazz) {
		return getNewInstance(beanClazz, false);
	}

	public static <T extends Injectable> T getNewInstanceWithContext(Class<T> beanClazz) {
		return getNewInstance(beanClazz, true);
	}

	private static <T extends Injectable> T getNewInstance(Class<T> beanClazz, boolean needContext) {
		T bean = buildAndInjectBean(beanClazz, beanClazz, needContext);
		return bean;
	}

	public static <T> T getInstanceInSelfScope(Class<T> beanClazz) {
		Scopes scopes = Scopes.get();
		Scope previousScope = scopes.exitToParentScope(beanClazz);
		T bean = scopes.getInCurrentScopeThrowing(beanClazz);
		scopes.reenterScope(previousScope);
		return bean;
	}

	public static <T extends Injectable> T getInstanceInScopeOrNew(Class<?> scopeKey, Class<T> beanClazz) {
		return getInstanceInScopeOrNew(scopeKey, beanClazz, false);
	}

	public static <T extends Injectable> T getInstanceInScopeOrNewWithContext(Class<?> scopeKey, Class<T> beanClazz) {
		return getInstanceInScopeOrNew(scopeKey, beanClazz, true);
	}

	public static <T extends Injectable> T getInstanceInScopeOrNew(Class<?> scopeKey, Class<T> beanClazz, boolean needContext) {
		Scopes scopes = Scopes.get();
		Scope previousScope = scopes.exitToParentScope(scopeKey);
		T bean = scopes.getInCurrentScopeOrNull(beanClazz);
		if (bean == null) {
			bean = buildAndInjectBean(scopeKey, beanClazz, needContext);
		}
		scopes.reenterScope(previousScope);
		return bean;
	}

	private static <T extends Injectable> T buildAndInjectBean(Class<?> scopeKey, Class<T> beanClazz, boolean needContext) {
		Scopes scopes = Scopes.get();

		T bean;
		if (needContext) {
			Context context = scopes.findThroughScopesOrNull(Context.class);
			bean = createInstanceWithContext(beanClazz, context);
		} else {
			bean = createInstance(beanClazz);
		}
		scopes.enterScope(beanClazz);
		scopes.put(scopeKey, beanClazz, bean);
		bean.inject();
		scopes.exitScope();
		return bean;
	}

	private static <T> T createInstanceWithContext(Class<T> beanClazz, Context context) {
		try {
			return beanClazz.getConstructor(Context.class).newInstance(context);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static <T> T createInstance(Class<T> beanClazz) {
		try {
			return beanClazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
