package org.jsp.UserBootApp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.UserBootApp.dto.Product;
import org.jsp.UserBootApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	private ProductRepository repository;

	public Product saveProduct(Product product) {
		return repository.save(product);
	}

	public Product updateProduct(Product product) {
		return repository.save(product);
	}

	public Optional<Product> findById(int id) {
		return repository.findById(id);
	}

	public boolean deleteProduct(int id) {
		Optional<Product> p = findById(id);
		if (p.isPresent()) {
			repository.delete(p.get());
			return true;
		}
		return false;
	}

	public List<Product> findProductByUserId(int user_id)
	{
		return repository.findProductByUserId(user_id);
	}

	
}
