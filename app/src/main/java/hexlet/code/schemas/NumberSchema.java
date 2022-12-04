package hexlet.code.schemas;

public class NumberSchema {

    private boolean availableChecking = false;
    private boolean validation = false;
    private boolean isNumberPositive;
    private Integer currentNumber;
    private Integer rangeMinimum = Integer.MIN_VALUE;
    private Integer rangeMaximum = Integer.MAX_VALUE;

    public NumberSchema required() {
        this.availableChecking = true;
        return this;
    }

    public NumberSchema positive() {
        this.isNumberPositive = true;
        return this;
    }

    public NumberSchema range(Integer minimum, Integer maximum) {
        this.rangeMinimum = minimum;
        this.rangeMaximum = maximum;
        return this;
    }

    public boolean getPositive(Integer number) {
        return number > 0;
    }

    public boolean getInRange(Integer min, Integer max, Integer number) {
        return (number >= min) & (number <= max) ? true : false;

    }

    public boolean isValid(Object obj) {
        BaseSchema schema = new BaseSchema();
        if (!this.availableChecking) {
            validation = true;
        } else if (obj instanceof Integer) {
            currentNumber = (Integer) obj;
            validation = getPositive(currentNumber) & getInRange(this.rangeMinimum, this.rangeMaximum, currentNumber);
        } else {
            validation = false;
        }
        return validation;
    }
}
