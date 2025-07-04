package com.demo.demo.structure.command.update;

import com.demo.demo.structure.command.BaseCommand;
import com.demo.demo.structure.model.bin.UpdateUserInputBin;
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
public class UpdateUserCommand extends BaseCommand<Void> {

    private final UpdateUserInputBin updateUserInputBin;
    private UserService userService;


    public UpdateUserCommand(UpdateUserInputBin updateUserInputBin) {
        this.updateUserInputBin = updateUserInputBin;
    }

    @Override
    public Void execute() {
        User user = userService.getUserById(updateUserInputBin.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Utente non trovato con ID: " + updateUserInputBin.getId()
                ));

        Optional.ofNullable(updateUserInputBin.getName()).ifPresent(user::setName);
        Optional.ofNullable(updateUserInputBin.getLastname()).ifPresent(user::setLastname);
        Optional.ofNullable(updateUserInputBin.getEmail()).ifPresent(user::setEmail);

        userService.save(user);
        return null;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
