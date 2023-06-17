import java.util.*;
import java.util.Iterator;

public class Playlist implements Cloneable, Iterable<Song>, FilteredSongIterable, OrderedSongIterable {
    private ArrayList<Song> playlist;
    private ScanningOrder scanningOrder;
    private String filterArtist;
    private Song.Genre filterGenre;
    private int filterDuration;


    public Playlist() {
        playlist = new ArrayList<>();
        scanningOrder = ScanningOrder.ADDING;
        filterArtist = null;
        filterGenre = null;
        filterDuration = 0;
    }

    public void addSong(Song song) throws SongAlreadyExistsException {
        if (playlist.contains(song))
            throw new SongAlreadyExistsException("This song is already in the playlist");
        playlist.add(song);
    }

    public boolean removeSong(Song song) {
        return playlist.remove(song);
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
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        Playlist otherPlaylist = (Playlist) other;
        Set<Song> thisSongs = new HashSet<>(playlist);
        Set<Song> otherSongs = new HashSet<>(otherPlaylist.playlist);
        return thisSongs.equals(otherSongs);
    }

    /**
     * creating a haseCode value for each playlist, using the hash code of each song in the playlist and
     * the length pf the playlist.
     * @return an int value representing the hash Value.
     */
    /*@Override
    public int hashCode(){
        int len = playlist.size();
        int hashValue=0;

        for (Song song : playlist) {
            hashValue += song.hashCode();
        }
        return hashValue * len;
    }*/

    @Override
    public int hashCode() {
        int hashValue = 0;
        for (Song song : playlist) {
            hashValue += song.hashCode();
        }
        return hashValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Song song : playlist) {
            sb.append("(").append(song).append("), ");
        }
        if (!playlist.isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        return "[" + sb + "]";
    }

    private void applyFilter() {
        ArrayList<Song> filteredSongs = new ArrayList<>();
        for (Song song : playlist) {
            if (filterArtist == null || filterArtist.equals(song.getArtist())) {
                if (filterGenre == null || filterGenre.equals(song.getGenre())) {
                    if (filterDuration == 0 || song.getDuration() <= filterDuration) {
                        filteredSongs.add(song);
                    }
                }
            }
        }
        playlist = filteredSongs;
    }

    @Override
    public void filterArtist(String artist) {
        filterArtist = artist;
        applyFilter();
    }

    @Override
    public void filterGenre(Song.Genre genre) {
        filterGenre = genre;
        applyFilter();
    }

    @Override
    public void filterDuration(int duration) {
        filterDuration = duration;
        applyFilter();
    }

    @Override
    public void setScanningOrder(ScanningOrder order) {
        scanningOrder = order;
    }

    @Override
    public Iterator<Song> iterator() {
        return new PlaylistIterator();
    }

    public class PlaylistIterator implements Iterator<Song> {
        private Iterator<Song> iterator;
        public PlaylistIterator() {
            ArrayList<Song> sortedSongs = new ArrayList<>(playlist);
            switch (scanningOrder) {
                case ADDING:
                    iterator = sortedSongs.iterator();
                    break;
                case NAME:
                    sortedSongs.sort(Comparator.comparing(Song::getName).thenComparing(Song::getArtist));
                    iterator = sortedSongs.iterator();
                    break;
                case DURATION:
                    sortedSongs.sort(Comparator.comparing(Song::getDuration).thenComparing(Song::getName).thenComparing(Song::getArtist));
                    iterator = sortedSongs.iterator();
                    break;
            }
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Song next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more songs in the playlist");
            }
            return iterator.next();
        }
    }
}
