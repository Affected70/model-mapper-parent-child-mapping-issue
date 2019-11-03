package com.example.modelmapper;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ParentDTO {
  private String id;
  private String name;
}
