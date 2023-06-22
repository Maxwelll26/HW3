import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Represents a playlist of songs.
 * Implements Cloneable, Iterable, FilteredSongIterable, and OrderedSongIterable interfaces.
 */
public class Playlist implements Cloneable, Iterable<Song>, FilteredSongIterable, OrderedSongIterable {
    private ArrayList<Song> playlist;
    private ScanningOrder scanningOrder;
    private String filterArtist;
    private Song.Genre filterGenre;
    private int filterDuration;

    /**
     * Constructs an empty playlist.
     */
    public Playlist() {
        playlist = new ArrayList<>();
        scanningOrder = ScanningOrder.ADDING;
        filterArtist = null;
        filterGenre = null;
        filterDuration = Integer.MAX_VALUE;
    }

    /**
     * Adds a song to the playlist.
     *
     * @param song the song to add
     * @throws SongAlreadyExistsException if the song already exists in the playlist
     */
    public void addSong(Song song) throws SongAlreadyExistsException {
        if (playlist.contains(song))
            throw new SongAlreadyExistsException("This song is already in the playlist");
        playlist.add(song);
    }

    /**
     * Removes a song from the playlist.
     *
     * @param song the song to remove
     * @return true if the song was removed, false otherwise
     */
    public boolean removeSong(Song song) {
        return playlist.remove(song);
    }

    /**
     * Creates a shallow clone of the playlist.
     *
     * @return a cloned playlist object
     */
    @Override
    public Playlist clone() {
        try {
            Playlist copyPlaylist = (Playlist) super.clone();
            copyPlaylist.playlist = (ArrayList<Song>) playlist.clone();
            for (int i = 0; i < playlist.size(); i++) {
                copyPlaylist.playlist.set(i, playlist.get(i).clone());
            }
            return copyPlaylist;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * Checks if the playlist is equal to another object.
     *
     * @param other the object to compare
     * @return true if the playlist is equal to the other object, false otherwise
     */
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
     * Computes the hash code for the playlist.
     *
     * @return the hash code value for the playlist
     */
    @Override
    public int hashCode() {
        int hashValue = 0;
        for (Song song : playlist) {
            hashValue += song.hashCode();
        }
        return hashValue;
    }

    /**
     * Returns a string representation of the playlist.
     *
     * @return a string representation of the playlist
     */
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

    /**
     * Applies the set filters to the playlist and returns the filtered songs.
     *
     * @return the filtered songs based on the applied filters
     */
    private ArrayList<Song> applyFilter() {
        ArrayList<Song> filteredSongs = new ArrayList<>();
        for (Song song : playlist) {
            if (filterArtist == null || filterArtist.equals(song.getArtist())) {
                if (filterGenre == null || filterGenre.equals(song.getGenre())) {
                    if (song.getDuration() <= filterDuration) {
                        filteredSongs.add(song);
                    }
                }
            }
        }
        return filteredSongs;
    }

    /**
     * Filters the playlist by artist name.
     *
     * @param artist the artist name to filter by
     */
    @Override
    public void filterArtist(String artist) {
        filterArtist = artist;
    }

    /**
     * Filters the playlist by song genre.
     *
     * @param genre the genre to filter by
     */
    @Override
    public void filterGenre(Song.Genre genre) {
        filterGenre = genre;
    }

    /**
     * Filters the playlist by maximum song duration.
     *
     * @param duration the maximum duration to filter by
     */
    @Override
    public void filterDuration(int duration) {
        filterDuration = duration;
    }

    /**
     * Sets the scanning order for iterating over the playlist.
     *
     * @param order the scanning order to set
     */
    @Override
    public void setScanningOrder(ScanningOrder order) {
        scanningOrder = order;
    }

    /**
     * Returns an iterator over the playlist.
     *
     * @return an iterator over the playlist
     */
    @Override
    public Iterator<Song> iterator() {
        return new PlaylistIterator();
    }

    /**
     * Iterator class for iterating over the playlist.
     */
    public class PlaylistIterator implements Iterator<Song> {
        private ArrayList<Song> sortedSongs;
        private int index;

        /**
         * Constructs a new PlaylistIterator.
         * Applies the filters and sorting order to the playlist.
         */
        public PlaylistIterator() {
            sortedSongs = applyFilter();
            switch (scanningOrder) {
                case ADDING:
                    break;
                case NAME:
                    sortedSongs.sort(Comparator.comparing(Song::getName).thenComparing(Song::getArtist));
                    break;
                case DURATION:
                    sortedSongs.sort(Comparator.comparing(Song::getDuration).thenComparing(Song::getName).thenComparing(Song::getArtist));
                    break;
            }
        }

        /**
         * Checks if there are more songs to iterate over.
         *
         * @return true if there are more songs, false otherwise
         */
        @Override
        public boolean hasNext() {
            return index < sortedSongs.size();
        }

        /**
         * Returns the next song in the iteration.
         *
         * @return the next song
         */
        @Override
        public Song next() {
            return sortedSongs.get(index++);
        }
    }
}
