import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class Javatest {
    String str;
    String str2 = "hello world";
    String str3;
    Javatest(){}
    Javatest(Object... args){
        this.str3 = "HI Sam!";
        System.out.println(this.str);
        System.out.println(this.str2);
        System.out.println(this.str3);
        for (Object o:args){
            System.out.println(o.toString());
        }
    }

    public static void main(String[] args) {
        Javatest test = new Javatest("你好",455,"demaxiya");
//        System.out.println(test.str);
//        System.out.println(test.str2);
//        System.out.println(test.str3);
        try {
            // 创建一个密钥生成器
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128); // 初始化密钥长度

            // 生成密钥
            SecretKey key = keyGenerator.generateKey();

            // 将密钥转换为Base64编码的字符串
            String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());

            // 输出Base64编码的密钥
            System.out.println("CipherKey in Base64: " + base64Key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
