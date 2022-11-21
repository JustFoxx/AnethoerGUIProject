package io.github.justfoxx.app.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DatabaseBuilder {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final Path mainPath =Path.of(System.getProperty("user.home")).resolve(".cookieclicker");
    private final Path dataPath = mainPath.resolve("data.json");
    public Data data = new Data();
    public JsonObject object = new JsonObject();
    public void load() throws IOException {
        if(Files.notExists(mainPath)) {
            Files.createDirectories(mainPath);
        }
    }
    public void createData() throws Exception {
        Files.writeString(dataPath, GSON.toJson(data));
        this.object = jsonReader(dataPath.toFile());
    }

    public static JsonObject jsonReader(File file) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(file));
        return JsonParser.parseReader(reader).getAsJsonObject();
    }

    public void readData() throws Exception {
        JsonObject object = jsonReader(dataPath.toFile());
        data = GSON.fromJson(Files.readString(dataPath), Data.class);
        this.object = object;
    }

    public void saveData() throws Exception {
        Files.writeString(dataPath, GSON.toJson(data));
    }
}
