package aa;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertSame;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import aa.Scope;
import aa.Scopes;
import aa.Singleton;

public class ScopesTest {

	private Scopes scopes;

	@Before
	public void setup() {
		scopes = new Scopes();
	}

	@Test
	public void singleton_scope_already_entered() {
		assertEquals(Singleton.class, scopes.currentScope.scopeKey);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void cannot_exit_singleton_scope() {
		scopes.exitScope();
	}

	@Test(expected = IllegalArgumentException.class)
	public void cannot_enter_scope_already_in_stack() {
		scopes.enterScope(Object.class);
		scopes.enterScope(Object.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void cannot_enter_superclass_scope_of_scope_already_in_task() {
		class ScopeKey {
		}
		scopes.enterScope(ScopeKey.class);
		scopes.enterScope(Object.class);
	}

	@Test
	public void can_access_binding_from_outer_scope() {
		class ScopeKey {
		}
		scopes.enterScope(ScopeKey.class);

		Object object = new Object();
		scopes.put(Singleton.class, Object.class, object);

		assertSame(object, scopes.findThroughScopesOrNull(Object.class));
	}

	@Test
	public void can_access_binding_in_outer_scope_after_exiting_inner_scope() {
		class ScopeKey {
		}
		scopes.enterScope(ScopeKey.class);

		Object object = new Object();
		scopes.put(Singleton.class, Object.class, object);
		scopes.exitScope();

		assertSame(object, scopes.findThroughScopesOrNull(Object.class));
	}

	@Test
	public void cannot_access_binding_in_inner_scope_when_exited_scope() {
		class ScopeKey {
		}
		scopes.enterScope(ScopeKey.class);

		scopes.put(ScopeKey.class, Object.class, new Object());

		scopes.exitToParentScope(Singleton.class);

		assertNull(scopes.findThroughScopesOrNull(Object.class));
	}

	@Test
	public void can_access_binding_in_inner_scope_when_reentering() {
		class ScopeKey {
		}
		scopes.enterScope(ScopeKey.class);

		Object object = new Object();
		scopes.put(ScopeKey.class, Object.class, object);

		Scope previousScope = scopes.exitToParentScope(Singleton.class);

		scopes.reenterScope(previousScope);

		assertSame(object, scopes.findThroughScopesOrNull(Object.class));
	}

	@Test
	public void empty_binding_returns_null() {
		assertNull(scopes.getInCurrentScopeOrNull(Object.class));
	}

	@Test
	public void added_bean_is_returned() {
		Object object = new Object();
		scopes.put(Singleton.class, Object.class, object);
		assertSame(object, scopes.getInCurrentScopeOrNull(Object.class));
	}

	@Test
	public void exiting_to_superclass_scope_stays_identical() {
		class SuperclassScopeKey {
		}
		class ChildScopeKey extends SuperclassScopeKey {
		}
		class InnerScopeKey {
		}

		scopes.enterScope(ChildScopeKey.class);
		scopes.enterScope(InnerScopeKey.class);
		scopes.exitToParentScope(SuperclassScopeKey.class);

		assertEquals(ChildScopeKey.class, scopes.currentScope.scopeKey);
	}

}
