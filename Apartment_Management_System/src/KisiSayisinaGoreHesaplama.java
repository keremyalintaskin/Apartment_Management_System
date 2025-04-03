class KisiSayisinaGoreHesaplama implements AidatHesaplama {
    private DuyuruSistemi duyuruSistemi;

    public KisiSayisinaGoreHesaplama(DuyuruSistemi duyuruSistemi) {
        this.duyuruSistemi = duyuruSistemi;
    }
    @Override
    public double hesapla(double miktar) {
        int sakinSayisi = duyuruSistemi.getApartmanSakiniSayisi();
        return miktar * sakinSayisi;
    }
}