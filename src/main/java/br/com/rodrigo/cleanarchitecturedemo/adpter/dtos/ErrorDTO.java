package br.com.rodrigo.cleanarchitecturedemo.adpter.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {

    private Integer statusCode;
    private String statusName;

    @JsonInclude(Include.NON_NULL)
    private String description;

    @JsonInclude(Include.NON_NULL)
    private List<String> descriptions;

    public ErrorDTO(Integer statusCode, String statusName, String description) {
        this.statusCode = statusCode;
        this.statusName = statusName;
        this.description = description;
    }

    public ErrorDTO(Integer statusCode, String statusName, List<String> descriptions) {
        this.statusCode = statusCode;
        this.statusName = statusName;
        this.descriptions = descriptions;
    }
}
