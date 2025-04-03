import java.util.ArrayList;
import java.util.List;

class DuyuruSistemi {
    private List<KullaniciSinifi> apartmanSakinleri = new ArrayList<>();
    public DuyuruSistemi() {
        apartmanSakinleri.add(new KullaniciSinifi("Kerem Yalın Taşkın"));
        apartmanSakinleri.add(new KullaniciSinifi("Mehmet Koyuncuoğlu"));
        apartmanSakinleri.add(new KullaniciSinifi("Emre Ünsal"));
    }
    public List<KullaniciSinifi> getApartmanSakinleri() {
        return apartmanSakinleri;
    }
    public int getApartmanSakiniSayisi() {
        return apartmanSakinleri.size();
    }
}