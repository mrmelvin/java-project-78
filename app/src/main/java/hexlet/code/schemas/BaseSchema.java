package hexlet.code.schemas;

public class BaseSchema {
    private boolean availableChecking = true;
    private boolean validation = false;
    //private String currentString;
    private String subString = "";
    private Integer minimumStringLength;

    public void required() {
        this.availableChecking = true;
    }

    public boolean getRequired() {
        return this.availableChecking;
    }

    public void contains(String str) {
        this.subString = str;
    }

//    public boolean getContains() {
//        return this.subString;
//    }

    public void minLength(Integer number) {
        this.minimumStringLength = number;
    }

    public Integer getMinLength() {
        return this.minimumStringLength;
    }

}
