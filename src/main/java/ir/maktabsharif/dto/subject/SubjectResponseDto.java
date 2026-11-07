package ir.maktabsharif.dto.subject;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SubjectResponseDto {

    private Long id;

    private String name;

    private Long userId;
}
