/**
 * Represents a song with its attributes such as name, artist, genre, and duration.
 */
public class Song implements Cloneable{

    private final String name;
    private final String artist;
    private Genre genre;
    private int duration = 0;
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

    @Override
    public String toString() {
        return "(" + name + ", " + artist + ", " + genre + ", " + duration + ")";
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
            throw new AssertionError();
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


}
