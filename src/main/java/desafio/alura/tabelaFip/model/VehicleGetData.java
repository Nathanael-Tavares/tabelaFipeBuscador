package desafio.alura.tabelaFip.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VehicleGetData(String codigo, String nome) {
}
