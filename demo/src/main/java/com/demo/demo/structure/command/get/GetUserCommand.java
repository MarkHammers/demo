package com.demo.demo.structure.command.get;

import com.demo.demo.structure.command.BaseCommand;
import com.demo.demo.structure.mapper.UserRepositoryMapper;
import com.demo.demo.structure.model.bin.CreateUserInputBin;
import com.demo.demo.structure.model.bin.CreateUserOutputBin;
import com.demo.demo.structure.model.bin.GetUserInputBin;
import com.demo.demo.structure.model.bin.GetUserOutputBin;
import com.demo.demo.structure.model.domain.User;
import com.demo.demo.structure.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GetUserCommand extends BaseCommand<GetUserOutputBin> {

    private final GetUserInputBin getUserInputBin;
    private UserService userService;
    private UserRepositoryMapper mapper;


    public GetUserCommand(GetUserInputBin getUserInputBin) {
        this.getUserInputBin = getUserInputBin;
    }

    @Override
    public GetUserOutputBin execute() {
        User user = userService.getUserById(getUserInputBin.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Utente non trovato con ID: " + getUserInputBin.getId()
                ));
        return mapper.fromEntityToBin(user);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setMapper(UserRepositoryMapper mapper) {
        this.mapper = mapper;
    }
}
