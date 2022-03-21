public class Item {
    protected String location;
    protected String title;
    protected String id;
    protected String author;
    protected String type;
    protected int year;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "\n" + type +
                ": {" +
                "location='" + location + '\'' +
                ", title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", type='" + type + '\'' +
                ", year=" + year +
                "}";
    }
}
