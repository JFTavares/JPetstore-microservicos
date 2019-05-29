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

import java.util.List;

import org.mybatis.jpetstore.domain.Category;
import org.mybatis.jpetstore.domain.Item;
import org.mybatis.jpetstore.domain.Product;

public class RestCatalogService implements ICatalogService {

  @Override
  public List<Category> getCategoryList() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Category getCategory(String categoryId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Product getProduct(String productId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Product> getProductListByCategory(String categoryId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Product> searchProductList(String keywords) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Item> getItemListByProduct(String productId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Item getItem(String itemId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isItemInStock(String itemId) {
    // TODO Auto-generated method stub
    return false;
  }

}
