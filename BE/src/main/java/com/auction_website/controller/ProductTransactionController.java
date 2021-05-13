package com.auction_website.controller;

import com.auction_website.model.ProductTransaction;
import com.auction_website.service.product_transaction.ProductTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/transaction")
public class ProductTransactionController {

    @Autowired
    private ProductTransactionService productTransactionService;

    @GetMapping("/list")
    public ResponseEntity<Page<ProductTransaction>> getAllTransaction(@PageableDefault(size = 5)Pageable pageable){
        try {
            Page<ProductTransaction> productTransactions = productTransactionService.getAllTransaction(pageable);
            if (productTransactions == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(productTransactions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Integer transactionId) {
        try {
            ProductTransaction productTransaction = productTransactionService.findTransaction(transactionId);
            if (productTransaction == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            productTransactionService.deleteTransaction(productTransaction.getTransactionId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
