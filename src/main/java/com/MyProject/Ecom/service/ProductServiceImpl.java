package com.MyProject.Ecom.service;


import com.MyProject.Ecom.dto.ProductDto;
import com.MyProject.Ecom.entity.Category;
import com.MyProject.Ecom.entity.Product;
import com.MyProject.Ecom.repository.CategoryRepository;
import com.MyProject.Ecom.repository.ProductRepository;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService  {

    private final ProductRepository productRepository ;

    private final CategoryRepository categoryRepository;


    public ProductDto addProduct (ProductDto productDto) throws Exception {
               Product product =new Product();
               product.setName(productDto.getName());
               product.setDescription(productDto.getDescription());
               product.setPrice(productDto.getPrice());
               product.setImg(productDto.getImg().getBytes());




              // product.setImg(productDto.getImg().getBytes());

            //   product.setImg(productDto.getImg().getBytes());
        Category category =categoryRepository.findById(productDto.getCategoryId()).orElseThrow();
        product.setCategory(category);
        return productRepository.save(product).getDto();
    }

    public List<ProductDto> getAllProduct(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public List<ProductDto> getAllProductByName(String name){
        List<Product> products = productRepository.findAllByNameContaining(name);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public boolean deleteProduct(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            productRepository.deleteById(id);
            return true;

        }

        return false;
    }

}
