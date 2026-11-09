package ir.maktabsharif.service.studySession;

import ir.maktabsharif.command.StudySessionCommand;
import ir.maktabsharif.dao.StudySession;
import ir.maktabsharif.dto.studySession.StudySessionRequestDto;
import ir.maktabsharif.dto.studySession.StudySessionResponseDto;
import ir.maktabsharif.exception.BuisenesException;
import ir.maktabsharif.exception.RepositoryException;
import ir.maktabsharif.exception.ValidationExceptio;
import ir.maktabsharif.repository.studySession.StudySessionRepository;
import ir.maktabsharif.util.Ruls;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudySessionServiceImpl implements StudySessionService<StudySession, Long, StudySessionRequestDto, StudySessionResponseDto> {

    private final StudySessionRepository repository;
    private final StudySessionCommand command;

    public StudySessionServiceImpl(StudySessionRepository repository, StudySessionCommand command) {
        this.repository = repository;
        this.command = command;
    }

    private void validation(StudySessionRequestDto studySessionRequestDto) {
        Ruls.check(studySessionRequestDto.getNotes().isBlank(), "your studySession is empty");
        Ruls.check(studySessionRequestDto.getMinutes() == null, "your studySession is empty");
    }

    @Override
    @CacheEvict(value = "StudySessionCoach", allEntries = true)
    public StudySessionResponseDto create(StudySessionRequestDto studySessionRequestDto) {
        try {
            validation(studySessionRequestDto);
            StudySession model = command.requestToDao(studySessionRequestDto);
            return command.daoToResponse(repository.create(model));
        } catch (ValidationExceptio | RepositoryException e) {
            throw new BuisenesException("service is failed => " + e.getMessage());
        }
    }

    @Override
    @CacheEvict(value = "StudySessionCoach", allEntries = true)
    public StudySessionResponseDto update(StudySessionRequestDto studySessionRequestDto) {
        try {
            validation(studySessionRequestDto);
            StudySession model = command.requestToDao(studySessionRequestDto);
            return command.daoToResponse(repository.update(model));
        } catch (ValidationExceptio | RepositoryException e) {
            throw new BuisenesException("service is failed => " + e.getMessage());
        }
    }

    @Override
    @CacheEvict(value = "StudySessionCoach", allEntries = true)
    public Long delete(Long aLong) {
        try {
            return repository.delete(aLong);
        } catch (RepositoryException e) {
            throw new BuisenesException("service is failed => " + e.getMessage());
        }
    }

    @Override
    public StudySessionResponseDto read(Long aLong) {
        try {
            return command.daoToResponse(repository.read(aLong));
        } catch (RepositoryException e) {
            throw new BuisenesException("service is failed => " + e.getMessage());
        }
    }

    @Override
    @Cacheable("StudySessionCoach")
    public List<StudySessionResponseDto> findAll() {
        try {
            List<StudySession> tList = repository.findAll();
            return tList.stream().map(command::daoToResponse).toList();
        } catch (RepositoryException e) {
            throw new BuisenesException("service is failed => " + e.getMessage());
        }
    }

    @Override
    public List<StudySessionResponseDto> userStudySession(Long userId) {
        try {
            return repository.userStudySession(userId).stream().map(s -> command.daoToResponse(s)).toList();
        } catch (RepositoryException e) {
            throw new BuisenesException("your user id is not founded => " + e.getMessage());
        }
    }


}
