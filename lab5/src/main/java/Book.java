public class Book extends Item{
    public Book(String id, String title, String location, int year, String author, String type){
        Book.super.setId(id);
        Book.super.setTitle(title);
        Book.super.setLocation(location);
        Book.super.setYear(year);
        Book.super.setAuthor(author);
        Book.super.setType(type);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
