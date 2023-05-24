package slsjava.example.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import slsjava.example.dao.ProductDao;
import slsjava.example.entity.Product;

@Named("createProduct")
public class CreateProductHandler implements RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {
  @Inject
  ProductDao productDao;

  @Inject
  ObjectMapper objectMapper;

  public APIGatewayV2HTTPResponse handleRequest(APIGatewayV2HTTPEvent requestEvent, Context context) {
    try {
      String id = requestEvent.getPathParameters().get("id");
      String jsonPayload = requestEvent.getBody();
      Product product = objectMapper.readValue(jsonPayload, Product.class);
      APIGatewayV2HTTPResponse response = new APIGatewayV2HTTPResponse();
      if (!product.id().equals(id)) {
        response.setStatusCode(400);
        response.setBody("Product ID in the body does not match path parameter");
        return response;
      }
      productDao.putProduct(product);

      response.setStatusCode(201);
      response.setBody("Product with id = " + id + " created");
      return response;
    } catch (Exception e) {
      e.printStackTrace();
      APIGatewayV2HTTPResponse errorResponse = new APIGatewayV2HTTPResponse();
      errorResponse.setStatusCode(500);
      errorResponse.setBody("Internal Server Error");
      return errorResponse;
    }
  }
}
