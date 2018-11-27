package Tests;

import BenutzerVerwaltung.Benutzer;
import BenutzerVerwaltung.BenutzerVerwaltungAdmin;
import BenutzerVerwaltung.BenutzerVerwaltungException;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class BenutzerVerwaltungAdminTest extends TestCase {

		public BenutzerVerwaltungAdminTest() {
		}

		BenutzerVerwaltungAdmin admin;
		Benutzer benutzer1;

		@BeforeEach
		protected void setUp() throws Exception {
				admin = new BenutzerVerwaltungAdmin();
				benutzer1 = new Benutzer("1", "pass".toCharArray());
				super.setUp();
				//admin.dbInitialisieren();
		}

		@AfterEach
		protected void tearDown() throws Exception {
				super.tearDown();
				admin.dbInitialisieren();
		}

		@Test
		public void benutzerEintragen() throws BenutzerVerwaltungException {
				admin.benutzerEintragen(benutzer1);
				admin.benutzerOk(benutzer1);
				try {
						admin.benutzerEintragen(benutzer1);
						fail("Expected a DuplicateException to be thrown.");
				} catch (BenutzerVerwaltungException e) {
						e.printStackTrace();
				}
		}

		@Test
		public void benutzerOk() throws BenutzerVerwaltungException {
				admin.benutzerEintragen(benutzer1);
				assertTrue(admin.benutzerOk(benutzer1));
		}

		@Test
		public void benutzerLöschen() throws BenutzerVerwaltungException {
				admin.benutzerEintragen(benutzer1);
				admin.benutzerLöschen(benutzer1);
				admin.read();
				assertFalse(admin.benutzerOk(benutzer1));
		}


		//Exceptions:
		////////////////////////////////////////////////////////////////
		@Test
		public void testNullPointerException() {
				try {
						Benutzer ben = null;
						admin.benutzerEintragen(ben);
						fail("Expected a NullPointerException to be thrown.");
				} catch (BenutzerVerwaltungException e) {
						e.printStackTrace();
				}
		}

		@Test
		public void testEmptyException() {
				try {
						benutzer1.userID = null;
						benutzer1.passWort = null;
						admin.benutzerEintragen(benutzer1);
						fail("Expected an EmptyException to be thrown.");
				} catch (BenutzerVerwaltungException e) {
						e.printStackTrace();
				}
		}

		@Test
		public void testNoIdException() {
				try {
						benutzer1.userID = null;
						admin.benutzerEintragen(benutzer1);
						fail("Expected a NoIdException to be thrown.");
				} catch (BenutzerVerwaltungException e) {
						e.printStackTrace();
				}
		}

		@Test
		public void testNoPasswordException() {
				try {
						benutzer1.passWort = null;
						admin.benutzerEintragen(benutzer1);
						fail("Expected a NoPasswordException to be thrown.");
				} catch (BenutzerVerwaltungException e) {
						e.printStackTrace();
				}
		}

		@Test
		public void testNotFoundException() {
				try {
						admin.benutzerLöschen(benutzer1);
						fail("Expected a NotFoundException to be thrown.");
				} catch (BenutzerVerwaltungException e) {
						e.printStackTrace();
				}
		}
		////////////////////////////////////////////////////////////////

		@Test
		public void testDbinitialisieren() {
				admin.dbInitialisieren();
				admin.read();
				assertEquals(admin.getUserlist().size(), 0);
		}

		@Test
		public void testWriteRead() throws BenutzerVerwaltungException {
				admin.benutzerEintragen(benutzer1);
				ArrayList<Benutzer> DB2 = new ArrayList<Benutzer>();
				DB2.add(0, benutzer1);

				admin.write();
				admin.getUserlist().clear();
				admin.read();

				assertEquals(admin.getUserlist(), DB2);
		}

		public static void main(String[] args) {
				junit.textui.TestRunner.run(BenutzerVerwaltungAdminTest.class);
		}
}