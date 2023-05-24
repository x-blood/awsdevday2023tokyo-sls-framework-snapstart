package slsjava.example.dao;

import slsjava.example.entity.Product;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public class ProductMapper {
  private static final String PK = "PK";
  private static final String NAME = "name";
  private static final String PRICE = "price";

  public static Map<String, AttributeValue> productToDynamoDb(Product product) {
    return Map.of(
      PK, AttributeValue.builder().s(product.id()).build(),
      NAME, AttributeValue.builder().s(product.name()).build(),
      PRICE, AttributeValue.builder().n(product.price().toString()).build()
    );
  }
}
