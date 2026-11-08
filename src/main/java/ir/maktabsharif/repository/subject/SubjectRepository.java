package ir.maktabsharif.repository.subject;

import ir.maktabsharif.dao.Subject;
import ir.maktabsharif.repository.base.BaseRepository;

import java.util.List;

public interface SubjectRepository extends BaseRepository<Subject,Long> {

    List<Subject> getUserSubjects(Long userId);
}
