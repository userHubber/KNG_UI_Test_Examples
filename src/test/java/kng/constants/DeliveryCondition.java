package kng.constants;

public class DeliveryCondition {

    public DeliveryCondition() {
    }
//    

    public static final String FREE_DELIVERY = "Бесплатно";
    public static final String DELIVERY_AMOUNT = "350";
    public static final String PVZ_DELIVERY_AMOUNT = "150";
    public static final String EURO_DELIVERY_AMOUNT = "700";
    public static final String DELIVERY_AMOUNT_KZ = "1200";
    public static final String DELIVERY_AMOUNT_BY = "15";
    public static final String FREE_DELIVERY_THRESHOLD = "4 999";
    
//

    public static int delivery_amount() {
        return Integer.parseInt(DELIVERY_AMOUNT);
    }

    public static int delivery_amount_kz() {
        return Integer.parseInt(DELIVERY_AMOUNT_KZ);
    }

    public static int delivery_amount_by() {
        return Integer.parseInt(DELIVERY_AMOUNT_BY);
    }

    public static int pvz_delivery_amount() {
        return Integer.parseInt(PVZ_DELIVERY_AMOUNT);
    }

    public static int euro_delivery_amount() {
        return Integer.parseInt(EURO_DELIVERY_AMOUNT);
    }

}
