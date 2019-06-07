package org.booknando.jpetstore.catalog.service;

import org.booknando.jpetstore.catalog.domain.Category;
import org.booknando.jpetstore.catalog.domain.Item;
import org.booknando.jpetstore.catalog.domain.Product;
import org.booknando.jpetstore.catalog.mapper.CategoryMapper;
import org.booknando.jpetstore.catalog.mapper.ItemMapper;
import org.booknando.jpetstore.catalog.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class CatalogService.
 *
 * @author Eduardo Macarron
 */
@Service
public class CatalogService implements ICatalogService {

  private final CategoryMapper categoryMapper;
  private final ItemMapper itemMapper;
  private final ProductMapper productMapper;
  private final ThreadLocal<String> categoryId;

  public CatalogService(CategoryMapper categoryMapper, ItemMapper itemMapper, ProductMapper productMapper) {
    this.categoryMapper = categoryMapper;
    this.itemMapper = itemMapper;
    this.productMapper = productMapper;
    categoryId = new ThreadLocal<String>();
  }

  @Override
  public List<Category> getCategoryList() {
    return categoryMapper.getCategoryList();
  }

  @Override
  @Transactional
  public Category getCategory(String categoryId) {
    this.categoryId.set(categoryId);
    return categoryMapper.getCategory(categoryId);
  }

  @Override
  public Product getProduct(String productId) {
    return productMapper.getProduct(productId);
  }

  @Override
  public List<Product> getProductListByCategory(String categoryId) {
    return productMapper.getProductListByCategory(categoryId);
  }

  /**
   * Search product list.
   *
   * @param keywords
   *          the keywords
   * @return the list
   */
  @Override
  public List<Product> searchProductList(String keywords) {
    List<Product> products = new ArrayList<>();
    for (String keyword : keywords.split("\\s+")) {
      products.addAll(productMapper.searchProductList("%" + keyword.toLowerCase() + "%"));
    }
    return products;
  }

  @Override
  public List<Item> getItemListByProduct(String productId) {
    return itemMapper.getItemListByProduct(productId);
  }

  @Override
  public Item getItem(String itemId) {
    return itemMapper.getItem(itemId);
  }

  @Override
  public boolean isItemInStock(String itemId) {
    return itemMapper.getInventoryQuantity(itemId) > 0;
  }
}