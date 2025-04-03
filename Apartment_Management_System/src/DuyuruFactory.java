class DuyuruFactory {
    public static Duyuru createDuyuru(DuyuruSistemi duyuruSistemi) {
        return new DuyuruSinifi(duyuruSistemi);
    }
}