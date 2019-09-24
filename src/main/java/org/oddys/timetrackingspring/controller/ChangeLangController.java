package org.oddys.timetrackingspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ChangeLangController {
    private static final String prefix = "/WEB-INF/pages";
    private static final String suffix = ".jsp";

    @GetMapping("/lang")
    public String changeLanguage(@RequestParam(defaultValue = "en") String lang,
            @RequestParam(defaultValue = "/") String sentFromPage, HttpSession session) {
        session.setAttribute("lang", lang);
        return "redirect:" + sentFromPage.replace(prefix, "").replace(suffix, "");
    }
}
