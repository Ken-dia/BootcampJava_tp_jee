package com.samanecorp.secureapp.service;

import com.samanecorp.secureapp.dao.IUser;
import com.samanecorp.secureapp.dao.LoginDao;
import com.samanecorp.secureapp.dao.UserImpl;
import com.samanecorp.secureapp.dto.UserDTO;
import com.samanecorp.secureapp.entities.UserEntity;
import com.samanecorp.secureapp.exception.EntityNotFoundException;
import com.samanecorp.secureapp.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class LoginService {
    private static Logger logger = LoggerFactory.getLogger(LoginService.class);
    private LoginDao loginDao = new LoginDao();
    private IUser userDao =  new UserImpl();

    public Optional<UserDTO> login(String email, String password) {
        if(email.equals("hamidou.dia@example.com") && password.equals("password")) {
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(email);
            userDTO.setPassword(password);
            return Optional.of(userDTO);
        } else {
            return Optional.empty();
        }
    }
    public Optional<UserDTO> loginMockito(String email, String password) {
        logger.info("Tentative de login {} et {}", email, password);
        Optional<UserEntity> userEntityOption = loginDao.loginUser(email, password);
        if(userEntityOption.isPresent()) {
            UserEntity userEntity = userEntityOption.get();
            UserDTO userDTO = UserMapper.userEntityToUserDTO(userEntity);
            return Optional.of(userDTO);
        }
        return Optional.ofNullable(null);
    }
    public Optional<UserDTO> loginUser (String email, String password) {

        logger.info("Tentattive email : {} pwd : {}", email, password);

        Optional<UserEntity> userEntityOption = loginDao.loginUser(email, password);
        if (userEntityOption.isPresent()) {
            UserEntity userEntity = userEntityOption.get();
            UserDTO userDto = UserMapper.userEntityToUserDTO(userEntity);

            return Optional.of(userDto) ;
        } else {
            return Optional.ofNullable(null);
        }

    }
    public Optional<UserDTO> logException (String email, String password) {

        logger.info("Tentative email : {} pwd : {}", email, password);

        return loginDao.logException(email, password)
                .map(user -> {
                    UserDTO userDto = UserMapper.userEntityToUserDTO(user);
                    logger.info("infos correct !");
                    return Optional.of(userDto) ;
                }).orElseThrow( () -> new EntityNotFoundException("infos incorrect."));
    }
    public boolean save (UserDTO userDTO) {
        return userDao.addUser(UserMapper.userDtoToUserEntity(userDTO));
    }




}
