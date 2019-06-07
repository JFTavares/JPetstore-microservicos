/**
 *    Copyright 2010-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.jpetstore.service;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.jpetstore.domain.Category;
import org.mybatis.jpetstore.domain.Item;
import org.mybatis.jpetstore.domain.Product;
import org.mybatis.jpetstore.mapper.CategoryMapper;
import org.mybatis.jpetstore.mapper.ItemMapper;
import org.mybatis.jpetstore.mapper.ProductMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
  private final RestTemplate rest;

  public CatalogService(CategoryMapper categoryMapper, ItemMapper itemMapper, ProductMapper productMapper) {
    this.categoryMapper = categoryMapper;
    this.itemMapper = itemMapper;
    this.productMapper = productMapper;
    this.rest = new RestTemplate();
    rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
  }

  @Override
  public List<Category> getCategoryList() {
    return categoryMapper.getCategoryList();
  }

  /**
   * @Override public Category getCategory(String categoryId) { return categoryMapper.getCategory(categoryId); }
   *
   * @return
   */

  @Override
  public Category getCategory(String categoryId) {
    ResponseEntity<Category> response = rest.getForEntity("http://localhost:8083/catalog/category/" + categoryId,
        Category.class);
    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
      return null;
    }
    return response.getBody();
  }

  @Override
  public Product getProduct(String productId) {
    ResponseEntity<Product> response = rest.getForEntity("http://localhost:8083/catalog/product/" + productId,
        Product.class);
    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
      return null;
    }
    return response.getBody();
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