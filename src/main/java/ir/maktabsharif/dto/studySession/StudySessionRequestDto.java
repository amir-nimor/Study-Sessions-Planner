package ir.maktabsharif.dto.studySession;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StudySessionRequestDto {

    private Long subjectId;

    private Integer minutes;

    private String notes;

    private Long userId;

}
