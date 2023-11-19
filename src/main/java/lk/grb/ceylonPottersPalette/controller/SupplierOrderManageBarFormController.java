package lk.grb.ceylonPottersPalette.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.grb.ceylonPottersPalette.db.DbConnection;
import lk.grb.ceylonPottersPalette.dto.EmployeeDto;
import lk.grb.ceylonPottersPalette.dto.SupplierDto;
import lk.grb.ceylonPottersPalette.dto.SupplierOrderDto;
import lk.grb.ceylonPottersPalette.model.SupplierModel;
import lk.grb.ceylonPottersPalette.model.SupplierOrderModel;
import lk.grb.ceylonPottersPalette.util.Navigation;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;

public class SupplierOrderManageBarFormController {

    @FXML
    private Text amount;

    @FXML
    private Text date;

    @FXML
    private Text id;

    @FXML
    private Text supplierId;

    @FXML
    private Text time;

    @FXML
    private ImageView viewImg;

    @FXML
    private ImageView reportImg;

    SupplierOrderModel supplierOrderModel = new SupplierOrderModel();
    SupplierModel supplierModel = new SupplierModel();

    @FXML
    void viewDetailsOnMouseClick(MouseEvent event) throws IOException {
        SupplierOrderViewPopUpFormController.supplierOrderId = id.getText();
        SupplierOrderViewPopUpFormController.supplierId = supplierId.getText();
        Navigation.imgPopUpBackground("supplierOrderViewPopUpForm.fxml");
    }

    @FXML
    void viewOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void viewOnMouseExited(MouseEvent event) {

    }

    @FXML
    void reportOnMouseClick(MouseEvent event) throws SQLException, JRException {
        SupplierOrderDto supplierOrderDto = supplierOrderModel.getData(id.getText());
        SupplierDto supplierDto = supplierModel.getData(supplierOrderDto.getSupplier_Id());

        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("date", supplierOrderDto.getDate());
        hashMap.put("time", supplierOrderDto.getDate());
        hashMap.put("orderId", supplierOrderDto.getSupplier_Order_Id());
        hashMap.put("supplierId", supplierOrderDto.getSupplier_Id());
        hashMap.put("supplierName", supplierDto.getName());
        hashMap.put("contactNo", supplierDto.getContact_No());
        hashMap.put("total", String.valueOf(supplierOrderDto.getTotal_Price()));

        InputStream resourceAsStream = getClass().getResourceAsStream("/report/supplierOrderReport.jrxml");

        JasperDesign load = JRXmlLoader.load(resourceAsStream);

        JRDesignQuery jrDesignQuery = new JRDesignQuery();
        jrDesignQuery.setText("SELECT\n" +
                "    sod.item_Id,\n" +
                "    i.description,\n" +
                "    sod.item_Qty,\n" +
                "    i.unit_Price\n" +
                "FROM\n" +
                "    supplier_Order so\n" +
                "JOIN\n" +
                "    supplier_Order_Detail sod ON so.supplier_Order_Id = sod.supplier_Order_Id\n" +
                "JOIN\n" +
                "    item_Stock i ON i.item_Id = sod.item_Id\n" +
                "WHERE\n" +
                "    so.supplier_Order_Id = '"+ id.getText() +"'");

        load.setQuery(jrDesignQuery);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
        JasperExportManager.exportReportToPdfFile(jasperPrint,"/home/gayanuka/Documents/Jasper Reports/Supplier_Report_PDFs/"+id.getText()+".pdf");
    }

    @FXML
    void reportOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void reportOnMouseExited(MouseEvent event) {

    }

    public void setData(String id) {
        try {
            SupplierOrderDto supplierOrderDto = supplierOrderModel.getData(id);

            this.id.setText(supplierOrderDto.getSupplier_Order_Id());
            supplierId.setText(supplierOrderDto.getSupplier_Id());
            amount.setText(String.valueOf(supplierOrderDto.getTotal_Price()));
            date.setText(supplierOrderDto.getDate());
            time.setText(supplierOrderDto.getTime());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
