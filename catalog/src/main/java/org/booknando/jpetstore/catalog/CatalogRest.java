package org.booknando.jpetstore.catalog;
import org.booknando.jpetstore.catalog.domain.Category;
import org.booknando.jpetstore.catalog.domain.Item;
import org.booknando.jpetstore.catalog.domain.Product;
import org.booknando.jpetstore.catalog.mapper.CategoryMapper;
import org.booknando.jpetstore.catalog.mapper.ItemMapper;
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
    private final ItemMapper itemMapper;


    public CatalogRest(ProductMapper productMapper, CategoryMapper categoryMapper, ItemMapper itemMapper) {
        this.productMapper = productMapper;
        this.categoryMapper = categoryMapper;
        this.itemMapper = itemMapper;
    }

    @RequestMapping(path = "/catalog/product/{productId}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable("productId") String productId) {
        return productMapper.getProduct(productId);
    }

    @RequestMapping(path = "/catalog/category/{categoryId}", method = RequestMethod.GET)
    public Category getCategory(@PathVariable("categoryId") String categoryId) {
        return categoryMapper.getCategory(categoryId);
    }

    @RequestMapping(path = "/catalog/item/{itemId}", method = RequestMethod.GET)
    public Item getItem(@PathVariable("itemId") String itemId) {
        return itemMapper.getItem(itemId);
    }

}
