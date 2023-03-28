# Repositório de estudos da #ImersaoJava da Alura

## Aula 1

Utilizando Java no VS Code para acessar e consumir API do IMDB (Top 250 Filmes).

[App](https://github.com/WMarques25/Imersao-Java-Alura/blob/main/alura-stickers/src/App.java) - Aplicação principal, acessando e exibindo os dados da API.

[Parser](https://github.com/WMarques25/Imersao-Java-Alura/blob/main/alura-stickers/src/JsonParser.java) - Classe para "parsear", filtrando as informações do arquivo Json

### Desafios Aula 1

1 - Utilizando End-Point para acessar as top séries do site.

    String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json";

2 - Configurando a estética da apresentação das informações.

        System.out.println("\u001b[37;1mTitulo:\u001b[44m " + serie.get("title") + " \u001b[0m");
        System.out.println("\u001b[37;1mPoster:" + " \u001b[0m" + serie.get("image"));
        double imDbRating = Double.parseDouble(serie.get("imDbRating"));
        System.out.println(imDbRating);

        for( int j = 0; j < (int)imDbRating; j++){
            System.out.print("⭐️");
        }

        System.out.println("\n");

3 - Utilizando variavel de ambiente para esconder chave de acesso.

    // Definindo a variavel de ambiente no terminal.
    Utilizei no PowerShell:
    $env:IMDB_KEY = "chave"                                         

    // Concatenando url + Chave de acesso da API no codigo.
    String url = "https://imdb-api.com/en/API/TopTVs/" + IMBD_KEY;  

## Aula 2

Importando imagens(arquivo local ou url), transformando em uma nova imagem .png com um texto em fundo transparente.

[Gerador de Stickers](https://github.com/WMarques25/Imersao-Java-Alura/blob/main/alura-stickers/src/ZapStickers.java) - Classe geradora de Stickers para WhatsApp.

### Desafios Aula 2

1 - Criando diretório para saida das imagens caso não exista.

        // Gerando o diretório "saida/".
        var diretorio = new File("saida/");
        diretorio.mkdir();                                  

        // Criando a imagem no diretório "saida/" + nome da série.
        gerador.Criar(inputStream, "saida/" + nomeArquivo); 

2 - Centralizando o texto na nova imagem.

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
