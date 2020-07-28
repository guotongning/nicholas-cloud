package com.ning.springcloud.baseutils.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @description
 * @date 2020/7/27 10:50
 * @author 不在能知，乃在能行 ——nicholas
 */
public class ImageUtils {
    public static final String WECHAT_PAYMENT_IMAGE_PATH = "D:\\project\\test\\nicholas-cloud\\cloud-provider-payment\\src\\main\\resources\\image\\payment_nicholas_2020年7月27日.png";

    public static String getImgStr() {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        String base64 = null;
        try {
            in = new FileInputStream(WECHAT_PAYMENT_IMAGE_PATH);
            data = new byte[in.available()];
            in.read(data);
            in.close();
            String prefix = "data:image/png;base64,";
            byte[] bytes = Base64.encodeBase64(data);
            base64 = new StringBuilder().append(prefix).append(new String(bytes)).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64;
    }
}
