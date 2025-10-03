import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TableOfContents {
    public static class Entry {
        public final String title;
        public final Integer page;

        public Entry(String title, Integer page) {
            this.title = title;
            this.page = page;
        }
    }

    protected final List<Entry> entries = new ArrayList<>();

    public void addEntry(String title) {
        addEntry(title, null);
    }

    public void addEntry(String title, Integer page) {
        entries.add(new Entry(title, page));
    }

    // Print simplificat și moștenit de toate clasele
    public void print() {
        String kind = getClass().getSimpleName();
        String label = firstNonEmptyField(this, "name", "title", "text", "imageName");
        if (label.isEmpty()) {
            System.out.println(kind);
        } else {
            System.out.println(kind + ": " + label);
        }
    }

    private static String firstNonEmptyField(Object target, String... fieldNames) {
        for (String f : fieldNames) {
            Field field = findField(target.getClass(), f);
            if (field != null) {
                try {
                    field.setAccessible(true);
                    Object v = field.get(target);
                    if (v != null) {
                        String s = String.valueOf(v);
                        if (!s.isEmpty()) return s;
                    }
                } catch (IllegalAccessException ignored) {
                }
            }
        }
        return "";
    }

    private static Field findField(Class<?> cls, String name) {
        Class<?> c = cls;
        while (c != null) {
            try {
                return c.getDeclaredField(name);
            } catch (NoSuchFieldException e) {
                c = c.getSuperclass();
            }
        }
        return null;
    }
}

