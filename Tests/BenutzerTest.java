package Tests;

import BenutzerVerwaltung.Benutzer;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
//import static org.junit.Assert.*;
import org.junit.Test;


public class BenutzerTest extends TestCase {

		@Before
		public void setUp() throws Exception {
				super.setUp();
		}

		@After
		public void tearDown() throws Exception {
				super.tearDown();
		}

		@Test
		public void testBenutzerStringString() {
				//Konstruktortest
				Benutzer ben1 = new Benutzer("uid", "pwd".toCharArray());
				//-> setUp()
				assertEquals("uid", ben1.userID);
				assertEquals("pwd", String.valueOf(ben1.passWort));
		}

		@Test
		public void testEqualsBenutzer() {
				Benutzer ben1 = new Benutzer("ID", "pwd".toCharArray());
				Benutzer ben2 = new Benutzer("ID", "pwd".toCharArray());
				assertEquals(ben1, ben2);
				assertNotSame(ben1, ben2);
				ben1 = ben2;
				assertSame(ben1, ben2);
				Benutzer ben3 = new Benutzer("ui", "pw".toCharArray());
				assertFalse(ben1.equals(ben3));
		}

		@Test
		public void testToStringBenutzer() {
				Benutzer ben1 = new Benutzer("uid", "pwd".toCharArray());
				assertEquals(ben1.toString(),
						"userID: " + ben1.userID + "\nPasswort: " +
								String.copyValueOf(ben1.passWort));
		}

		public static void main(String[] args) {
				junit.textui.TestRunner.run(BenutzerTest.class);
		}
}
