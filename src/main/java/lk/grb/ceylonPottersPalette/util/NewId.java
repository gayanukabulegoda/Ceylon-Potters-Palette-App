package lk.grb.ceylonPottersPalette.util;

import javafx.scene.control.Alert;

import java.util.ArrayList;

public class NewId {
    public static String newId(ArrayList<String> list, GetType getType) {
        int maxIdNum = 0;

        // Find the maximum ID number in the list
        for (String id : list) {
            try {
                String[] split = id.split("[^0-9]+");
                int idNum = Integer.parseInt(split[1]);
                if (idNum > maxIdNum) {
                    maxIdNum = idNum;
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage());
            }
        }

        // Increment the maximum ID number
        maxIdNum++;

        // Generate the new ID based on the type
        switch (getType) {
            case EMPLOYEE:
                return "E-00" + maxIdNum;
            case SUPPLIER:
                return "S-00" + maxIdNum;
            case CUSTOMER:
                return "C-00" + maxIdNum;
            case PRODUCT_STOCK:
                return "P-00" + maxIdNum;
            case ITEM_STOCK:
                return "I-00" + maxIdNum;
            case ATTENDANCE_ID:
                return "A-00" + maxIdNum;
            case CUSTOMER_ORDER:
                return "CO-0" + maxIdNum;
            case SUPPLIER_ORDER:
                return "SO-0" + maxIdNum;
            case SALARY_ID:
                return "SI-0" + maxIdNum;
            default:
                return null;
        }
    }

//    public static String newId(ArrayList<String> list, GetType getType) {
//
//        String lastId = null;
//
//        for (int i = 0; i < list.size(); i++) {
//            lastId = list.get(i);
//        }
//
//        switch (getType) {
//            case EMPLOYEE:
//                try {
//                    String[] split = lastId.split("E00");
//                    int idNum = Integer.parseInt(split[1]);
//                    idNum++;
//                    return "E00" + idNum;
//                } catch (Exception e) {
//                    return "E001";
//                }
//
//            case SUPPLIER:
//                try {
//                    String[] split = lastId.split("S00");
//                    int idNum = Integer.parseInt(split[1]);
//                    idNum++;
//                    return "S00" + idNum;
//                } catch (Exception e) {
//                    return "S001";
//                }
//
//            case CUSTOMER:
//                try {
//                    String[] split = lastId.split("C00");
//                    int idNum = Integer.parseInt(split[1]);
//                    idNum++;
//                    return "C00" + idNum;
//                } catch (Exception e) {
//                    return "C001";
//                }
//
//            case PRODUCT_STOCK:
//                try {
//                    String[] split = lastId.split("P00");
//                    int idNum = Integer.parseInt(split[1]);
//                    idNum++;
//                    return "P00" + idNum;
//                } catch (Exception e) {
//                    return "P001";
//                }
//
//            case ITEM_STOCK:
//                try {
//                    String[] split = lastId.split("I00");
//                    int idNum = Integer.parseInt(split[1]);
//                    idNum++;
//                    return "I00" + idNum;
//                } catch (Exception e) {
//                    return "I001";
//                }
//
//            case SUPPLIER_ORDER:
//                try {
//                    String[] split = lastId.split("SO-0");
//                    int idNum = Integer.parseInt(split[1]);
//                    idNum++;
//                    return "SO-0" + idNum;
//                } catch (Exception e) {
//                    return "SO-01";
//                }
//
//            case CUSTOMER_ORDER:
//                try {
//                    String[] split = lastId.split("CO-0");
//                    int idNum = Integer.parseInt(split[1]);
//                    idNum++;
//                    return "CO-0" + idNum;
//                } catch (Exception e) {
//                    return "CO-01";
//                }
//
//            case ATTENDANCE_ID:
//                try {
//                    String[] split = lastId.split("A00");
//                    int idNum = Integer.parseInt(split[1]);
//                    idNum++;
//                    return "A00" + idNum;
//                } catch (Exception e) {
//                    return "A001";
//                }
//
//            case SALARY_ID:
//                try {
//                    String[] split = lastId.split("SI-0");
//                    int idNum = Integer.parseInt(split[1]);
//                    idNum++;
//                    return "SI-0" + idNum;
//                } catch (Exception e) {
//                    return "SI-01";
//                }
//
//            default:
//                return null;
//        }
//    }
    public enum GetType {
        EMPLOYEE, SUPPLIER, CUSTOMER, PRODUCT_STOCK, ITEM_STOCK, SUPPLIER_ORDER, CUSTOMER_ORDER, ATTENDANCE_ID, SALARY_ID
    }
}
