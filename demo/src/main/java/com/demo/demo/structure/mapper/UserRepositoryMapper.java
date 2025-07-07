package com.demo.demo.structure.mapper;


import com.demo.demo.structure.model.bin.CreateUserInputBin;
import com.demo.demo.structure.model.bin.GetUserOutputBin;
import com.demo.demo.structure.model.bin.UserSearchOutputBin;
import com.demo.demo.structure.model.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRepositoryMapper {
     User fromBinToEntity(CreateUserInputBin createUserInputBin);
     GetUserOutputBin fromEntityToBin(User user);
     UserSearchOutputBin fromEntityToBinResearch(User user);

}
