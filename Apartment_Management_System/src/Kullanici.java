abstract class Kullanici {
    protected String adSoyad;
    public Kullanici(String adSoyad) {
        this.adSoyad = adSoyad;
    }
    public String getAdSoyad() {
        return adSoyad;
    }
    public abstract void bildirimAl(String mesaj);
}