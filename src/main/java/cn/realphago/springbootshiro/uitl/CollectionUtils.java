package cn.realphago.springbootshiro.uitl;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/17 21:33
 */
public class CollectionUtils {

    public static void showMap(Map map) {
        if (map == null) {
            System.out.println("map = " + map);
            return;
        }
        Set keySet = map.keySet();
        for (Object key : keySet) {
            System.out.println(key.toString() + map.get(key).toString());
        }
    }

    public static void showMap(Map<String, String[]> map, int a) {
        if (map == null) {
            System.out.println("map = " + map);
            return;
        }
        Set keySet = map.keySet();
        for (Object key : keySet) {
            System.out.println(key.toString() + map.get(key)[0]);
        }
    }

    public static void showList(List<Object> elementList) {
        if (elementList == null) {
            System.out.println("elementList = " + elementList);
            return;
        }
        for (int i = 0; i < elementList.size(); i++) {
            System.out.println("element" + i + ": " + elementList.get(i));
        }
    }

    public static void showArray(Object[] elements) {
        if (elements == null) {
            System.out.println("elements = " + elements);
            return;
        }
        for (int i = 0; i < elements.length; i++) {
            System.out.println("element" + i + ": " + elements[i]);
        }
    }

}
