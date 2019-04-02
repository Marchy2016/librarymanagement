package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Publisher;

import java.util.List;

public interface PublisherService {

   Publisher addPublisher(String name, String address,String email);
   List<Publisher> listPublishers();
   Publisher findPublisherById(Long id);
   Publisher updatePublisher(Long id, String name, String address,String email);
   void deletePublisher(Long id);



}
