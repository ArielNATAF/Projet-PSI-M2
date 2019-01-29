package com.example.superBPMN.web;

import com.example.superBPMN.Model.DockerChoice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DockerChoiceController {

    @GetMapping("/dockerChoice")
    public String dockerChoiceForm(Model model) {
        model.addAttribute("dockerChoice", new DockerChoice());
        return "dockerChoice";
    }

    @PostMapping("/dockerChoice")
    public String dockerChoiceSubmit(@ModelAttribute DockerChoice dockerChoice) {
        return "result";
    }

}
