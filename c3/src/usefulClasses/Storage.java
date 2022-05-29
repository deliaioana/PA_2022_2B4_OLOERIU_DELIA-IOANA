package usefulClasses;

public interface Storage {
    void setStorageCapacity(int storageCapacity);
    int getStorageUsed();
    void setStorageUsed(int storageUsed);
    int getStorageCapacity();
    default long convertCapacityUnit(String unit){
        long value = this.getStorageCapacity();
        switch (unit) {
            case "megabytes" -> value *= 1000;
            case "kilobytes" -> value *= 1000000;
            case "bytes" -> value *= 1073741824;
            default -> throw new IllegalStateException("Unexpected value: " + unit);
        }
        return value;
    }
}
