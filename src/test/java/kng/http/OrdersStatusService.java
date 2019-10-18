package kng.http;

import java.util.HashMap;
import java.util.Map;
import static kng.EnvironmentDomain.DOMAIN;

public class OrdersStatusService extends Requests {

    private boolean setOrderStatusByAdmin(String orderNumber, String changeStatus) {
        final String REQUEST_URL = DOMAIN + "/protected/admin/testing/index/order/state";
        String authCookie;

        if (REQUEST_URL.contains("dev")) {
            authCookie = "kjQxNw;";
        } else {
            authCookie = "k1MQ;";
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("Cookie", authCookie);

        Map<String, String> params = new HashMap<>();
        params.put("id", orderNumber);
        params.put("orderState", changeStatus);
        return super.responsePost(REQUEST_URL, headers, params) != null;
    }

    public boolean setOrderStatusCancel(String orderNumber) {
        return this.setOrderStatusByAdmin(orderNumber, "CANCELED_TEST");
    }

    public void setTestOrderStatusAssembling_ok(String orderNumber) {
        this.setOrderStatusByAdmin(orderNumber, "ASSEMBLING_OK");
    }

    public void setTestOrderStatusDispatched(String orderNumber) {
        this.setOrderStatusByAdmin(orderNumber, "DISPATCHED");
    }

    public void setTestOrderStatusReturn(String orderNumber) {
        this.setOrderStatusByAdmin(orderNumber, "RETURN");
    }
}
