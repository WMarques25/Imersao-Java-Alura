import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 3 S�ries de acordo com IMDB
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json";
        String IMDB_KEY = System.getenv("IMDB_KEY");
        System.out.println(IMDB_KEY);

        // String url = "https://imdb-api.com/en/API/Top250Movies/" + IMBD_KEY;  (API da imdb com instabilidade, usando arquivo pronto disponibilizado pela Alura)
        
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados 
        for (int i = 0; i < 3; i++) {
            Map<String,String> filme = listaDeFilmes.get(i);
            System.out.println("\u001b[37;1mTitulo:\u001b[44m " + filme.get("title") + " \u001b[0m");
            System.out.println("\u001b[37;1mPoster:" + " \u001b[0m" + filme.get("image"));
            double imDbRating = Double.parseDouble(filme.get("imDbRating"));
            System.out.println(imDbRating);

            for( int j = 0; j < (int)imDbRating; j++){
                System.out.print("??");
            }
            System.out.println("\n");
        }
    }
}
