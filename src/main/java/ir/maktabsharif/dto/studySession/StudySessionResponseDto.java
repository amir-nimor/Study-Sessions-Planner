package ir.maktabsharif.dto.studySession;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StudySessionResponseDto {

    private Long id;

    private Long subjectId;

    private Integer minutes;

    private String notes;

    private Long userId;
}
