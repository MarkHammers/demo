package com.demo.demo.structure.command.insert;

import com.demo.demo.structure.command.BaseCommand;
import com.demo.demo.structure.mapper.UserRepositoryMapper;
import com.demo.demo.structure.model.bin.CreateUserInputBin;
import com.demo.demo.structure.model.bin.GetUserInputBin;
import com.demo.demo.structure.model.bin.GetUserOutputBin;
import com.demo.demo.structure.model.domain.User;
import com.demo.demo.structure.service.UserService;
import com.demo.demo.structure.util.ParsingCsvUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class InsertUserCommand extends BaseCommand<Void> {

    private final MultipartFile multipartFile;
    private UserService userService;
    private ParsingCsvUtil parsingCsvUtil;


    public InsertUserCommand(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    @Override
    public Void execute() {
        List<User> users = parsingCsvUtil.parseCsv(multipartFile);
        userService.saveAll(users);
        return null;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setParsingCsvUtil(ParsingCsvUtil parsingCsvUtil) {
        this.parsingCsvUtil = parsingCsvUtil;
    }
}
