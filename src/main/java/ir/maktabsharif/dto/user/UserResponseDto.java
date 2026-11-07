package ir.maktabsharif.dto.user;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserResponseDto {

    private Long id;

    private String username;

    private String fullname;

    private List<Long> subjectsId;

    private List<Long> studySessionsID;

}
