package com.example.library.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Book {
    private Long id;

    @NotBlank
    @Size(min = 1, max = 100)
    private String title;

    @NotBlank
    private String author;

    
}
