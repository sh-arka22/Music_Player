package entities;

public class Genre {
    private final String genre;
    public Genre(){
        this.genre = null;
    }
    public Genre(String genre){
        this.genre = genre;
    }
    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Genre [genre=" + genre + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((genre == null) ? 0 : genre.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Genre other = (Genre) obj;
        if (genre == null) {
            if (other.genre != null)
                return false;
        } else if (!genre.equals(other.genre))
            return false;
        return true;
    }

}
