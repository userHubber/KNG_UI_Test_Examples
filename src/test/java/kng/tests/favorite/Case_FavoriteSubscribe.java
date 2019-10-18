package kng.tests.favorite;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import kng.driver.DriverTools;
import kng.pageObjects.account.Favorite;
import kng.pageObjects.Basket;
import kng.pageObjects.BasketPopUp;
import kng.pageObjects.Catalog;
import kng.pageObjects.Checkout;
import kng.pageObjects.MainPage;
import kng.pageObjects.Product;
import kng.pageObjects.QuickModal;
import kng.tests.CompletedSteps;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Case_FavoriteSubscribe extends DriverTools {

    private Data_Favorite case_data;
    private CompletedSteps step;
    private Checkout checkout;
    private Catalog catalog;
    private Product product;
    private QuickModal quickModal;
    private MainPage header;
    private Favorite favorite;
    private Basket basket;
    private BasketPopUp basketPopup;
    private int currentFavorCount;
    private String productLink;
    private String catalogLink;
//================================================================   

    @BeforeTest(alwaysRun = true)
    public void preconditions() {
        System.out.println("ИЗБРАННОЕ");
        super.driverAssign();
        case_data = new Data_Favorite();
        step = new CompletedSteps();
        catalog = new Catalog();
        product = new Product();
        quickModal = new QuickModal();
        header = new MainPage();
        favorite = new Favorite();
        basket = new Basket();
        basketPopup = new BasketPopUp();
        checkout = new Checkout();
    }

    @BeforeClass(alwaysRun = true)
    public void conditions() {
        productLink = case_data.getProductLink();
        catalogLink = case_data.getCatalogLink();
    }

    @Test(priority = 1, groups = {"dev", "prod", "pre"})
    public void case_autoregFromCatalog() {
        super.driverGet(this.catalogLink);
        int $currentFavorCount = header.getFavoriteCountByHeader();
        assertTrue(catalog.pressFavoritePerPosition(5), "Not clicked fav icon");
        currentFavorCount = $currentFavorCount + 1;
        assertTrue(header.w_FavoritePerHeaderCountingContain(5, currentFavorCount), "Header - not currentFavorCount + 1");
    }

    @Test(priority = 2, groups = {"dev", "prod", "pre"})
    public void case_autoregFromProduct() {
        super.driverGet(this.productLink);
        int $currentFavorCount = header.getFavoriteCountByHeader();
        assertTrue(product.pressFavorite(5, true), "Not added to favorite from icon");
        currentFavorCount = $currentFavorCount + 1;
        assertTrue(header.w_FavoritePerHeaderCountingContain(5, currentFavorCount), "Header - not currentFavorCount + 1");
    }

    @Test(priority = 3, groups = {"dev", "prod", "pre"})
    public void case_autoregFromQuickModal() {
        super.driverGet(this.catalogLink);
        int $currentFavorCount = header.getFavoriteCountByHeader();
        assertTrue(catalog.pressQuickModal(9), "Not quick modal visible");
        assertTrue(quickModal.pressFavorite(5, true), "Not added to favorite from icon");
        currentFavorCount = $currentFavorCount + 1;
        assertTrue(header.w_FavoritePerHeaderCountingContain(5, currentFavorCount), "Header - not currentFavorCount + 1");
    }

    @Test(dependsOnMethods = {"case_autoregFromCatalog", "case_autoregFromProduct", "case_autoregFromQuickModal"},
            groups = {"dev", "prod", "pre"})
    public void case_migrationSelectedAutoregFavorToAccAfterRegistrationControl() {
        step.firstStageRegistration(case_data.MAIL, case_data.PASSWORD, true);
        super.driverGet(Favorite.getUrl());
        int countFavor = favorite.getCountProductsInFavoritePage();
        assertEquals(currentFavorCount, countFavor);
        favorite.clickUnsubscribeToAllIcons();
        assertTrue(header.w_FavoritePerHeaderCountingContain(5, 0), "Not all products deleted");
    }

    @Test(priority = 1, dependsOnMethods = {"case_migrationSelectedAutoregFavorToAccAfterRegistrationControl"},
            groups = {"dev", "prod", "pre"})
    public void case_addToBasketAfterAddedFromProduct() {
        super.driverGet(this.productLink);
        int $currentFavorCount = header.getFavoriteCountByHeader();
        assertTrue(product.pressFavorite(5, true), "Not added to favorite from icon");
        currentFavorCount = $currentFavorCount + 1;
        assertTrue(header.w_FavoritePerHeaderCountingContain(5, currentFavorCount), "Header - not currentFavorCount + 1");
        super.driverGet(Favorite.getUrl());

        if (!case_data.getProductSize().isEmpty()) {//if one size
            favorite.selectAvailableSize(case_data.getProductSize());
        }

        favorite.pressAddProductToBasket();
        assertTrue(basketPopup.w_BasketPopup_vis(10), "BasketPopup not visible");
        super.driverGet(basketPopup.getBasketUrlFromButton());
        checkout.pressDeleteProduct();
        assertTrue(basket.w_URLContain(7));
    }

    @Test(priority = 2, dependsOnMethods = {"case_migrationSelectedAutoregFavorToAccAfterRegistrationControl"},
            groups = {"dev", "prod", "pre"})
    public void case_addFromCatalog() {
        super.driverGet(this.catalogLink);
        int $currentFavorCount = header.getFavoriteCountByHeader();
        assertTrue(catalog.pressFavoritePerPosition(5), "Not clicked fav icon");
        currentFavorCount = $currentFavorCount + 1;
        assertTrue(header.w_FavoritePerHeaderCountingContain(5, currentFavorCount), "Header - not currentFavorCount + 1");
    }

    @Test(priority = 3, dependsOnMethods = {"case_migrationSelectedAutoregFavorToAccAfterRegistrationControl"},
            groups = {"dev", "prod", "pre"})
    public void case_addFromQuickModal() {
        super.driverGet(this.catalogLink);
        int $currentFavorCount = header.getFavoriteCountByHeader();
        assertTrue(catalog.pressQuickModal(9), "Not quick modal visible");
        assertTrue(quickModal.pressFavorite(5, true), "Not added to favorite from icon");
        currentFavorCount = $currentFavorCount + 1;
        assertTrue(header.w_FavoritePerHeaderCountingContain(5, currentFavorCount), "Header - not currentFavorCount + 1");
    }

    @Test(dependsOnMethods = {"case_addToBasketAfterAddedFromProduct", "case_addFromCatalog", "case_addFromQuickModal"},
            groups = {"dev", "prod", "pre"})
    public void case_addedFavorCountControlAndDeletedAfterLogin() {
        super.driverClear();
        step.authorization(case_data.MAIL, case_data.PASSWORD);
        super.driverGet(Favorite.getUrl());
        int countFavor = favorite.getCountProductsInFavoritePage();
        assertEquals(currentFavorCount, countFavor);
        favorite.clickUnsubscribeToAllIcons();
        assertTrue(header.w_FavoritePerHeaderCountingContain(5, 0), "Not all products deleted");
    }

    @AfterTest(alwaysRun = true)
    public void closeBrowser() {
        super.driverCloseQuit();
    }
}
