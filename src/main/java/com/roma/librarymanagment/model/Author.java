package com.roma.librarymanagment.model;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id != null ? id.equals(author.id) : author.id == null;

    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", books=" + books +
                '}';
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public Author(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public Author(String firstName, String lastName, Set<Book> books){
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = books;
    }
    public Author(){}


}
