package br.com.costa.cadastrodeprodutosSecurity.enitity;

import br.com.costa.cadastrodeprodutosSecurity.config.PasswordConfig;
import br.com.costa.cadastrodeprodutosSecurity.enitity.entityenum.EntityStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name  = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

     PasswordConfig passwordConfig;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private EntityStatus status;

    public UserEntity(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = passwordConfig.passwordEncoder().encode(password);
    }
}
