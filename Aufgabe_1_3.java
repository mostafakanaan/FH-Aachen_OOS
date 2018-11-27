import static java.lang.Math.abs;

public class Aufgabe_1_3 {
		public static void main(String[] args) {
				Geometrie[] a = new Geometrie[4];
				a[0] = new KreisAgg(64, 14, 2);
				a[1] = new Rechteck(5, 15, 10, 20);
				a[2] = new KreisAgg(3, 10, 3);
				a[3] = new Rechteck(100, 900, 200, 1000);

				int gesamtflaeche = 0;

				for (int i=0; i<a.length; i++) {
						System.out.println(a[i]);
						System.out.println("Der Flächeninhalt beträgt: " + a[i].getFlaecheninhalt());
						System.out.println();

						gesamtflaeche += a[i].getFlaecheninhalt();
				}
				System.out.println("Gesamtflächeninhalt: " + gesamtflaeche);
		}
}

abstract class Geometrie {
		abstract double getFlaecheninhalt();
}

class KreisAgg extends Geometrie {

		Point p;

		int r;

		KreisAgg() {
				this.p = new Point(); //hier wird das Objekt erst erzeugt..
				this.r = 1;
		}

		KreisAgg(KreisAgg k) {
				this.p = new Point(k.p);
				this.r = k.r;
		}

		KreisAgg(Point p, int r) {
				this.p = new Point(p);
				this.r = r;
		}

		KreisAgg(int x, int y, int r) {
				this.p = new Point(x, y);
				this.r = r;
		}

		double getFlaecheninhalt() {
				return Math.PI * r * r;
		}

		int getRadius() {
				return this.r;
		}

		void setRadius(int r) {
				this.r = r;
		}

		void setLocation(int x, int y) {
				this.p.setLocation(x, y);
		}

		void move(int x, int y) {
				this.p.move(x, y);
		}

		boolean equals(KreisAgg k) {
				return (this.p.equals(k.p) && this.r == k.getRadius());
		}

		protected KreisAgg clone() {
				return new KreisAgg(this);
		}

		public String toString() {
				return this.p.toString() + ", r: " + this.r;
		}

}

class Rechteck extends Geometrie {

		Point a, b;

		Rechteck() {
				this.a = new Point();
				this.b = new Point();
		}

		Rechteck(Rechteck r) {
				this.a = new Point(r.a);
				this.b = new Point(r.b);
		}

		Rechteck(Point p1, Point p2) {
				this.a = new Point(p1);
				this.b = new Point(p2);
		}

		Rechteck(int a1, int a2, int b1, int b2) {
				this.a = new Point(a1, a2);
				this.b = new Point(b1, b2);
		}

		double getFlaecheninhalt() {
				return abs(this.a.x - this.b.x) * abs(this.a.y - this.b.y);

		}

		boolean equals(Rechteck r) {
				return (this.a.equals(r.a) && this.b.equals(r.b));
		}

		protected Rechteck clone() {
				return new Rechteck(this);
		}

		public String toString() {
				return this.a.toString() + " | " + this.b.toString();
		}

}