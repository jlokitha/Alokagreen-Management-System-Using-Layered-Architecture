package lk.ijse.alokagreen.util;

import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SendSMS {
    public static void main(String[] args) {
        try {
            // Construct data
            String apiKey = "apikey=" + URLEncoder.encode("MzI3NjY2Njc3YTc3NTk0NzQ0NmUzMDUyNTk0MTUwMzU=", "UTF-8");
            String message = "&message=" + URLEncoder.encode("111111", "UTF-8");
            String sender = "&sender=" + URLEncoder.encode("Alokagreen", "UTF-8");
            String numbers = "&numbers=" + URLEncoder.encode("0711018201", "UTF-8");

            // Send data
            String data = "https://api.txtlocal.com/send/?" + apiKey + numbers + message + sender;
            URL url = new URL(data);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            String sResult="";
            while ((line = rd.readLine()) != null) {
                // Process line...
                sResult=sResult+line+" ";
            }
            rd.close();
            new Alert(Alert.AlertType.CONFIRMATION, "OTP Sends Successfully !").show();
            //return sResult;
        } catch (Exception e) {
            System.out.println("Error SMS "+e);
            //return "Error "+e;
        }
    }
}
