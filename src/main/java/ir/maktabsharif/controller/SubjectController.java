package ir.maktabsharif.controller;

import ir.maktabsharif.dto.subject.SubjectRequestDto;
import ir.maktabsharif.dto.subject.SubjectResponseDto;
import ir.maktabsharif.dto.user.UserRequestDto;
import ir.maktabsharif.dto.user.UserResponseDto;
import ir.maktabsharif.service.subject.SubjectService;
import ir.maktabsharif.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;
    private final UserService userService;

    public SubjectController(SubjectService subjectService, UserService userService) {
        this.subjectService = subjectService;
        this.userService = userService;
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
        SubjectResponseDto subjectResponseDto = (SubjectResponseDto) subjectService.create(subjectRequestDto);


        List<Long> subjectsList = new ArrayList<>();
        subjectsList.addAll(userResponseDto.getSubjectsId());
        subjectsList.add(subjectResponseDto.getId());

        UserRequestDto userRequestDto = UserRequestDto.builder()
                .password(userResponseDto.getPasswordHash())
                .fullname(userResponseDto.getFullname())
                .username(userResponseDto.getUsername())
                .subjectsId(subjectsList)
                .studySessionsID(userResponseDto.getStudySessionsID())
                .build();

        userService.update(userRequestDto,userResponseDto.getId());

        return "redirect:all";
    }


    @GetMapping("/{id}")
    public String getDatalis(@PathVariable("id") Long id , Model model){
        model.addAttribute("subject",subjectService.read(id));
        return "subjectDestails";
    }


}
