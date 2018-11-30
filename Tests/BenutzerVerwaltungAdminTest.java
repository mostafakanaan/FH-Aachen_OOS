package Tests;

import BenutzerVerwaltung.Benutzer;
import BenutzerVerwaltung.BenutzerVerwaltungAdmin;
import BenutzerVerwaltung.Exceptions.BenutzerNullPointerException;
import BenutzerVerwaltung.Exceptions.BenutzerVerwaltungException;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class BenutzerVerwaltungAdminTest extends TestCase {

		public BenutzerVerwaltungAdminTest() {
		}

		BenutzerVerwaltungAdmin admin;
		Benutzer benutzer0;
		Benutzer benutzer1;
		Benutzer benutzer2;
		Benutzer benutzer3;
		Benutzer benutzerEmpty;
		Benutzer benutzerNoID;
		Benutzer benutzerNoPass;

		@BeforeEach
		protected void setUp() throws Exception {
				admin = new BenutzerVerwaltungAdmin();
				benutzer0 = null;
				benutzer1 = new Benutzer("1", "pass1".toCharArray());
				benutzer2 = new Benutzer("2", "pass2".toCharArray());
				benutzer3 = new Benutzer("3", "pass3".toCharArray());
				benutzerEmpty = new Benutzer("", "".toCharArray());
				benutzerNoID = new Benutzer(null, "pass3".toCharArray());
				benutzerNoPass = new Benutzer("3", null);
				super.setUp();
				//admin.dbInitialisieren();
		}

		@AfterEach
		protected void tearDown() throws Exception {
				admin.dbInitialisieren();
				super.tearDown();
		}

		public static void main(String[] args) {
				junit.textui.TestRunner.run(BenutzerVerwaltungAdminTest.class);
		}

		@Test
		public void testBenutzerEintragen() throws BenutzerVerwaltungException {
				admin.benutzerEintragen(benutzer1);
				admin.benutzerEintragen(benutzer2);
				admin.benutzerOk(benutzer1);
				admin.benutzerOk(benutzer2);
				
				// Exceptions:
				try {
						admin.benutzerEintragen(benutzer0);
						fail("Expected a NullPointerException to be thrown.");
				} catch (BenutzerVerwaltungException e) {
						e.printStackTrace();
				}
				try {
						admin.benutzerEintragen(benutzerEmpty);
						fail("Expected an EmptyException to be thrown.");
				} catch (BenutzerVerwaltungException e) {
						e.printStackTrace();
				}
				try {
						admin.benutzerEintragen(benutzerNoID);
						fail("Expected a NoIdException to be thrown.");
				} catch (BenutzerVerwaltungException e) {
						e.printStackTrace();
				}
				try {
						admin.benutzerEintragen(benutzerNoPass);
						fail("Expected a NoPasswordException to be thrown.");
				} catch (BenutzerVerwaltungException e) {
						e.printStackTrace();
				}
				try {
						admin.benutzerEintragen(benutzer1);
						fail("Expected a DuplicateException to be thrown.");
				} catch (BenutzerVerwaltungException e) {
						e.printStackTrace();
				}
		}

		@Test
		public void testBenutzerOk() throws BenutzerVerwaltungException {
				admin.benutzerEintragen(benutzer1);
				admin.benutzerEintragen(benutzer2);
				assertTrue(admin.benutzerOk(benutzer1));
				assertTrue(admin.benutzerOk(benutzer2));

				// Exceptions:
				try {
						admin.benutzerOk(benutzer0);
						fail("Expected a NullPointerException to be thrown.");
				} catch (BenutzerVerwaltungException e) {
						e.printStackTrace();
				}
				try {
						admin.benutzerOk(benutzerEmpty);
						fail("Expected an EmptyException to be thrown.");
				} catch (BenutzerVerwaltungException e) {
						e.printStackTrace();
				}
				try {
						admin.benutzerOk(benutzerNoID);
						fail("Expected a NoIdException to be thrown.");
				} catch (BenutzerVerwaltungException e) {
						e.printStackTrace();
				}
				try {
						admin.benutzerOk(benutzerNoPass);
						fail("Expected a NoPasswordException to be thrown.");
				} catch (BenutzerVerwaltungException e) {
						e.printStackTrace();
				}
		}

		@Test
		public void testBenutzerLöschen() throws BenutzerVerwaltungException {
				admin.benutzerEintragen(benutzer1);
				admin.benutzerEintragen(benutzer2);
				admin.benutzerLöschen(benutzer1);
				admin.benutzerLöschen(benutzer2);
				admin.read();
				assertFalse(admin.benutzerOk(benutzer1));
				assertFalse(admin.benutzerOk(benutzer2));

				// Exceptions:
				try {
						admin.benutzerLöschen(benutzer0);
						fail("Expected a NullPointerException to be thrown.");
				} catch (BenutzerVerwaltungException e) {
						e.printStackTrace();
				}
				try {
						admin.benutzerLöschen(benutzerEmpty);
						fail("Expected an EmptyException to be thrown.");
				} catch (BenutzerVerwaltungException e) {
						e.printStackTrace();
				}
				try {
						admin.benutzerLöschen(benutzerNoID);
						fail("Expected a NoIdException to be thrown.");
				} catch (BenutzerVerwaltungException e) {
						e.printStackTrace();
				}
				try {
						admin.benutzerLöschen(benutzerNoPass);
						fail("Expected a NoPasswordException to be thrown.");
				} catch (BenutzerVerwaltungException e) {
						e.printStackTrace();
				}
				try {
						admin.benutzerLöschen(benutzer3);
						fail("Expected a NotFoundException to be thrown.");
				} catch (BenutzerVerwaltungException e) {
						e.printStackTrace();
				}
		}

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
}