package com.roma.librarymanagment.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min=5, max = 15)
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Column(unique = true)
    @Email
    private String email;
    @OneToMany
    private Set<Book> book = new HashSet<>();

    public Author(String firstName, String lastName,String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

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
                ", email='" + email + '\'' +

                '}';
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
