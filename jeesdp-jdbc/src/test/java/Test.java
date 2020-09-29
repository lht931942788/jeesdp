import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.HashMap;

public class Test {
    static class Demo {
        String id;
        String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        double[] dd = {3.97, 0.54, -1.13, -0.77, 2.08, -1.91, -1.66, -2.77, -1.99, 1.56};
        double sum = 10000;
        double tmp = 0;
        for (int i = 0; i < 10; i++) {
            tmp += dd[i] / 110;
            sum = sum + sum * dd[i] / 100;
            System.out.println(sum);
        }
        System.out.println(tmp);
        System.out.println(sum);
        System.out.println((sum + sum * tmp));
    }

}
