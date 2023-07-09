package Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public final class JsonUtil {

    private JsonUtil() {
    }

    public static String toJson(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object);
    }

    public static <T> T fromJson(String jsonString, Class<T> objectType) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(jsonString, objectType);
    }

    public static <T> List<T> fromJsonToList(String jsonString, Class<T> elementType) {
        Gson gson = new GsonBuilder().create();
        Type listType = TypeToken.getParameterized(List.class, elementType).getType();
        return gson.fromJson(jsonString, listType);
    }
}
