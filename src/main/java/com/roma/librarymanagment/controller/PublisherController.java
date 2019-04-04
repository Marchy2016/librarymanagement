package com.roma.librarymanagment.controller;

import com.roma.librarymanagment.config.BookProsConfig;
import com.roma.librarymanagment.model.Publisher;
import com.roma.librarymanagment.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PublisherController {

    private PublisherService publisherService;
    @Autowired
    private BookProsConfig bookProsConfig;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @RequestMapping(path = "/publishers", method = RequestMethod.GET)
    private String listPublishers(Model model){
        model.addAttribute("publishers", publisherService.listPublishers());
        return "publishers";

    }

    @RequestMapping(path = "/savepublishers", method = RequestMethod.GET)
    private String savePublisher(Model model){

        model.addAttribute("publisher",new Publisher());
        return "addPublishers";
    }

    @RequestMapping(path = "/savepublishers", method = RequestMethod.POST)
    private String savePublisher(Model model, @ModelAttribute Publisher publisher){
        Publisher publisher1 = publisherService.addPublisher(publisher.getName(),publisher.getAddress(),publisher.getEmail());
        model.addAttribute("publisher", publisher1);
        return "publisher";

    }

    @RequestMapping(path = "/findpublisher/{id}", method = RequestMethod.GET)
    private String savePublisher(Model model, @PathVariable Long id){

          Publisher publisher = publisherService.findPublisherById(id);
          if(publisher != null){
              model.addAttribute("publisher",publisher);
          }
        return "updatePublisher";
    }
    @RequestMapping(path = "/updatepublisher", method = RequestMethod.POST)
    private String updatePublisher(Model model, @ModelAttribute Publisher publisher){
        Publisher publisher1 = publisherService.updatePublisher(publisher.getId(),publisher.getName(),publisher.getAddress(),publisher.getEmail());
        model.addAttribute("publisher", publisher1);
        return "publisher";

    }

    @RequestMapping(path = "/deletepublisher/{id}", method = RequestMethod.GET)
    private String deletePublisher(Model model, @PathVariable Long id){
          publisherService.deletePublisher(id);
        return bookProsConfig.getDisplayPublishers();
    }








}
