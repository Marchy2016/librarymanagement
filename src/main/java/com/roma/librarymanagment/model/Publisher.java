package com.roma.librarymanagment.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Data
@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String address;

    public Publisher(String name, String address) {
        this.name = name;
        this.address = address;
    }
    public Publisher(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return id != null ? id.equals(publisher.id) : publisher.id == null;

    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "Publisher{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
