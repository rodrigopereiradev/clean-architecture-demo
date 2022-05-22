package br.com.rodrigo.cleanarchitecturedemo.adpter.jpa.entities;

import br.com.rodrigo.cleanarchitecturedemo.domain.models.Brand;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TB_PRODUCT")
public class ProductEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 722149011108207672L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToOne
    @JoinColumn(name = "ID_BRAND")
    private BrandEntity brand;

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
