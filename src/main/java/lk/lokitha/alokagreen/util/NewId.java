package lk.lokitha.alokagreen.util;

import lk.lokitha.alokagreen.model.*;

public class NewId {

    public static String newEmployeeId() {

        int lastId = EmployeeModel.getLastId();

        if (lastId != -1) {
            lastId++;
            return "E-0" + lastId;
        } else {
            return "E-01";
        }
    }

    public static String newCustomerId() {
        int lastId = CustomerModel.getLastId();

        if (lastId != -1) {
            lastId++;
            return "C-0" + lastId;
        } else {
            return "C-01";
        }
    }

    public static String newSupplierId() {
        int lastId = SupplierModel.getLastId();

        if (lastId != -1) {
            lastId++;
            return "S-0" + lastId;
        } else {
            return "S-01";
        }
    }

    public static String newMaterialCode() {
        int lastId = MaterialModel.getLastId();

        if (lastId != -1) {
            lastId++;
            return "M-0" + lastId;
        } else {
            return "M-01";
        }
    }

    public static String newProductCode() {
        int lastId = ProductModel.getLastId();

        if (lastId != -1) {
            lastId++;
            return "P-0" + lastId;
        } else {
            return "P-01";
        }
    }

    public static String newProductStockCode() {
        int lastId = ProductStockModel.getLastId();

        if (lastId != -1) {
            lastId++;
            return "PS-0" + lastId;
        } else {
            return "PS-01";
        }
    }

    public static String newMaterialStockCode() {
        int lastId = MaterialStockModel.getLastId();

        if (lastId != -1) {
            lastId++;
            return "MS-0" + lastId;
        } else {
            return "MS-01";
        }
    }

    public static String newCustomerOrderId() {
        int lastId = CustomerOrderModel.getLastId();

        if (lastId != -1) {
            lastId++;
            return "CO-0" + lastId;
        } else {
            return "CO-01";
        }
    }

    public static String newSupplierOrderId() {
        int lastId = SupplierOrderModel.getLastId();

        if (lastId != -1) {
            lastId++;
            return "SO-0" + lastId;
        } else {
            return "SO-01";
        }
    }

    public static String newAttendanceId() {
        int lastId = EmployeeAttendanceModel.getLastId();

        if (lastId != -1) {
            lastId++;
            return "EA-0" + lastId;
        } else {
            return "EA-01";
        }
    }

    public static String newSalaryId() {
        int lastId = EmployeeSalaryModel.getLastId();

        if (lastId != -1) {
            lastId++;
            return "ES-0" + lastId;
        } else {
            return "ES-01";
        }
    }

    public static String newSpoiledReportId() {
        int lastId = SpoiledReportModel.getLastId();

        if (lastId != -1) {
            lastId++;
            return "SR-0" + lastId;
        } else {
            return "SR-01";
        }
    }

}