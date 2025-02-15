package com.MyProject.Ecom.service.customer;

import com.MyProject.Ecom.dto.WishlistDto;

import java.util.List;

public interface WishlistService {
    WishlistDto addProductToWishlist(WishlistDto wishlistDto);
    List<WishlistDto> getWishlistByUserId(Long userId);
}
