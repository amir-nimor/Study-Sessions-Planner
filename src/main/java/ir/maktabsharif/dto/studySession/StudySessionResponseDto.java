package ir.maktabsharif.dto.studySession;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class StudySessionResponseDto {

    private Long id;

    private Long subjectId;

    private Integer minutes;

    private String notes;

    private Long userId;

    @Override
    public String toString() {
        return "StudySessionResponseDto{" +
                "id=" + id +
                ", subjectId=" + subjectId +
                ", minutes=" + minutes +
                ", notes='" + notes + '\'' +
                ", userId=" + userId +
                '}';
    }
}
