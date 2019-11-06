package java8.base64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

/**
 * @see java.util.Base64
 */
public class Base64Test
{
    public static void main(String args[])
    {
        try
        {
            // 基本编码
            String base64encodedString = Base64.getEncoder().encodeToString("cai&java8".getBytes("utf-8"));
            System.out.println("Base64编码字符串(基本):" + base64encodedString);
            byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
            System.out.println("原始字符串:" + new String(base64decodedBytes, "utf-8"));

            // URL
            base64encodedString = Base64.getUrlEncoder().encodeToString("cai&&java8".getBytes("utf-8"));
            System.out.println("Base64编码字符串(URL):" + base64encodedString);
            base64decodedBytes = Base64.getUrlDecoder().decode(base64encodedString);
            System.out.println("原始字符串:" + new String(base64decodedBytes, "utf-8"));

            // MIME
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 10; ++i)
            {
                stringBuilder.append(UUID.randomUUID().toString());
            }
            byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
            String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
            System.out.println("Base64编码字符串(MIME):" + mimeEncodedString);
            base64decodedBytes = Base64.getMimeDecoder().decode(mimeEncodedString);
            System.out.println("原始字符串:" + new String(base64decodedBytes, "utf-8"));
        }
        catch(UnsupportedEncodingException e)
        {
            System.out.println("error" + e.getMessage());
        }
    }
}
