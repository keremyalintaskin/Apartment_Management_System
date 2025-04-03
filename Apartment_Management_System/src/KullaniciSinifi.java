class KullaniciSinifi extends Kullanici {
    public KullaniciSinifi(String adSoyad) {
        super(adSoyad);
    }@Override
    public void bildirimAl(String mesaj) {
        System.out.println(adSoyad + " duyuruyu aldı: " + mesaj);
    }
}