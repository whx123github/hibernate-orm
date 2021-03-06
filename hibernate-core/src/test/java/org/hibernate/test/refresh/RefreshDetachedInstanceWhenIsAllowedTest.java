/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.test.refresh;

import org.hibernate.Session;
import org.hibernate.jpa.test.refresh.TestEntity;

import org.hibernate.testing.TestForIssue;
import org.hibernate.testing.junit4.BaseCoreFunctionalTestCase;
import org.junit.Before;
import org.junit.Test;

import static org.hibernate.testing.transaction.TransactionUtil.doInHibernate;

/**
 * @author Andrea Boriero
 */
@TestForIssue(jiraKey = "HHH-11188")
public class RefreshDetachedInstanceWhenIsAllowedTest extends BaseCoreFunctionalTestCase {
	private TestEntity testEntity;

	@Override
	protected Class<?>[] getAnnotatedClasses() {
		return new Class[] {TestEntity.class};
	}

	@Before
	public void setUp() {
		testEntity = new TestEntity();
		doInHibernate( this::sessionFactory, session -> {
			session.save( testEntity );
		} );
	}

	@Test
	public void testRefreshDetachedInstance() {
		final Session session = openSession();
		session.refresh( testEntity );
	}
}
