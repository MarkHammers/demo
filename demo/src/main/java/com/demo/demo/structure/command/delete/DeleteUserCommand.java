package com.demo.demo.structure.command.delete;

import com.demo.demo.structure.command.BaseCommand;
import com.demo.demo.structure.mapper.UserRepositoryMapper;
import com.demo.demo.structure.model.bin.DeleteUserInputBin;
import com.demo.demo.structure.model.bin.UpdateUserInputBin;
import com.demo.demo.structure.model.domain.User;
import com.demo.demo.structure.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DeleteUserCommand extends BaseCommand<Void> {

    private final DeleteUserInputBin deleteUserInputBin;
    private UserService userService;
    private UserRepositoryMapper mapper;


    public DeleteUserCommand(DeleteUserInputBin deleteUserInputBin) {
        this.deleteUserInputBin = deleteUserInputBin;
    }

    @Override
    public Void execute() {
        userService.deleteById(deleteUserInputBin.getId());
        return null;
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
