package repository;

import entity.ApiResponse;
import entity.Order;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.util.Map;

public class OrderRepository extends EntityHandling {
    private final String uri = "https://petstore.swagger.io/v2/store/";

    public OrderRepository(CloseableHttpClient httpClient) {
        super(httpClient);
    }

    //POST/store/order            Place an order for a pet
    public Order placeOrder(Order order) throws IOException {
        StringEntity requestEntity = new StringEntity(gson.toJson(order));
        HttpPost request = new HttpPost(uri + "order/");
        request.setEntity(requestEntity);
        setContentTypeJson(request);
        HttpEntity entity = getHttpEntity(httpClient, request);
        return objectFromJsonOrNull(entity, Order.class);
    }

    //GET/store/order/{orderId}       Find purchase order by ID
    //For valid response try integer IDs with value >= 1 and <= 10.
    //Other values will generate exceptions
    public Order findOrderById(Integer id) throws IOException {
        HttpGet request = new HttpGet(uri + "order/" + id);
        HttpEntity entity = getHttpEntity(httpClient, request);
        return objectFromJsonOrNull(entity, Order.class);
    }

    //DELETE/store/order/{orderId}     Delete purchase order by ID
    //For valid response try integer IDs with positive integer value.
    //Negative or non-integer values will generate API errors
    public ApiResponse deleteOrderById(Integer id) throws IOException {
        HttpDelete request = new HttpDelete(uri + "order/" + id);
        HttpEntity entity = getHttpEntity(httpClient, request);
        return objectFromJsonOrNull(entity, ApiResponse.class);
    }

    //GET/store/inventory     Returns pet inventories by status
    //Returns a map of status codes to quantities
    public Map<String, Integer> getPetInventories() throws IOException {
        HttpGet request = new HttpGet(uri + "inventory/");
        HttpEntity entity = getHttpEntity(httpClient, request);
        return mapFromJsonOrNull(entity);
    }
}
