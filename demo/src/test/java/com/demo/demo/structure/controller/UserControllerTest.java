package com.demo.demo.structure.controller;

import com.demo.demo.structure.facade.UserControllerFacade;
import com.demo.demo.structure.mapper.UserControllerMapper;
import com.demo.demo.structure.model.bin.CreateUserInputBin;
import com.demo.demo.structure.model.bin.CreateUserOutputBin;
import com.demo.demo.structure.model.dto.CreateUserDto;
import com.demo.demo.structure.model.resource.CreateUserResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private UserControllerFacade facade;

    @Mock
    private UserControllerMapper mapper;

    @InjectMocks
    private UserController controller;

    @Test
    void createUser_shouldReturn200AndMappedResource() {
        CreateUserDto inputDto = new CreateUserDto();
        CreateUserInputBin inputBin = CreateUserInputBin.builder()
                .name("Mario")
                .lastname("Rossi")
                .email("mario.rossi@example.com")
                .build();

        CreateUserOutputBin outputBin = CreateUserOutputBin.builder()
                .id(1L)
                .build();

        CreateUserResource resource = new CreateUserResource();
        resource.setId(1L);

        when(mapper.fromDtoToBin(inputDto)).thenReturn(inputBin);
        when(facade.createUser(inputBin)).thenReturn(outputBin);
        when(mapper.fromBinToDto(outputBin)).thenReturn(resource);

        ResponseEntity<CreateUserResource> response = controller.createUser(inputDto);

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCode().value());
        assertEquals(1L, response.getBody().getId());

        verify(mapper).fromDtoToBin(inputDto);
        verify(facade).createUser(inputBin);
        verify(mapper).fromBinToDto(outputBin);
    }
}