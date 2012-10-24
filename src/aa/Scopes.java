package aa;

/**
 * Scopes is just here to help when building the object graph
 */
public class Scopes {

	static Scopes INSTANCE = new Scopes();

	public static Scopes get() {
		return INSTANCE;
	}

	Scope currentScope;

	public Scopes() {
		currentScope = Scope.newSingletonScope();
	}

	/**
	 * @throws IllegalArgumentException
	 *             if the scope is null
	 * @throws IllegalArgumentException
	 *             if the scope is already in the stack
	 */
	public Scope enterScope(Class<?> scopeKey) {
		if (scopeKey == null) {
			throw new IllegalArgumentException("scope should not be null");
		}
		currentScope = Scope.newScope(scopeKey, currentScope);
		return currentScope;
	}

	/**
	 * @throws UnsupportedOperationException
	 *             if the current scope is the {@link Singleton} scope
	 */
	public void exitScope() {
		Scope parentScope = currentScope.getParentScope();
		if (parentScope == null) {
			throw new UnsupportedOperationException("Cannot exit singleton scope");
		}
		currentScope = parentScope;
	}

	public Scope exitToParentScope(Class<?> parentScopeKey) {
		Scope parentScope = currentScope.findScope(parentScopeKey);
		Scope previousScope = currentScope;
		currentScope = parentScope;
		return previousScope;
	}

	public void reenterScope(Scope scope) {
		if (scope == null) {
			throw new IllegalArgumentException("scope should not be null");
		}
		currentScope = scope;
	}

	public <T> void putInCurrentScope(Class<T> key, T value) {
		currentScope.put(key, value);
	}

	public <T> void put(Class<?> scopeKey, Class<T> key, T value) {
		Scope scope = currentScope.findScope(scopeKey);
		scope.put(key, value);
	}

	public <T> T getInCurrentScopeOrNull(Class<T> key) {
		return currentScope.getOrNull(key);
	}

	/**
	 * throws IllegalArgumentException if the key cannot be found
	 */
	public <T> T getInCurrentScopeThrowing(Class<T> key) {
		T instance = getInCurrentScopeOrNull(key);
		if (instance == null) {
			throw new IllegalArgumentException("Instance of " + key + " not found in current scope " + currentScope.toString());
		}
		return instance;
	}

	public <T> T findThroughScopesOrNull(Class<T> key) {
		return currentScope.getRecursivelyOrNull(key);
	}

}
