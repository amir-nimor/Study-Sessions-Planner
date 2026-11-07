package ir.maktabsharif.dao;


import ir.maktabsharif.dao.base.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User extends BaseModel<Long> {

    private String username;
    private String password;
    private String fullname;

    @OneToMany
    private List<Subject> subjects;

    @OneToMany
    private List<StudySession> studySessions;
}
