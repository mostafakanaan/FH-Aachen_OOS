public class Aufgabe_1_2 {
		public static void main(String[] args) {

				//Point:

				Point p1 = new Point();
				System.out.println(p1);
				p1.setLocation(2, 3);
				System.out.println();
				System.out.print("Nach dem Setten..\n");
				System.out.println(p1);

				System.out.println();

				Point p2 = new Point(p1);
				System.out.print("P2=P1=\n");
				System.out.println(p1);
				System.out.println(p2);
				System.out.println((p2.equals(p1)));

				System.out.println();

				p1.move(-2,-3);
				System.out.println("P1 wurde zum Ursprung verschoben.");
				System.out.println(p1);

				System.out.println("\n---------------------------------\n");
				//------------------------------------------------------//

				//Kreis:

				KreisVererb k1 = new KreisVererb(p2, 2);
				System.out.println("K1=\n" + k1);
				KreisVererb k2 = new KreisVererb(k1);
				System.out.println("K2=\n" + k2);

				System.out.println();

				k2.setLocation(5,5);
				k2.setRadius(7);
				System.out.println("K2 nach dem Setten:\n" + k2.getLocation());
				System.out.println("K2's Umfang= " + k2.getUmfang());
				System.out.println("K2's Fläche= " + k2.getFlaecheninhalt());


		}
}

class Point {

		int x, y;

		Point() {
				this.x = 0;
				this.y = 0;
		}

		Point(Point p) {
				this.x = p.x;
				this.y = p.y;
		}

		Point(int x, int y) {
				this.x = x;
				this.y = y;
		}

		Point getLocation() {
				return this;
		}

		void setLocation(Point p) {
				this.x = p.x;
				this.y = p.y;
		}

		void setLocation(int x, int y) {
				this.x = x;
				this.y = y;
		}

		void move(int dx, int dy) {
				this.x += dx;
				this.y += dy;
		}

		boolean equals(Point p) {
				return (this.x == p.x && this.y == p.y);
		}

		protected Point clone() {
				return new Point(this);
		}

		public String toString() {
				return "x: " + this.x + ", y: " + this.y;
		}

}

class KreisVererb extends Point {

		int r;

		KreisVererb() {
				this.r = 1;
		}

		KreisVererb(KreisVererb k) {
				this.x = k.x;
				this.y = k.y;
				this.r = k.r;
		}

		KreisVererb(Point p, int r) {
				this.x = p.x;
				this.y = p.y;
				this.r = r;
		}

		KreisVererb(int x, int y, int r) {
				this.x = x;
				this.y = y;
				this.r = r;
		}

		double getFlaecheninhalt() {
				return Math.PI * r * r;
		}

		double getUmfang() {
				return 2 * Math.PI * r;
		}

		int getRadius() {
				return this.r;
		}

		void setRadius(int r) {
				this.r = r;
		}

		public String toString() {
				return super.toString() + ", r: " + this.r;
		}
}
