package com.MyProject.Ecom.service;

import com.MyProject.Ecom.entity.FAQDto;

public interface FAQService {
    FAQDto postFAQ(FAQDto faqDto, Long productId);
}
