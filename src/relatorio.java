
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class relatorio {

	static List<vendedor> lVendedores = new ArrayList<vendedor>();
	static List<clientes> lClientes = new ArrayList<clientes>();
	static List<vendas> lVendas = new ArrayList<vendas>();

	relatorio(String endereco) {

		List<String> registros = buscaRegistros(endereco);

		for (int i = 0; i < registros.size(); i++) {
			String linha = registros.get(i);
			String[] parts = linha.split(Pattern.quote("ç"));

			if (parts[0].equals("001")) {
				vendedor nVendedor = new vendedor(parts[1], parts[2], Double.parseDouble(parts[3]));
				lVendedores.add(nVendedor);
			}

			if (parts[0].equals("002")) {
				clientes nCliente = new clientes(parts[1], parts[2], parts[3]);
				lClientes.add(nCliente);
			}

			if (parts[0].equals("003")) {
				vendas v = new vendas(Integer.valueOf(parts[1]), parts[3]);
				parts[2] = parts[2].replace("[", "");
				parts[2] = parts[2].replace("]", "");
				String[] itensV = parts[2].split(Pattern.quote(","));
				for (int cont = 0; cont < itensV.length; cont++) {
					String[] cad = itensV[cont].split(Pattern.quote("-"));
					v.novoItem(cad[0], cad[1], cad[2]);
				}
				lVendas.add(v);
			}
		}
	}

	public List<String> buscaRegistros(String endArquivo) {

		List<String> arquivo = new ArrayList<String>();

		try {
			FileReader arq = new FileReader(endArquivo);
			BufferedReader lerArq = new BufferedReader(arq);

			String linha = lerArq.readLine();
			// Funcao para remover o lixo da primeira linha
			byte[] ascii = linha.getBytes(StandardCharsets.UTF_8);
			if (ascii[0] == -17 && ascii[1] == -69 && ascii[2] == -65) {

				ascii = Arrays.copyOfRange(ascii, 3, ascii.length);
			}
			;

			linha = new String(ascii, "UTF-8");
			

			while (linha != null) {

				arquivo.add(linha);

				linha = lerArq.readLine();
			}

			lerArq.close();

		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}

		return arquivo;
	}

	
	public boolean emiteRelatorio(String endereco) throws IOException {

		vendas maior = vendaMaisCara(lVendas);
		vendas menor = piorVendedor(lVendas);
		
		endereco = endereco.replace(".dat", ".done.dat");
		
		FileWriter arq = new FileWriter(endereco);
	    PrintWriter gravarArq = new PrintWriter(arq);
	    
	    
	    gravarArq.printf(" --- Relatório de Vendas --- %n");
	    gravarArq.printf("Quantidade de clientes no arquivo de entrada: " + lClientes.size() + "%n");
	    gravarArq.printf("Quantidade de vendedores no arquivo de entrada: " + lVendedores.size() + "%n");
	    gravarArq.printf("ID da venda mais cara: " + maior.getSaleID() + "%n");
	    gravarArq.printf("O pior vendedor: " + menor.getNome() + "%n");
		
	    arq.close();
		return true;

	}

	public static vendas vendaMaisCara(List<vendas> lVendas) {
		vendas maior = new vendas(0, null);

		for (vendas v : lVendas) {
			if (v.calculaTotal() > maior.calculaTotal())
				maior = v;
		}
		return maior;
	}

	public static vendas piorVendedor(List<vendas> lVendas) {
		vendas menor = vendaMaisCara(lVendas);
		for (vendas v : lVendas) {
			if (v.calculaTotal() < menor.calculaTotal())
				menor = v;
		}
		return menor;
	}

	public static void resumo() {
		for (vendedor v : lVendedores) {
			System.out.println("Nome: " + v.getNome() + "  Salario:" + v.getSalary() + "  CPF:" + v.getCPF());
		}

		System.out.println("\n");

		for (clientes c : lClientes) {
			System.out.println(
					"Nome: " + c.getNome() + "  CNPJ:" + c.getCNPJ() + "  Business Area:" + c.getBusiness_area());
		}

		System.out.println("\n");

		for (vendas v : lVendas) {
			System.out.println("SaleID: " + v.getSaleID() + "  Vendedor:" + v.getNome());
			System.out.println("Valor Total: " + v.calculaTotal());
		}
	}

	public void limpaArquivos() {
		lVendas.clear();
		lVendedores.clear();
		lClientes.clear();
		
	}

}
