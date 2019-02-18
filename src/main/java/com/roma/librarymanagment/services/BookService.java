package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Book;
import org.springframework.stereotype.Service;

@Service
public interface BookService extends CrudService<Book, Long> {

}
