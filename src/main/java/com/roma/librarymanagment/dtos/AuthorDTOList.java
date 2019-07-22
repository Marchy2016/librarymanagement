package com.roma.librarymanagment.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthorDTOList {
    List<AuthorDTO> authorDTOS;
}
