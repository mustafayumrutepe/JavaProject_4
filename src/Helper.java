import java.util.Scanner;

public class Helper {
    static Scanner scan = new Scanner(System.in);

    private static String loggedInUsername;
    private static UserRole loggedInUserRole;

    private static void login() {
        if (loggedInUserRole == null)
            while (true) {
                System.out.println("Kullanici Adi: ");
                String username = scan.nextLine();
                System.out.println("Sifre: ");
                String password = scan.nextLine();

                if (username.equals("admin") && password.equals("Admin123")) {
                    loggedInUserRole = UserRole.ADMIN;
                    System.out.println("Admin olarak giris yapildi!");
                    return; // login() fonksiyonundan çıkış yap
                } else if (username.equals("user") && password.equals("User123")) {
                    loggedInUserRole = UserRole.USER;
                    System.out.println("Standart kullanici olarak giris yapildi!");
                    return; // login() fonksiyonundan çıkış yap
                } else {
                    loggedInUserRole = null;
                    System.out.println("Gecersiz kullanici adi veya sifre!");
                }
            }
    }

    public static void anaMenu() throws InterruptedException {

        login(); // Kullanıcı girişi yapılıyor
        if (loggedInUserRole == UserRole.ADMIN) {
            showAdminMenu();
        } else if (loggedInUserRole == UserRole.USER) {
            showUserMenu();
        }

        String tercih = "";
        do {
            System.out.println(
                    "\n=========== TECHNO STUDY CONFLUENCE =============\n" +
                            "=================== ANA MENU ====================\n" +
                            "\n" +
                            "\t   1- Kutuphane Bilgileri Goruntule\n" +
                            "\t   2- Uyeler Menu\n" +
                            "\t   3- Kitaplar Menu\n" +
                            "\t   Q- CIKIS\n");
            System.out.print("Lutfen Menuden tercihinizi yapiniz:");

            tercih = scan.nextLine().toLowerCase();

            switch (tercih) {
                case "1":
                    kutuphaneBilgileriniYazdir();
                    break;
                case "2":
                    loginAndShowUserMenu(UserRole.ADMIN);
                    break;
                case "3":
                    loginAndShowUserMenu(UserRole.ADMIN);
                    break;
                case "q":
                    break;
                default:
                    System.out.print("Lutfen gecerli bir tercih giriniz:");
            }

        } while (!tercih.equalsIgnoreCase("q"));

        projeDurdur();
    }

    public static void kutuphaneBilgileriniYazdir() throws InterruptedException {

        System.out.print("Kutuphane bilgileri yazdiriliyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }

        System.out.println("\n" +
                "\n============= TECHNO STUDY CONFLUENCE =============\n" +
                "\t\t Kutuphane : " + Kutuphane.kutuphaneIsim +
                "\n\t\t Adres : " + Kutuphane.adres +
                "\n\t\t Telefon : " + Kutuphane.telefon);

    }

    private static void loginAndShowUserMenu(UserRole requiredRole) throws InterruptedException {
        System.out.print("Kullanici Adi: ");
        String username = scan.nextLine();
        System.out.print("Sifre: ");
        String password = scan.nextLine();

        UserRole role = authenticateUser(username, password);

        if (role == null) {
            System.out.println("Gecersiz kullanici adi veya sifre.");
        } else if (role == requiredRole || role == UserRole.ADMIN) {
            if (requiredRole == UserRole.ADMIN) {
                showAdminMenu();
            } else {
                showUserMenu();
            }
        } else {
            System.out.println("Bu sayfaya erisim izniniz yok.");
        }
    }

    private static UserRole authenticateUser(String username, String password) {
        if ("admin".equalsIgnoreCase(username) && "Admin123".equals(password)) {
            return UserRole.ADMIN;
        } else if ("uye".equalsIgnoreCase(username) && "Uye123".equals(password)) {
            return UserRole.USER;
        }
        return null;
    }

    private static void showAdminMenu() throws InterruptedException {
        System.out.println(
                "\n=========== TECHNO STUDY CONFLUENCE ==========\n" +
                        "================== ADMIN MENU ================\n" +
                        "\n" +
                        "\t   1- Uyeler Menu\n" +
                        "\t   2- Kitaplar Menu\n" +
                        "\t   A- ANAMENU\n" +
                        "\t   Q- CIKIS");
        String tercih = scan.nextLine().toLowerCase();
        switch (tercih) {
            case "1":
                UyeManager.uyeMenu();
                break;
            case "2":
                KitapManager.kitapMenu();
                break;
            case "a":
                anaMenu();
                break;
            case "q":
                projeDurdur();
                break;
            default:
                System.out.println("Lutfen gecerli bir tercih giriniz");
        }
    }

    private static void showUserMenu() throws InterruptedException {
        String tercih = "";
        do {
            System.out.println(
                          "\n========== TECHNO STUDY CONFLUENCE ===========\n" +
                            "================== USER MENU =================\n" +
                            "\n" +
                            "\t   1- Uyeleri Listele\n" +
                            "\t   2- Soyisimden Uye Bulma\n" +
                            "\t   3- Sehire Gore Uye Bulma\n" +
                            "\t   4- Kitaplari Listele\n" +
                            "\t   5- Yazardan Kitap Bulma\n" +
                            "\t   6- Kitap Turu veya Yayin Tarihi Ile Kitap Bulma\n" +
                            "\t   7- Kitap Odunc Al\n" +
                            "\t   8- Kitap Iade Et\n" +
                            "\t   A- ANAMENU\n" +
                            "\t   Q- CIKIS");
            tercih = scan.nextLine().toLowerCase();

            switch (tercih) {
                case "1":
                    UyeManager.uyeListesiYazdir();
                    break;
                case "2":
                    UyeManager.soyisimdenUyeBulma();
                    break;
                case "3":
                    UyeManager.sehreGoreUyeBulma();
                    break;
                case "4":
                    KitapManager.kitapListesiYazdir();
                    break;
                case "5":
                    KitapManager.yazardanKitapBulma();
                    break;
                case "6":
                    KitapManager.turVeyaYayinTarihiIleKitapBulma();
                    break;
                case "7":
                    KitapManager.kitapOduncAl();
                    break;
                case "8":
                    KitapManager.kitapIadeEt();
                    break;
                case "a":
                    anaMenu();
                    break;
                case "q":
                    projeDurdur();
                    break;
                default:
                    System.out.println("Lutfen gecerli bir tercih giriniz");
            }
        } while (!tercih.equalsIgnoreCase("Q"));
    }

    public static void projeDurdur() {
        System.out.println("Kutuphane projesinden ciktiniz");
        System.exit(0);
    }
}

