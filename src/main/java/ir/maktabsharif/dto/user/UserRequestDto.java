package ir.maktabsharif.dto.user;


import ir.maktabsharif.dao.StudySession;
import ir.maktabsharif.dao.Subject;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserRequestDto {


    private String username;
    private String password;
    private String fullname;

    private List<Long> subjectsId;

    private List<Long> studySessionsID;

}
