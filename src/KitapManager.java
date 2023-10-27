import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class KitapManager extends Veritabani{

    static Scanner scan = new Scanner(System.in);

    public static void kitapMenu() throws InterruptedException {
        String tercih = "";
        do {
            System.out.println(
                          "\n============ TECHNO STUDY BOOTCAMP ============\n" +
                            "================== KITAP MENU =================\n" +
                            "\t   1- Kitap Listesi Yazdir\n" +
                            "\t   2- Yazardan Kitap Bulma\n" +
                            "\t   3- Kitap Turu veya Yayin Tarihi Ile Kitap Bulma\n" +
                            "\t   4- Bilgilerini Girerek Kitap Ekleme\n" +
                            "\t   5- Kitap Ismi Ile Kayit Silme \t\n" +
                            "\t   6- Kitap Odunc Al \t\n" +
                            "\t   7- Kitap Iade Et \t\n" +
                            "\t   A- ANAMENU\n" +
                            "\t   Q- CIKIS");
            tercih = scan.nextLine().toLowerCase();
            switch (tercih) {
                case "1":
                    kitapListesiYazdir();
                    break;
                case "2": // Yazar Ismiyle Kitap Bulma
                    yazardanKitapBulma();
                    break;
                case "3": // Kitap Turu veya Yayin Tarihi Ile Kitap Bulma
                    turVeyaYayinTarihiIleKitapBulma();
                    break;
                case "4": // Bilgilerini Girerek Kitap Ekleme
                    kitapEkle();
                    break;
                case "5":
                    isimIleKitapSilme();
                    break;
                case "6":
                    kitapOduncAl();
                    break;
                case "7":
                    kitapIadeEt();
                    break;
                case "a":
                    Helper.anaMenu();
                    break;
                case "q":
                    Helper.projeDurdur();
                    break;
                default:
                    System.out.println("Lutfen gecerli bir tercih giriniz");
            }


        } while (!tercih.equalsIgnoreCase("q"));
        Helper.projeDurdur();

    }

    public static void kitapOduncAl() {
        System.out.println("Odunc almak istediginiz kitabin ismini giriniz: ");
        String kitapIsmi = scan.nextLine();

        if (kitaplarMap.containsKey(kitapIsmi)) {
            String kitapBilgisi = kitaplarMap.get(kitapIsmi);
            oduncAlinanKitaplarMap.put(kitapIsmi, kitapBilgisi);
            kitaplarMap.remove(kitapIsmi);
            System.out.println(kitapIsmi + " adli kitap odunc alindi.");
        } else {
            System.out.println("Belirtilen isimde bir kitap bulunamadi.");
        }
    }


    public static void kitapIadeEt() {
        System.out.println("Iade etmek istediginiz kitabin ismini giriniz: ");
        String kitapIsmi = scan.nextLine();

        if (oduncAlinanKitaplarMap.containsKey(kitapIsmi)) {
            String kitapBilgisi = oduncAlinanKitaplarMap.get(kitapIsmi);
            kitaplarMap.put(kitapIsmi, kitapBilgisi);
            oduncAlinanKitaplarMap.remove(kitapIsmi);
            System.out.println(kitapIsmi + " adli kitap iade edildi.");
        } else {
            System.out.println("Belirtilen isimde odunc alinmis bir kitap bulunamadi.");
        }
    }


    private static void isimIleKitapSilme() throws InterruptedException {
        System.out.println("Silinecek kitabin ismini giriniz");
        String silinecekKitap = scan.nextLine();

        String silinecekValue = kitaplarMap.get(silinecekKitap);
        String sonucValue = kitaplarMap.remove(silinecekKitap);

        System.out.print(silinecekKitap + " Siliniyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }

        //////////////////////////////////////////////////////////////////////////////////////////////////
        try {
            boolean sonuc = sonucValue.equals(silinecekValue);
        } catch (Exception e) {
            System.out.println("Istediginiz kitap ismi bulunamadi");
        }/////////////////////////////////////////////////////////////////////////////////////////////////

    }

    private static void kitapEkle() {
        System.out.println("Kitap Adini Giriniz: ");
        String kitapAdi = scan.nextLine();
        System.out.println("Yazar Adini Giriniz: ");
        String yazarAdi = scan.nextLine();
        KitapTuru kitapTuru = null;
        while (true) {
            try {
                System.out.print("Kitap Turu: ");
                kitapTuru = KitapTuru.valueOf(scan.nextLine().toUpperCase());
                break; // Eğer giriş başarılı ise döngüden çık.
            } catch (Exception e) {
                System.out.println("Hatali giris! Lutfen kitap turunu tekrar giriniz: ");
            }
        }
        // kitapTuru değişkeni artık geçerli bir değere sahiptir.
        // Diğer işlemlere devam edebilirsiniz.
        System.out.println("Yayinlanma Tarihi: ");
        String yayinTarihi = scan.nextLine();

        //"A Tale of Two Cities", "Charles Dickens, Tarih, 1859"

        String key = kitapAdi;
        String value = yazarAdi + ", " + kitapTuru.name() + ", " + yayinTarihi;
        kitaplarMap.put(key, value);
    }

    public static void turVeyaYayinTarihiIleKitapBulma() throws InterruptedException {
        Set<Map.Entry<String, String>> myEntrySet = kitaplarMap.entrySet();

        System.out.println("Istediginiz kitabin turunu yaziniz: ");
        System.out.println("(Tarih, Polisiye, Kurgu, Roman, Destan)");
        String istenilenKitapTuru = scan.nextLine();
        System.out.println("Istediginiz yayinlanma tarihini yaziniz: ");
        String istenilenYayinTarihi = scan.nextLine();

        System.out.print(istenilenKitapTuru + "," + istenilenYayinTarihi + " Taraniyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }
        System.out.println(
                      "\n============ TECHNO STUDY CONFLUENCE ==========\n" +
                        "================= KITAP LISTESI ===============\n" +
                        "Kitap Ismi    :   Yazar Ismi , Kitap Turu , Yayin Yili");

        for (Map.Entry<String, String> each : myEntrySet)
        {
            String eachKey = each.getKey();
            String eachValue = each.getValue();
            String[] eachValueArr = eachValue.split(", ");
            if (istenilenKitapTuru.equalsIgnoreCase(eachValueArr[1]) || istenilenYayinTarihi.equalsIgnoreCase(eachValueArr[2]))
                System.out.println(eachKey + " : " + eachValue + " | ");
        }
    }


    public static void yazardanKitapBulma() throws InterruptedException {
        Set<Map.Entry<String, String>> myEntrySet = kitaplarMap.entrySet();

        System.out.println("Istediginiz yazar ismini yaziniz: ");
        String istanilenYazarIsmi = scan.nextLine();

        System.out.print(istanilenYazarIsmi + " Araniyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }

        System.out.println(
                      "\n============ TECHNO STUDY CONFLUENCE ==========\n" +
                        "================= KITAP LISTESI ===============\n" +
                        "Kitap Ismi    :   Yazar Ismi , Kitap Turu , Yayin Yili");

        // printf veya String.format metodları kullanılarak daha düzgün bi çıktı elde edilebilir. Şart değil, isteğe bağlı.
        for (Map.Entry<String, String> each : myEntrySet) {
            String eachKey = each.getKey();
            String eachValue = each.getValue();
            String[] eachValueArr = eachValue.split(", ");

            // Yazarın isminin bi kısmı girilse bile içeren öğeler ekrana basılır...
            if ((eachValueArr[0].toLowerCase()).contains(istanilenYazarIsmi.toLowerCase())) {
                System.out.println(eachKey + "  : " + eachValue);
            }
        }
    }

    public static void kitapListesiYazdir() throws InterruptedException {

        Set<Map.Entry<String, String>> myEntrySet = kitaplarMap.entrySet();

        System.out.print("Kayitli kitaplar yazdiriliyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }

        System.out.println(
                "\n============ TECHNO STUDY CONFLUENCE ==========\n" +
                        "================= KITAP LISTESI ===============\n" +
                        "Kitap Ismi    :   Yazar Ismi , Kitap Turu , Yayin Yili");

        for (Map.Entry<String, String> each : myEntrySet) {
            String eachKey = each.getKey();
            String eachValue = each.getValue();

            System.out.println(eachKey + " : " + eachValue + " | ");
        }
    }
}
