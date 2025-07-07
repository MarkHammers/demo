package com.demo.demo.structure.facade;

import com.demo.demo.structure.command.create.CreateUserCommand;
import com.demo.demo.structure.command.delete.DeleteUserCommand;
import com.demo.demo.structure.command.get.GetUserCommand;
import com.demo.demo.structure.command.insert.InsertUserCommand;
import com.demo.demo.structure.command.update.UpdateUserCommand;
import com.demo.demo.structure.model.bin.*;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserControllerFacade {
    private BeanFactory beanFactory;

    public CreateUserOutputBin createUser(CreateUserInputBin createUserInputBin) {
        CreateUserCommand command = beanFactory.getBean(CreateUserCommand.class, createUserInputBin);
        return command.execute();
    }

    public GetUserOutputBin getUser(GetUserInputBin getUserInputBin) {
        GetUserCommand command = beanFactory.getBean(GetUserCommand.class, getUserInputBin);
        return command.execute();
    }

    public void updateUser(UpdateUserInputBin updateUserInputBin) {
        UpdateUserCommand command = beanFactory.getBean(UpdateUserCommand.class, updateUserInputBin);
        command.execute();
    }

    public void deleteUser(DeleteUserInputBin deleteUserInputBin) {
        DeleteUserCommand command = beanFactory.getBean(DeleteUserCommand.class, deleteUserInputBin);
        command.execute();
    }

    public void insertUsersByCsv(MultipartFile file) {
        InsertUserCommand command = beanFactory.getBean(InsertUserCommand.class, file);
        command.execute();
    }

    @Autowired
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

}
