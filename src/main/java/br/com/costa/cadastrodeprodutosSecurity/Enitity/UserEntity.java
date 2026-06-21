package br.com.costa.cadastrodeprodutosSecurity.Enitity;

import br.com.costa.cadastrodeprodutosSecurity.Enitity.EntityEnum.EntityStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String name;
    private String email;
    private String password;
    private EntityStatus status;

}
