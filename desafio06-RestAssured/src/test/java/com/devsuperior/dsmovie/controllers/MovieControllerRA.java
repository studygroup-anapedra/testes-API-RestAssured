package com.devsuperior.dsmovie.controllers;

import org.json.JSONException;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class MovieControllerRA {
	
	
	@Test
	public void findAllShouldReturnOkWhenMovieNoArgumentsGiven() {
		given()
				.baseUri(BASE_URI)
				.when()
				.get(MOVIE_ENDPOINT + "?page=0")
				.then()
				.statusCode(200)
				.body("content.title", hasItems("The Witcher", "Venom: Tempo de Carnificina"));

	}

	@Test
	public void findAllShouldReturnPagedMoviesWhenMovieTitleParamIsNotEmpty() {
		given()
				.baseUri(BASE_URI)
				.queryParam("title", "The Witcher")
				.when()
				.get(MOVIE_ENDPOINT)
				.then()
				.statusCode(200)
				.body("content.id[0]", is(1))
				.body("content.title[0]", equalTo("The Witcher"));
	}

	@Test
	public void findByIdShouldReturnMovieWhenIdExists() {
		given()
				.baseUri(BASE_URI)
				.pathParam("id", 1)
				.when()
				.get(MOVIE_ENDPOINT + "/{id}")
				.then()
				.statusCode(200)
				.body("id", is(1))
				.body("title", equalTo("The Witcher"));

	}

	@Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() {
		given()
				.baseUri(BASE_URI)
				.pathParam("id", 1000)
				.when()
				.get(MOVIE_ENDPOINT + "/{id}")
				.then()
				.statusCode(404);
	}

	@Test
	public void insertShouldReturnUnprocessableEntityWhenAdminLoggedAndBlankTitle() throws JSONException {		
	}
	
	@Test
	public void insertShouldReturnForbiddenWhenClientLogged() throws Exception {
	}
	
	@Test
	public void insertShouldReturnUnauthorizedWhenInvalidToken() throws Exception {
	}
}
