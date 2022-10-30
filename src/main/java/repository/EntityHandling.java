package repository;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class EntityHandling {
    CloseableHttpClient httpClient;
    Gson gson = new Gson();

    public EntityHandling(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    HttpEntity getHttpEntity(CloseableHttpClient httpClient, HttpRequestBase httpRequest) throws IOException {
        CloseableHttpResponse response = httpClient.execute(httpRequest);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode > 299) {
            return null;
        } else {
            return response.getEntity();
        }
    }
    void setContentTypeJson(HttpRequestBase httpRequest){
        httpRequest.setHeader("Accept", "application/json");
        httpRequest.setHeader("Content-type", "application/json");
    }
    void setContentTypeForm(HttpRequestBase httpRequest){
        httpRequest.setHeader("Accept", "application/json");
        httpRequest.setHeader("Content-type", "application/x-www-form-urlencoded");
    }
    void setContentTypeMultipart(HttpRequestBase httpRequest){
        httpRequest.setHeader("Accept", "application/json");
        httpRequest.setHeader("Content-type", "multipart/form-data");
    }

    <T> T objectFromJsonOrNull(HttpEntity entity, Class<T> classOfT) throws IOException {
        return (entity == null) ? null : gson.fromJson(EntityUtils.toString(entity), classOfT);
    }
}
