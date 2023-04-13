package com.storage.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false, name = "plan_id", referencedColumnName = "id")
    private Plan plan;

    @NotNull
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String password;
}
