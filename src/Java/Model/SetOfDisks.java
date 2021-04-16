package Java.Model;

public class SetOfDisks {

    private String DiskOne;
    private String DiskTwo;
    private String ControlDisk;

    public SetOfDisks(String diskOne, String diskTwo, String controlDisk) {
        DiskOne = diskOne;
        DiskTwo = diskTwo;
        ControlDisk = controlDisk;
    }

    public String getDiskOne() {
        return DiskOne;
    }

    public void setDiskOne(String diskOne) {
        DiskOne = diskOne;
    }

    public String getDiskTwo() {
        return DiskTwo;
    }

    public void setDiskTwo(String diskTwo) {
        DiskTwo = diskTwo;
    }

    public String getControlDisk() {
        return ControlDisk;
    }

    public void setControlDisk(String controlDisk) {
        ControlDisk = controlDisk;
    }
}
