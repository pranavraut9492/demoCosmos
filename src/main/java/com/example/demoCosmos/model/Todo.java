package com.example.demoCosmos.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import org.springframework.data.annotation.Id;

@Container(containerName = "newContainer")
public class Todo {

    @Id
    private String id;
    private String product;
    private String code;
    @PartitionKey
    private String category;

    public Todo(String id, String product, String code, String category) {
        this.id = id;
        this.product = product;
        this.code = code;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id='" + id + '\'' +
                ", product='" + product + '\'' +
                ", code='" + code + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
