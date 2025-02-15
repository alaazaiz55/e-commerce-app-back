package com.MyProject.Ecom.service;


import com.MyProject.Ecom.entity.FAQ;
import com.MyProject.Ecom.entity.FAQDto;
import com.MyProject.Ecom.entity.Product;
import com.MyProject.Ecom.repository.FAQRepository;
import com.MyProject.Ecom.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService {

private final FAQRepository faqRepository;

private final ProductRepository productRepository;

public FAQDto postFAQ(FAQDto faqDto, Long productId){

    Optional<Product> optionalProduct = productRepository.findById(productId);
    if(optionalProduct.isPresent()){

        FAQ faq = new FAQ();
        faq.setQuestion(faqDto.getQuestion());
        faq.setAnswer(faqDto.getAnswer());
        faq.setProduct(optionalProduct.get());

        return faqRepository.save(faq).getFAQDto();
    }
    return null;
}
}
