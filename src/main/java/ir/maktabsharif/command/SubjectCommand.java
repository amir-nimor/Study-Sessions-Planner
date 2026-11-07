package ir.maktabsharif.command;

import ir.maktabsharif.dao.Subject;
import ir.maktabsharif.dto.subject.SubjectRequestDto;
import ir.maktabsharif.dto.subject.SubjectResponseDto;
import ir.maktabsharif.repository.user.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class SubjectCommand implements Commandable<Subject, SubjectRequestDto, SubjectResponseDto> {

    private final UserRepository userRepository;

    public SubjectCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Subject requestToDao(SubjectRequestDto subjectRequestDto) {
        return Subject.builder()
                .user(userRepository.read(subjectRequestDto.getUserId()))
                .name(subjectRequestDto.getName())
                .build();
    }

    @Override
    public SubjectResponseDto daoToResponse(Subject subject) {
        return SubjectResponseDto.builder()
                .id(subject.getId())
                .name(subject.getName())
                .userId(subject.getUser().getId())
                .build();
    }
}
