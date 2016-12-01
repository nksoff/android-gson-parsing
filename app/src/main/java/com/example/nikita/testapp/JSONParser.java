package com.example.nikita.testapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

import java.lang.reflect.Type;

class JSONParser {
    private final static Gson gson;

    static {
        gson = new GsonBuilder()
                .registerTypeAdapter(UserGender.class, new UserGenderDeserializer())
                .registerTypeAdapter(Boolean.class, new BooleanDeserializer())
                .create();
    }

    UserCollection parse(final String json) {
        return gson.fromJson(json, UserCollection.class);
    }

    private static class UserGenderDeserializer implements JsonDeserializer<UserGender> {
        @Override
        public UserGender deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if(json.isJsonPrimitive()) {
                JsonPrimitive val = (JsonPrimitive) json;
                if(val.isString()) {
                    try {
                        return UserGender.valueOf(val.getAsString());
                    }
                    catch(IllegalArgumentException e) {
                        return UserGender.NOT_SURE;
                    }
                }
            }

            return UserGender.NOT_SURE;
        }
    }

    private static class BooleanDeserializer implements JsonDeserializer<Boolean> {
        @Override
        public Boolean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if(json.isJsonPrimitive()) {
                JsonPrimitive val = (JsonPrimitive) json;
                if(val.isString()) {
                    if(val.getAsString().equals("yes")) {
                        return true;
                    }
                    else if(val.getAsString().equals("no")) {
                        return false;
                    }
                }
                else if(val.isNumber()) {
                    if(val.getAsInt() == 1) {
                        return true;
                    }
                    else if(val.getAsInt() == 0) {
                        return false;
                    }
                }
            }

            return json.getAsBoolean();
        }
    }

}

