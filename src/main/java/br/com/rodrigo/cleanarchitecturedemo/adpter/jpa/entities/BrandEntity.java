package br.com.rodrigo.cleanarchitecturedemo.adpter.jpa.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_BRAND")
public class BrandEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -2693175128882193652L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FANTASY_NAME")
    private String fantasyName;

    @Column(name = "CORPORATE_NAME")
    private String corporateName;

    @Column(name = "ACTIVE")
    private Boolean isActive;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createdIn;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updatedIn;

}
