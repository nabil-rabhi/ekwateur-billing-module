package com.ekwateur.energybilling.common.assertions;

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

    private final ClassLoader classLoader = JsonAssert.class.getClassLoader();
    private final JsonMapper objectMapper = JsonMapper.builder()
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
            failWithMessage("Expected Json response to be\n %s \nbut was\n %s",
                            expectedPrettyPrintedJson, actualPrettyPrintedJson);
        }
        return this;
    }

    @SneakyThrows
    public void assertThatJsonBodyEqualsToFileContent(String actualJsonContent,
                                                      String expectedJsonFilePath) {

        String actualTransformedJson = prettyPrint(actualJsonContent);

        String expectedJsonFileContent = readFileContent(expectedJsonFilePath);
        String expectedTransformedJson = prettyPrint(expectedJsonFileContent);

        assertThat(actualTransformedJson).isEqualTo(expectedTransformedJson);
    }

    private String prettyPrint(String jsonContent) throws JsonProcessingException {

        JsonNode actual = objectMapper.readTree(jsonContent);
        return objectMapper.writerWithDefaultPrettyPrinter()
                           .writeValueAsString(actual);
    }

    public String readFileContent(String filePath) {

        try {
            URL resource = classLoader.getResource(filePath);
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
