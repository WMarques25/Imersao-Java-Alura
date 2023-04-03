# Repositório de estudos da #ImersaoJava da Alura

## Aulas  - [Aula 1](https://github.com/WMarques25/Imersao-Java-Alura/tree/Aula1) - [Aula 2](https://github.com/WMarques25/Imersao-Java-Alura/tree/Aula2) - [Aula 3](https://github.com/WMarques25/Imersao-Java-Alura/tree/Aula3) - [Aula 5](https://github.com/WMarques25/Imersao-Java-Alura/tree/Aula5)

## Aula 1

Utilizando Java no VS Code para acessar e consumir API do IMDB (Top 250 Filmes).

[App](https://github.com/WMarques25/Imersao-Java-Alura/blob/main/alura-stickers/src/App.java) - Aplicação principal, acessando e exibindo os dados da API.

[Parser](https://github.com/WMarques25/Imersao-Java-Alura/blob/main/alura-stickers/src/JsonParser.java) - Classe para "parsear", filtrando as informações do arquivo Json

### Desafios Aula 1

1. Utilizando End-Point para acessar as top séries do site.

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json";

2. Configurando a estética da apresentação das informações.

            System.out.println("\u001b[37;1mTitulo:\u001b[44m " + serie.get("title") + " \u001b[0m");
            System.out.println("\u001b[37;1mPoster:" + " \u001b[0m" + serie.get("image"));
            double imDbRating = Double.parseDouble(serie.get("imDbRating"));
            System.out.println(imDbRating);

            for( int j = 0; j < (int)imDbRating; j++){
                System.out.print("⭝︝");
            }

            System.out.println("\n");

3. Utilizando variavel de ambiente para esconder chave de acesso.

            // Definindo a variavel de ambiente no terminal.
            Utilizei no PowerShell:
            $env:IMDB_KEY = "chave"                                         

            // Concatenando url + Chave de acesso da API no codigo.
            String url = "https://imdb-api.com/en/API/TopTVs/" + IMBD_KEY;  

## Aula 2

Importando imagens(arquivo local ou url), transformando em uma nova imagem .png com um texto em fundo transparente.

[Gerador de Stickers](https://github.com/WMarques25/Imersao-Java-Alura/blob/Aula2/alura-stickers/src/ZapStickers.java) - Classe geradora de Stickers para WhatsApp.

### Desafios Aula 2

1. Criando diretório para saida das imagens caso não exista.

            // Gerando o diretório "saida/".
            var diretorio = new File("saida/");
            diretorio.mkdir();                                  

            // Criando a imagem no diretório "saida/" + nome da série.
            gerador.Criar(inputStream, "saida/" + nomeArquivo); 

2. Centralizando o texto na nova imagem.

            // Texto + medidas
            String texto = "Bom";
            FontMetrics fontMetrics = graphics.getFontMetrics();
            Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
            int alturaTexto = (int)retangulo.getHeight();
            int larguraTexto = (int)retangulo.getWidth();

            // Posição do texto em X e Y.
            int posXTexto = (largura - larguraTexto)/2;
            float posYTexto = (novaAltura - altura)/2 + altura + alturaTexto/3;

            // Desenhando o texto na nova imagem.
            graphics.drawString(texto, posXTexto, posYTexto);

3. Alterando a fonte para uma pre-instalada no windows.

            // nova fonte
            var fonte = new Font("Impact", Font.BOLD, 128);

4. Desenhando contorno no texto.

            // Definindo estilo do contorno.
            FontRenderContext fontRenderContext = graphics.getFontRenderContext();
            var textLayout = new TextLayout(texto, fonte, fontRenderContext);

            // Definindo posição.
            Shape outLine = textLayout.getOutline(null);
            AffineTransform transform = graphics.getTransform();
            transform.translate(posXTexto, posYTexto);
            graphics.setTransform(transform);

            // Definindo largura do contorno.
            var outLineStoke = new BasicStroke(largura * 0.004f);
            graphics.setStroke(outLineStoke);

            // Definindo cor e desenhando o contorno.
            graphics.setColor(Color.BLACK);
            graphics.draw(outLine);
            graphics.setClip(outLine);

5. Modificando texto de acordo com o Rating.

            // Atribuindo o texto de acordo com imDbRating no App.java
            String texto;
            if (imDbRating >= 8.5 ){
                texto = "Bom";
            }else{
                texto = "meh";
            }

            // Passando o texto atualizado para o gerador de imagens.
            gerador.Criar(inputStream, "saida/" + nomeArquivo, texto);

            // Metodo Criar() recebendo o texto.
            public void Criar(InputStream inputStream, String nomeArquivo, String texto) throws Exception{}

            // Colocando sobreposição da imagem.
            if (imDbRating >= 8.5 ){
                texto = "Bom";
                selo = new FileInputStream("selos/aprovado.png");
            }else{
                texto = "meh";
                selo = new FileInputStream("selos/reprovado.png");
            }

            // Desenhando a sobreposição.
            BufferedImage seloImage = ImageIO.read(selo);
            int seloImageY = altura - seloImage.getHeight();

            Graphics2D graphics = (Graphics2D)novaImagen.getGraphics();
            graphics.drawImage(imgOriginal, 0, 0, null);

            graphics.drawImage(seloImage, 0, seloImageY, null);

## Aula 3

Refatorando o [App](https://github.com/WMarques25/Imersao-Java-Alura/blob/Aula3/alura-stickers/src/App.java), transferindo parte do código em novas classes.

Criando classes para:

- [Conteudo](https://github.com/WMarques25/Imersao-Java-Alura/blob/Aula3/alura-stickers/src/Conteudo.java)

- [ClienteHttp](https://github.com/WMarques25/Imersao-Java-Alura/blob/Aula3/alura-stickers/src/ClienteHttp.java)

Criando uma [interface](https://github.com/WMarques25/Imersao-Java-Alura/blob/Aula3/alura-stickers/src/ExtratorConteudo.java) para as novas classes extratoras:

- [ExtratorConteudoIMDB](https://github.com/WMarques25/Imersao-Java-Alura/blob/Aula3/alura-stickers/src/ExtratorConteudoIMDB.java)

- [ExtratorConteudoNasa](https://github.com/WMarques25/Imersao-Java-Alura/blob/Aula3/alura-stickers/src/ExtratorConteudoNasa.java)

### Modificado da Aula 2

#### Algumas modificações do [App](https://github.com/WMarques25/Imersao-Java-Alura/blob/Aula3/alura-stickers/src/App.java)

1. Criado a classe ClienteHttp, alteramos todo o código que buscava os dados em .json para im objeto do tipo ClienteHttp.

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

2. O mesmo para os ExtratorConteudo.

        // fazer uma conexão HTTP e buscar os top 3 Séries de acordo com IMDB
        String API_KEY = System.getenv("API_KEY");
        System.out.println(API_KEY);

        // String url = "https://raw.githubusercontent.com/alura-cursos
            /imersao-java-2-api/main/MostPopularTVs.json";
        // ExtratorConteudo extrator = new ExtratorConteudoIMDB();

        String url = "https://api.nasa.gov/planetary/apod?api_key=" 
            + API_KEY + "&start_date=2022-07-4&end_date=2022-07-10";
        ExtratorConteudo extrator = new ExtratorConteudoNasa();

3. Criação de uma lista de conteudos

        List<Conteudo> conteudos = extrator.extrairConteudos(json);

4. No looping que gera as imagens pegamos cada conteudo da lista, chamando suas propriedades pelos metodos .getTitulo() e .getUrlImage()

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);
           
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = conteudo.getTitulo();      

            System.out.println("\u001b[37;1mTitulo:\u001b[44m " + conteudo.getTitulo() + " \u001b[0m");
            System.out.println("\u001b[37;1mPoster:" + " \u001b[0m" + conteudo.getUrlImagem());
            
            gerador.Criar(inputStream, nomeArquivo);

            System.out.println("\n");
        }

#### Algumas modificações do [Gerador de Figurinhas](https://github.com/WMarques25/Imersao-Java-Alura/blob/Aula3/alura-stickers/src/ZapStickers.java)

1. Retirada a sobreposição de selos("aprovado"/"reprovado").

2. Variavel "texto" subistituida pelo próprio titulo da imagem("nomeArquivo").

        graphics.drawString(nomeArquivo, posXTexto, posYTexto);

3. Diretório de saida e formato da imagem definidos no ImageIO.write().

        ImageIO.write(novaImagen, "png", new File("saida/" + nomeArquivo + ".png"));

### Desafios Aula 3

1. Transformando a classe Conteudo para record.

        public record Conteudo(String titulo, String urlImagem){}

    Nos metodos que retornam o titulo e a url de Conteudo é retirado a expressão "get"

    .~~getT~~**t**itulo()

    .~~getU~~**u**rlImage()

2. Criando classe [ClienteHttpException](https://github.com/WMarques25/Imersao-Java-Alura/blob/Aula3/alura-stickers/src/ClienteHttpException.java).

        public class ClienteHttpExeption extends RuntimeException{

            public ClienteHttpExeption(String msg) {
                super(msg);
                
            }

        }

    Alteração do [ClienteHttp](https://github.com/WMarques25/Imersao-Java-Alura/blob/Aula3/alura-stickers/src/ClienteHttp.java).

        catch(IOException | InterruptedException ex){
            throw new ClienteHttpExeption("\nErro na consulta da URL\n(╯°□°)╯︵ ┻┝┻\n");

        }

3. Utilizando .stram() e expressão lambda nos Extratores.

        public List<Conteudo> extrairConteudos(String json){

        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        return listaDeAtributos.stream()
            .map(atributos -> new Conteudo(atributos.get("title"), atributos.get("url")))
            .toList();

        }

4. Utilizando uma enum [API](https://github.com/WMarques25/Imersao-Java-Alura/blob/Aula3/alura-stickers/src/API.java) para guardar as urls das APIs.

        public enum API {
            IMDB_TOP_TVS("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api
                /main/Top250TVs.json", new ExtratorConteudoIMDB()),
            NASA_APOD("https://api.nasa.gov/planetary/apod?api_key=" + System.getenv("API_KEY") 
                + "&start_date=2022-07-4&end_date=2022-07-10", 
                new ExtratorConteudoNasa()),
            IMDB_POPULAR_TVS("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api
                /main/MostPopularTVs.json", new ExtratorConteudoIMDB());

            private String url;
            private ExtratorConteudo ext;

            API(String url, ExtratorConteudo ext){
                this.url = url;
                this.ext = ext;
                
            }

            public String getUrl() {
                return url;
            }

            public ExtratorConteudo getExt() {
                return ext;
            }

        }

## Aula 4

Criação da api [Linguagens.api](https://github.com/WMarques25/Imersao-Java-Alura/blob/Aula4/linguagens.api/src/main/java/com/wmarques/linguagens/api/) com Spring, Mongodb e utilizando Postman para testes

Criação do link e extrator de conteudo da nova API

- [API.java](https://github.com/WMarques25/Imersao-Java-Alura/blob/Aula4/alura-stickers/src/API.java)

        WELL_LINGUAGENS("http://localhost:8080/linguagens",
            new ExtratorConteudoLinguagens());

- [ExtratorConteudoLinguagens.java](https://github.com/WMarques25/Imersao-Java-Alura/blob/Aula4/alura-stickers/src/ExtratorConteudoLinguagens.java)

        public class ExtratorConteudoLinguagens implements ExtratorConteudo {

            public List<Conteudo> extrairConteudos(String json){

                // extrair só os dados que interessam (titulo, poster, classificação)
                var parser = new JsonParser();
                List<Map<String, String>> listaDeAtributos = parser.parse(json);

                return listaDeAtributos.stream()
                    .map(atributos -> new Conteudo(atributos.get("title"), atributos.get("image")))
                    .toList();

            }

        }

### API

- [Linguagem.java](https://github.com/WMarques25/Imersao-Java-Alura/blob/Aula4/linguagens.api/src/main/java/com/wmarques/linguagens/api/Linguagem.java)

        package com.wmarques.linguagens.api;

        import org.springframework.data.annotation.Id;
        import org.springframework.data.mongodb.core.mapping.Document;

        @Document(collection = "Lingdb")
        public class Linguagem {
            
            @Id
            private String id;
            private String title;
            private String image;
            private int ranking;


            public Linguagem(String title, String image, int ranking) {
                this.title = title;
                this.image = image;
                this.ranking = ranking;
            }
            
            public String getId() {
                return id;
            }
            public String getTitle() {
                return title;
            }
            public String getImage() {
                return image;
            }
            public int getRanking() {
                return ranking;
            }

            public void setId(String id) {
                this.id = id;
            }  

        }

- [LinguagemController.java](https://github.com/WMarques25/Imersao-Java-Alura/blob/Aula4/linguagens.api/src/main/java/com/wmarques/linguagens/api/LinguagemController.java)

        package com.wmarques.linguagens.api;

        import java.util.List;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.mongodb.repository.Update;
        import org.springframework.http.HttpStatus;
        import org.springframework.web.bind.annotation.DeleteMapping;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.Mapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.PutMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RestController;
        import org.springframework.web.server.ResponseStatusException;

        @RestController
        public class LinguagemController {
            
            @Autowired
            private LinguagemRepository repositorio;

            @GetMapping("/linguagens") // Buscando tds as linguagens
            public List<Linguagem> obterLinguagens(){
                List<Linguagem> linguagens = repositorio.findAll();
                return linguagens;
                
            }

            @PostMapping("/linguagens") // Adicionando linguagem
            public Linguagem cadastrarLinguagem(@RequestBody Linguagem linguagem){
                Linguagem newSave = repositorio.save(linguagem);
                return newSave;
            }
        }

- [LinguagemRepository.java](https://github.com/WMarques25/Imersao-Java-Alura/blob/Aula4/linguagens.api/src/main/java/com/wmarques/linguagens/api/LinguagemRepository.java)

        package com.wmarques.linguagens.api;

        import org.springframework.data.mongodb.repository.MongoRepository;

        public interface LinguagemRepository extends 
            MongoRepository<Linguagem, String>{
            
        }

### Desáfios

1. Finalizando CRUD([LinguagemController.java](https://github.com/WMarques25/Imersao-Java-Alura/blob/Aula4/linguagens.api/src/main/java/com/wmarques/linguagens/api/LinguagemController.java)) das linguagens na API

    - Lendo linguagem especificada por id

                @GetMapping("/linguagens/{id}")
                public Linguagem obterLinguagemPorId(@PathVariable String id){
                    return repositorio.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
                }

    - Atualizando linguagem por id

                @PutMapping("/linguagens/{id}")
                public Linguagem atualizarLinguagemPorId(@PathVariable String id , @RequestBody Linguagem linguagem){
                    repositorio.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
                    linguagem.setId(id);

                    Linguagem linguagemSalva = repositorio.save(linguagem);
                    return linguagemSalva;
                }

    - Deletando linguagem por id

                @DeleteMapping("/linguagens/{id}")
                public void excluirLinguagem(@PathVariable String id){
                    repositorio.deleteById(id);
                }

2. Ordenando a lista de linguagens

    - Criando método de busca ordenada

                 public interface LinguagemRepository extends MongoRepository<Linguagem, String>{
                    List<Linguagem> findByOrderByRanking();
                }

    - Utilizando o novo método na busca  ~~.findAll();~~

                @GetMapping("/linguagens") // Buscando tds as linguagens
                public List<Linguagem> obterLinguagens(){
                    List<Linguagem> linguagens = repositorio.findByOrderByRanking();
                    return linguagens;

                }

3. Alterando status da linguagem criada

                @PostMapping("/linguagens") // Adicionando linguagem
                public ResponseEntity<Linguagem> cadastrarLinguagem(@RequestBody Linguagem linguagem){
                    Linguagem newSave = repositorio.save(linguagem);
                    return new ResponseEntity<>(newSave, HttpStatus.CREATED);

                }
