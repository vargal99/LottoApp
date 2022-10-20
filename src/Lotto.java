import java.util.Random;
import java.util.Scanner;

public class Lotto {
    public static void main(String[] args) {
        int[] osszSzam = new int[91];
        System.out.println("Az osszes valaszhato Lottoszam: ");
        for (int i = 1; i < osszSzam.length; i++) {
            osszSzam[i] = i;
            System.out.print(osszSzam[i] + " ");
        }

        System.out.println();

        Random rnd = new Random();
        int[] tombVeletlenSzam = new int[5];
        int szamlalo = 0;
        while (szamlalo < 5) {
            int szam = rnd.nextInt(90) + 1;
            boolean benneVan = false;
            for (int j = 0; j < szamlalo; j++) {
                if (tombVeletlenSzam[j] == szam) {
                    benneVan = true;
                }
            }
            if (benneVan == false) {
                tombVeletlenSzam[szamlalo] = szam;
                szamlalo++;
            }
        }

        boolean rendezett = false;
        while (!rendezett) {
            rendezett = true;
            for (int j = 1; j < tombVeletlenSzam.length; j++) {
                if (tombVeletlenSzam[j - 1] > tombVeletlenSzam[j]) {
                    int seged = tombVeletlenSzam[j - 1];
                    tombVeletlenSzam[j - 1] = tombVeletlenSzam[j];
                    tombVeletlenSzam[j] = seged;
                    rendezett = false;
                }
            }
        }

        System.out.println("\nKerem az tippjeit (5 db, szam 1 és 90 kozott)!: ");
        int[] tombTipp = new int[5];
        tombTipp[0] = -1;
        tombTipp[1] = -2;
        tombTipp[2] = -3;
        tombTipp[3] = -4;
        tombTipp[4] = -5;

        int tombTippIndexSzamlalo = 0;
        boolean rosszAdat = false;
        String stringTipp = new String();

        for (int i = 1; i <= 5; i++) {
            System.out.println("Kerem, adja meg a(z) " + i + ". tippet!");
            do {
                int seged = 0;
                rosszAdat = false;
                do {
                    rosszAdat = false;
                    Scanner in = new Scanner(System.in);
                    stringTipp = in.nextLine();
                    char karakter = stringTipp.charAt(0);
                    for (int j = 0; j < stringTipp.length(); j++) {
                        karakter = stringTipp.charAt(j);
                        if (karakter != '0'
                                && karakter != '1'
                                && karakter != '2'
                                && karakter != '3'
                                && karakter != '4'
                                && karakter != '5'
                                && karakter != '6'
                                && karakter != '7'
                                && karakter != '8'
                                && karakter != '9') {
                            rosszAdat = true;
                            break;
                        }
                    }
                    if (rosszAdat == true) {
                        System.out.println("Kerem, hogy csak 1 és 90 kozotti egesz szamokat adjon meg!");

                    }
                } while (rosszAdat == true);

                seged = Integer.parseInt(stringTipp);
                if (seged < 1 || seged > 90) {
                    rosszAdat = true;
                }
                if (rosszAdat == true) {
                    System.out.println("Kerem, hogy csak 1 és 90 kozotti egesz szamokat adjon meg!");
                }

                tombTipp[tombTippIndexSzamlalo] = seged;
                label:
                for (int k = 0; k <= tombTippIndexSzamlalo; k++) {
                    for (int l = 1; l <= tombTippIndexSzamlalo; l++) {
                        if (k != l && tombTipp[k] == tombTipp[l]) {
                            rosszAdat = true;
                            System.out.println("Ez a tipp mar letezik! Egy szamot csak egyszer tippelhet");
                            break label;
                        }
                    }
                }

            } while (rosszAdat == true);

            tombTipp[tombTippIndexSzamlalo] = Integer.parseInt(stringTipp);
            tombTippIndexSzamlalo++;
        }

        System.out.println();
        System.out.println("Az On tippei (novekvo sorrendben):");

        rendezett = false;
        while (!rendezett) {
            rendezett = true;
            for (int j = 1; j < tombTipp.length; j++) {
                if (tombTipp[j - 1] > tombTipp[j]) {
                    int seged = tombTipp[j - 1];
                    tombTipp[j - 1] = tombTipp[j];
                    tombTipp[j] = seged;
                    rendezett = false;
                }
            }
        }

        for (int i = 0; i < tombTipp.length; i++) {
            System.out.print(tombTipp[i] + " ");
        }

        System.out.println();
        System.out.println("\nA Lotto huzas szamai(novekvo sorrend):");
        for (int i = 0; i < tombVeletlenSzam.length; i++) {
            System.out.print(tombVeletlenSzam[i] + " ");
        }

        System.out.println();
        int talalat = 0;

        for (int i = 0; i < tombTipp.length; i++) {
            for (int j = 0; j < tombVeletlenSzam.length; j++) {
                if (tombTipp[i] == tombVeletlenSzam[j]) {
                    talalat++;
                }
            }
        }
        System.out.println();
        System.out.println("Találatok szama: " + talalat);

        int[] metszet = new int[5];
        int j;
        int db = 0;
        for (int i = 0; i < tombTipp.length; i++) {
            j = 0;
            while (j < 5 && tombTipp[j] != tombVeletlenSzam[i]) {
                j++;
            }
            if (j < 5) {
                metszet[db] = tombTipp[j];
                db++;
            }
        }
        if (db == 0) {
            System.out.println("Sajnos nem jól tippeltel!");
        } else {
            for (int i = 0; i < db; i++) {
                System.out.println("Az eltalalt szam: " + metszet[i] + " ");

            }
            System.out.println("\n");
        }

    }
}

