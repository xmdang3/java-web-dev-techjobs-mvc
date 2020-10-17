package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        String lastSearchType = "all";
        model.addAttribute("lastSearchType", lastSearchType);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @RequestMapping(value = "/results")
    public String displaySearchResults(Model model,
                                       @RequestParam String searchType,
                                       @RequestParam String searchTerm) {
        ArrayList<Job> jobs;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            jobs = JobData.findAll();
            model.addAttribute("title", "All Jobs");
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
        }
        String lastSearchType = searchType;
        model.addAttribute("lastSearchType", lastSearchType);
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);

        return "search";
    }

}
