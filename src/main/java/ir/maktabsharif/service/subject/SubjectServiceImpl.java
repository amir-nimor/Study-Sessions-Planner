package ir.maktabsharif.service.subject;

import ir.maktabsharif.command.SubjectCommand;
import ir.maktabsharif.dao.Subject;
import ir.maktabsharif.dto.subject.SubjectRequestDto;
import ir.maktabsharif.dto.subject.SubjectResponseDto;
import ir.maktabsharif.exception.BuisenesException;
import ir.maktabsharif.exception.RepositoryException;
import ir.maktabsharif.exception.ValidationExceptio;
import ir.maktabsharif.repository.subject.SubjectRepository;
import ir.maktabsharif.util.Ruls;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService<Subject, Long, SubjectRequestDto, SubjectResponseDto> {

    private final SubjectRepository repository;
    private final SubjectCommand command;

    public SubjectServiceImpl(SubjectRepository subjectRepository, SubjectCommand subjectCommand) {
        this.repository = subjectRepository;
        this.command = subjectCommand;
    }

    private void validation(SubjectRequestDto subjectRequestDto) {
        Ruls.check(subjectRequestDto.getName().isBlank(), "your name is empty");
    }

    @Override
    @CacheEvict(value = "SubjectCache", allEntries = true)
    public SubjectResponseDto create(SubjectRequestDto subjectRequestDto) {
        try {
            validation(subjectRequestDto);
            Subject model = command.requestToDao(subjectRequestDto);
            return command.daoToResponse(repository.create(model));
        } catch (ValidationExceptio | RepositoryException e) {
            throw new BuisenesException("service is failed => " + e.getMessage());
        }
    }

    @Override
    @CacheEvict(value = "SubjectCache", allEntries = true)
    public SubjectResponseDto update(SubjectRequestDto subjectRequestDto) {
        try {
            validation(subjectRequestDto);
            Subject model = command.requestToDao(subjectRequestDto);
            return command.daoToResponse(repository.update(model));
        } catch (ValidationExceptio | RepositoryException e) {
            throw new BuisenesException("service is failed => " + e.getMessage());
        }
    }

    @Override
    @CacheEvict(value = "SubjectCache", allEntries = true)
    public Long delete(Long aLong) {
        try {
            return repository.delete(aLong);
        } catch (RepositoryException e) {
            throw new BuisenesException("service is failed => " + e.getMessage());
        }
    }

    @Override
    public SubjectResponseDto read(Long aLong) {
        try {
            return command.daoToResponse(repository.read(aLong));
        } catch (RepositoryException e) {
            throw new BuisenesException("service is failed => " + e.getMessage());
        }
    }

    @Override

    public List<SubjectResponseDto> findAll() {
        try {
            List<Subject> tList = repository.findAll();
            return tList.stream().map(command::daoToResponse).toList();
        } catch (RepositoryException e) {
            throw new BuisenesException("service is failed => " + e.getMessage());
        }
    }

    @Override
    @Cacheable("SubjectCache")
    public List<SubjectResponseDto> getUserSubjects(Long userId) {
        try {
            List<Subject> tList = repository.getUserSubjects(userId);
            return tList.stream().map(command::daoToResponse).toList();
        } catch (RepositoryException e) {
            throw new BuisenesException("service is failed => " + e.getMessage());
        }
    }
}
