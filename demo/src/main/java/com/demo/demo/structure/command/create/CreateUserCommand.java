package com.demo.demo.structure.command.create;

import com.demo.demo.structure.command.BaseCommand;
import com.demo.demo.structure.mapper.UserRepositoryMapper;
import com.demo.demo.structure.model.bin.CreateUserInputBin;
import com.demo.demo.structure.model.bin.CreateUserOutputBin;
import com.demo.demo.structure.model.domain.User;
import com.demo.demo.structure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CreateUserCommand extends BaseCommand<CreateUserOutputBin> {

    private final CreateUserInputBin createUserInputBin;
    private UserService userService;
    private UserRepositoryMapper mapper;


    public CreateUserCommand(CreateUserInputBin createUserInputBin) {
        this.createUserInputBin = createUserInputBin;
    }

    @Override
    public CreateUserOutputBin execute() {
        User user = userService.save(mapper.fromBinToEntity(createUserInputBin));
        return CreateUserOutputBin.builder().id(user.getId()).build();
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
