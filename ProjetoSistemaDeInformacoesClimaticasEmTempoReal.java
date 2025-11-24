import org.json.JSONObject;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ProjetoSistemaDeInformacoesClimaticasEmTempoReal {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Digite o nome da cidade: ");
		String cidade = scanner.nextLine();
		System.out.println();

		try {
			String dadosClimaticos = getDadosClimaticos(cidade);
			// Código 1006 significa localização não é encontrada.
			if (dadosClimaticos.contains("\"code\":1006")) {
				System.out.println("Localização não encontrada. Por favor, tente novamente");
			} else {
				imprimirDadosClimaticos(dadosClimaticos);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	public static String getDadosClimaticos(String cidade) throws Exception {
		String apiKey = Files.readString(Paths.get("api-key.txt")).trim();

		String formataNomeCidade = URLEncoder.encode(cidade, StandardCharsets.UTF_8);
		String apiUrl = "http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + formataNomeCidade;
		HttpRequest request = HttpRequest.newBuilder()  //Começa a construçao de uma nova solicitação HTTP
			.uri(URI.create(apiUrl))  // Este método define o URI da solicatação HTTP.
			.build(); // Finaliza a construção da solicitação HTTP.

		//Criar objeto enviar solicitações HTTP e receber respostar HTTP, para acessar o site WeatherAPI
		HttpClient client = HttpClient.newHttpClient();

		// Agora vamos enviar requisições HTTP e receber respostas HTTP, comunicar com o site da API.
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		return response.body(); //Retorna os dados meteorológicos obtidos
	}

	//Método para imprimir os dados meteorológicos de forma organizada
	public static void imprimirDadosClimaticos(String dados) {
		//System.out.println("Dados originais (JSON) obtidos no site meteorológico: " + dados);

		JSONObject dadosJson = new JSONObject(dados);
		JSONObject informacoesMeteorologicas = dadosJson.getJSONObject("current");

		// Extrai os dados da localização
		String cidade = dadosJson.getJSONObject("location").getString("name");
		String pais = dadosJson.getJSONObject("location").getString("country");

		//Extrai dados adicionais
		String condicaoTempo = informacoesMeteorologicas.getJSONObject("condition").getString("text");
		int umidade = informacoesMeteorologicas.getInt("humidity");
		float velocidadeVento = informacoesMeteorologicas.getFloat("wind_kph");
		float pressaoAtmosferica = informacoesMeteorologicas.getFloat("pressure_mb");
		float sesacaoTermica = informacoesMeteorologicas.getFloat("feelslike_c");
		float temperaturaAtual = informacoesMeteorologicas.getFloat("temp_c");

		// Extrai data e hora da string retornada pela API
		String dataHoraString = informacoesMeteorologicas.getString("last_updated");

		// Imprime informações atuais
		System.out.println("Informações Meteorológicas para " + cidade + ", " + pais);
		System.out.println("Data e hora: " + dataHoraString);
		System.out.println("Temperatura Atual: " + temperaturaAtual + "°C");
		System.out.println("Sensação Térmica: " + sesacaoTermica + "°C");
		System.out.println("Condição do Tempo: " + condicaoTempo);
		System.out.println("Umidade: " + umidade + "%");
		System.out.println("Velocidade do Vento: " + velocidadeVento + " km/h");
		System.out.print("Pressão Atmosférica: " + pressaoAtmosferica + "mb");
	}
}
