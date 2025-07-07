package com.demo.demo.structure.command.research;

import com.demo.demo.structure.command.BaseCommand;
import com.demo.demo.structure.mapper.UserRepositoryMapper;
import com.demo.demo.structure.model.bin.*;
import com.demo.demo.structure.model.domain.User;
import com.demo.demo.structure.service.UserService;
import com.demo.demo.structure.util.UserSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ResearchUserCommand extends BaseCommand<ResearchUserListOutputBin> {

    private final ResearchUserInputBin researchUserInputBin;
    private UserService userService;
    private UserRepositoryMapper mapper;


    public ResearchUserCommand(ResearchUserInputBin researchUserInputBin) {
        this.researchUserInputBin = researchUserInputBin;
    }

    @Override
    public ResearchUserListOutputBin execute() {
        ResearchUserListOutputBin result = new ResearchUserListOutputBin();

        String name = Optional.ofNullable(researchUserInputBin).map(ResearchUserInputBin::getName).orElse(null);
        String lastname = Optional.ofNullable(researchUserInputBin).map(ResearchUserInputBin::getLastname).orElse(null);
        String email = Optional.ofNullable(researchUserInputBin).map(ResearchUserInputBin::getEmail).orElse(null);

        Specification<User> spec = Stream.of(
                        UserSpecification.hasName(name),
                        UserSpecification.hasLastname(lastname),
                        UserSpecification.hasEmail(email)
                )
                .reduce(Specification::and)
                .orElse(null);

        result.setResearchUserOutputBinList(userService.findAll(spec).stream()
                .map(mapper::fromEntityToBinResearch)
                .toList());

        return result;
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
