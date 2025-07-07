package com.demo.demo.structure.mapper;


import com.demo.demo.structure.model.bin.*;
import com.demo.demo.structure.model.dto.CreateUserDto;
import com.demo.demo.structure.model.dto.UpdateUserDto;
import com.demo.demo.structure.model.resource.CreateUserResource;
import com.demo.demo.structure.model.resource.GetUserResource;
import com.demo.demo.structure.model.resource.UserSearchResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserControllerMapper {
     CreateUserInputBin fromDtoToBin(CreateUserDto dto);
     CreateUserResource fromBinToDto(CreateUserOutputBin user);

     GetUserInputBin fromDtoToBinGet(Long id);
     GetUserResource fromBinToDtoGet(GetUserOutputBin user);

     UpdateUserInputBin fromDtoToBinUpdate(UpdateUserDto updateUserDto, Long id);

     DeleteUserInputBin fromDtoToBinDelete(Long id);

     UserSearchInputBin fromDtoToBinSearch(String name, String lastname, String email);

     UserSearchResource fromBinToDtoResource(UserSearchOutputBin researchUserListOutputBin);

}
