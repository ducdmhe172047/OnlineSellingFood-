package model;

public class Unit {
    private Integer unitID;
    private String name;
    private Integer baseUnitID;
    private Integer conversionRate;

    public Unit() {
    }

    public Unit(Integer unitID, String name, Integer baseUnitID, Integer conversionRate) {
        this.unitID = unitID;
        this.name = name;
        this.baseUnitID = baseUnitID;
        this.conversionRate = conversionRate;
    }

    public Integer getUnitID() {
        return unitID;
    }

    public void setUnitID(Integer unitID) {
        this.unitID = unitID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBaseUnitID() {
        return baseUnitID;
    }

    public void setBaseUnitID(Integer baseUnitID) {
        this.baseUnitID = baseUnitID;
    }

    public Integer getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Integer conversionRate) {
        this.conversionRate = conversionRate;
    }
}
