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
        if (other==null)
            return false;
        if (!(this.hashCode() == other.hashCode() || other instanceof Song))
            return false;

        Song otherSong = (Song) other;
        return this.name.equals(otherSong.name) && this.artist.equals(otherSong.artist);

    }

    /**
     * calculating a hashcode for each song using the Haski value for each character in the song name and artist and
     * the duration of the song.
     * @return the int value of the Hashcode.
     */
    @Override
    public int hashCode() {
        int nameValue = 0;
        int artistValue = 0;
        int currentHashCode = 0;
        for (int i=0; i<this.name.length(); i++) {
            nameValue += name.charAt(i);
            artistValue += artist.charAt(i);
            currentHashCode += (nameValue * artistValue)*(duration/11);

        }
        return currentHashCode;
    }

}
