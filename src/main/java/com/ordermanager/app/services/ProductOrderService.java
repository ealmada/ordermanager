package com.ordermanager.app.services;

import com.ordermanager.app.dao.ProductOrderDAO;
import com.ordermanager.app.model.ProductOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class ProductOrderService {

    private ProductOrderDAO productOrderDao;

    Logger logger = LoggerFactory.getLogger(ProductOrderService.class);

    @Autowired
    public ProductOrderService(ProductOrderDAO productOrderDao){
        this.productOrderDao = productOrderDao;

        //Adding some sample data
        productOrderDao.save(new ProductOrder("Example P 1", 2, 3));
        productOrderDao.save(new ProductOrder("Example P 2", 4,5));

        productOrderDao.findAll().stream().forEach(p -> BumexMemcached.getInstance().set(p.getId().toString(), p));

    }

    @GetMapping(value = "/pedidos/{idProductOrder}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductOrder> selectProductOrderById(@PathVariable Integer idProductOrder){
        logger.info("Querying ProductOrder " + idProductOrder);

        logger.info("Looking for ProductOrder" + idProductOrder + " in cache");

        Optional<ProductOrder> productOrder = null;

        ProductOrder cachedProductOrder = (ProductOrder) BumexMemcached.getInstance().get(idProductOrder.toString());

        if ( cachedProductOrder != null ){
            logger.debug("ProductOrder found in cache");
            return new ResponseEntity<ProductOrder>(cachedProductOrder, HttpStatus.OK);
        }

        logger.info("Looking for ProductOrder " + idProductOrder + " in DB");
        productOrder = productOrderDao.findById(idProductOrder);

        if(!productOrder.isPresent()){
            logger.debug("ProductOrder " + idProductOrder + " not found.");
            return new ResponseEntity<ProductOrder>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ProductOrder>(productOrder.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/pedidos/create/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductOrder> create(@Valid @RequestBody ProductOrder productOrder){
        return new ResponseEntity<>(productOrderDao.save(productOrder), HttpStatus.CREATED);
    }

    @PutMapping(value = "/pedidos/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductOrder> update(@PathVariable("id") long id, @Valid @RequestBody ProductOrder productOrder){
        logger.info("Looking for product order: " + productOrder.getId() + " to insert/update.");
        ProductOrder foundOrder = null;

        if( productOrder.getId() != null ){
            logger.info("It's an update");

        }

        logger.info("Updating cache " + productOrder.getId());
        BumexMemcached.getInstance().set(productOrder.getId().toString(), productOrder);

        logger.info("Saving into the db " + productOrder.getId() );
        return new ResponseEntity<>(productOrderDao.save(productOrder), HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "pedidos/{idProductOrder}")
    ResponseEntity<?> delete(@PathVariable Integer idProductOrder) {
        try {
            logger.info("Removing product order from cache");
            BumexMemcached.getInstance().delete(idProductOrder.toString());

            productOrderDao.deleteById(idProductOrder);
            logger.info("Product Order " + idProductOrder + " successfully deleted.");

            return new ResponseEntity<>("Data deleted successfully", HttpStatus.ACCEPTED);

        } catch (Exception e) {
            logger.info("Product Order " + idProductOrder + " Resource not found.");
            return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
        }
    }
}

