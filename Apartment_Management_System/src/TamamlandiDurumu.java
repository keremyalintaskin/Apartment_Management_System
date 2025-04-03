class TamamlandiDurumu implements TalepDurumu {
    @Override
    public void birSonrakiDurum(BakimTalebi talep) {
        System.out.println("Talep zaten alınmıştır.");
    }
    @Override
    public String getDurum() {
        return "Alınmıştır";
    }
}