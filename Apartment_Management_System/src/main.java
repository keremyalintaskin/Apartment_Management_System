import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class main extends JFrame {
    DuyuruSistemi duyuruSistemi = new DuyuruSistemi();
    private double[] aidatBorclari = new double[3];

    private List<BakimTalebi> bakimTalepleri = new ArrayList<>();

    public main() {
        setTitle("Apartman Yönetim Sistemi");
        setSize(700, 500); //boyutu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //kapatma tuşu
        setLocationRelativeTo(null); //pencerenin nerede açılcağı
        setLayout(new BorderLayout());

        Random random = new Random();
        for (int i = 0; i < aidatBorclari.length; i++) {
            aidatBorclari[i] = 100 + random.nextInt(501); //100 ile 500 arasında aidat borcu çıktısı
        }

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(5, 1, 10, 10));
        menuPanel.setPreferredSize(new Dimension(200, getHeight())); //arayüz düzeni

        JButton duyuruGonderButton = new JButton("Apartman sakinlerine duyuru geç");
        JButton aidatGoruntuleButton = new JButton("Apartman sakinlerinin aidat borçlarını görüntüle");
        JButton aidatHesaplamaButton = new JButton("Daire başına alınan aidat(Aylık toplam aidat hesaplama)");
        JButton bakimTalebiButton = new JButton("Bakım talebi oluştur");
        JButton cikisButton = new JButton("Çıkış");

        duyuruGonderButton.setBackground(Color.GREEN); //tuş renkleri
        aidatGoruntuleButton.setBackground(Color.BLUE);
        aidatHesaplamaButton.setBackground(Color.RED);
        bakimTalebiButton.setBackground(Color.WHITE);
        cikisButton.setBackground(Color.ORANGE);

        duyuruGonderButton.setPreferredSize(new Dimension(180, 50)); //tuşların boyutu
        aidatGoruntuleButton.setPreferredSize(new Dimension(180, 50));
        aidatHesaplamaButton.setPreferredSize(new Dimension(180, 50));
        bakimTalebiButton.setPreferredSize(new Dimension(180, 50));
        cikisButton.setPreferredSize(new Dimension(180, 50));

        menuPanel.add(duyuruGonderButton);
        menuPanel.add(aidatGoruntuleButton);
        menuPanel.add(aidatHesaplamaButton);
        menuPanel.add(bakimTalebiButton);
        menuPanel.add(cikisButton);

        add(menuPanel, BorderLayout.WEST);

        JTextArea sonucArea = new JTextArea();
        sonucArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(sonucArea);
        add(scrollPane, BorderLayout.CENTER); //sonuç alanları ve text alanı

        duyuruGonderButton.addActionListener(new ActionListener() { //tuşların tıklanma durumu
            @Override
            public void actionPerformed(ActionEvent e) {
                String duyuru = JOptionPane.showInputDialog("Yapmak istediğiniz duyuruyu giriniz:"); // yeni duyuru tuşu basılırsa
                if (duyuru != null && !duyuru.trim().isEmpty()) {
                    Duyuru duyuruSinifi = DuyuruFactory.createDuyuru(duyuruSistemi);
                    duyuruSinifi.duyuruGonder(duyuru);

                    for (KullaniciSinifi kullanici : duyuruSistemi.getApartmanSakinleri()) {
                        sonucArea.append(kullanici.getAdSoyad() + " duyuruyu aldı: " + duyuru + "\n"); //yeni duyuru gönderilirse
                    }
                }
            }
        });

        aidatGoruntuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sonucArea.append("Apartman sakinlerinin aidat borçları:\n"); //aidat borçları görünmek istenirse
                for (int i = 0; i < duyuruSistemi.getApartmanSakinleri().size(); i++) {
                    String sakinAdSoyad = duyuruSistemi.getApartmanSakinleri().get(i).getAdSoyad();
                    double borc = aidatBorclari[i];
                    sonucArea.append(sakinAdSoyad + ": " + borc + " TL\n"); //borçların tutarı
                }
            }
        });

        aidatHesaplamaButton.addActionListener(new ActionListener() { //daire başı aidat tutarı istenirse
            @Override
            public void actionPerformed(ActionEvent e) {
                String miktarStr = JOptionPane.showInputDialog("Aidat miktarını giriniz:"); //tutar girilmek için alan
                try {
                    double miktar = Double.parseDouble(miktarStr);
                    AidatHesaplama hesapla = new KisiSayisinaGoreHesaplama(duyuruSistemi);
                    double sonuc = hesapla.hesapla(miktar);
                    sonucArea.append("Toplam gelen aidat: " + sonuc + "\n");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Geçersiz bir miktar girildi.");//sayısal bir değer girilmezse
                }
            }
        });

        bakimTalebiButton.addActionListener(new ActionListener() { //Arıza bakım tuşuna basılırsa
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Asansör arızası", "Elektrik arızası"}; //iki seçenekten biri seçilir
                int secim = JOptionPane.showOptionDialog(null, "Bir arıza türü seçin", "Arıza Seçimi",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                BakimTalebi talep = null;
                boolean talepVarMi = false;
                for (BakimTalebi mevcutTalep : bakimTalepleri) {
                    if (mevcutTalep.getTalepDetayi().equals(options[secim])) {
                        talepVarMi = true;
                        talep = mevcutTalep;
                        break;
                    }
                }

                if (!talepVarMi) {
                    if (secim == 0) {
                        talep = new BakimTalebi("Asansör arızası");
                    } else if (secim == 1) {
                        talep = new BakimTalebi("Elektrik arızası");
                    }
                    bakimTalepleri.add(talep);
                    sonucArea.append("Bakım Talebi: " + talep.getDurum() + "\n"); //son durumu döndürür
                    talep.ilerlet();
                } else if (talep.isTamamlandi()) {
                    sonucArea.append("Talep zaten alınmıştır: " + talep.getTalepDetayi() + "\n");//son durumu döndürür
                } else {
                    sonucArea.append("Durum ilerledi: " + talep.getDurum() + "\n");//son durumu döndürür
                    talep.ilerlet();
                }
            }
        });

        cikisButton.addActionListener(new ActionListener() { //çıkma tuşu
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new main().setVisible(true);
            } //çalıştırma
        });
    }
}