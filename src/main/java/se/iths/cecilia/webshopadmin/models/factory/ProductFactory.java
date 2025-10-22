package se.iths.cecilia.webshopadmin.models.factory;

import se.iths.cecilia.webshopadmin.models.Product;

public abstract class ProductFactory {
    public Product create() {
        Product product = createProduct();
        return product;
    }

    protected abstract Product createProduct();

}
