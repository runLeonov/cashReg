package ua.training;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import ua.training.dao.daoimpl.*;
import ua.training.dao.entity.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseTest {
    @InjectMocks
    private UserDAO storageUser;
    @InjectMocks
    private ProductDAO storageProduct;
    @InjectMocks
    private ProductInCheckStoreDAO storageProductInCheckOrStore;
    @InjectMocks
    private CheckDAO storageCheck;
    @InjectMocks
    private CheckWithProductsDAO storageCheckWithProducts;

    @Test
    public void storageUserTest() {
        User expectedUser = new User.Builder().withUserRole(UserRole.CASHIER)
                .withName("django")
                .withEmail("email@em.com")
                .withPassword("pass")
                .withUserRoleId(1)
                .build();

        storageUser.insert(expectedUser);
        User actualUser = storageUser.findUserByLogin("email@em.com");

        assertEquals(expectedUser, actualUser);
        assertNotNull("User is null", actualUser);
        storageUser.delete(expectedUser.getId());
    }

    @Test
    public void storageProductInCheckOrStoreTest() {
        Product product = new Product.Builder()
                .withName("Картопля")
                .withId(2)
                .withPrice(70)
                .build();
        ProductInCheckStore expectedProductInCheckStore = new ProductInCheckStore.Builder()
                .withTotalPrice(1)
                .withId(2)
                .withProduct(product)
                .withWeightOrCount(123)
                .build();

        storageProductInCheckOrStore.updateWeight(123.0, 2);
        ProductInCheckStore actualProductInCheckStore = storageProductInCheckOrStore.findById(2);
        assertEquals(expectedProductInCheckStore, actualProductInCheckStore);
        assertNotNull("prod is null", actualProductInCheckStore);

        actualProductInCheckStore = storageProductInCheckOrStore.findByNameOfProd("Картопля");
        assertEquals(expectedProductInCheckStore, actualProductInCheckStore);
        assertNotNull("prod is null", actualProductInCheckStore);

        actualProductInCheckStore = storageProductInCheckOrStore.updateWeight(17.11, 2);
        assertNotEquals(expectedProductInCheckStore.getWeightOrCount(), actualProductInCheckStore.getWeightOrCount(), 0.0);
        assertNotNull("prod is null", actualProductInCheckStore);

        actualProductInCheckStore = storageProductInCheckOrStore.updateWeight(123.0, 2);
        assertTrue(storageProductInCheckOrStore.isEnough(122.0, 2));
        assertNotNull("prod is null", actualProductInCheckStore);

        assertTrue(storageProductInCheckOrStore.decrementWeight(1.0, 2));
        assertNotNull("prod is null", actualProductInCheckStore);
        storageProductInCheckOrStore.updateWeight(123.0, 2);
    }

    @Test
    public void storageProductTest() {
        Product expectedProduct = new Product.Builder()
                .withName("Картопля")
                .withId(2)
                .withPrice(70)
                .build();

        storageProduct.update(expectedProduct);
        Product actualProduct = storageProduct.findById(2);
        assertEquals(expectedProduct, actualProduct);

        actualProduct = storageProduct.findByNameOfProd("Картопля");
        assertEquals(expectedProduct, actualProduct);

        assertFalse("already exist", storageProduct.insert(expectedProduct));
        assertFalse(storageProduct.delete(expectedProduct.getId()));

        storageProduct.updatePrice(70.0, 2);
        assertEquals(expectedProduct, actualProduct);

    }


    @Test
    public void storageCheckTest() {
        Check check = new Check.Builder()
                .withId(1001)
                .withProducts(storageProductInCheckOrStore.findAll())
                .build();
        assertNull(storageCheck.update(check));
        assertFalse(storageCheck.insert(check));
        assertTrue(storageCheck.delete(1001));
        Check actualCheck = storageCheck.findById(1001);
        assertNotEquals(check, actualCheck);

        assertNotEquals(java.util.Optional.ofNullable(storageCheck.findLast()), 1000);
        assertTrue(storageCheck.getCountOfChecks() >= 0);
        assertTrue(storageCheck.getCountOfDeletedChecks() >= 0);
    }

    @Test
    public void storageCheckWithProductsTest() {
        assertTrue(storageCheckWithProducts.findById(1000).getProducts().isEmpty());
        assertEquals(storageCheckWithProducts.getTotalSum(), 0, 1000000.0);
        assertTrue(storageCheckWithProducts.delete(1000));
    }
}
