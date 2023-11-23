package lk.grb.ceylonPottersPalette.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.grb.ceylonPottersPalette.db.DbConnection;
import lk.grb.ceylonPottersPalette.dto.CustomerDto;
import lk.grb.ceylonPottersPalette.dto.CustomerOrderDto;
import lk.grb.ceylonPottersPalette.dto.SupplierOrderDto;
import lk.grb.ceylonPottersPalette.model.CustomerModel;
import lk.grb.ceylonPottersPalette.model.CustomerOrderDetailModel;
import lk.grb.ceylonPottersPalette.model.CustomerOrderModel;
import lk.grb.ceylonPottersPalette.model.ProductStockModel;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.StyleUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;

public class CustomerOrderManageBarFormController {

    @FXML
    private Text amount;

    @FXML
    private Text customerId;

    @FXML
    private Text date;

    @FXML
    private Text id;

    @FXML
    private Text time;

    @FXML
    private ImageView viewImg;

    @FXML
    private ImageView reportImg;

    CustomerModel customerModel = new CustomerModel();
    CustomerOrderModel customerOrderModel = new CustomerOrderModel();
    CustomerOrderDetailModel customerOrderDetailModel = new CustomerOrderDetailModel();

    @FXML
    void viewDetailsOnMouseClick(MouseEvent event) throws IOException {
        CustomerOrderViewPopUpFormController.customerOrderId = id.getText();
        CustomerOrderViewPopUpFormController.customerId = customerId.getText();
        Navigation.imgPopUpBackground("customerOrderViewPopUpForm.fxml");
    }

    @FXML
    void viewOnMouseEntered(MouseEvent event) {
        StyleUtil.viewImgSelected(viewImg);
    }

    @FXML
    void viewOnMouseExited(MouseEvent event) {
        StyleUtil.viewImgUnselected(viewImg);
    }

    @FXML
    void reportOnMouseClick(MouseEvent event) throws SQLException, JRException {
        CustomerOrderDto customerOrderDto = customerOrderModel.getData(id.getText());
        CustomerDto customerDto = customerModel.getData(customerOrderDto.getCustomer_Id());

        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("date",customerOrderDto.getDate());
        hashMap.put("time",customerOrderDto.getTime());
        hashMap.put("orderId",customerOrderDto.getCustomer_Order_Id());
        hashMap.put("customerId",customerOrderDto.getCustomer_Id());
        hashMap.put("customerName",customerDto.getName());
        hashMap.put("contactNo",customerDto.getContact_No());
        hashMap.put("total",String.valueOf(customerOrderDto.getTotal_Price()));

        InputStream resourceAsStream = getClass().getResourceAsStream("/report/customerOrderReport.jrxml");

        JasperDesign load = JRXmlLoader.load(resourceAsStream);

        JRDesignQuery jrDesignQuery = new JRDesignQuery();
        jrDesignQuery.setText("SELECT\n" +
                "    cod.product_Id,\n" +
                "    p.description,\n" +
                "    cod.product_Quantity,\n" +
                "    p.unit_Price,\n" +
                "    p.category\n" +
                "FROM\n" +
                "    customer_Order co\n" +
                "JOIN\n" +
                "    customer_Order_Detail cod ON co.customer_Order_Id = cod.customer_Order_Id\n" +
                "JOIN\n" +
                "    product_Stock p ON p.product_Id = cod.product_Id\n" +
                "WHERE\n" +
                "    co.customer_Order_Id = '"+ id.getText() +"'");

        load.setQuery(jrDesignQuery);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
        JasperExportManager.exportReportToPdfFile(jasperPrint,"/home/gayanuka/Documents/Jasper Reports/Customer_Report_PDFs/"+id.getText()+".pdf");
    }

    @FXML
    void reportOnMouseEntered(MouseEvent event) {
        StyleUtil.viewReportImgSelected(reportImg);
    }

    @FXML
    void reportOnMouseExited(MouseEvent event) {
        StyleUtil.viewReportImgUnselected(reportImg);
    }

    public void setData(String id) {
        try {
            CustomerOrderDto customerOrderDto = customerOrderModel.getData(id);

            this.id.setText(customerOrderDto.getCustomer_Order_Id());
            customerId.setText(customerOrderDto.getCustomer_Id());
            amount.setText(String.valueOf(customerOrderDto.getTotal_Price()));
            date.setText(customerOrderDto.getDate());
            time.setText(customerOrderDto.getTime());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
