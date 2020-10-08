package com.example.rmm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RichardsonMaturityModelApplication {
	@Bean
	public List<Book> buildBookList() {
		return Arrays.asList(
			new Book(1, "Ken Follet", "Fall of giants", true),
			new Book(2, "Ken Follet", "Winter of the world", true),
			new Book(3, "Ken Follet", "The pillars of the Earth", false),
			new Book(4, "Arturo Perez Reverte", "El capitan Alatriste", true),
			new Book(5, "Arturo Perez Reverte", "Falco", false),
			new Book(6, "Arturo Perez Reverte", "El asedio", true),
			new Book(7, "Henning Mankell", "Faceless Killers", false),
			new Book(8, "Henning Mankell", "The pyramid", true),
			new Book(9, "Henning Mankell", "One step behind", true)
		);
	}
	
	@Bean
	public List<BookV3> buildBookV3List() {
		return Arrays.asList(
			new BookV3(1, "Ken Follet", "Fall of giants", true),
			new BookV3(2, "Ken Follet", "Winter of the world", true),
			new BookV3(3, "Ken Follet", "The pillars of the Earth", false),
			new BookV3(4, "Arturo Perez Reverte", "El capitan Alatriste", true),
			new BookV3(5, "Arturo Perez Reverte", "Falco", false),
			new BookV3(6, "Arturo Perez Reverte", "El asedio", true),
			new BookV3(7, "Henning Mankell", "Faceless Killers", false),
			new BookV3(8, "Henning Mankell", "The pyramid", true),
			new BookV3(9, "Henning Mankell", "One step behind", true)
		);
	}
	
	// Swagger needed bean
	@Bean
	public Docket buildDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.example.rmm.level3"))
				.paths(PathSelectors.any()).build();
	}
	
	//HATEOAS needed bean
    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }

	public static void main(String[] args) {
		SpringApplication.run(RichardsonMaturityModelApplication.class, args);
	}

}
