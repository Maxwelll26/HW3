/**
 * Represents a song with its attributes such as name, artist, genre, and duration.
 */
public class Song implements Cloneable{

    private final String name;
    private final String artist;
    private Genre genre;
    private int duration;
    private Object [] song;

    /**
     * Constructs a Song object with the specified attributes.
     * @param name     the name of the song
     * @param artist   the artist of the song
     * @param genre    the genre of the song - using the ENUM.
     * @param duration the duration of the song in seconds
     */
    public Song(String name,String artist, Genre genre, int duration) {
        this.name=name;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;

        int mm = duration/60;
        int ss = duration%60;

        String formattedDuration = String.format("%d:%02d", mm, ss);
        song = new Object[] {name,artist, genre, formattedDuration};
    }

    /**
     * Getters for every feature in the song.
     * @return Exactly the requested value
     */
    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    public Object[] getSong() {
        return song;
    }

    /**
     * setts the duration of the song in seconds.
     * @param duration the new applicable duration for the song.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * new way to write the string appropriately according to the instructions.
     * @return a formatted string.
     */
    @Override
    public String toString() {
        String durationString = String.format("%d:%02d", duration / 60, duration % 60);
        return String.format("%s, %s, %s, %s", name, artist, genre, durationString);
    }

    /**
     * Creates and returns a copy of this Song object with all his features. the name and artist are STR
     * and are immutable and also regarding genre from the enum - is those cases, so shallow coping is
     * deep coping for us - super.clone handles it.
     * regarding for duration - int is a primitive type, it is copied by value so in this case in order to do
     * deep copy we need to create new duration.
     * @return a clone of this Song object
     */
    @Override
    public Song clone() {
        try {
            Song copySong = (Song) super.clone();
            copySong.duration = duration;
            return copySong;

        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * internal enum for the Genre.
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
     * overriding on equals. If the other object is null or the hashcode between the two objects are different
     * or the other object is not instance of Song - the two objects are not equal so returns false.
     * defining that otherSong from Song type is the other object.
     * @param other who is the other object we are trying to say if this song is equals to.
     * @return true or false. if the song name and the artist are the same, so we will get true. else - false.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass() || this.hashCode() != other.hashCode())
            return false;
        Song otherSong = (Song) other;
        return name.equals(otherSong.name) && artist.equals(otherSong.artist);
    }

    /**
     * calculating a hashcode for each song using the hashcode for the name of the song and the artist.
     * @return the int value of the Hashcode.
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + artist.hashCode();
        return result;
    }

}
