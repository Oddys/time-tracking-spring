//package org.oddys.timetrackingspring.controllers;
//
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.oddys.timetrackingspring.service.AddActivityService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Slf4j
//@Controller
//@AllArgsConstructor
//@RequestMapping
//public class HomeController {
//    private final AddActivityService service;
//
//    @GetMapping("/")
//    public String home() {
//        return "home";
//    }
//
//    @GetMapping("/form")
//    public String findByName(@RequestParam String activityName, Model model) {
//        log.debug("The name is: " + activityName);
//        model.addAttribute("activityName", activityName);
//        model.addAttribute("exists", service.check(activityName));
//        model.addAttribute("hello", "world");
//        return "home";
//    }
//}
