//package com.kadirdogan97.newsexample.database;
//
//import androidx.annotation.NonNull;
//import androidx.room.ColumnInfo;
//import androidx.room.Entity;
//import androidx.room.Ignore;
//import androidx.room.PrimaryKey;
//
//@Entity(tableName = "news_reading_table")
//public class News {
//    @PrimaryKey
//    @NonNull
//    @ColumnInfo(name = "id")
//    private String id;
//
//    @NonNull
//    @ColumnInfo(name = "sourceName")
//    private String sourceName;
//
//    @NonNull
//    @ColumnInfo(name = "author")
//    private String author;
//
//    @NonNull
//    @ColumnInfo(name = "title")
//    private String title;
//
//    @NonNull
//    @ColumnInfo(name = "description")
//    private String description;
//
//    @NonNull
//    @ColumnInfo(name = "url")
//    private String url;
//
//    @NonNull
//    @ColumnInfo(name = "urlToImage")
//    private String urlToImage;
//
//    public News(@NonNull String id, @NonNull String sourceName, @NonNull String author, @NonNull String title, @NonNull String description, @NonNull String url, @NonNull String urlToImage) {
//        this.id = id;
//        this.sourceName = sourceName;
//        this.author = author;
//        this.title = title;
//        this.description = description;
//        this.url = url;
//        this.urlToImage = urlToImage;
//    }
//
//    @Ignore
//    public News() {
//        id = "";
//        sourceName = "";
//        author = "";
//        title = "";
//        description = "";
//        url = "";
//        urlToImage = "";
//
//
//
//    }
//
//    @NonNull
//    public String getId() {
//        return id;
//    }
//
//    public void setId(@NonNull String id) {
//        this.id = id;
//    }
//
//    @NonNull
//    public String getSourceName() {
//        return sourceName;
//    }
//
//    public void setSourceName(@NonNull String sourceName) {
//        this.sourceName = sourceName;
//    }
//
//    @NonNull
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(@NonNull String author) {
//        this.author = author;
//    }
//
//    @NonNull
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(@NonNull String title) {
//        this.title = title;
//    }
//
//    @NonNull
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(@NonNull String description) {
//        this.description = description;
//    }
//
//    @NonNull
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(@NonNull String url) {
//        this.url = url;
//    }
//
//    @NonNull
//    public String getUrlToImage() {
//        return urlToImage;
//    }
//
//    public void setUrlToImage(@NonNull String urlToImage) {
//        this.urlToImage = urlToImage;
//    }
//}
