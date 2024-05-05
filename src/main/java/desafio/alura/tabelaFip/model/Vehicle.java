package desafio.alura.tabelaFip.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Vehicle(String Valor, String Marca, String Modelo, String AnoModelo, String Combustivel ) {
}
