import java.util.ArrayList;

public class Playlist implements Cloneable {

    private ArrayList<Song> playlist;

    public Playlist() {
        playlist = new ArrayList<>();
    }

    public void addSong(Song song) throws SongAlreadyExistsException {
        if (playlist.contains(song))
            throw new SongAlreadyExistsException("This song is already in the playlist");

        playlist.add(song);
    }

    public boolean removeSong(Song song) {
        boolean songRemoval = playlist.remove(song);
        return songRemoval;

    }

    /**
     * super.clone() - create a shallow copy of the Playlist object. This means that the
     * object itself is copied, but the objects referenced by the playlist field are not cloned.
     * The copyPlaylist.playlist line creates a new ArrayList object for the copyPlaylist object to store its songs
     * meaning allocation new space in the memory. The for loop iterates over each Song object in the original
     * playlist and for each song, it calls the clone() method on it to create a deep copy of the Song object.
     * The cloned Song objects are then added to the copyPlaylist.playlist list.
     * if the object is not clonable than it will not throw a new message but it w`ont work.
     * @return the coppy of the playlist of cloneable.
     */
    @Override
    public Playlist clone() {
        try {
            Playlist copyPlaylist = (Playlist) super.clone();
            copyPlaylist.playlist = new ArrayList<>();
            for (Song song : this.playlist)
                copyPlaylist.playlist.add(song.clone());

            return copyPlaylist;
        }
        catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public boolean equals (Object other) {
        if (other==null)
            return false;
        if (!(this.hashCode() == other.hashCode() || other instanceof Playlist))
            return false;

        /// and need to continue :)
    }

    /**
     * creating a haseCode value for each playlist, using the hash code of each song in the playlist and
     * the length pf the playlist.
     * @return an int value representing the hash Value.
     */
    @Override
    public int hashCode(){
        int len = playlist.size();
        int hashValue=0;

        for (int index=0; index<len; index++){
            hashValue+=playlist.get(index).hashCode();
        }
        return hashValue * len;
    }
}
