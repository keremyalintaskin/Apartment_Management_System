class BakimTalebi {
    private String talepDetayi;
    private TalepDurumu durum;
    private boolean tamamlandi;
    public BakimTalebi(String talepDetayi) {
        this.talepDetayi = talepDetayi;
        this.durum = new BeklemedeDurumu();
        this.tamamlandi = false;
    }
    public void setDurum(TalepDurumu durum) {
        this.durum = durum;
    }
    public void ilerlet() {
        if (tamamlandi) {
            System.out.println("Talep zaten tamamlanmış.");
        } else {
            durum.birSonrakiDurum(this);
            if (durum instanceof TamamlandiDurumu) {
                tamamlandi = true;
                System.out.println("Talep tamamlandı.");
            } else {
                System.out.println("Durum ilerledi: " + durum.getDurum());
            }
        }
    }
    public String getDurum() {
        return durum.getDurum();
    }
    public String getTalepDetayi() {
        return talepDetayi;
    }
    public boolean isTamamlandi() {
        return tamamlandi;
    }
}