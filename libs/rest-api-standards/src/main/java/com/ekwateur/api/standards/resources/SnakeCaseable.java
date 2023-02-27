package com.ekwateur.api.standards.resources;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Préconisation 22
 * <p>
 * Pour le nom des données composants les URI ou transmises, soit en paramètre de l'URI,
 * soit dans le body de la requête et de la réponse,
 * il est préconisé d’utiliser le format « snake_case ».
 * Le format « lowerCamelCase », historiquement utilisé dans le Groupe, reste toléré.
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface SnakeCaseable extends Serializable {

}
