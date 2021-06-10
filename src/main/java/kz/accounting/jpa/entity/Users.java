package kz.accounting.jpa.entity;

import kz.accounting.jpa.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users", schema = "user_data")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Users extends BaseEntity {

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;
}
