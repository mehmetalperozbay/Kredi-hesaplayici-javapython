import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {


        Scanner scanner = new Scanner(System.in);
        System.out.print("Kredi Hesaplama Bölümüne Hoşgeldiniz.\nBelirtilen Alana Adınızı Giriniz\nSeçim :  ");
        String ad = scanner.nextLine();

        System.out.print("Belirtilen Alana Yaşınızı Giriniz (18 yaşın altındakiler kredi çekemez)\nSeçim :  ");
        int yas = scanner.nextInt();

        if (yas >= 18) {

            System.out.print("Kaç TL Kredi çekmek istediğinizi yazınız\nSeçim :  ");
            int kredi = scanner.nextInt();
            
            clear();
            
            Thread.sleep(500);
            
            System.out.println("1 - AKBANK (Faiz ==> %3.99)");
            System.out.println("2 - QNB FİNANSBANK (Faiz ==> %6.93) ");
            System.out.println("3 - ZİRAATBANK (Faiz %4.4)");
            System.out.println("4 - INGBANK (Faiz ==> %3.99)");
            System.out.println("5 - İSLAMBANK (Faiz ==> %0");
            System.out.println("\n Banka Seçiniz. (çekmek istediğiniz kredi miktarı:  " + kredi + ")");
            System.out.println("\n\n");
            
            System.out.print("Seçim :  ");
            int secim = scanner.nextInt();

            double faiz = 0;

            if (secim == 1 ){
                faiz = 3.99;
            }else if (secim == 2){
                faiz = 6.93;
            }else if ( secim == 3){
                faiz = 4.4;
            }else if (secim == 4){
                faiz = 3.99;
            }else if (secim == 5){
                faiz = 0;
            }else{
                System.out.println("Yanlış bir seçim yaptınız. Program kapatılıyor.");
                scanner.close();
                return;
            }
            System.out.print("Vadelerimiz 1 - 36 Ay Arasındadır. Ödeme planınıza Uygun Bir Vade Seçiniz.\nSeçim :  ");
            int vade = scanner.nextInt();
            scanner.close();
            if (vade >= 1 && vade <= 36) {

                

                try (FileWriter dosyayazıcı = new FileWriter("data.json")) {

                    String jsondata = "{\n" +
                    "  \"ad\": \"" + ad + "\",\n" + 
                    "  \"kredi\": " + kredi + ",\n" + 
                    "  \"vade\": " + vade + ",\n" + 
                    "  \"faiz\": " + faiz + "\n" + 
                    "}";
                    dosyayazıcı.write(jsondata);

                    ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "python", "Main.py");
                    pb.start();
                    
                    
                } catch (IOException e) {
                    System.out.println(e);
                }

            } else {
                System.out.println("Vadeler 1 ile 36 arasındadır. yanlış giriş yaptınız");
            }


        } else {
            System.out.println("Kredi çekmek için yaşınızın 18'in üzerinde olması gerekir.");
        }


    }
    public static void clear() throws IOException{
        try {
            ProcessBuilder clearing = new ProcessBuilder("cmd","/c","cls");
            clearing.inheritIO().start();
            
        } catch (IOException e) {
            System.out.println("Dosya çalıştırılırken sorun oluştu. " + e.getMessage());
        }
       
    }

}
