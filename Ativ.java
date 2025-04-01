package Ativ;
import java.io.*;

public class Ativ {
    public static void main(String[] args) {
        String caminhoA = "C:\\Users\\Guilherme\\Documents";  // Não consegui fazer rodar,
        Resumo(caminhoA);
    }
    public static void Resumo(String caminhoB) {
        File in = new File(caminhoB);
        if (!in.exists()) {
            System.out.println("Erro: O arquivo não foi encontrado.");
            return;
        }
        String diretorio = in.getParent();
        File pastaSaida = new File(diretorio + "/out");
        if (!pastaSaida.exists()) {
            pastaSaida.mkdirs();
        }
        File out = new File(pastaSaida, "summary.csv");
        try (
                BufferedReader reader = new BufferedReader(new FileReader(in));
                BufferedWriter writer = new BufferedWriter(new FileWriter(out))
        ) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 3) {
                    String item = dados[0].trim();
                    double preco = Double.parseDouble(dados[1].trim());
                    int quantidade = Integer.parseInt(dados[2].trim());
                    double total = preco * quantidade;
                    writer.write(String.format("%s,%.2f\n", item, total));
                }
            }
            System.out.println("Resumo gerado com sucesso em: " + out.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Erro ao ler ou escrever o arquivo: " + e.getMessage());
        }
    }
}
