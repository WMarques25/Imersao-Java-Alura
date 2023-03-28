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

    Utilizei no PowerShell:
    $env:IMDB_KEY = "chave"                                         // Definindo a variavel de ambiente no terminal.

    String url = "https://imdb-api.com/en/API/TopTVs/" + IMBD_KEY;  // Concatenando url + Chave de acesso da API no codigo.
