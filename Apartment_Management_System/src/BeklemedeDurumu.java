import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class BeklemedeDurumu implements TalepDurumu {
    @Override
    public void birSonrakiDurum(BakimTalebi talep) {
        talep.setDurum(new IslemdeDurumu());
    }
    @Override
    public String getDurum() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        return "Oluşturuldu - Oluşturma tarihi - " + formattedDateTime;
    }
}