package com.demo.demo.structure.mapper;


import com.demo.demo.structure.model.bin.CreateUserInputBin;
import com.demo.demo.structure.model.bin.GetUserOutputBin;
import com.demo.demo.structure.model.bin.UpdateUserInputBin;
import com.demo.demo.structure.model.domain.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserRepositoryMapper {
     User fromBinToEntity(CreateUserInputBin createUserInputBin);
     GetUserOutputBin fromEntityToBin(User user);
}
