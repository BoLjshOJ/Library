package ru.otus.boljshoj.changelog;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

@ChangeLog
public class DBChangeLog {

    @ChangeSet(order = "001", id = "addRecords", author = "BoLjshOJ")
    public void insert(DB db){
        DBCollection myCollection = db.getCollection("books");

        BasicDBObject author1 = new BasicDBObject().append("name", "Михаил").append("surname", "Булгаков");
        BasicDBObject author2 = new BasicDBObject().append("name", "Джейн").append("surname", "Остин");
        BasicDBObject author3 = new BasicDBObject().append("name", "Джоан").append("surname", "Роулинг");
        BasicDBObject author4 = new BasicDBObject().append("name", "Лев").append("surname", "Толстой");

        BasicDBObject genre1 = new BasicDBObject().append("name", "Мистика");
        BasicDBObject genre2 = new BasicDBObject().append("name", "Роман");
        BasicDBObject genre3 = new BasicDBObject().append("name", "Фэнтези");

        BasicDBObject comment1 = new BasicDBObject().append("text", "Прекрасно");
        BasicDBObject comment2 = new BasicDBObject().append("text", "Всем советую");
        BasicDBObject comment3 = new BasicDBObject().append("text", "Ужас полный");

        BasicDBObject book1 = new BasicDBObject().append("_id", "1")
                .append("title", "Мастер и Маргарита")
                .append("author", author1)
                .append("genre", genre1)
                .append("comments", new BasicDBObject[]{comment1, comment2});

        BasicDBObject book2 = new BasicDBObject().append("_id", "2")
                .append("title", "Собачье сердце")
                .append("author", author1)
                .append("genre", genre1)
                .append("comments", new BasicDBObject[]{comment2});
        BasicDBObject book3 = new BasicDBObject().append("_id", "3")
                .append("title", "Гордость и предубеждение")
                .append("author", author2)
                .append("genre", genre2);
        BasicDBObject book4 = new BasicDBObject().append("_id", "4")
                .append("title", "Гарри Поттер и философский камень")
                .append("author", author3)
                .append("genre", genre3)
                .append("comments", new BasicDBObject[]{comment3, comment2, comment1});
        BasicDBObject book5 = new BasicDBObject().append("_id", "5")
                .append("title", "Война и Мир")
                .append("author", author4)
                .append("genre", genre2);

        myCollection.insert(book1, book2, book3, book4, book5);
    }
}