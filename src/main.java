
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

		Path diretorio = Paths.get("C:\\Users\\HenriqueFaveroBitten\\data\\in\\");

		WatchKey watchKey = diretorio.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

		while (true) {

			for (WatchEvent<?> event : watchKey.pollEvents()) {
				Path arquivo = (Path) event.context();
				if (arquivo.endsWith(".dat")) {
					String endereco = "C:\\Users\\HenriqueFaveroBitten\\data\\in\\" + arquivo;
					System.out.println(endereco);
					relatorio rel = new relatorio(endereco);
					endereco = "C:\\Users\\HenriqueFaveroBitten\\data\\out\\" + arquivo;
					rel.emiteRelatorio(endereco);
				}else System.out.println("NAO É UM .DAT");
			}
		}

	}

}
