package hexlet.code.schemas;

public interface Schema {
    boolean isValid(Object obj);
    void required();
    boolean contains(String checkedString);
    boolean minLength(Integer len);
}
