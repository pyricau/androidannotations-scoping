package aa;

import java.util.HashMap;
import java.util.Map;

public final class Scope {
	final Class<?> scopeKey;
	private final Scope parentScope;
	private final Map<Class<?>, Object> beansByType = new HashMap<Class<?>, Object>();

	static Scope newSingletonScope() {
		return new Scope(Singleton.class, null);
	}

	static Scope newScope(Class<?> scopeKey, Scope parentScope) {

		Scope result = parentScope.findScopeResursivelyOrNull(scopeKey);

		if (result != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("Scope key ") //
					.append(scopeKey)//
					.append(" already exists in the scope stack ");
			parentScope.scopeStackToStringBuilder(sb);
			throw new IllegalArgumentException(sb.toString());
		}

		return new Scope(scopeKey, parentScope);
	}

	private Scope(Class<?> scopeKey, Scope parentScope) {
		this.scopeKey = scopeKey;
		this.parentScope = parentScope;
	}

	Scope getParentScope() {
		return parentScope;
	}

	<T> void put(Class<T> key, T value) {
		beansByType.put(key, value);
	}

	<T> T getOrNull(Class<T> key) {
		@SuppressWarnings("unchecked")
		T result = (T) beansByType.get(key);
		return result;
	}

	<T> T getRecursivelyOrNull(Class<T> key) {
		@SuppressWarnings("unchecked")
		T result = (T) beansByType.get(key);
		if (result == null) {
			if (parentScope == null) {
				result = null;
			} else {
				result = parentScope.getRecursivelyOrNull(key);
			}
		}

		return result;

	}

	Scope findScope(Class<?> scopeKey) {

		Scope result = findScopeResursivelyOrNull(scopeKey);

		if (result == null) {
			StringBuilder sb = new StringBuilder();
			sb.append("Could not find scope key ") //
					.append(scopeKey)//
					.append(" in scope stack ");
			scopeStackToStringBuilder(sb);
			throw new IllegalArgumentException(sb.toString());
		}

		return result;
	}

	private Scope findScopeResursivelyOrNull(Class<?> scopeKey) {
		if (scopeKeyMatches(scopeKey)) {
			return this;
		} else {
			if (parentScope != null) {
				return parentScope.findScopeResursivelyOrNull(scopeKey);
			} else {
				return null;
			}
		}
	}

	@Override
	public String toString() {
		return scopeKey.getName();
	}

	private boolean scopeKeyMatches(Class<?> scopeKey) {
		return scopeKey.isAssignableFrom(this.scopeKey);
	}

	private void scopeStackToStringBuilder(StringBuilder builder) {
		if (parentScope != null) {
			parentScope.scopeStackToStringBuilder(builder);
			builder.append(" > ");
		}
		builder.append(toString());
	}
}