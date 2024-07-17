import com.google.gson.Gson;
    import com.google.gson.JsonObject;

    import java.util.Scanner;

    import java.io.IOException;
    import java.net.URI;
    import java.net.http.HttpClient;
    import java.net.http.HttpRequest;
    import java.net.http.HttpResponse;
    import java.util.concurrent.CompletionException;

    public class main {

        static Object Currency(String to, String from, Integer value) {
            final String API_URL = "https://v6.exchangerate-api.com/v6/c5d0c441394469accab13671/latest/" + to;


            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_URL)).build();

            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    String responseBody = response.body();
                    JsonObject replyJson = new Gson().fromJson(responseBody, JsonObject.class);
                    JsonObject conversion_rates = new Gson().fromJson(replyJson.get("conversion_rates"), JsonObject.class);
                    String ratesTo = conversion_rates.get(to).toString();
                    String ratesFrom = conversion_rates.get(from).toString();

                    return value * (Double.parseDouble(ratesTo) / Double.parseDouble(ratesFrom));

                } else {
                    System.out.println("Erro:" + response.statusCode());
                }

            } catch (IOException | InterruptedException e) {
                throw new CompletionException(e);
            }


            return null;
        }

        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);  // Create a Scanner object

            System.out.println("******************************************");
            System.out.println("Seja bem-vindo/a ao Conversor de Moeda =] ");
            System.out.println(" ");
            System.out.println("Favor escolher duas moedas, a primeira que vai ser a moeda atual, e a segunda para ser convertida");
            System.out.println("Segue uma lista das moedas disponiveis");
            System.out.println("1) USD - Dólar americano");
            System.out.println("2) ARS - Peso argentino");
            System.out.println("3) BOB - Boliviano boliviano");
            System.out.println("4) BRL - Real brasileiro");
            System.out.println("5) CLP - Peso chileno");
            System.out.println("6) COP - Peso colombiano");
            System.out.println("7) LYD - Dinar líbio");
            System.out.println("8) SSP - Libra sul-sudanesa");
            System.out.println("9) SYP - Libra síria");
            System.out.println("0) Sair");
            System.out.println("******************************************");

            System.out.println("Escolha a Moeda atual:");

            int option = scanner.nextInt();
            String from;
            String to;

            from = switch (option) {
                case 1 -> "USD";
                case 2 -> "ARS";
                case 3 -> "BOB";
                case 4 -> "BRL";
                case 5 -> "CLP";
                case 6 -> "COP";
                case 7 -> "LYD";
                case 8 -> "SSP";
                case 9 -> "SYP";
                default -> throw new IllegalArgumentException("Opção inválida.");
            };

            System.out.println("Escolha para qual moeda sera convertido:");

            int option2 = scanner.nextInt();

            to = switch (option2) {
                case 1 -> "USD";
                case 2 -> "ARS";
                case 3 -> "BOB";
                case 4 -> "BRL";
                case 5 -> "CLP";
                case 6 -> "COP";
                case 7 -> "LYD";
                case 8 -> "SSP";
                case 9 -> "SYP";
                default -> throw new IllegalArgumentException("Opção inválida.");
            };

            System.out.println("Escolha o valor a ser convertido:");

            int valueConvert = scanner.nextInt();

            System.out.println("O Valor convertido é de: " + Currency(to, from, valueConvert));


        }

    }