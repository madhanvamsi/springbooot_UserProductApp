package org.jsp.UserBootApp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.UserBootApp.dao.ProductDao;
import org.jsp.UserBootApp.dao.UserDao;
import org.jsp.UserBootApp.dto.Product;
import org.jsp.UserBootApp.dto.ResponseStructure;
import org.jsp.UserBootApp.dto.User;
import org.jsp.UserBootApp.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao pdao;
	@Autowired
	private UserDao udao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product, int User_id) {
		Optional<User> reUser = udao.findById(User_id);
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (reUser.isPresent()) {
			User u = reUser.get();
			u.getProducts().add(product);// adding product to user
			product.setUser(reUser.get());//setting(assigning) user to the product
			udao.updateUser(reUser.get());
			pdao.saveProduct(product);
			structure.setData(product);
			structure.setMessage("product added successfully");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product, int User_id) {
		Optional<User> r = udao.findById(User_id);
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (r.isPresent()) {
			product.setUser(r.get());// assigning user to the product
			pdao.saveProduct(product);
			structure.setData(product);
			structure.setMessage("product updated successfully");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Product>> findById(int id) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Product> reProduct = pdao.findById(id);
		if (reProduct.isPresent()) {
			structure.setData(reProduct.get());
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("product found");
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<String>> deleteProduct(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Product> reProduct = pdao.findById(id);
		if (reProduct.isPresent()) {
			pdao.deleteProduct(id);
			structure.setData("product deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("product found");
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findProductByUserID(int user_id) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setData(pdao.findProductByUserId(user_id));
		structure.setMessage("product found for user id");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}
}
