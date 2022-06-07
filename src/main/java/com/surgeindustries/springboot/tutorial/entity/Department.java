package com.surgeindustries.springboot.tutorial.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * The Entity annotation tells Spring this is an Entity.
 * Entity annotation handles our tables in the database
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    // ID annotation tells spring our primary key.
    // Generated Value is used to auto increase the id
    // The entity must have one non-parameterized constructor and a parametrized one
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;

    @NotBlank(message = "Please input the Department Name")
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;

}
