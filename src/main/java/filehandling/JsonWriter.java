package filehandling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import classes.FullInfo;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonWriter {
    private static final Logger logger = Logger.getLogger(JsonWriter.class.getName());

    private JsonWriter() {
    }

    public static void generateJsonFile(FullInfo fullInfo) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            logger.log(Level.INFO, "JSON сериализация старт");

            String json = gson.toJson(fullInfo);

            createDirectoryIfNeeded();

            String fileName = "jsonReqs/req" + new Date().getTime() + ".json";
            writeFile(fileName, json);

            logger.log(Level.INFO, "JSON сериализация успешно завершена");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "JSON провал сериализации", e);
        }
    }

    private static void createDirectoryIfNeeded() throws IOException {
        Path dirPath = Paths.get("jsonReqs");
        if (!Files.exists(dirPath)) {
            Files.createDirectory(dirPath);
            logger.log(Level.INFO, "Успешно создана директория: " + "jsonReqs");
        } else {
            logger.log(Level.INFO, "Уже существует директория: " + "jsonReqs");
        }
    }

    private static void writeFile(String filePath, String content) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        }
    }
}
