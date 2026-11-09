package ir.maktabsharif.repository.studySession;

import ir.maktabsharif.dao.StudySession;
import ir.maktabsharif.repository.base.BaseRepository;

import java.util.List;

public interface StudySessionRepository extends BaseRepository<StudySession,Long> {

    List<StudySession> userStudySession(Long userId);
}
