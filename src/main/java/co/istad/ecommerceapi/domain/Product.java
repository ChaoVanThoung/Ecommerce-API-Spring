package co.istad.ecommerceapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String productName;

    private String size;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Boolean available;

    @Column(nullable = false)
    private Integer stock;

    private Integer numberOfPeopleBought;



    @ManyToOne()
    private Category category;

//    @ManyToOne()
//    private Order order;

//    @OneToMany(mappedBy = "product")
//    private List<Order> orders;

    @OneToMany(mappedBy = "product")
    private List<Order> orders;


}
