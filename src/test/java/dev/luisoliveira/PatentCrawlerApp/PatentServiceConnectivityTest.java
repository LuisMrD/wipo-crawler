package dev.luisoliveira.PatentCrawlerApp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.jupiter.api.Test;

class PatentServiceConnectivityTest {

    @Test
    void shouldConnectSuccessfullyToPatentUrl() throws IOException {
        String applicationNumber = "WO2002008676";

        String urlString = "https://patentscope.wipo.int/search/pt/detail.jsf?docId=" + applicationNumber + "&redirectedID=true";
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10000); // 10 segundos
        connection.setReadTimeout(10000);    // 10 segundos
        connection.connect();

        int responseCode = connection.getResponseCode();

        assertEquals(200, responseCode, "URL não respondeu com sucesso!");

        connection.disconnect();
    }
}
