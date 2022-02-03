package br.com.rodrigo.cleanarchitecturedemo.adpter.jpa.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TB_PRODUCT")
public class ProductEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "VALUE")
    private BigDecimal value;

    @Column(name = "ACTIVE")
    private Boolean isActive;

    @Column(name = "CREATION_DATE")
    private LocalDateTime createdIn;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updatedIn;

}
