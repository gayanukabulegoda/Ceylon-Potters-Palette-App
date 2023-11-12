package lk.grb.ceylonPottersPalette.util;

import java.util.ArrayList;

public class NewId {
    public static String newId(ArrayList<String> list, GetType getType) {

        String lastId = null;

        for (int i = 0; i < list.size(); i++) {
            lastId = list.get(i);
        }

        switch (getType) {
            case EMPLOYEE:
                try {
                    String[] split = lastId.split("E00");
                    int idNum = Integer.parseInt(split[1]);
                    idNum++;
                    return "E00" + idNum;
                } catch (Exception e) {
                    return "E001";
                }

            case SUPPLIER:
                try {
                    String[] split = lastId.split("S00");
                    int idNum = Integer.parseInt(split[1]);
                    idNum++;
                    return "S00" + idNum;
                } catch (Exception e) {
                    return "S001";
                }

            case CUSTOMER:
                try {
                    String[] split = lastId.split("C00");
                    int idNum = Integer.parseInt(split[1]);
                    idNum++;
                    return "C00" + idNum;
                } catch (Exception e) {
                    return "C001";
                }

            case PRODUCT_STOCK:
                try {
                    String[] split = lastId.split("P00");
                    int idNum = Integer.parseInt(split[1]);
                    idNum++;
                    return "P00" + idNum;
                } catch (Exception e) {
                    return "P001";
                }

            case ITEM_STOCK:
                try {
                    String[] split = lastId.split("I00");
                    int idNum = Integer.parseInt(split[1]);
                    idNum++;
                    return "I00" + idNum;
                } catch (Exception e) {
                    return "I001";
                }

            case SUPPLIER_ORDER:
                try {
                    String[] split = lastId.split("SO-0");
                    int idNum = Integer.parseInt(split[1]);
                    idNum++;
                    return "SO-0" + idNum;
                } catch (Exception e) {
                    return "SO-01";
                }

            case CUSTOMER_ORDER:
                try {
                    String[] split = lastId.split("CO-0");
                    int idNum = Integer.parseInt(split[1]);
                    idNum++;
                    return "CO-0" + idNum;
                } catch (Exception e) {
                    return "CO-01";
                }

            case ATTENDANCE_ID:
                try {
                    String[] split = lastId.split("A00");
                    int idNum = Integer.parseInt(split[1]);
                    idNum++;
                    return "A00" + idNum;
                } catch (Exception e) {
                    return "A001";
                }

            case SALARY_ID:
                try {
                    String[] split = lastId.split("SI-0");
                    int idNum = Integer.parseInt(split[1]);
                    idNum++;
                    return "SI-0" + idNum;
                } catch (Exception e) {
                    return "SI-01";
                }

            default:
                return null;
        }
    }
    public enum GetType {
        EMPLOYEE, SUPPLIER, CUSTOMER, PRODUCT_STOCK, ITEM_STOCK, SUPPLIER_ORDER, CUSTOMER_ORDER, ATTENDANCE_ID, SALARY_ID
    }
}
