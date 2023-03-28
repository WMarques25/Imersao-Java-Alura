import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ZapStickers {
    
    public void Criar(InputStream inputStream, String nomeArquivo) throws Exception{
        // leitura de imagem
        BufferedImage imgOriginal = ImageIO.read(inputStream);

        // criar nova imagem com memoria e transparencia e tamanho novo
        int largura = imgOriginal.getWidth();
        int altura = imgOriginal.getHeight();
        float novaAltura = altura*1.2f;
        BufferedImage novaImagen = new BufferedImage(largura, (int)novaAltura, BufferedImage.TRANSLUCENT);      

        // copiar imagem original para a nova
        Graphics2D graphics = (Graphics2D)novaImagen.getGraphics();
        graphics.drawImage(imgOriginal, 0, 0, null);

        // nova fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 128);
        graphics.setFont(fonte);
        graphics.setColor(Color.blue);

        // escrever frase na nova imagem
        graphics.drawString("Bom", largura*0.37f, altura*1.13f);

        // salvar em arquivo
        ImageIO.write(novaImagen, "png", new File(nomeArquivo));
        
    }

}
