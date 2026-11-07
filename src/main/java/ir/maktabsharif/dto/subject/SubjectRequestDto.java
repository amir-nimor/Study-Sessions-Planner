package ir.maktabsharif.dto.subject;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SubjectRequestDto {

    private String name;

    private Long userId;
}
