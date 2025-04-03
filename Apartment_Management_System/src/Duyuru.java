abstract class Duyuru {
    protected DuyuruSistemi duyuruSistemi;
    public Duyuru(DuyuruSistemi duyuruSistemi) {
        this.duyuruSistemi = duyuruSistemi;
    }
    public abstract void duyuruGonder(String mesaj);
}