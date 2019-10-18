package kng.tests.favorite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kng.helpers.Utils;
import kng.http.DataSupplier;
import static kng.EnvironmentDomain.DOMAIN;

public class Data_Favorite {

    private final Utils UTIL = new Utils();
    private final DataSupplier SUPPLIER = new DataSupplier();
    //
    final String MAIL = UTIL.getGeneratedMail();
    final String PASSWORD = "121212";
    final String FIRSTNAME = "Добавь";
    final String SECONDNAME = "Визбранное";
    private final Map<String, String> productParameters;

//
    public Data_Favorite() {
        String usedCatalogLink = "/search?razmer=48&sort=pa&tip=ekspress_dostavka&tsena=do_1000";
        productParameters = this.getBasicProductParameters(usedCatalogLink);
    }
//--------------------------------------------------------------------

    String getProductSize() {
        return productParameters.get("variantCode");
    }

    String getProductLink() {
        return productParameters.get("productLink");
    }

    String getCatalogLink() {
        String usedCatalogUrl = DOMAIN + "/search?razmer=48&sort=pa&tip=ekspress_dostavka&tsena=5100_7000";
        return usedCatalogUrl;
    }

    //-----------------------------------------------------------------
    private Map<String, String> getBasicProductParameters(String catLink) {
        Map<String, String> productParametersForBasket = new HashMap<>();

        List<String> productLinksByCatalog
                = SUPPLIER.getProductLinksFromCatalog(DOMAIN + catLink);

        for (String link : productLinksByCatalog) {
            String productLink = DOMAIN + link;
            String jsonProductContent = SUPPLIER.getJsonContent(productLink, null);
            List<Integer> availableProductSizes = SUPPLIER.getAvailableProductSizesIndex(jsonProductContent);

            for (int indexOfSize = 0; indexOfSize < availableProductSizes.size(); indexOfSize++) {

                if (availableProductSizes.get(indexOfSize) > 0
                        && SUPPLIER.isProductCompaignTypeBasic(jsonProductContent)
                        && !SUPPLIER.isOneSize(jsonProductContent)) {

                    productParametersForBasket.put("variantCode",
                            SUPPLIER.getProductVariantCode(jsonProductContent, indexOfSize));

                    productParametersForBasket.put("productLink", productLink);

                    return productParametersForBasket;
                }
            }
        }
        return productParametersForBasket;
    }
}
