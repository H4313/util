package com.h4313.deephouse.adapter;

import com.google.gson.*;
import com.h4313.deephouse.actuator.Actuator;

import java.lang.reflect.Type;

public class ActuatorAdapter implements JsonSerializer<Actuator<Object>>, JsonDeserializer<Actuator<Object>> {
    @Override
    public JsonElement serialize(Actuator<Object> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(src.getClass().getSimpleName()));
        result.add("properties", context.serialize(src, src.getClass()));

        return result;
    }

    @Override
    public Actuator<Object> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement element = jsonObject.get("properties");

        try {
            return context.deserialize(element, Class.forName("com.h4313.deephouse.actuator." + type));
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Unknown element type: " + type, cnfe);
        }
    }
}