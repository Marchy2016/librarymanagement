package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Publisher;
import com.roma.librarymanagment.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    PublisherRepository publisherRepository;
    Publisher publisher;
    public Publisher addPublisher(String name, String address) {

        if((!"".equals(name) && name != null) && (!"".equals(address) && address != null)) {
            publisher = new Publisher(name,address);
        }
         return  publisherRepository.save(publisher);

    }
    public List<Publisher> findAll(){
        return publisherRepository.findAll();
    }
}