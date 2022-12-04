package hexlet.code.schemas;

public class StringSchema {

    //
    private boolean availableChecking = false;
    private boolean validation = false;
    private String currentString;

    private String patternString = "";

    private Integer minimumStringLength = 0;

    public StringSchema() {
    }

    public StringSchema contains(String contStr) {
        this.patternString = contStr;
        return this;
    }

    public StringSchema required() {
        this.availableChecking = true;
        return this;
    }

    public StringSchema minLength(Integer len) {
        this.minimumStringLength = len;
        return this;
    }

    public boolean getContains(String checkingString, String subString) {
        return checkingString.contains(subString);
    }

    public boolean getMinLength(Integer len) {
        return len > this.minimumStringLength;
    }

    public boolean isValid(Object obj) {

        BaseSchema schema = new BaseSchema();
        if (!this.availableChecking) {
            validation = true;
        } else if (obj instanceof String) {
            currentString = obj.toString();
            validation = getContains(currentString, this.patternString) & getMinLength(currentString.length());
        } else {
            validation = false;
        }
        return validation;
    }

}
