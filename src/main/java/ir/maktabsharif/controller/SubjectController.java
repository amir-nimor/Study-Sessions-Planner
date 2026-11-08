package ir.maktabsharif.controller;

import ir.maktabsharif.service.subject.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }


    @GetMapping("/all")
    public String allSubject(Model model){
        model.addAttribute("subjects",subjectService.findAll());
        return "showAllSubjects";
    }
}
