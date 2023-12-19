package lk.lokitha.alokagreen.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GenerateQrCode {

    public static void generateQr(String id) throws IOException, WriterException {
        String input = id;
        String path = "/home/lokitha/Pictures/QR/";
        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hasMap = new HashMap<>();
        hasMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        generateQrCode(input, path + input + ".png", charset, hasMap, 400, 400);
    }

    public static void generateQrCode(String data, String path, String charset, Map map, int h, int w) throws IOException, WriterException {
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);
        MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
    }
}
