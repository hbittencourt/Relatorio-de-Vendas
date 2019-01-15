
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class main {

	public static void main(String[] args) throws IOException {

		WatchService watchService = FileSystems.getDefault().newWatchService();
		String caminho = System.getProperty("user.dir");

		Path diretorio = Paths.get(caminho + "\\data\\in");

		WatchKey watchKey = diretorio.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

		while (true) {

			for (WatchEvent<?> event : watchKey.pollEvents()) {
				Path arquivo = (Path) event.context();
				String nArquivo = arquivo.toString();

				if (nArquivo.endsWith(".dat")) {
					String endereco = diretorio + "\\" + arquivo;
					relatorio rel = new relatorio(endereco);
					endereco = caminho + "\\data\\out\\" + arquivo;
					rel.emiteRelatorio(endereco);
					rel.limpaArquivos();
				}
			}
		}

	}

}
