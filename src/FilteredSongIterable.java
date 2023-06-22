/**
 * The FilteredSongIterable interface represents an iterable collection of songs that can be filtered based on
 * different criteria such as artist name, genre, and maximum duration.
 */
public interface FilteredSongIterable extends Iterable<Song> {

    /**
     * Filters the songs by the specified artist name.
     *
     * @param artistName the artist name used for filtering
     */
    void filterArtist(String artistName);

    /**
     * Filters the songs by the specified genre.
     *
     * @param genre the genre used for filtering
     */
    void filterGenre(Song.Genre genre);

    /**
     * Filters the songs by the maximum duration.
     *
     * @param maxDuration the maximum duration used for filtering
     */
    void filterDuration(int maxDuration);
}
