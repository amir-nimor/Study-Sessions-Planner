package ir.maktabsharif.dao;


import ir.maktabsharif.dao.base.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "subjects")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Subject extends BaseModel<Long> {


    @Column(nullable = false)
    private String name;


    @ManyToOne
    private User user;
}
