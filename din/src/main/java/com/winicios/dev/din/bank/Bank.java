package com.winicios.dev.din.bank;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "bank")
@Entity(name = "bank")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Bank {

   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "hexadecimal_color")
    private String hexadecimalColor;
}
