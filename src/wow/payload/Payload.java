package wow.payload;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Payload {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public final Class<?> payloadClass;
    private final HashMap<String, String> containers = new HashMap<>();

    public Payload(Object object) {
        if (object == null)
            throw new IllegalArgumentException("Object cannot be null");
        this.payloadClass = object.getClass();
    }

    public String spawnContainer(String id, Object data) {
        if (id == null || id.isBlank())
            throw new IllegalArgumentException("ID cannot be null or blank");
        if (data == null)
            throw new IllegalArgumentException("Data cannot be null");
        if (containers.containsKey(id))
            throw new IllegalArgumentException("Container with ID '" + id + "' already exists");
        if (!payloadClass.isInstance(data))
            throw new IllegalArgumentException("Data must be an instance of " + payloadClass.getName());

        String raw = "{\"id\":\"" + id + "\",\"data\":" + GSON.toJson(data) + "}";
        String repackaged = GSON.toJson(JsonParser.parseString(raw));
        containers.put(id, repackaged);
        return repackaged;
    }

    public Map<String, String> getContainers() {
        return Collections.unmodifiableMap(containers);
    }

    public String getContainer(String id) {
        if (id == null)
            throw new IllegalArgumentException("ID cannot be null");
        String json = containers.get(id);
        if (json == null)
            throw new IllegalArgumentException("No container with ID '" + id + "'");
        return json;
    }

    public String parseId(String containerJson) {
        if (containerJson == null)
            throw new IllegalArgumentException("Container JSON cannot be null");
        return JsonParser.parseString(containerJson).getAsJsonObject().get("id").getAsString();
    }

    public String parseData(String containerJson) {
        if (containerJson == null)
            throw new IllegalArgumentException("Container JSON cannot be null");
        return GSON.toJson(JsonParser.parseString(containerJson).getAsJsonObject().get("data"));
    }

    public Object parseObject(String containerJson) {
        if (containerJson == null)
            throw new IllegalArgumentException("Container JSON cannot be null");
        return GSON.fromJson(JsonParser.parseString(containerJson).getAsJsonObject().get("data"), payloadClass);
    }

}