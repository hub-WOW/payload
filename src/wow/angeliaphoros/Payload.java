package wow.angeliaphoros;
import java.util.HashMap;
import com.google.gson.Gson;
import lombok.Getter;

@Getter
public class Payload<T> {

    private HashMap<String, T> payloadData;

    public Payload() {
        this.payloadData = new HashMap<>();
    }

    public Payload(HashMap<String, T> data) {
        this.payloadData = data;
    }

    public Payload(T[] values, String[] variableNames) {

        if (values.length != variableNames.length) {
            throw new IllegalArgumentException("Values and variableNames must have same length");
        }

        HashMap<String, T> dataMap = new HashMap<>();

        for (int i = 0; i < values.length; i++) {
            dataMap.put(variableNames[i], values[i]);
        }

        this.payloadData = dataMap;
    }

    public void put(String key, T value) {
        payloadData.put(key, value);
    }

    public T get(String key) {
        return payloadData.get(key);
    }

    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}