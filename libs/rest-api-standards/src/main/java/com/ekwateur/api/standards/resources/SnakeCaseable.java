package com.ekwateur.api.standards.resources;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * for each class that implements this interface,
 * this instructs  Jackson to convert all its fields
 * from lowerCamelCase to  snake_case.
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface SnakeCaseable extends Serializable {

}
