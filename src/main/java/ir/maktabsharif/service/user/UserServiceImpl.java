package ir.maktabsharif.service.user;

import ir.maktabsharif.command.Commandable;
import ir.maktabsharif.command.UserCommand;
import ir.maktabsharif.dao.User;
import ir.maktabsharif.dto.user.UserRequestDto;
import ir.maktabsharif.dto.user.UserResponseDto;
import ir.maktabsharif.exception.BuisenesException;
import ir.maktabsharif.exception.RepositoryException;
import ir.maktabsharif.exception.ValidationExceptio;
import ir.maktabsharif.repository.user.UserRepository;
import ir.maktabsharif.util.Ruls;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService<User, Long, UserRequestDto, UserResponseDto> {


    private final UserCommand command;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserCommand commandable, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.command = commandable;
        this.repository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private void validation(UserRequestDto userRequestDto) {
        Ruls.check(userRequestDto.getUsername().isBlank(), "your user name is empty");
        Ruls.check(userRequestDto.getPassword().isBlank(), "your user pass is empty");
        Ruls.check(userRequestDto.getFullname().isBlank(), "your name full is empty");
    }

    @Override
    @CacheEvict(value = "UserCash", allEntries = true)
    public UserResponseDto create(UserRequestDto userRequestDto) {
        try {
            validation(userRequestDto);
            //password hash
            userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
            User model = command.requestToDao(userRequestDto);
            return command.daoToResponse(repository.create(model));
        } catch (ValidationExceptio | RepositoryException e) {
            throw new BuisenesException("service is failed => " + e.getMessage());
        }
    }

    @Override
    @CacheEvict(value = "UserCash", allEntries = true)
    public UserResponseDto update(UserRequestDto userRequestDto, Long id) {
        try {
            validation(userRequestDto);
            User model = command.requestToDao(userRequestDto);
            model.setId(id);
            return command.daoToResponse(repository.update1(model, id));
        } catch (ValidationExceptio | RepositoryException e) {
            throw new BuisenesException("service is failed => " + e.getMessage());
        }
    }

    @Override
    @CacheEvict(value = "UserCash", allEntries = true)
    public Long delete(Long aLong) {
        try {
            return repository.delete(aLong);
        } catch (RepositoryException e) {
            throw new BuisenesException("service is failed => " + e.getMessage());
        }
    }

    @Override
    public UserResponseDto read(Long aLong) {
        try {
            return command.daoToResponse(repository.read(aLong));
        } catch (RepositoryException e) {
            throw new BuisenesException("service is failed => " + e.getMessage());
        }
    }

    @Override
    @Cacheable("UserCash")
    public List<UserResponseDto> findAll() {
        try {
            List<User> tList = repository.findAll();
            return tList.stream().map(command::convertWitOuthLazyException).toList();
        } catch (RepositoryException e) {
            throw new BuisenesException("service is failed => " + e.getMessage());
        }
    }

    @Override
    public UserResponseDto findByUserrName(String username) {
        try {
            return command.daoToResponse(repository.findByUserName(username));
        } catch (RepositoryException e) {
            throw new BuisenesException("your find by username is failed => " + e.getMessage());
        }
    }
}
