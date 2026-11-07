package ir.maktabsharif.dao;


import ir.maktabsharif.dao.base.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "Study_Sessions")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StudySession extends BaseModel<Long> {


    @ManyToOne
    private Subject subject;

    private Integer minutes;

    private String notes;

    @ManyToOne
    private User user;
}
