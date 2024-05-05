package desafio.alura.tabelaFip.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record Modeldata(List<VehicleGetData> modelos) {
}
