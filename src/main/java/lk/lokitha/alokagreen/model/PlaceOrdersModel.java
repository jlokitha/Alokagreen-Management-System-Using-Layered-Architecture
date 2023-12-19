package lk.lokitha.alokagreen.model;

import lk.lokitha.alokagreen.db.DbConnection;
import lk.lokitha.alokagreen.dto.CustomerOrderDto;
import lk.lokitha.alokagreen.dto.SupplierOrderDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class PlaceOrdersModel {

    public static boolean saveCustomerOrder(final CustomerOrderDto customerOrderDto) throws SQLException {

        Connection connection = null;
        boolean result = false;

        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = CustomerOrderModel.saveCustomerOrder(customerOrderDto);

            if (isOrderSaved) {

                boolean isQtyUpdated = ProductStockModel.updateProductStock(customerOrderDto.getItems());

                if (isQtyUpdated) {

                    boolean isAssociatedUpdate = CustomerOrderDetailModel.saveCustomerOrderDetail(customerOrderDto.getCustomer_Order_Id(), customerOrderDto.getItems());

                    if (isAssociatedUpdate) {
                        connection.commit();
                        result = true;
                    }

                }
            }
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }

        return result;
    }

    public static boolean saveSupplierOrder(final SupplierOrderDto supplierOrderDto) throws SQLException {

        Connection connection = null;
        boolean result = false;

        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = SupplierOrderModel.saveSupplierOrder(supplierOrderDto);

            if (isOrderSaved) {

                boolean isQtyUpdated = MaterialStockModel.SaveMaterialStock(supplierOrderDto.getItems(), supplierOrderDto.getDate());

                if (isQtyUpdated) {

                    boolean isAssociatedUpdate = SupplierOrderDetailModel.saveSupplierOrderDetail(supplierOrderDto.getSupplier_Order_Id(), supplierOrderDto.getItems());

                    if (isAssociatedUpdate) {
                        connection.commit();
                        result = true;
                    }
                }
            }
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }

        return result;
    }

}
