import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpClient {
    private int connectionRequestTimeout;
    private int connectTimeout;
    private int socketTimeout;

    public HttpClient(int connectionRequestTimeout,int connectTimeout,int socketTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
        this.connectTimeout = connectTimeout;
        this.socketTimeout = socketTimeout;
    }

    //Setting timeouts
    private final RequestConfig REQUEST_CONFIG = RequestConfig.custom()
            .setConnectionRequestTimeout(connectionRequestTimeout)
            .setConnectTimeout(connectTimeout)
            .setSocketTimeout(socketTimeout)
            .build();
    //Building Http client
    private final CloseableHttpClient CLIENT = HttpClientBuilder.create()
            .setDefaultRequestConfig(REQUEST_CONFIG)
            .build();
    public CloseableHttpClient getClient(){
        return  CLIENT;
    }
}


