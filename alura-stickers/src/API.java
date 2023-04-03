public enum API {
    IMDB_TOP_TVS("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/Top250TVs.json", new ExtratorConteudoIMDB()),
    NASA_APOD("https://api.nasa.gov/planetary/apod?api_key=" + System.getenv("API_KEY") + "&start_date=2022-07-4&end_date=2022-07-10", new ExtratorConteudoNasa()),
    IMDB_POPULAR_TVS("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json", new ExtratorConteudoIMDB());

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