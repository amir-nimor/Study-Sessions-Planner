package ir.maktabsharif.controller;


import ir.maktabsharif.dto.studySession.StudySessionRequestDto;
import ir.maktabsharif.dto.studySession.StudySessionResponseDto;
import ir.maktabsharif.dto.user.UserRequestDto;
import ir.maktabsharif.dto.user.UserResponseDto;
import ir.maktabsharif.service.studySession.StudySessionService;
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
@RequestMapping("/session")
public class SessionController {

    private final StudySessionService studySessionService;
    private final SubjectService subjectService;
    private final UserService userService;

    public SessionController(StudySessionService studySessionService, SubjectService subjectService, UserService userService) {
        this.studySessionService = studySessionService;
        this.subjectService = subjectService;
        this.userService = userService;
    }


    @GetMapping("/sessions")
    public String displayAll(Model model,HttpServletRequest request){
        HttpSession session = request.getSession(false);
        UserResponseDto userResponseDto = (UserResponseDto) session.getAttribute("user");
        model.addAttribute("sessions",studySessionService.userStudySession(userResponseDto.getId()));
//        model.addAttribute("sessions",studySessionService.findAll());
        return "allSessions";
    }

    @GetMapping("/add")
    public String sessionsAdd(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        UserResponseDto userResponseDto = (UserResponseDto) session.getAttribute("user");
        model.addAttribute("subjects",subjectService.getUserSubjects(userResponseDto.getId()));
        model.addAttribute("userId",userResponseDto.getId());
        return "sessionForm";
    }

    @PostMapping("/add")
    public String addSession(Model model,
                             HttpServletRequest request,
                             @RequestParam("minutes") Integer minutes,
                             @RequestParam("notes") String notes,
                             @RequestParam("subjectId") Long subjectId
                             ) {
        HttpSession session = request.getSession(false);
        UserResponseDto userResponseDto = (UserResponseDto) session.getAttribute("user");

        StudySessionRequestDto studySessionRequestDto = StudySessionRequestDto.builder()
                .userId(userResponseDto.getId())
                .minutes(minutes)
                .notes(notes)
                .subjectId(subjectId)
                .build();

        StudySessionResponseDto studySessionResponseDto = (StudySessionResponseDto)studySessionService.create(studySessionRequestDto);

        List<Long> sessionslist = new ArrayList<>();
        sessionslist.addAll(userResponseDto.getStudySessionsID());
        sessionslist.add(studySessionResponseDto.getId());


        UserRequestDto userRequestDto = UserRequestDto.builder()
                .studySessionsID(sessionslist)
                .username(userResponseDto.getUsername())
                .fullname(userResponseDto.getFullname())
                .subjectsId(userResponseDto.getSubjectsId())
                .password(userResponseDto.getPasswordHash())
                .build();

        userService.update(userRequestDto,userResponseDto.getId());

        return "redirect:sessions";
    }


    @GetMapping("/{id}")
    public String sessionsDeatails(Model model, @PathVariable("id") Long sessionsId){
        model.addAttribute("session",studySessionService.read(sessionsId));
        return "deatailsSessions";
    }



}
