import javax.swing.*;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Thymeleaf {

    private static Thymeleaf instance;

    private MainFrame mainFrame;

    // 생성자 private
    private Thymeleaf() {}

    // 싱글턴 접근 메서드
    public static Thymeleaf getInstance() {
        if (instance == null) {
            instance = new Thymeleaf();
        }
        return instance;
    }

    // 메인프레임 세터
    public void setMainFrame(MainFrame frame) {
        this.mainFrame = frame;
    }

    public enum ViewName {
        FORM,
        HOME,
        LIST
    }

    public void showView(ViewName viewName, Map<String, Object> box) {
        JPanel view = null;

        switch (viewName) {
            case FORM:
                view = new FormPanel(box);
                break;
            // case HOME:
            //     view = new HomePanel();
            //     break;
            default:
                System.out.println("Unknown View : " + viewName);
                // view = new JLabel("❗ 알 수 없는 화면");
        }

        if (mainFrame != null) {
            mainFrame.setView(view);
        } else {
            System.out.println("mainFrame이 설정되지 않았습니다!");
        }
    }

    public static class Model {
        public static Map<String, Object> box = new HashMap<>();
    }

    // converterMap, convert(), bind()는 그대로 유지
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
}
