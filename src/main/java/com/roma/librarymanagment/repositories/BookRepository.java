package com.roma.librarymanagment.repositories;

import com.roma.librarymanagment.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
    Book findBookByTitle(String title);
    Book findBookById(Long id);
    @Query("SELECT b FROM Book b INNER JOIN Author a ON b.author.id = a.id where b.author.id = :authorId and b.author.id in :authorIds")
    List<Book> findBooksByAuthorId(@Param("authorId") Long authorId,@Param("authorIds")List<Long> authorIds);
    @Query("SELECT v FROM Book v INNER JOIN Publisher p ON v.publisher.id = p.id where v.publisher.id = :publisherId and v.publisher.id in :bookIds")
    List<Book> findBooksByPublisherId(@Param("publisherId") Long publisherId,@Param("bookIds")List<Long> bookIds);
}
