package ir.maktabsharif.command;

import ir.maktabsharif.dao.User;
import ir.maktabsharif.dao.base.BaseModel;
import ir.maktabsharif.dto.user.UserRequestDto;
import ir.maktabsharif.dto.user.UserResponseDto;
import ir.maktabsharif.repository.studySession.StudySessionRepository;
import ir.maktabsharif.repository.subject.SubjectRepository;
import ir.maktabsharif.repository.subject.SubjectRepositoryImpl;
import org.springframework.stereotype.Component;

@Component
public class UserCommand implements Commandable<User, UserRequestDto, UserResponseDto> {

    private final SubjectRepository subjectRepository;
    private final StudySessionRepository studySessionRepository;

    public UserCommand(SubjectRepository subjectRepository, StudySessionRepository studySessionRepository) {
        this.subjectRepository = subjectRepository;
        this.studySessionRepository = studySessionRepository;
    }

    public UserResponseDto convertWitOuthLazyException(User user){
        return UserResponseDto.builder()
                .fullname(user.getFullname())
                .username(user.getUsername())
                .id(user.getId())
                .build();
    }

    @Override
    public User requestToDao(UserRequestDto userRequestDto) {
        User user = User.builder()
                .username(userRequestDto.getUsername())
                .password(userRequestDto.getPassword())
                .studySessions(userRequestDto.getStudySessionsID().stream().map(u -> studySessionRepository.read(u)).toList())
                .subjects(userRequestDto.getSubjectsId().stream().map(u -> subjectRepository.read(u)).toList())
                .fullname(userRequestDto.getFullname())
                .build();
        return user;
    }

    @Override
    public UserResponseDto daoToResponse(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .fullname(user.getFullname())
                .subjectsId(user.getSubjects().stream().map(u -> u.getId()).toList())
                .studySessionsID(user.getStudySessions().stream().map(u -> u.getId()).toList())
                .username(user.getUsername())
                .passwordHash(user.getPassword())
                .build();
    }
}
