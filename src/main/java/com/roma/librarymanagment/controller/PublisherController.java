package com.roma.librarymanagment.controller;

import com.roma.librarymanagment.model.Publisher;
import com.roma.librarymanagment.services.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class PublisherController {


    private PublisherService publisherService;

      public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;

    }

    @RequestMapping(path = "/publishers", method = RequestMethod.GET)
    private String listPublishers(Model model){
        model.addAttribute("publishers", publisherService.findAll());
        return "publishers";

    }

    @RequestMapping(path = "/savepublishers", method = RequestMethod.GET)
    private String saveBook(Model model){

        model.addAttribute("publisher",new Publisher());
        return "addPublishers";
    }

    @RequestMapping(path = "/savepublishers", method = RequestMethod.POST)
    private String addPublisher(Model model, @ModelAttribute Publisher publisher){
        Publisher publisher1 = publisherService.add(publisher.getName(),publisher.getAddress(),publisher.getEmail());
        model.addAttribute("publisher", new ArrayList<Publisher>(){{add(publisher1);}});
        return "menu";

    }





}
