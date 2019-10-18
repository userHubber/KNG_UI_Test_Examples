package kng.http;

import org.apache.http.HttpResponse;
import java.util.Map;
import java.util.List;
import com.jayway.jsonpath.JsonPath;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.HashMap;
import kng.helpers.Utils;
import org.apache.http.util.EntityUtils;
import static kng.EnvironmentDomain.DOMAIN;

public class DataSupplier extends Requests {

//
    public String addProductToBasket(Map<String, String> basketParam, String authCookie) {
        final String REQUEST_URL = DOMAIN + "/basket";
        String $basketUrl = "$..basketURL";

        Map<String, String> headers = new HashMap<>();
        headers.put("Cookie", authCookie);
        headers.put("Accept", "application/json");

        Map<String, String> params = new HashMap<>();
        params.put("skuNumber", basketParam.get("skuNumber"));
        params.put("variantCode", basketParam.get("variantCode"));
        params.put("quantity", "1");
        params.put("event", "AddToCart_product");

        HttpResponse httpResponse = super.responsePost(REQUEST_URL, headers, params);

        if (httpResponse != null) {
            try {
                String jsonString = EntityUtils.toString(httpResponse.getEntity());
                List<String> $variantCode = JsonPath.parse(jsonString).read($basketUrl);
                return DOMAIN + $variantCode.get(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getJsonContent(String url, String authCookie) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Cookie", authCookie);
        headers.put("Accept", "application/json");

        HttpResponse httpResponse = super.responseGet(url, headers);

        if (httpResponse != null) {
            try {
                return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<String> getProductLinksFromCatalog(String url) {
        String productLink = "$..hits..productLink";
        String jsonString = this.getJsonContent(url, null);
        ArrayList<String> links = JsonPath.parse(jsonString).read(productLink);
        return links;
    }

    public String getOrderNumberByThankYouPage(String url, String cookie) {
        String orderNumber = "$..order._id";
        String jsonString = this.getJsonContent(url, cookie);
        ArrayList<String> $orderNumber = JsonPath.parse(jsonString).read(orderNumber);
        return String.valueOf($orderNumber.get(0));
    }

    public String getSupplierOrderNumberBySplitPage(String url, String cookie) {
        String orderNumber = "$..supplierOrder._id";
        String jsonString = this.getJsonContent(url, cookie);
        ArrayList<String> $orderNumber = JsonPath.parse(jsonString).read(orderNumber);
        return String.valueOf($orderNumber.get(0));
    }

    public String getWarehouseOrderNumberBySplitPage(String url, String cookie) {
        String orderNumber = "$..warehouseOrder._id";
        String jsonString = this.getJsonContent(url, cookie);
        ArrayList<String> $orderNumber = JsonPath.parse(jsonString).read(orderNumber);
        return String.valueOf($orderNumber.get(0));
    }

    public boolean getToggleEnabled(String toggleName, String cookie) {
        String jsonString = this.getJsonContent(DOMAIN, cookie);
        String toggleValue = "$..togglz.." + toggleName;
        ArrayList<Boolean> $toggleValue = JsonPath.parse(jsonString).read(toggleValue);
        return $toggleValue.get(0);
    }

    public List<Integer> getAvailableProductSizesIndex(String jsonContent) {
        String productAvailable = "$..product.items..available";
        return JsonPath.parse(jsonContent).read(productAvailable);
    }

    public String getProductSKU(String jsonContent) {
        String skuNumber = "$..product.skuNumber";
        List<String> $skuNumber = JsonPath.parse(jsonContent).read(skuNumber);
        return $skuNumber.get(0);
    }

    public String getProductVariantCode(String jsonContent, int availablePosition) {
        String variantCode = "$..product.items..variantCode";
        List<String> $variantCode = JsonPath.parse(jsonContent).read(variantCode);
        List<String> $variantCodeWithoutDublicate = $variantCode.stream().distinct().collect(Collectors.toList());
        return $variantCodeWithoutDublicate.get(availablePosition);
    }

    public String getProductSizeRu(String jsonContent, int availablePosition) {
        String sizeRu = "$..product.items..russianSize";
        List<String> $sizeRu = JsonPath.parse(jsonContent).read(sizeRu);
        if (!$sizeRu.isEmpty()) {
            return $sizeRu.get(availablePosition) + " RU";
        }
        return "";
    }

    public boolean isOneSize(String jsonContent) {
        String noSize = "$..product.noSize";
        List<Boolean> $noSize = JsonPath.parse(jsonContent).read(noSize);
        return $noSize.get(0);
    }

    public boolean isShowOneSize(String jsonContent) {
        String shouldShowOneSize = "$..product.shouldShowOneSize";
        List<Boolean> $shouldShowOneSize = JsonPath.parse(jsonContent).read(shouldShowOneSize);
        return $shouldShowOneSize.get(0);
    }

    public String getProductSizeVendor(String jsonContent, int availablePosition) {
        String sizeCountry = "$..product.sizeCountry";
        String sizeVendor = "$..product.items..internationalSize";

        List<String> $sizeVendor = JsonPath.parse(jsonContent).read(sizeVendor);
        List<String> $sizeCountry = JsonPath.parse(jsonContent).read(sizeCountry);

        if (!$sizeVendor.isEmpty()) {
            if (!$sizeCountry.isEmpty()) {//because, for example, size M INT not correct
                if (!$sizeCountry.get(0).contains("INT")) {
                    return $sizeVendor.get(availablePosition) + " " + $sizeCountry.get(0);
                }
            }
            return $sizeVendor.get(availablePosition);
        }
        return "";
    }

    public String getProductName(String jsonContent) {
        String productName = "$..product.name";
        List<String> $name = JsonPath.parse(jsonContent).read(productName);
        String name = $name.get(0).replaceAll("\\p{Z}", " ");
        return name;
    }

    public String getProductBrand(String jsonContent) {
        String productBrand = "$..product.brand";
        List<String> $brand = JsonPath.parse(jsonContent).read(productBrand);
        String brand = $brand.get(0).replaceAll("\\p{Z}", " ");
        return brand;
    }

    public int getProductAmount(String jsonContent) {
        String amount = "$..product.price.value";
        List<Integer> $amount = JsonPath.parse(jsonContent).read(amount);
        return $amount.get(0);
    }

    public String getProductCashlessDiscountValue(String jsonContent) {
        String discount = "$..product.cashlessDiscountValue.value";
        List<Integer> $discount = JsonPath.parse(jsonContent).read(discount);
        return Utils.amountStringFormat($discount.get(0));
    }

    public boolean isFinalPrice(String jsonContent) {
        String finalPrice = "$..product.finalPrice";
        List<Boolean> $finalPrice = JsonPath.parse(jsonContent).read(finalPrice);
        return $finalPrice.get(0);
    }

    public boolean isProductTypeZeroCount(String jsonContent, int availablePosition, String productType) {
        String type = "$..product.items..supplierQuantity";

        if (productType.contains("warehouse")) {
            type = "$..product.items..warehouseQuantity";
        }

        List<Integer> $typeCount = JsonPath.parse(jsonContent).read(type);
        return $typeCount.get(availablePosition) == 0;
    }

    public boolean isProductCompaignTypeEuro(String jsonContent) {
        String productType = "$..product.productType";
        List<String> $productType = JsonPath.parse(jsonContent).read(productType);
        return $productType.get(0).equals("euro");
    }

    public boolean isProductCompaignTypeBasic(String jsonContent) {
        String productType = "$..product.productType";
        List<String> $productType = JsonPath.parse(jsonContent).read(productType);
        return $productType.get(0).equals("basic");
    }

    public boolean isPartnerBad(String jsonContent) {
        String partner = "$..product.partner.bad";
        List<Boolean> partnerBad = JsonPath.parse(jsonContent).read(partner);
        return partnerBad.get(0);
    }

    public boolean isProductRetail(String jsonContent) {
        String productRetail = "$..product.items..retail";
        List<String> $productRetail = JsonPath.parse(jsonContent).read(productRetail);
        return !$productRetail.isEmpty();
    }

    public boolean isBrandDiscount(String jsonContent) {
        String brandDiscountAllowed = "$..brandDiscountAllowed";
        List<Boolean> $brandDiscountAllowed = JsonPath.parse(jsonContent).read(brandDiscountAllowed);
        return $brandDiscountAllowed.get(0);
    }

    public boolean isCampaignDiscount(String jsonContent) {
        String campaignDiscountAllowed = "$..campaignDiscountAllowed";
        List<Boolean> $campaignDiscountAllowed = JsonPath.parse(jsonContent).read(campaignDiscountAllowed);
        return $campaignDiscountAllowed.get(0);
    }

}
