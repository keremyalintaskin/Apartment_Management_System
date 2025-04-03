class IslemdeDurumu implements TalepDurumu {
    @Override
    public void birSonrakiDurum(BakimTalebi talep) {
        talep.setDurum(new TamamlandiDurumu());
    }
    @Override
    public String getDurum() {
        return "İşlemde...";
    }
}