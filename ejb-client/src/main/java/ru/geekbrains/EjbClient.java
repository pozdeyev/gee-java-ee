package ru.geekbrains;
import ru.geekbrains.service.ProductRepresentative;
import ru.geekbrains.service.ProductServiceRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class EjbClient {

    public static void main(String[] args) throws IOException, NamingException, ExecutionException, InterruptedException {
        Context context = createInitialContext();

       ProductServiceRemote productService = (ProductServiceRemote) context.lookup("ejb:/store-jsf/ProductServiceImpl!ru.geekbrains.service.ProductServiceRemote");

        productService.findAll()
               .forEach(product -> System.out.println(product.getId() + " " + product.getTitle() + " "+ product.getDescription() + " " + product.getPrice()));
//Тест асинхронности
        Future<ProductRepresentative> byIdAsync = productService.findByIdAsync(6);
        System.out.println(byIdAsync.get());
    }

    public static Context createInitialContext() throws IOException, NamingException {
        final Properties env = new Properties();
        env.load(EjbClient.class.getClassLoader().getResourceAsStream("wildfly-jndi.properties"));
        return new InitialContext(env);
    }
}
