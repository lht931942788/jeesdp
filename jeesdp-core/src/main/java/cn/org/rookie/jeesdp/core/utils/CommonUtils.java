package cn.org.rookie.jeesdp.core.utils;

import cn.org.rookie.jeesdp.core.entity.Tree;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommonUtils {

    public static List<Tree> toTree(List<Tree> data, String rootId) {
        List<Tree> treeList = new ArrayList<>();
        for (int size = data.size() - 1; size > -1; size--) {
            Tree item = data.get(size);
            if (rootId.equals(item.getParent())) {
                data.remove(size);
                treeList.add(item);
            }
        }
        for (Tree tree : treeList) {
            tree.setChildren(toTree(data, tree.getId()));
        }
        return treeList.size() > 0 ? treeList : null;
    }

    public static <T> List<T> toTree(List<T> data, String rootId, String idName, String parentName, String childName, Class<T> type) {
        List<T> result = new ArrayList<>();
        Field idField = ReflectionUtils.findField(type, idName);
        Field parentField = ReflectionUtils.findField(type, parentName);
        Field childField = ReflectionUtils.findField(type, childName);
        Objects.requireNonNull(idField).setAccessible(true);
        Objects.requireNonNull(parentField).setAccessible(true);
        Objects.requireNonNull(childField).setAccessible(true);

        for (int size = data.size() - 1; size > -1; size--) {
            T item = data.get(size);
            if (rootId.equals(ReflectionUtils.getField(Objects.requireNonNull(parentField), item))) {
                data.remove(size);
                result.add(item);
            }
        }
        for (T item : result) {
            ReflectionUtils.setField(Objects.requireNonNull(childField), item,
                    toTree(data, Objects.requireNonNull(ReflectionUtils.getField(Objects.requireNonNull(idField), item)).toString(), idName, parentName, childName, type));
        }
        return result.size() > 0 ? result : null;
    }


    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.setId("0");
        tree.setLabel("0");
        tree.setParent("99");
        tree.setValue("0");

        Tree tree2 = new Tree();
        tree2.setId("1");
        tree2.setLabel("1");
        tree2.setParent("0");
        tree2.setValue("1");

        Tree tree3 = new Tree();
        tree3.setId("2");
        tree3.setLabel("2");
        tree3.setParent("0");
        tree3.setValue("2");

        Tree tree4 = new Tree();
        tree4.setId("3");
        tree4.setLabel("3");
        tree4.setParent("1");
        tree4.setValue("3");

        List<Tree> treeList;
        treeList = new ArrayList<>();
        treeList.add(tree);
        treeList.add(tree2);
        treeList.add(tree3);
        treeList.add(tree4);
        System.out.println(toTree(treeList, "99", "id", "parent", "children", Tree.class));
    }
}
