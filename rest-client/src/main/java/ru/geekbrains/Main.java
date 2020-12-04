package ru.geekbrains;


import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient(new ClientConfig().register(JacksonJsonProvider.class));
        WebTarget productTarget = client.target("http://localhost:8080/store-jsf/api/v1/product/all");

        Invocation.Builder request = productTarget.request(MediaType.APPLICATION_JSON);
        Response response = request.get();
        List<ProductRepresentative> products = response.readEntity(new GenericType<List<ProductRepresentative>>() {
        });
        products.forEach(product -> System.out.println(product.getId() + " "
                + product.getTitle() + " " + product.getDescription() +
                " " + product.getPrice()));
    }
}
