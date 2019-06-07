package org.booknando.jpetstore.catalog;
import org.booknando.jpetstore.catalog.domain.Category;
import org.booknando.jpetstore.catalog.domain.Product;
import org.booknando.jpetstore.catalog.mapper.CategoryMapper;
import org.booknando.jpetstore.catalog.mapper.ProductMapper;
import org.booknando.jpetstore.catalog.service.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CatalogRest {

    @Autowired
    private ICatalogService service;


    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;


    public CatalogRest(ProductMapper productMapper, CategoryMapper categoryMapper) {
        this.productMapper = productMapper;
        this.categoryMapper = categoryMapper;
    }


    @RequestMapping(path = "/catalog/product/{productId}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable("productId") String productId) {
        return productMapper.getProduct(productId);
    }

    @RequestMapping(path = "/catalog/category/{categoryId}", method = RequestMethod.GET)
    public Category getCategory(@PathVariable("categoryId") String categoryId) {
        return categoryMapper.getCategory(categoryId);
    }

}
