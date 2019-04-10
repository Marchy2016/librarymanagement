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
    public Publisher addPublisher(String name, String address,String email) {

        if((!"".equals(name) && name != null) && (!"".equals(address) && address != null)
                && (!"".equals(email) && email != null)) {
            publisher = new Publisher(name,address,email);
        }
         return  publisherRepository.save(publisher);

    }
    public List<Publisher> listPublishers(){
        return publisherRepository.findAll();
    }
    public Publisher findPublisherById(Long id){
        return publisherRepository.findById(id).isPresent() ? publisherRepository.findById(id).get() : null;
    }
    public Publisher updatePublisher(Long id, String name, String address,String email){
        Publisher publisher = findPublisherById(id);
        if(publisher != null){
            publisher.setName(name);
            publisher.setEmail(email);
            publisher.setAddress(address);
        }
        return publisherRepository.save(publisher);
    }
    public void deletePublisher(Long id){
        publisher = findPublisherById(id);
        publisherRepository.delete(publisher);
    }
}
