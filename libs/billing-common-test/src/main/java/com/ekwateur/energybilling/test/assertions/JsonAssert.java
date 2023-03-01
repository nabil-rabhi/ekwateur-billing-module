package com.ekwateur.energybilling.test.assertions;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.assertj.core.api.AbstractAssert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonAssert extends AbstractAssert<JsonAssert, String> {

    private static final ClassLoader CLASS_LOADER = JsonAssert.class.getClassLoader();
    private static final JsonMapper OBJECT_MAPPER = JsonMapper.builder()
                                                              .enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
                                                              .build();

    protected JsonAssert(String string) {

        super(string, JsonAssert.class);
    }

    public static JsonAssert assertThat(String actual) {

        return new JsonAssert(actual);
    }

    @SneakyThrows
    public JsonAssert isEqualTo(String jsonFilePath) {

        isNotNull();

        String actualPrettyPrintedJson = prettyPrint(actual);

        String jsonFileContent = readFileContent(jsonFilePath);
        String expectedPrettyPrintedJson = prettyPrint(jsonFileContent);

        if (!actualPrettyPrintedJson.equals(expectedPrettyPrintedJson)) {
            failWithMessage("Expected Json response to be %s but was %s",
                            expectedPrettyPrintedJson, actualPrettyPrintedJson);
        }
        return this;
    }

    @SneakyThrows
    public static void assertThatJsonBodyEqualsToFileContent(String actualJsonContent,
                                                             String expectedJsonFilePath) {

        String actualTransformedJson = prettyPrint(actualJsonContent);

        String expectedJsonFileContent = readFileContent(expectedJsonFilePath);
        String expectedTransformedJson = prettyPrint(expectedJsonFileContent);

        assertThat(actualTransformedJson).isEqualTo(expectedTransformedJson);
    }

    private static String prettyPrint(String jsonContent) throws JsonProcessingException {

        JsonNode actual = OBJECT_MAPPER.readTree(jsonContent);
        return OBJECT_MAPPER.writerWithDefaultPrettyPrinter()
                            .writeValueAsString(actual);
    }

    public static String readFileContent(String filePath) {

        try {
            URL resource = CLASS_LOADER.getResource(filePath);
            if (resource == null) {
                throw new IOException("Resource not found: " + filePath);
            }
            return IOUtils.toString(resource, StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new IllegalArgumentException(e);
        }
    }

}
