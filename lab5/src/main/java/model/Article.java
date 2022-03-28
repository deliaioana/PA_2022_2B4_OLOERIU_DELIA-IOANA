package model;

public class Article extends Item {
    public Article(String id, String title, String location, int year, String author, String type){
        Article.super.setId(id);
        Article.super.setTitle(title);
        Article.super.setLocation(location);
        Article.super.setYear(year);
        Article.super.setAuthor(author);
        Article.super.setType(type);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
