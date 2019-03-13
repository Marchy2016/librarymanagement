package com.roma.librarymanagment.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;



@Entity
@Data
public class Book extends BaseEntity {
    @NotNull
    @Column(unique = true)
    private String isbn;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "publisher_book",joinColumns = @JoinColumn(name ="book_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id"))
    @NotNull
    private Publisher publisher;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "author_book",joinColumns = @JoinColumn(name ="book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @NotNull
    private Author author;
    @NotNull
    private String title;
    @OneToOne
    @JoinTable(name = "book_category", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @JoinColumn(name = "category_id")
    private Category category;
    private String edition;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "year_published")
    @DateTimeFormat(pattern="YYYY")
    private Date yearPublished;

    public Book(String isbn, Publisher publisher,Author author,String title,Category category,String edition, Date yearPublished) {
        this.isbn = isbn;
        this.publisher = publisher;
        this.author = author;
        this.title = title;
        this.category = category;
        this.edition = edition;
        this.yearPublished = yearPublished;
    }

    @Override
    public String toString() {
        return "Book{" +
                ", isbn='" + isbn + '\'' +
                ", publisher=" + publisher +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", edition='" + edition + '\'' +
                ", yearPublished=" + yearPublished +
                '}';
    }

    public Book() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn) &&
                Objects.equals(publisher, book.publisher) &&
                Objects.equals(author, book.author) &&
                Objects.equals(title, book.title) &&
                Objects.equals(category, book.category) &&
                Objects.equals(edition, book.edition) &&
                Objects.equals(yearPublished, book.yearPublished);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), isbn, publisher, author, title, category, edition, yearPublished);
    }
}
