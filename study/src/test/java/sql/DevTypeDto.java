package sql;

import java.util.Objects;

public class DevTypeDto {
    private String devType;
    private double percent;

    public DevTypeDto(String devType, double percent) {
        this.devType = devType;
        this.percent = percent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DevTypeDto that = (DevTypeDto) o;
        return Double.compare(that.percent, percent) == 0 &&
                Objects.equals(devType, that.devType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(devType, percent);
    }
}
