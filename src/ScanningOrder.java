/**
 * Represents the scanning order options for iterating over a playlist.
 */
public enum ScanningOrder {
    /**
     * Scanning order based on the order songs were added to the playlist.
     */
    ADDING,

    /**
     * Scanning order based on the song name, followed by the artist name.
     */
    NAME,

    /**
     * Scanning order based on the song duration, followed by the song name, and then the artist name.
     */
    DURATION
}
