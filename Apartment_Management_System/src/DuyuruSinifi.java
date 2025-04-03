class DuyuruSinifi extends Duyuru {
    public DuyuruSinifi(DuyuruSistemi duyuruSistemi) {
        super(duyuruSistemi);
    }
    @Override
    public void duyuruGonder(String mesaj) {
        for (Kullanici kullanici : duyuruSistemi.getApartmanSakinleri()) {
            kullanici.bildirimAl(mesaj);
        }
    }
}