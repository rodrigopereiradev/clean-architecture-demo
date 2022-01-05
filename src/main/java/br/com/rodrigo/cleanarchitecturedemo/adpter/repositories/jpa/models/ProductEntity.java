package br.com.rodrigo.cleanarchitecturedemo.adpter.repositories.jpa.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TB_PRODUCT")
public class ProductEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

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
