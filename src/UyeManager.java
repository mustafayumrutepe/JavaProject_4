import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class UyeManager extends Veritabani {
    static Scanner scan = new Scanner(System.in);

    public static void uyeMenu() throws InterruptedException {
        String tercih = "";
        do {

            System.out.println(
                    "\n========== TECHNO STUDY CONFLUENCE =========\n" +
                            "================= UYE MENU =================\n" +
                            "\n" +
                            "\t   1- Uye Listesi Yazdir\t\n" +
                            "\t   2- Soyisimden Uye Bulma\n" +
                            "\t   3- Sehire Gore Uye Bulma\n" +
                            "\t   4- Bilgilerini Girerek Uye Ekleme\n" +
                            "\t   5- Kimlik No Ile Kayit Silme \t\n" +
                            "\t   A- ANAMENU\n" +
                            "\t   Q- CIKIS\n");
            tercih = scan.nextLine();

            switch (tercih) {

                case "1": // Uye Listesi Yazdir
                    uyeListesiYazdir();
                    break;
                case "2": // Soyisimden Uye Bulma
                    soyisimdenUyeBulma();
                    break;
                case "3": // Sehre Gore Uye Bulma
                    sehreGoreUyeBulma();
                    break;
                case "4": // Bilgilerini Girerek Uye Ekleme
                    uyeEkleme();
                    break;
                case "5": // Kimlik No Ile Kayit Silme
                    tcNoIleUyeSil();
                    break;
                case "a":
                case "A":
                    Helper.anaMenu();
                    break;
                case "q":
                case "Q":
                    break;
                default:
                    System.out.println("Lutfen gecerli tercih yapiniz: ");
            }

        } while (!tercih.equalsIgnoreCase("q"));
        Helper.projeDurdur();
    }

    public static void tcNoIleUyeSil() throws InterruptedException {
        System.out.println("Silinecek uyeye ait kimlik no giriniz: ");
        String silinecekUye = scan.nextLine();

        String silinecekValue = uyelerMap.get(silinecekUye);
        String sonucValue = uyelerMap.remove(silinecekUye);

        System.out.print(silinecekUye + " Siliniyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }

        try {
            boolean sonuc = sonucValue.equals(silinecekValue);
        } catch (Exception e) {
            System.out.println("Istediginiz Tc numarasi ile uye bulunamadi.");
        }
    }

    public static void uyeEkleme() {
        System.out.println("Tc no: ");
        String tcNo = scan.nextLine();
        System.out.println("Isim: ");
        String isim = scan.nextLine();
        System.out.println("Soyisim: ");
        String soyisim = scan.nextLine();
        System.out.println("Sehir: ");
        String sehir = scan.nextLine();
        System.out.println("Dogum Yili: ");
        String dYili = scan.nextLine();

        String eklenecekValue = isim + ", " + soyisim + ", " + sehir + ", " + dYili;
        uyelerMap.put(tcNo, eklenecekValue);
    }

    public static void sehreGoreUyeBulma() throws InterruptedException {
        System.out.println("Aradiginiz Uyenin Sehrini Giriniz:");
        String istenenSehir = scan.nextLine();

        System.out.print(istenenSehir + " Uyeleri Listeleniyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }

        Set<Map.Entry<String, String>> uyelerEntrySet = uyelerMap.entrySet();
        System.out.println(
                      "\n============= TECHNO STUDY CONFLUENCE =============\n" +
                        "=============== SEHIR ILE UYE ARAMA ===============\n" +
                        "TcNo : Isim , Soyisim , Sehir, D.Yili");

        // Daha düzgün bi görünüm için printf veya String.format kullanılabilir...
        for (Map.Entry<String, String> each : uyelerEntrySet) {
            String eachKey = each.getKey();
            String eachValue = each.getValue();

            String[] eachValuarr = eachValue.split(", ");
            if (eachValuarr[2].toLowerCase().contains(istenenSehir.toLowerCase())) {
                System.out.println(eachKey + " : " + eachValue + " | ");
            }
        }
    }

    public static void soyisimdenUyeBulma() throws InterruptedException {
        System.out.println("Aradiginiz uyenin soyisminin tamamini ya da birkismini giriniz: ");
        String istenenSoyisim = scan.nextLine();

        Set<Map.Entry<String, String>> uyelerEntrySet = uyelerMap.entrySet();

        System.out.print(istenenSoyisim + " Araniyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }

        System.out.println(
                      "\n========== TECHNO STUDY BOOTCAMP ===========\n" +
                        "=========== SOYISIM ILE UYE ARAMA ==========\n" +
                        "TcNo : Isim , Soyisim , Sehir , D.Yili");

        // printf veya String.format metodları kullanılarak daha düzgün bi çıktı elde edilebilir. Şart değil, isteğe bağlı.
        for (Map.Entry<String, String> each : uyelerEntrySet) {
            String eachKey = each.getKey();
            String eachValue = each.getValue();
            String[] eachValuarr = eachValue.split(", ");

            // Soyisminin bi kısmı girilse bile içeren öğeler ekrana basılır...
            if (eachValuarr[1].toLowerCase().contains(istenenSoyisim.toLowerCase())) {
                System.out.println(eachKey + " : " + eachValue + " | ");
            }
        }
    }

    public static void uyeListesiYazdir() throws InterruptedException {

        Set<Map.Entry<String, String>> uyelerEntrySet = uyelerMap.entrySet();

        System.out.print("Uye Listesi yazdiriliyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }

        System.out.println(
                      "\n========== TECHNO STUDY CONFLUENCE =========\n" +
                        "=============== UYE LISTESI ================\n" +
                        "TcNo : Isim , Soyisim , Sehir , D.Yili");

        // Daha düzgün bi görünüm için printf veya String.format kullanılabilir...
        for (Map.Entry<String, String> each : uyelerEntrySet) {
            String eachKey = each.getKey();
            String eachValue = each.getValue();

            System.out.println(eachKey + " : " + eachValue + " | ");
        }
    }


}
