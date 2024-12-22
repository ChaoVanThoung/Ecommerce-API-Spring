package co.istad.ecommerceapi.domain;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;



@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 3)
    private String cvv;

    @Column(nullable = false, length = 20)
    private String number;

    @Column(nullable = false)
    private LocalDate expiryDate;

    

    @OneToOne()
    private User user;

    @ManyToOne()
    private CardType cardType;

}
