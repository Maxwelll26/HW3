/**
 * Represents a song with its associated properties.
 */
public class Song implements Cloneable {
    private final String name;
    private final String artist;
    private Genre genre;
    private int duration;

    /**
     * Constructs a new Song object with the specified name, artist, genre, and duration.
     *
     * @param name     the name of the song
     * @param artist   the artist of the song
     * @param genre    the genre of the song
     * @param duration the duration of the song in seconds
     */
    public Song(String name, String artist, Genre genre, int duration) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
    }

    /**
     * Retrieves the name of the song.
     *
     * @return the name of the song
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the artist of the song.
     *
     * @return the artist of the song
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Retrieves the genre of the song.
     *
     * @return the genre of the song
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * Retrieves the duration of the song in seconds.
     *
     * @return the duration of the song in seconds
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the song in seconds.
     *
     * @param duration the duration of the song in seconds
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Returns a string representation of the song.
     *
     * @return a string representation of the song
     */
    @Override
    public String toString() {
        String durationString = String.format("%d:%02d", duration / 60, duration % 60);
        return String.format("%s, %s, %s, %s", name, artist, genre, durationString);
    }

    /**
     * Creates and returns a copy of this Song object.
     *
     * @return a clone of this Song object
     */
    @Override
    public Song clone() {
        try {
            return (Song) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * Represents the genre of a song.
     */
    public enum Genre {
        POP,
        ROCK,
        HIP_HOP,
        COUNTRY,
        JAZZ,
        DISCO
    }

    /**
     * Checks if this Song object is equal to another object.
     *
     * @param other the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        Song otherSong = (Song) other;
        return name.equals(otherSong.name) && artist.equals(otherSong.artist);
    }

    /**
     * Returns the hash code value for this Song object.
     *
     * @return the hash code value for this Song object
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + artist.hashCode();
        return result;
    }
}
