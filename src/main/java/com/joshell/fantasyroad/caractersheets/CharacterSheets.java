package com.joshell.fantasyroad.caractersheets;


import com.joshell.fantasyroad.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_sheets")
public class CharacterSheets {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "jsonb")
    private String charSheet;

    @OneToOne(mappedBy = "characterSheets")
    private User user;



}
