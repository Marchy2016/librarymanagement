package com.roma.librarymanagment.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    //@ManyToMany(mappedBy = "authors")
    //private Book books;
    public Author(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;

    }
    public Author(){}

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
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
