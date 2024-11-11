package model;

public class Gender {
    private Integer genderID;
    private String gender;

    public Gender() {
    }

    public Gender(Integer genderID, String gender) {
        this.genderID = genderID;
        this.gender = gender;
    }

    public Integer getGenderID() {
        return genderID;
    }

    public void setGenderID(Integer genderID) {
        this.genderID = genderID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
