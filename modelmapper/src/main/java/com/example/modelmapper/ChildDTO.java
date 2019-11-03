package com.example.modelmapper;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ChildDTO {
  private String id;
  private int age;
  private ParentDTO parent;
}
