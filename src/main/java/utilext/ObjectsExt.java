package utilext;

public final class ObjectsExt {

    private ObjectsExt() {
        throw new AssertionError("No java.util.Objects instances for you!");
    }

    public static void requireNotEquals(Object objA, Object objB) {
        if (objA == objB)
            try {
                throw new Exception("Objects shall not be duplicate references in this context");
            } catch (Exception e){
                e.printStackTrace();
            }
    }
}
