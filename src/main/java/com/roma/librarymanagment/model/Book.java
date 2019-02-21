package com.roma.librarymanagment.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String isbn;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "publisher_book",joinColumns = @JoinColumn(name ="book_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id") )
    @NotNull
    private Publisher publisher;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "author_book",joinColumns = @JoinColumn(name ="book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @NotNull
    private Author author;
    @NotNull
    private String title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id != null ? id.equals(book.id) :  book.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }



    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publisher='" + publisher + '\'' +
                ", authors=" + author +
                '}';
    }

    public Book() {

    }
    public Book(String title, String isbn, Publisher publisherId, Author authorId){
        this.isbn = isbn;
        this.title = title;
        this.publisher = publisherId;
        this.author = authorId;

    }

}
