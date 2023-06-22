/**
 * The OrderedSongIterable interface represents an iterable collection of songs that can be iterated in a specific order.
 */
public interface OrderedSongIterable extends Iterable<Song> {

    /**
     * Sets the scanning order for iterating over the songs.
     *
     * @param order the scanning order to be set
     */
    void setScanningOrder(ScanningOrder order);
}
