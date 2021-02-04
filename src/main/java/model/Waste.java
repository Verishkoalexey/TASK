package model;

public class Waste {
    private int codeWaste;
    private String nameWaste;
    private String degreeOfDanger;
    private String hazardClass;
    private String kindOfActivity;
    private String educationStandard;

    public Waste(int codeWaste, String nameWaste, String degreeOfDanger, String hazardClass, String kindOfActivity, String educationStandard) {
        this.codeWaste = codeWaste;
        this.nameWaste = nameWaste;
        this.degreeOfDanger = degreeOfDanger;
        this.hazardClass = hazardClass;
        this.kindOfActivity = kindOfActivity;
        this.educationStandard = educationStandard;
    }

    public Waste() {

    }

    public Integer getCodeWaste() {
        return codeWaste;
    }

    public void setCodeWaste(int codeWaste) {
        this.codeWaste = codeWaste;
    }

    public String getNameWaste() {
        return nameWaste;
    }

    public void setNameWaste(String nameWaste) {
        this.nameWaste = nameWaste;
    }

    public String getDegreeOfDanger() {
        return degreeOfDanger;
    }

    public void setDegreeOfDanger(String degreeOfDanger) {
        this.degreeOfDanger = degreeOfDanger;
    }

    public String getHazardClass() {
        return hazardClass;
    }

    public void setHazardClass(String hazardClass) {
        this.hazardClass = hazardClass;
    }

    public String getKindOfActivity() {
        return kindOfActivity;
    }

    public void setKindOfActivity(String kindOfActivity) {
        this.kindOfActivity = kindOfActivity;
    }

    public String getEducationStandard() {
        return educationStandard;
    }

    public void setEducationStandard(String educationStandard) {
        this.educationStandard = educationStandard;
    }

    @Override
    public String toString() {
        return "Waste{" +
                "codeWaste=" + codeWaste +
                ", nameWaste='" + nameWaste + '\'' +
                ", degreeOfDanger='" + degreeOfDanger + '\'' +
                ", hazardClass='" + hazardClass + '\'' +
                ", kindOfActivity='" + kindOfActivity + '\'' +
                ", educationStandard='" + educationStandard + '\'' +
                '}';
    }
}
