package br.edu.utfpr.posjava.soa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by talyssondecastro on 14/05/17.
 */
@SpringBootApplication
@ComponentScan
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
