import java.util.*;
import java.util.Iterator;

public class Playlist implements Cloneable, Iterable<Song>, FilteredSongIterable, OrderedSongIterable {
    private ArrayList<Song> playlist;
    private ArrayList<Song> filterPlaylist;
    private ScanningOrder scanningOrder;


    /**
     * Returns an iterator to use for iterating over the songs in the playlist.
     * @return an iterator.
     */
    @Override
    public Iterator<Song> iterator() {
        return new PlaylistIterator();
    }

    /**
     * inner class, for an iterator for iterating over the songs in the playlist.
     */
    public class PlaylistIterator implements Iterator<Song> {
        private int count;

        //Constructs a new PlaylistIterator with an initial count of 0.

        public PlaylistIterator() {
            count = 0;
        }

        /**
         * Checks if there are remaining songs to iterate over.
         * @return true if there are remaining songs to iterate over, false otherwise
         */
        @Override
        public boolean hasNext() {
            if (filterPlaylist != null) {
                if (count < filterPlaylist.size()) {
                    return true;
                } else {
                    filterPlaylist = new ArrayList<>(playlist);
                    return false;
                }
            } else {
                return false;
            }
        }


        /**
         * If there are remaining songs to iterate over, returns the next song in the iteration.
         * Since count is incremented before returning the song, we need to use count - 1 as the
         * index to retrieve the correct song.
         * @return the next song in the iteration
         * @throws NoSuchElementException if there are no more songs to iterate over
         */
        @Override
        public Song next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more songs in the playlist.");
            }

            count++;
            return filterPlaylist.get(count-1);
        }
    }



    /**
     * Constructs an empty Playlist object.
     */
    public Playlist() {
        playlist = new ArrayList<>();
        filterPlaylist = (ArrayList<Song>) playlist.clone();
    }


    /**
     * Adds a song to the playlist.
     * @param song the song to be added
     * @throws SongAlreadyExistsException if the song is already in the playlist
     */

    public void addSong(Song song) throws SongAlreadyExistsException {
        if (playlist.contains(song))
            throw new SongAlreadyExistsException("This song is already in the playlist");
        playlist.add(song);
    }

    /**
     * Removes a song from the playlist.
     *
     * @param song the song to be removed
     * @return true if the song was found and removed, false otherwise
     */

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
     *
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
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * Checks if the Playlist object is equal to another object. Two playlists are considered equal
     * if they contain the same songs in any order.
     *
     * @param other the object to compare with
     * @return true if the objects are equal, false otherwise
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
     * creating a haseCode value for each playlist, using the hash code of each song in the playlist and
     *
     * @return an int value representing the hash Value.
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
     * Returns a string representation of the Playlist object.
     *
     * @return a string representation of the Playlist
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
     * Filters the playlist by removing songs that do not match the specified artist.
     * By iterating over the list using the iterator, we remove from the filtered playlist the songs that
     * doesn't match the filter.
     * @param artist the artist name to filter by
     */
    public void filterArtist(String artist) {
        if (artist != null) {
            Iterator<Song> iterator = filterPlaylist.iterator();
            while (iterator.hasNext()) {
                Song song = iterator.next();
                if (!song.getArtist().equals(artist)) {
                    iterator.remove();
                }
            }
        }
    }

    /**
     * fillters the list using the iterator. if there is a next song we will check if the song matches
     * the filters demand - if not, we will remove the song from the filtered list.
     * @param maxSeconds - max seconds of the song.
     */
    public void filterDuration(int maxSeconds) {
        Iterator<Song> iterator = filterPlaylist.iterator();
        while (iterator.hasNext()) {
            Song song = iterator.next();
            if (song.getDuration() > maxSeconds) {
                iterator.remove();
            }
        }
    }

    /**
     * Filters the playlist by removing songs that do not match the specified genre.
     * The filter works using the iterator. while there is a next song we will check if the song
     * answers to the demands of the filter.
     * @param genre the genre to filter the playlist by
     */

    public void filterGenre(Song.Genre genre) {
        if (genre != null) {
            Iterator<Song> iterator = filterPlaylist.iterator();
            while (iterator.hasNext()) {
                Song song = iterator.next();
                if (song.getGenre() != genre) {
                    iterator.remove();
                }
            }
        }
    }


    /**
     * Sets the scanning order for the playlist based on the specified  ScanningOrder from the ENUM.
     * The scanning order determines how the songs in the playlist are sorted for iteration.
     * @param scanningOrder the scanning order to apply to the playlist
     */
    @Override
    public void setScanningOrder(ScanningOrder scanningOrder) {
        if (filterPlaylist.size() > 0) {
            if (scanningOrder == ScanningOrder.ADDING) {
                return;
            }

            if (scanningOrder == ScanningOrder.NAME) {
                filterPlaylist.sort(Comparator.comparing(Song::getName)
                        .thenComparing(Song::getArtist));

            }

            if (scanningOrder == ScanningOrder.DURATION) {
                filterPlaylist.sort(Comparator.comparingInt(Song::getDuration)
                        .thenComparing(Song::getName)
                        .thenComparing(Song::getArtist));
            }
        }
    }

}





    /*
    public void setScanningOrder(ScanningOrder order) {
        scanningOrder = order;
    }

    @Override
    public Iterator<Song> iterator() {
        return new PlaylistIterator();
    }

    private void updateIterator() {
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

    private class PlaylistIterator implements Iterator<Song> {
        private Iterator<Song> iterator;

        public PlaylistIterator() {
            updateIterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Song next() {
            if (!hasNext())
                throw new NoSuchElementException("No more songs in the playlist");
            return iterator.next();
        }
    }
}


        /**
         * Returns an iterator over the songs in the playlist.
         * @return an iterator over the songs in the playlist
         */



/*
    public class PlaylistIterator implements Iterator<Song> {
        private Iterator<Song> iterator;

        /**
         * Constructs a new PlaylistIterator.
         */
/*
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

        /**
         * @return {@code true} if there are more songs in the playlist, {@code false} otherwise
         */
/*
        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        /**
         * Returns the next song in the playlist.
         * @return the next song in the playlist
         * @throws NoSuchElementException if there are no more songs in the playlist
         */
/*
        @Override
        public Song next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more songs in the playlist");
            }
            return iterator.next();
        }
    }


 */
        /*
        private class PlaylistIterator implements Iterator<Song> {
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






         */






