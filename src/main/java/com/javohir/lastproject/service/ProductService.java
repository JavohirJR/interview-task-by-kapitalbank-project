package com.javohir.lastproject.service;

import com.javohir.lastproject.entity.Category;
import com.javohir.lastproject.entity.Detail;
import com.javohir.lastproject.entity.Product;
import com.javohir.lastproject.entity.photo.Attachment;
import com.javohir.lastproject.payload.ApiResponse;
import com.javohir.lastproject.payload.ProductDTO;
import com.javohir.lastproject.repository.AttachmentRepository;
import com.javohir.lastproject.repository.CategoryRepository;
import com.javohir.lastproject.repository.DetailRepository;
import com.javohir.lastproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    DetailRepository detailRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AttachmentService attachmentService;

    @Autowired
    AttachmentRepository attachmentRepository;


    // GET  ALL PRODUCTS
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    // GET PRODUCT BY ID
    public ApiResponse getProduct(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.map(product -> new ApiResponse("Success", true, product)).orElseGet(() -> new ApiResponse("Product is not found", false));
    }

//    GET PRODUCT DETAIL BY PRODUCT ID
    public ApiResponse getDetailsByProductId(Integer id) {
        List<Detail> details = detailRepository.findByProductId(id);
        return new ApiResponse("Success", true, details);
    }


    //POST PRODUCT AND PHOTO
    // Buni boshqattan ko'rish kerak!!!!!!
    public ApiResponse addWithPhotoProduct(ProductDTO productDTO, MultipartHttpServletRequest request) {
        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
        if (!optionalCategory.isPresent()) return new ApiResponse("Category is not found", false);
        ApiResponse upload = attachmentService.upload(request);
        productRepository.save(new Product(
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                (Attachment) upload.getObject(),
                optionalCategory.get()
        ));
        return new ApiResponse("Product is added successfully", true);
    }

    public ApiResponse addWithProduct(ProductDTO productDTO) {
        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
        if (!optionalCategory.isPresent()) return new ApiResponse("Category is not found", false);

        productRepository.save(new Product(
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                optionalCategory.get()
        ));
        return new ApiResponse("Product is added successfully", true);
    }

    //EDIT PRODUCT
    public ApiResponse editProduct(ProductDTO productDTO, Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) return new ApiResponse("Product is not found", false);

        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
        if (!optionalCategory.isPresent()) return new ApiResponse("Category is not found", false);

        Product product = optionalProduct.get();
        product.setName(productDTO.getName());
        product.setCategory(optionalCategory.get());
        product.setPrice(productDTO.getPrice());
        productRepository.save(product);
        return new ApiResponse("Product is edited successfully", true);
    }

    public ApiResponse deleteProduct(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.delete(optionalProduct.get());
            return new ApiResponse("Product is deleted successfully", true);
        }
        return new ApiResponse("Product is not found", false);
    }
}
