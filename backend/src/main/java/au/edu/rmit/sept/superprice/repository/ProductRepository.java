package au.edu.rmit.sept.superprice.repository;

import au.edu.rmit.sept.superprice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE lower(p.productName) LIKE lower('%'||?1||'%')")
    List<Product> findByProductName(String productName);

    @Query("SELECT p FROM Product p WHERE lower(p.description) LIKE lower('%'||?1||'%')")
    List<Product> findByDescriptionContaining(String description);

    @Query("SELECT p FROM Product p WHERE p.lastUpdated = ?1")
    List<Product> findByLastUpdated(String lastUpdated);

    // Use of JOIN FETCH
    // https://stackoverflow.com/a/72238549/13298307
    @Query("SELECT p FROM Product p JOIN FETCH p.details d WHERE d.available > 0")
    List<Product> findByAvailability();

    @Query("SELECT p FROM Product p JOIN FETCH p.details d WHERE d.price BETWEEN ?1 AND ?2")
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    @Query("SELECT p FROM Product p WHERE lower(p.allergens) LIKE lower('%'||?1||'%')")
    List<Product> findByAllergensContaining(String allergen);

    @Query("SELECT p FROM Product p JOIN FETCH p.details d WHERE lower(d.store.storeName) LIKE lower('%'||?1||'%')")
    List<Product> findByStoreName(String storeName);

    @Query("SELECT p FROM Product p JOIN FETCH p.details d WHERE d.store.id IN ?1")
    List<Product> findByStoreIds(List<Long> storeIds);

    // @Query("SELECT p FROM Product p WHERE p.subCategory.subCategoryId = ?1")
    // List<Product> findAllBySubCategoryId(Long subCategoryId);

    // @Query("SELECT p FROM Product p WHERE lower(p.subCategory.subCategoryName) LIKE lower('%'||?1||'%')")
    // List<Product> findAllBySubCategoryName(String subCategoryName);

    @Query("SELECT p FROM Product p WHERE p.category.categoryId = ?1")
    List<Product> findAllByCategoryId(Long categoryId);

    @Query("SELECT p FROM Product p WHERE lower(p.category.categoryName) LIKE lower('%'||?1||'%')")
    List<Product> findAllByCategoryName(String categoryName);

    @Query("SELECT p FROM Product p JOIN p.details pd WHERE pd.discount <= ?1 and pd.discount >= ?1 - 30")
    List<Product> findAllByProductDetailDiscount(Integer discount);

    @Query("SELECT p FROM Product p JOIN p.details pd WHERE pd.discount >= 1")
    List<Product> findAllThatHaveDiscount();
}
