package ir.maktabsharif.controller;

import ir.maktabsharif.dto.subject.SubjectRequestDto;
import ir.maktabsharif.dto.user.UserResponseDto;
import ir.maktabsharif.service.subject.SubjectService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }


    @GetMapping("/all")
    public String allSubject(Model model,HttpServletRequest request){
        HttpSession session = request.getSession(false);
        UserResponseDto userResponseDto = (UserResponseDto) session.getAttribute("user");
        model.addAttribute("subjects",subjectService.getUserSubjects(userResponseDto.getId()));
//        model.addAttribute("subjects",subjectService.findAll());
        return "showAllSubjects";
    }


    @GetMapping("/add")
    public String getSubjectForm(Model model){
        return "StudyForm";
    }

    @PostMapping("/add")
    public String addForm(Model model, HttpServletRequest request, @RequestParam("name") String name){
        HttpSession session = request.getSession();
        UserResponseDto userResponseDto = (UserResponseDto) session.getAttribute("user");

        SubjectRequestDto subjectRequestDto = SubjectRequestDto.builder().name(name).userId(userResponseDto.getId()).build();
        subjectService.create(subjectRequestDto);

        return "redirect:all";
    }


    @GetMapping("/{id}")
    public String getDatalis(@PathVariable("id") Long id , Model model){
        model.addAttribute("subject",subjectService.read(id));
        return "subjectDestails";
    }


}
