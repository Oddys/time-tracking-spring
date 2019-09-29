package org.oddys.timetrackingspring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("lang")
@Slf4j
public class ChangeLangController {
    private static final String prefix = "/WEB-INF/pages";
    private static final String suffix = ".jsp";

    @GetMapping("/lang")
    public String changeLanguage(@RequestParam(defaultValue = "en") String lang,
            @RequestParam(defaultValue = "/") String sentFromPage, Model model) {
        model.addAttribute("lang", lang);
        return "redirect:" + sentFromPage.replace(prefix, "").replace(suffix, "");
    }
}
