package co.uk.gcsample.cakes.stepdefs;

import co.uk.gcsample.cakes.CakesApplication;
import co.uk.gcsample.cakes.entity.Cake;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@CucumberContextConfiguration
@SpringBootTest(classes = CakesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CakesStepdefs {

    @Value("${baseUrl}")
    private String baseUrl;

    private RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<Cake> cakeResponse;
    private ResponseEntity<String[]> cakeTitlesResponse;
    private ResponseEntity<Cake[]> cakeDetailsResponse;
    private Cake newCake = new Cake("newCake", "long new cake description", "dummyLocation");

    @Given("the Cake Manager is running")
    public void theCakeManagerIsRunning() {
        ResponseEntity<String> result = restTemplate.getForEntity(baseUrl + "/actuator/health", String.class);
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody(), containsStringIgnoringCase("UP"));
    }

    @When("a list of cake titles is requested")
    public void aListOfCakeTitlesIsRequested() {
        cakeTitlesResponse = restTemplate.getForEntity(baseUrl+ "/cakes/list", String[].class);
    }

    @Then("the list of cake titles is returned")
    public void theListOfCakeTitlesIsReturned() {
        assertThat(cakeTitlesResponse.getStatusCode(), is(HttpStatus.OK));
        assertThat(cakeTitlesResponse.getBody().length, greaterThan(0));
    }

    @When("a list of cake details is requested")
    public void aListOfCakeDetailsIsRequested() {
        cakeDetailsResponse = restTemplate.getForEntity(baseUrl + "/cakes/", Cake[].class);
    }

    @Then("the list of cake details is returned")
    public void theListOfCakeDetailsIsReturned() {
        assertThat(cakeDetailsResponse.getStatusCode(), is(HttpStatus.OK));
        assertThat(cakeDetailsResponse.getBody().length, greaterThan(0));
    }

    @When("a new cake is added")
    public void aNewCakeIsAdded() {
        ResponseEntity<String> result = restTemplate.postForEntity(baseUrl + "/cakes/", newCake, String.class);
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
    }

    @And("a request to retrieve the new cake is made")
    public void aRequestToRetrieveTheNewCakeIsMade() {
        cakeResponse = restTemplate.getForEntity(baseUrl +"/cakes/findCake?title=" + newCake.getTitle(), Cake.class);
    }

    @Then("the new cake is returned")
    public void theNewCakeIsReturned() {
        assertThat(cakeResponse.getStatusCode(), is(HttpStatus.OK));
        assertThat(cakeResponse.getBody().getTitle(), is(newCake.getTitle()));
    }

}
