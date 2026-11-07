package ir.maktabsharif.command;

import ir.maktabsharif.dao.StudySession;
import ir.maktabsharif.dto.studySession.StudySessionRequestDto;
import ir.maktabsharif.dto.studySession.StudySessionResponseDto;
import ir.maktabsharif.repository.studySession.StudySessionRepository;
import ir.maktabsharif.repository.subject.SubjectRepository;
import ir.maktabsharif.repository.user.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class StudySessionCommand implements Commandable<StudySession, StudySessionRequestDto, StudySessionResponseDto> {

    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    public StudySessionCommand(UserRepository userRepository, SubjectRepository subjectRepository) {
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public StudySession requestToDao(StudySessionRequestDto studySessionRequestDto) {
        return StudySession.builder()
                .user(userRepository.read(studySessionRequestDto.getUserId()))
                .notes(studySessionRequestDto.getNotes())
                .minutes(studySessionRequestDto.getMinutes())
                .subject(subjectRepository.read(studySessionRequestDto.getSubjectId()))
                .build();
    }

    @Override
    public StudySessionResponseDto daoToResponse(StudySession studySession) {
        return StudySessionResponseDto.builder()
                .id(studySession.getId())
                .minutes(studySession.getMinutes())
                .notes(studySession.getNotes())
                .userId(studySession.getUser().getId())
                .subjectId(studySession.getSubject().getId())
                .build();
    }
}
