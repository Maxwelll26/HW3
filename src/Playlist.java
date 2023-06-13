public class Playlist implements Cloneable{
    @Override
    public Playlist clone() {
        try {
            return (Playlist) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
