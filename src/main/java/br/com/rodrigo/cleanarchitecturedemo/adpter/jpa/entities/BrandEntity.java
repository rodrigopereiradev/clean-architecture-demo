package br.com.rodrigo.cleanarchitecturedemo.adpter.jpa.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_BRAND")
public class BrandEntity {

    @Id
    @Column(name = "ID")
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
