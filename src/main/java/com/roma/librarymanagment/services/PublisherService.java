package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Publisher;

import java.util.List;

public interface PublisherService {
    Publisher addPublisher(String name, String address);
    List<Publisher> findAll();

}
