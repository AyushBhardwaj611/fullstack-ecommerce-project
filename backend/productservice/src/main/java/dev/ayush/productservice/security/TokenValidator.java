package dev.ayush.productservice.security;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/*a service to connect to the authenticationService to validate the token
*
* if the token is not valid --> the optional will be empty
* ekse all the data will be present in the payload*/
@Service
public class TokenValidator {


    private RestTemplateBuilder restTemplateBuilder;

    public TokenValidator(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public Optional<JwtObject> validateToken(String token) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        return Optional.empty();
    }

}
