package net.prosavage.baseplugin.serializer.typeadapter;

import com.google.gson.*;
import net.prosavage.baseplugin.SavagePlugin;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.util.logging.Level;

public class LocalTimeTypeAdapter implements JsonSerializer<LocalTime>, JsonDeserializer<LocalTime> {

    @Override
    public JsonElement serialize(LocalTime localTime, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        try {
            object.add("hour", new JsonPrimitive(localTime.getHour()));
            object.add("minute", new JsonPrimitive(localTime.getMinute()));
            object.add("second", new JsonPrimitive(localTime.getSecond()));
            object.add("nano", new JsonPrimitive(localTime.getNano()));
            return object;
        } catch (Exception ex) {
            ex.printStackTrace();
            SavagePlugin.getInstance().getLogger().log(Level.WARNING, "Error encountered while serializing a LocalTime instance.");
            return object;
        }
    }


    @Override
    public LocalTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        JsonObject object = jsonElement.getAsJsonObject();
        try {
            return LocalTime.of(object.get("hour").getAsInt(),
                    object.get("minute").getAsInt(),
                    object.get("second").getAsInt(),
                    object.get("nano").getAsInt());

        } catch (Exception ex) {
            ex.printStackTrace();
            SavagePlugin.getInstance().getLogger().log(Level.WARNING, "Error encountered while" +
                    " deserializing a LocalTime instance.");
            return null;
        }


    }
}