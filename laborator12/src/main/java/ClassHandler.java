import java.lang.reflect.Method;
import java.util.Arrays;

public class ClassHandler {
    private Class className;

    ClassHandler(Class className) {
        setClassName(className);
    }

    public void printMethods() {
        Method[] classMethods = className.getMethods();
        for (Method method : classMethods) {
            System.out.println(method.getGenericReturnType() + " " + method.getName() + "()");
        }
    }

    public Class getClassName() {
        return className;
    }

    public void setClassName(Class className) {
        this.className = className;
    }
}
