package Utils.model;

import Utils.ConfigForTests;

public class OtusUser {

    private String nameRu;
    private String nameLatin;
    private String secondnameRu;
    private String secondnameLatin;
    private String blogName;
    private String birthDate;
    private String country;
    private String city;
    private boolean relocate;

    public OtusUser() {
    }


    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameLatin() {
        return nameLatin;
    }

    public void setNameLatin(String nameLatin) {
        this.nameLatin = nameLatin;
    }

    public String getSecondnameRu() {
        return secondnameRu;
    }

    public void setSecondnameRu(String secondnameRu) {
        this.secondnameRu = secondnameRu;
    }

    public String getSecondnameLatin() {
        return secondnameLatin;
    }

    public void setSecondnameLatin(String secondnameLatin) {
        this.secondnameLatin = secondnameLatin;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isRelocate() {
        return relocate;
    }

    public void setRelocate(boolean relocate) {
        this.relocate = relocate;
    }
}
