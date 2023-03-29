import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 3 Séries de acordo com IMDB
        String API_KEY = System.getenv("API_KEY");
        System.out.println(API_KEY);

        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";
        // ExtratorConteudo extrator = new ExtratorConteudoIMDB();

        String url = "https://api.nasa.gov/planetary/apod?api_key=" + API_KEY + "&start_date=2022-07-4&end_date=2022-07-10";  // (API da imdb com instabilidade, usando arquivo pronto disponibilizado pela Alura)
        ExtratorConteudo extrator = new ExtratorConteudoNasa();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        
        // exibir e manipular os dados 
        
        List<Conteudo> conteudos = extrator.extrairConteudos(json);

        var gerador = new ZapStickers();

        var diretorio = new File("saida/");
        diretorio.mkdir();

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);
           
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = conteudo.getTitulo();      

            System.out.println("\u001b[37;1mTitulo:\u001b[44m " + conteudo.getTitulo() + " \u001b[0m");
            System.out.println("\u001b[37;1mPoster:" + " \u001b[0m" + conteudo.getUrlImagem());
            
            gerador.Criar(inputStream, nomeArquivo);

            System.out.println("\n");
        }
    }
}
