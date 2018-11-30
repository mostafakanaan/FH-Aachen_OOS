public class Aufgabe_1_1 {
		public static void main(String[] args) {
				final int tiefe = 10;
				int[][] dreieck = new int[tiefe][];

				for (int i = 0; i < tiefe; i++) {
						//eine Zeile anlegen
						dreieck[i] = new int[i + 1];
						//erstes Element in der Zeile = 1..
						dreieck[i][0] = 1;

						//die Werte von inneren Feldern in der Zeile i berechnen..
						for (int j = 1; j < i; j++) {
								dreieck[i][j] = dreieck[i - 1][j - 1] + dreieck[i - 1][j];
						}
						//letztes Element in der Zeile = 1..
						dreieck[i][i] = 1;

						//Ausgabe:
						for(int f=0; f<i+1; f++) {
								System.out.print(dreieck[i][f]);
										System.out.print(f!=i? " " : "");
						}
						System.out.println();
				}
		}
}
