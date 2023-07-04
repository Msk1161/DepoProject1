package Proje01_Group;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Metodlar extends Urunler{
    Scanner scan = new Scanner(System.in);
    List<String> urunIsmiList = new ArrayList<>();
    List<String> ureticiList = new ArrayList<>();
    List<String> birimList = new ArrayList<>();
    List<Double> miktarList = new ArrayList<>();
    List<Integer> idList = new ArrayList<>();
    List<String> rafList = new ArrayList<>();

    int urunIndex;
    String stringId;
    int urunId;
    Urunler sl1 =new Urunler();
    public void menu() {
        String  menuSecim;
        System.out.println("|| ========= ANA MENU ========== ||");
        System.out.println("|| Urun Tanimlama           => 1 ||");
        System.out.println("|| Urun Listeleme           => 2 ||");
        System.out.println("|| Urun Girisi              => 3 ||");
        System.out.println("|| Urun Cikisi              => 4 ||");
        System.out.println("|| Urun Sil                 => 5 ||");
        System.out.println("|| Silinen Urunleri Yazdir  => 6 ||");
        System.out.println("|| Menu Cikisi              => 7 ||");
        System.out.println("|| ============================= ||");
        System.out.print("\u001B[34mLutfen seciminiz icin bir numara giriniz: \u001B[0m");
        menuSecim = scan.next().substring(0,1);
        switch (menuSecim) {
            case "1":
                urunTanimla();
                break;
            case "2":
                urunListele();
                break;
            case "3":
                urunGirisi();
                break;
            case "4":
                double toplam=0;
                for (double w:miktarList) {
                    toplam = toplam+w;
                }if (toplam==0){
                System.out.println("\u001B[31mUyari! Henuz urun girisi yapilmamistir. Lutfen urun girisi yapiniz.\u001B[0m");
                menuSor();
            }else {
                urunCikisi();
            }
                break;
            case "5":
                urunSil();
                break;
            case "6":
                silinenUrunleriYazdir();
                break;
            case "7":
                menuCikis();
                break;
            default:
                System.out.println("\u001B[31mHatali Giris! Lutfen gecerli giris yapiniz.\u001B[0m");
                menu(); }
    }
    private void urunTanimla() {
        System.out.println("Urun Tanimla  ==>");
        System.out.print("\u001B[34mUrun ismini giriniz: \u001B[0m");
        setUrunIsmi(scan.next());
        urunIsmiList.add(getUrunIsmi());
        idList.add(getId());
        setId(getId() + 1);
        System.out.print("\u001B[34mLutfen uretici adini giriniz: \u001B[0m");
        setUretici(scan.next());
        ureticiList.add(getUretici());
        birimsec();
        miktarList.add(getMiktar());
        rafList.add(getRaf());
        urunListeleCagir();
        menuSor();
    }
    private void birimsec() {
        System.out.print("\u001B[34mLutfen urunun birimini giriniz: \u001B[0m");
        setBirim(scan.next());
        birimList.add(getBirim());
    }
    private void urunListeleCagir() {
        System.out.println("  Id     Urun Adi    Uretici    Miktar     Birim      Raf");
        System.out.println("------  ----------  ---------  --------   -------    -----");
        for (int i = 0; i < urunIsmiList.size(); i++) {
            System.out.printf("%5d    %-11s %-10s %-10.2f %-9s %5s",
                    idList.get(i), urunIsmiList.get(i), ureticiList.get(i), miktarList.get(i), birimList.get(i), rafList.get(i));
            System.out.println();
        }
    }

    private void menuSor(){
        System.out.println("\u001B[34mAna menu icin =====> A - Cikmak icin =====> Q giriniz.\u001B[0m");
        char ch = scan.next().toUpperCase().charAt(0);
        if (ch=='A'){
            menu();
        }else if (ch=='Q'){
            menuCikis();
        }else {
            System.out.println("\u001B[31mGecersiz giris yaptiniz. Lutfen tekrar deneyiniz.\u001B[0m");
            menuSor();
        }
    }
    private void urunListele() {
        System.out.println("Urun Listele ==>");
        System.out.println("  Id     Urun Adi    Uretici    Miktar     Birim      Raf");
        System.out.println("------  ----------  ---------  --------   -------    -----");
        for (int i = 0; i < urunIsmiList.size(); i++) {
            System.out.printf("%5d    %-11s %-10s %-10.2f %-9s %5s",
                    idList.get(i), urunIsmiList.get(i), ureticiList.get(i), miktarList.get(i), birimList.get(i), rafList.get(i));
            System.out.println();
        } menuSor();
    }
    private void urunGirisi() {
        urunListeleCagir();
        System.out.println("Urun Girisi  ==>");
        idKontrol();
        rafaKoy(urunIndex);
        System.out.print("\u001B[34mGiris yapacaginiz '" +urunIsmiList.get(urunIndex)+ "' miktarini giriniz: \u001B[0m");
        double urunMiktari=scan.nextDouble();
        miktarList.set(urunIndex,miktarList.get(urunIndex)+urunMiktari);
        System.out.println("\u001B[34mUrun Girisi  ==> Id: "+idList.get(urunIndex)+"  Urun Miktari: \u001B[0m"+urunMiktari);
        urunListeleCagir();
        menuSor();
    }
    private int idKontrol(){
        System.out.print("\u001B[34mListeye gore islem yapmak istediginiz Urunun Id'sini giriniz : \u001B[0m");
        stringId= scan.next();
        char charId=stringId.charAt(0);

        if((int)charId<49 || (int)charId>57){
            System.out.println("\u001B[31mHatalı giriş! Lutfen listeye gore bir ID deger giriniz.\u001B[0m");
            urunListeleCagir();
            idKontrol();
        }else {
            urunId=Integer.parseInt(stringId);
            if(idList.contains(urunId)){
                urunIndex =idList.indexOf(urunId);
            }else {
                System.out.println("\u001B[31mHatalı giriş! Lutfen listeye gore bir ID deger giriniz.\u001B[0m");
                urunListeleCagir();
                idKontrol();
                    }
                }
        return urunIndex;
    }

    private void rafaKoy(int urunIndex) {
        List<String> raflar= List.of("A100","A200","B100","B200","C100","C200");
        if (rafList.get(urunIndex).equals("-")){
            int i=1;
            System.out.println("Raf Listesi");
            System.out.println("No------Raf ismi----");
            for(String w:raflar) {
                System.out.printf("%-2d %s %-5s ",i," => ", w);
                i++;
                System.out.println();
            }
            System.out.print("\u001B[34mLutfen listeden '"+urunIsmiList.get(urunIndex)+"' icin raf ismini yaziniz: \u001B[0m");
            String raf=scan.next().toUpperCase();
            if(raflar.contains(raf)){
                rafList.set(urunIndex,raf);

            }else {
                System.out.println("\u001B[31mLutfen dogru giris yapiniz.\u001B[0m");
                rafaKoy(urunIndex);
            }
        }
    }

    private void urunCikisi() {
        urunListeleCagir();
        System.out.println("Urun Cikisi  ==>");
        idKontrol();
        if(miktarList.get(urunIndex)==0){
            System.out.println("\u001B[31mUyari! Stokta '" + urunIsmiList.get(urunIndex) + "' bulunmamaktadir. \u001B[0m");
            System.out.println("\u001B[34mCikis yapabilmek icin stokta urun bulunmalidir. \u001B[0m");
            idKontrol();
        }
        System.out.print("\u001B[34mCikis yapacaginiz '" + urunIsmiList.get(urunIndex) + "' miktarini giriniz: \u001B[0m");
        double urunMiktari=scan.nextDouble();

        if (urunMiktari<=miktarList.get(urunIndex)){
            miktarList.set(urunIndex, miktarList.get(urunIndex)-urunMiktari);
            System.out.println("\u001B[32mUrun Cikisi  ==> Id: "+idList.get(urunIndex)+"  Urun Miktari: "+urunMiktari+" "+birimList.get(urunIndex)+ "\u001B[0m");
            if (miktarList.get(urunIndex)==0.00){
                rafList.set(urunIndex, getRaf());
                System.out.println("\u001B[31mStokta '" + urunIsmiList.get(urunIndex) + "' kalmamistir. \u001B[0m");
            }
            urunListeleCagir();
        }else {
            System.out.println("\u001B[31mCikis yapacaginiz miktar stoktan fazla olamaz!\u001B[0m");
            System.out.println("\u001B[31mStokta " + miktarList.get(urunIndex)+" " + birimList.get(urunIndex)+" "+ urunIsmiList.get(urunIndex)+ " var.\u001B[0m");

            urunCikisi();
        }
        menuSor();
    }

    private void urunSil() {
        urunListeleCagir();
        System.out.println("Urun Sil   ==>");
        idKontrol();

        if(miktarList.get(urunIndex)>0){
            System.out.println("\u001B[31mUyari! Stokta " + urunIsmiList.get(urunIndex) + " bulunmaktadir. \u001B[0m");
            System.out.println("\u001B[31mBu urunu tanim listesinden silemezsiniz. \u001B[0m");
            idKontrol();
        }

        System.out.println("\u001B[31mTanim listesinden '" + urunIsmiList.get(urunIndex) + "' silinecektir. \u001B[0m");
        System.out.print("\u001B[34mOnaylamak icin 'E', iptal etmek icin 'H' giriniz : \u001B[0m");
        String onay=scan.next().toUpperCase().substring(0,1);
        if(onay.equals("H")){
            menuSor();
        }else {
            if(rafList.get(urunIndex)=="-"){
                silinenUrunler();
                urunListeleCagir();
                menuSor();
            }else{
                System.out.println("\u001B[31mUyari! Stokta " + urunIsmiList.get(urunIndex) +" bulunmaktadir. Silmek icin stok bos olmalidir. \u001B[0m");
                urunListeleCagir();
                menuSor();
            }
        }

    }
    ArrayList <String> sln= new ArrayList();
        private void silinenUrunler(){
            String silinen ="";
                silinen=silinen + idList.get(urunIndex) +" / ";
                idList.remove(urunIndex);
                silinen=silinen + urunIsmiList.get(urunIndex)+" / ";
                urunIsmiList.remove(urunIndex);
                silinen=silinen + ureticiList.get(urunIndex)+" / ";
                ureticiList.remove(urunIndex);
                miktarList.remove(urunIndex);
                silinen=silinen + birimList.get(urunIndex)+" / ";
                birimList.remove(urunIndex);
                rafList.remove(urunIndex);

            sln.add(silCount,silinen);
            silCount++;

    }
    private void silinenUrunleriYazdir(){
        System.out.println("Silinen Urunler  ==>");
        System.out.println(" Id  / Urun Adi / Uretici / Birim ");
        System.out.println("----- ---------- --------- ------- ");
        for (String w:sln){
            System.out.println(w);
        }
        menuSor();
    }
    private void menuCikis() {
        System.out.println("Listenin son durumu  ==>");
        urunListeleCagir();
        System.out.println("\u001B[31mSistemden cikis yaptiniz.\u001B[0m");
    }
    int silCount=0;

    public void hazirTanimliUrunler(){
        idList.add(1001);   urunIsmiList.add("Un"); ureticiList.add("Telli"); miktarList.add(0.0);birimList.add("Cuval");rafList.add("-");
        idList.add(1002);   urunIsmiList.add("Seker"); ureticiList.add("Beyaz"); miktarList.add(0.0);birimList.add("Koli");rafList.add("-");
        idList.add(1003);   urunIsmiList.add("Cay"); ureticiList.add("Rize"); miktarList.add(0.0);birimList.add("Paket");rafList.add("-");
        idList.add(1004);   urunIsmiList.add("Peynir"); ureticiList.add("Sutas"); miktarList.add(0.0);birimList.add("Teneke");rafList.add("-");
        idList.add(1005);   urunIsmiList.add("Zeytin"); ureticiList.add("Marin"); miktarList.add(0.0);birimList.add("Bidon");rafList.add("-");
        int maxId= getId();
        for(Integer w: idList){
            maxId = Math.max(w, maxId);
        }
        setId(maxId+1);
    }

}