import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.lang.reflect.Field;

public class Thymeleaf {
    public enum ViewName {
        FORM,
        HOME,
        LIST
    }


    public static void start() {


    }

    public static void showView(JPanel view, Model model) {

    }
    public void showView(ViewName viewName) {
        JPanel view;

        switch (viewName) {
            case FORM:
                view = new FormPanel(Model.box);
                break;
            case HOME:
                view = new HomePanel();
                break;
            case LIST:
                view = new ListPanel();
                break;
            default:
                view = new JLabel("❗ 알 수 없는 화면");
        }

        mainFrame.setView(view);
    }

    public static class Model {
        public static Map<String, Object> box = new HashMap<>();
    }

    public static final Map<Class<?>, Function<String, Object>> converterMap = new HashMap<>();

    static {
        converterMap.put(String.class, s -> s);
        converterMap.put(Integer.class, Integer::valueOf);
        converterMap.put(int.class, Integer::parseInt);
        converterMap.put(Long.class, Long::valueOf);
        converterMap.put(long.class, Long::parseLong);
        converterMap.put(Double.class, Double::valueOf);
        converterMap.put(double.class, Double::parseDouble);
        converterMap.put(Boolean.class, Boolean::valueOf);
        converterMap.put(boolean.class, Boolean::parseBoolean);
        converterMap.put(LocalDate.class, LocalDate::parse); // yyyy-MM-dd
    }



    public static Object convert(String value, Class<?> targetType) {
        Function<String, Object> converter = converterMap.get(targetType);
        if (converter == null) {
            throw new IllegalArgumentException("지원하지 않는 타입: " + targetType.getName());
        }
        return converter.apply(value);
    }

    // 모델 바인딩 기능
    public static <T> T bind(Map<String, String> formData, Class<T> clazz) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                String fieldName = field.getName();
                String value = formData.get(fieldName);
                if (value != null) {
                    field.setAccessible(true);
                    Object converted = convert(value, field.getType());
                    field.set(instance, converted);
                }
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("모델 바인딩 실패", e);
        }
    }

    // 뷰 라우팅, 뷰 생성


    public void showView(MainFrame frame, String viewName) {
        JPanel view;

        switch (viewName) {
            case ViewNames.FORM:
                view = new FormPanel();  // 각 화면은 JPanel을 상속받은 클래스
                break;
            case ViewNames.HOME:
                view = new HomePanel();
                break;
            case ViewNames.LIST:
                view = new ListPanel();
                break;
            default:
                view = new JLabel("알 수 없는 화면: " + viewName);
        }

        mainFrame.setView(view);
    }
}
