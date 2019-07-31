package com.lwo.akulov.graph.repository;

import com.lwo.akulov.graph.serivice.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private final String CURRENCY_RATES_URL = "http://www.nbrb.by/API/ExRates/Rates/";

    @Test
    public void findByIsActive(){
        int count = userService.findByActive(true).size();
        Assert.assertTrue( count > 0);
    }

    @Test
    public void getAllUsers () {
        String uri = CURRENCY_RATES_URL + "145";

/*        RestTemplate restTemplate = new RestTemplate();

//        CredentialsProvider credsProvider = new BasicCredentialsProvider();
//        credsProvider.setCredentials( new AuthScope("192.168.33.210", 3128), new UsernamePasswordCredentials("", "") );
        HttpClientBuilder clientBuilder = HttpClientBuilder.create();

        clientBuilder.useSystemProperties();
        clientBuilder.setProxy(new HttpHost("192.168.33.210", 3128));
//        clientBuilder.setDefaultCredentialsProvider(credsProvider);
        clientBuilder.setProxyAuthenticationStrategy(new ProxyAuthenticationStrategy());

        CloseableHttpClient client = clientBuilder.build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(client);

        restTemplate.setRequestFactory(factory);

        restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(60L))
                .setReadTimeout(Duration.ofSeconds(60L))
                .build();
        System.out.println(restTemplate.getForObject(uri, String.class));*/

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.33.210", 3128));
        requestFactory.setProxy(proxy);

//        RestTemplate restTemplate = new RestTemplate(requestFactory);
        RestTemplate restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(60L))
                .setReadTimeout(Duration.ofSeconds(60L))
                .build();
        restTemplate.setRequestFactory(requestFactory);
        System.out.println(restTemplate.getForObject(uri, String.class));
    }
}