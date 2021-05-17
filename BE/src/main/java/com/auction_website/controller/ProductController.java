package com.auction_website.controller;
import com.auction_website.model.dto.ProductAuctionResultDTO;
import com.auction_website.model.dto.ProductAuctionTopDTO;
import com.auction_website.model.Auction;
import com.auction_website.model.ProductImage;
import com.auction_website.model.ProductTransaction;
import com.auction_website.service.auction.AuctionService;
import com.auction_website.service.category.CategoryService;
import com.auction_website.service.product.ProductService;
import com.auction_website.service.product_image.ProductImageService;
import com.auction_website.service.product_transaction.ProductTransactionService;

import com.auction_website.model.Product;
import com.auction_website.model.dto.DetailProductDTO;
import com.auction_website.model.Category;
import com.auction_website.model.ProductDTO;

import com.auction_website.service.schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductImageService productImageService;
    @Autowired
    private ProductTransactionService productTransactionService;
    @Autowired
    private AuctionService auctionService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ScheduleService scheduleService;

    /**
     * Method: Get all data product auction by category
     * Author: HanTH
     *
     * @param categoryId
     * @return
     */
    @GetMapping("api/product/category/{categoryId}")
    public ResponseEntity<?> showAllProductAuction(@PathVariable("categoryId") Integer categoryId) {
        try {
            List<ProductImage> listProduct = productImageService.showAllProductAuction( categoryId );
            return new ResponseEntity<>( listProduct, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }

    }

    /**
     * Author : SonPH
     * find product by productId
     *
     * @param productId
     */
    @GetMapping("api/product/read/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Integer productId) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }

    /**
     * author: PhucPT
     * method: get product detail
     * @param productId
     * @return
     */
    @GetMapping("/api/product/detail/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable("productId") int productId) {
        try{
            Product product = productService.getProductById(productId);
            Iterable<ProductImage> productImages = productImageService.getAllImageByProductId(productId);
            return new ResponseEntity<>(new DetailProductDTO(product, productImages), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Author: CuongNVM
     * List History product register
     */
    @GetMapping("/product-register/{id}")
    public ResponseEntity<Page<Product>> getAllProductRegister(@PageableDefault(size = 5) Pageable pageable, @PathVariable Integer id) {
        try {
            Page<Product> product = productService.findAllProductRegister(pageable, id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Author : TungNT
     * Get all product
     */
    @GetMapping("/api/account/admin/list_product")
    public ResponseEntity<List<Product>> getAllProduct() {
        try {
            List<Product> productList = productService.getAllProduct();
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Author: CuongNVM
     * Update status for button Cancel items register
     */
    @PutMapping("/product-register/update/{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable Integer id) {
        try {
            productService.updateStatus(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Author : TungNT
     * Approved product to auction;
     */
    @GetMapping("/api/account/admin/approve/{productId}")
    public ResponseEntity<Void> approvedProduct(@PathVariable Integer productId) {
        try {
            productService.approvedProduct(productId);
            scheduleService.endOfAuctionSchedule(productId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Author: CuongNVM
     * Update status for button "Đăng kí lại" items
     */
    @PutMapping("/product-register/update/{price}/{description}/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Double price,@PathVariable String description , @PathVariable Integer id) {
        try {
            productService.updateProduct(price, description, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Author : TungNT
     * Edit product
     */
    @PutMapping("/api/account/admin/edit_product")
    public ResponseEntity<Void> editProduct(@RequestBody ProductDTO productDTO) {
        try {

            if (productDTO == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            List<ProductImage> productImageList = productDTO.getProductImageList();
            productService.editProduct(productDTO.getProduct());
            productImageService.deleteImagesById(productDTO.getProduct().getProductId());

            for (ProductImage productImage : productImageList) {
                productImageService.saveProductImage(productImage);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Author : TungNT
     * Search product multi cases
     */
    @GetMapping("/api/account/admin/search")
    public ResponseEntity<List<Product>> getProductBySearch(
            @RequestParam String productName,
            @RequestParam Integer categoryId,
            @RequestParam String userName,
            @RequestParam Integer productStatusId) {
        try {
            if (productName.equals("undefined")) {
                productName = null;
            }

            if (userName.equals("undefined")) {
                userName = null;
            }

            if (categoryId == 0) {
                categoryId = null;
            }

            if (productStatusId == 0) {
                productStatusId = null;
            }
            List<Product> productList = productService.getProductBySearch(productName, categoryId, userName, productStatusId);
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Author: DungHA
     *
     * @param productId
     * @return
     */

    @GetMapping("/api/productImage/read/{productId}")
    public ResponseEntity<List<ProductImage>> getAllProductImage(@PathVariable("productId") Integer productId) {
        List<ProductImage> productImages = productImageService.getAllProductImageByProductId(productId);

        if (productImages == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(productImages, HttpStatus.OK);
    }

    /**
     * Author : TungNT
     * Statistics product by month or by year
     */
    @GetMapping("/api/account/admin/statistic")
    public ResponseEntity<List<Product>> getProductByDate(
            @RequestParam(value = "monthSearch", required = false) Integer monthSearch,
            @RequestParam(value = "yearSearch", required = false) Integer yearSearch) {
        try {
            if (monthSearch == 0) {
                monthSearch = null;
            }
            List<Product> productList = productService.getProductByDate(monthSearch, yearSearch);
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Author : TungNT
     * Get product by product's id.
     */
    @GetMapping("/api/account/admin/product/{id}")
    public ResponseEntity<ProductDTO> getProductByIdForAdmin(@PathVariable Integer id) {
        try {
            Product product = productService.getProductById(id);
            List<ProductImage> productImageList = productImageService.getImagesProductById(id);

            ProductDTO productDTO = new ProductDTO();

            productDTO.setProduct(product);
            productDTO.setProductImageList(productImageList);

            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Author : TungNT
     * Get all category.
     */
    @GetMapping("/api/account/admin/product_category")
    public ResponseEntity<List<Category>> getAllCategory() {
        try {
            List<Category> categoryList = categoryService.findAll();
            return new ResponseEntity<>(categoryList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Author : TungNT
     * Get category by id.
     */
    @GetMapping("/api/account/admin/product_category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
        try {
            Category category = this.categoryService.findById(id);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method: get product end auction
     * Author: HanTH
     *
     * @param categoryId
     * @return
     */
    @GetMapping("api/product/end/category/{categoryId}")
    public ResponseEntity<?> showAllProductEndAuction(@PathVariable("categoryId") Integer categoryId) {
        try {
            List<ProductImage> listProduct = productImageService.showAllProductEndAuction( categoryId );
            return new ResponseEntity<>( listProduct, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

    /**
     * Method: Get data result auction by category
     * Author: HanTH
     *
     * @param categoryId
     * @return
     */
    @GetMapping("api/product/result/category/{categoryId}")
    public ResponseEntity<?> showAllProductResult(@PathVariable Integer categoryId) {
        try {
            List<ProductAuctionResultDTO> listProduct = new ArrayList<>();
            List<ProductTransaction> listProductTrans = productTransactionService.showAllProductResult( categoryId );
            ProductAuctionResultDTO productAuctionResultDTO = null;
            for (ProductTransaction listProductTran : listProductTrans) {
                productAuctionResultDTO = new ProductAuctionResultDTO();
                productAuctionResultDTO.setProductId( listProductTran.getProduct().getProductId() );
                productAuctionResultDTO.setLastPrice( listProductTran.getProduct().getLastPrice() );
                productAuctionResultDTO.setProductName( listProductTran.getProduct().getProductName() );
                productAuctionResultDTO.setUserName( listProductTran.getAccount().getUser().getUserName() );
                productAuctionResultDTO.setProductImage( productImageService.findOneByProductId( productAuctionResultDTO.getProductId() ).getImage() );
                listProduct.add( productAuctionResultDTO );
            }
            return new ResponseEntity<>( listProduct, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }

    }

    /**
     * Method: search product auction
     * Author: HanTH
     * @param keySearch
     * @param category
     * @param priceRange
     * @return
     */

    @GetMapping("api/product/search")
    public ResponseEntity<?> searchProductAuction(@RequestParam("keySearch") String keySearch,
                                                  @RequestParam("category") Integer category,
                                                  @RequestParam("priceRange") Integer priceRange) {
        try {
            List<ProductImage> listProduct = productImageService.searchProductAuction( keySearch, category, priceRange );
            return new ResponseEntity<>( listProduct, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

    /**
     * Method: get top 10 product auction .
     * Author: HanTH
     * @param category
     * @return
     */
    @GetMapping("api/product/top/category/{category}")
    public ResponseEntity<?> showTopProductAuction(@PathVariable Integer category) {
        try {
            List<ProductAuctionTopDTO> listProduct = new ArrayList<>();
            List<Auction> auctionList = auctionService.showTopProductAuction( category );
            ProductAuctionTopDTO productAuctionTopDTO = null;
            for (Auction auction : auctionList) {
                productAuctionTopDTO = new ProductAuctionTopDTO();
                productAuctionTopDTO.setProductId( auction.getProduct().getProductId() );
                productAuctionTopDTO.setProductName( auction.getProduct().getProductName() );
                productAuctionTopDTO.setProductLastPrice( auction.getProduct().getLastPrice() );
                productAuctionTopDTO.setProductEndTime( auction.getProduct().getEndTime() );
                productAuctionTopDTO.setProductImage( productImageService.findOneByProductId( auction.getProduct().getProductId() ).getImage() );
                listProduct.add( productAuctionTopDTO );
            }
            return new ResponseEntity<>( listProduct, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

    /**
     * @author PhinNL
     * find all category for header
     */
    @GetMapping("/api/product/guest/category")
    public ResponseEntity<List<Category>> findAllCategory() {
        try {
            List<Category> list = categoryService.findAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    /**
     * Author : CaoLPT
     * get full product by id
     * @param productId
     * @return
     */
    @GetMapping("/api/product/{productId}")
    public ResponseEntity<?> getFullProductById(@PathVariable("productId") int productId) {
        try{
            Product product = productService.getProductById(productId);
            if (product == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
