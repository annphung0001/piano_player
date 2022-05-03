package piano_player;

public class Notes {
    private int note;
    private long timePress;
    private long timeRelease;

    public Notes(int note, long timeP, long timeR) {
        this.note = note;
        this.timePress = timeP;
        this.timeRelease = timeR;
    }

    public Notes() {
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public long getTimePress() {
        return timePress;
    }

    public void setTimePress(long timePress) {
        this.timePress = timePress;
    }

    public long getTimeRelease() {
        return timeRelease;
    }

    public void setTimeRelease(long timeRelease) {
        this.timeRelease = timeRelease;
    }

    @Override
    public String toString() {
        return note + " " + timePress + " " + timeRelease;
    }

}
