package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Publisher;

import java.util.List;

public interface PublisherService {

   Publisher add(String name, String address,String email);
   List<Publisher> findAll();



}
