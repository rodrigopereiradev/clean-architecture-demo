package br.com.rodrigo.cleanarchitecturedemo.domain.models;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Brand {

    private Long id;
    private String fantasyName;
    private String corporateName;
    private Boolean isActive;
    private LocalDateTime createdIn;
    private LocalDateTime updatedIn;

    public void setPropertiesOnCreation() {
        this.isActive = Boolean.FALSE;
        this.createdIn = LocalDateTime.now();
    }

    public void activate() {
        this.isActive = Boolean.TRUE;
        this.updatedIn = LocalDateTime.now();
    }

    public void inactivate() {
        this.isActive = Boolean.FALSE;
        this.updatedIn = LocalDateTime.now();
    }

    public void updateProperties(Brand brand) {
        this.fantasyName = brand.fantasyName;
        this.corporateName = brand.corporateName;
        this.updatedIn = LocalDateTime.now();
    }
}
