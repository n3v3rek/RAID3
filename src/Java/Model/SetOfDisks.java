package Java.Model;

public class SetOfDisks {

    private String DiscOne;
    private String DiscTwo;
    private String ControlDisc;

    public SetOfDisks(String discOne, String discTwo, String controlDisc) {
        this.DiscOne = discOne;
        this.DiscTwo = discTwo;
        this.ControlDisc = controlDisc;
    }

    public String getDiscOne() {
        return this.DiscOne;
    }

    public void setDiscOne(String discOne) {
        this.DiscOne = discOne;
    }

    public String getDiscTwo() {
        return this.DiscTwo;
    }

    public void setDiscTwo(String discTwo) {
        this.DiscTwo = discTwo;
    }

    public String getControlDisc() {
        return this.ControlDisc;
    }

    public void setControlDisc(String controlDisc) {
        this.ControlDisc = controlDisc;
    }
}
