import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 3 Séries de acordo com IMDB
        API api = API.NASA_APOD;

        String url = api.getUrl();
        ExtratorConteudo extrator = api.getExt();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        
        // exibir e manipular os dados 
        List<Conteudo> conteudos = extrator.extrairConteudos(json);

        var gerador = new ZapStickers();

        var diretorio = new File("saida/");
        diretorio.mkdir();

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);
           
            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = conteudo.titulo();      

            System.out.println("\u001b[37;1mTitulo:\u001b[44m " + conteudo.titulo() + " \u001b[0m");
            System.out.println("\u001b[37;1mPoster:" + " \u001b[0m" + conteudo.urlImagem());
            
            gerador.Criar(inputStream, nomeArquivo);

            System.out.println("\n");
        }
    }
}
