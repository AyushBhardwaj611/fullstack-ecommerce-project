package dev.ayush.productservice.configs;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
@Primary
public class RestTemplateConfig {

    @Bean
  //  @LoadBalanced
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }
}


/*the load balanced annotaion is used to ease our work so wherever we will use
* the url we dont need to specify the exact host and port number...we can just mention the
* microservice we need to call..the api call will look for the microservice in the eureka server mapping
* and automatically to the client side balancing*/
//
//NOTE--> commenting loadbalancerd because the request was searching for the fakestore api in the eureka
//        even when it is an external api...