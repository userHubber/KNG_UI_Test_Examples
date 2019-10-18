package kng.http;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class KngHttpClient {

    HttpClient httpClient() {

        Logger.getRootLogger().setLevel(Level.ERROR); //will not show debug messages

        HttpClient httpclient;
        final SSLConnectionSocketFactory sslsf;
        SSLContext sslContext = null;

        try {
            sslContext = new SSLContextBuilder()
                    .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                    .build();
        } catch (KeyStoreException | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }

        sslsf = new SSLConnectionSocketFactory(sslContext,
                NoopHostnameVerifier.INSTANCE);

        final Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new PlainConnectionSocketFactory())
                .register("https", sslsf)
                .build();

        final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        cm.setMaxTotal(100);

        httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .setConnectionManager(cm)
                .build();

        return httpclient;
    }
}
