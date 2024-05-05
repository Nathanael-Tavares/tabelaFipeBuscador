package desafio.alura.tabelaFip.principal;

import desafio.alura.tabelaFip.model.Vehicle;
import desafio.alura.tabelaFip.model.VehicleGetData;
import desafio.alura.tabelaFip.model.Modeldata;
import desafio.alura.tabelaFip.service.ConvertData;
import desafio.alura.tabelaFip.service.FipDataFetcher;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConvertData convertData = new ConvertData();
    private FipDataFetcher fipDataFetcher = new FipDataFetcher();
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void showMenu() {

        System.out.println("**************************MENU***********************************");
        var addres = "";


        System.out.println("escolha carro, moto, ou caminhão;");
        var vehiclechoice = scanner.nextLine();

        if (vehiclechoice.toLowerCase().contains("car")) {
            addres = "carros/marcas";
        } else if (vehiclechoice.toLowerCase().contains("mot")) {
            addres = "motos/marcas";
        } else {
            addres = "caminhoes/marcas";
        }
        var json = fipDataFetcher.fetch(URL_BASE + addres);
        var vehicleBrand = convertData.getDataList(json, VehicleGetData.class);
        vehicleBrand.stream()
                .sorted(Comparator.comparing(VehicleGetData::codigo))
                .forEach(e -> System.out.println("Codigo: " + e.codigo() + " Marca: " + e.nome()));

        System.out.println("escolha a marca do veiculo(USE O CODIGO): ");
        var vehicleBrandChoice = scanner.nextInt();
        System.out.println(URL_BASE + addres + "/" + vehicleBrandChoice + "/modelos");
        scanner.nextLine();
        json = fipDataFetcher.fetch(URL_BASE + addres + "/" + vehicleBrandChoice + "/modelos");

        var vehicleModels = convertData.getData(json, Modeldata.class);
        var vehicleModelList = vehicleModels.modelos();

       System.out.println("escreva um trecho do nome do carro: ");
       var vehicleName = scanner.nextLine();

       List<VehicleGetData> modelsFiltered = vehicleModelList.stream()
               .filter(m -> m.nome().toLowerCase().contains(vehicleName.toLowerCase()))
               .collect(Collectors.toList());

       System.out.println("\nModelos filtrados");
       modelsFiltered.forEach(e-> System.out.println("MODELO: "+e.nome()+" CODIGO: "+ e.codigo()));
        System.out.println("Digite o codigo modelo do veiculo desejado ");
        var vehicleModelChoice = scanner.nextInt();
        scanner.nextLine();
        json = fipDataFetcher.fetch(URL_BASE + addres + "/" + vehicleBrandChoice + "/modelos" + "/" + vehicleModelChoice + "/anos");
        var vehicleYears = convertData.getDataList(json, VehicleGetData.class);


        for (VehicleGetData vehicleYear : vehicleYears) {

            json = fipDataFetcher.fetch(URL_BASE + addres + "/" + vehicleBrandChoice + "/modelos" + "/" + vehicleModelChoice + "/anos/" + vehicleYear.codigo());
            var vehicles = convertData.getData(json, Vehicle.class);
           System.out.println("Valor: "+ vehicles.Valor() + " Modelo: "+ vehicles.Modelo()+" Ano: " + vehicles.AnoModelo()+" Combustivel: "+ vehicles.Combustivel());

        }


    }
}
