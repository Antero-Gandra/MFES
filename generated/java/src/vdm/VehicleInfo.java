package vdm;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class VehicleInfo {
  private Number Seats = 1L;
  private Number Doors = 3L;
  private String Model = SeqUtil.toStr(SeqUtil.seq());
  private Number Year = 1950L;
  private String FuelType = SeqUtil.toStr(SeqUtil.seq());
  private String Type = SeqUtil.toStr(SeqUtil.seq());
  private String Brand = SeqUtil.toStr(SeqUtil.seq());
  private String Color = SeqUtil.toStr(SeqUtil.seq());

  public void cg_init_VehicleInfo_1(
      final Number seats,
      final Number doors,
      final String model,
      final Number year,
      final String fuel,
      final String type,
      final String brand,
      final String color) {

    Seats = seats;
    Doors = doors;
    Model = model;
    Year = year;
    FuelType = fuel;
    Type = type;
    Brand = brand;
    Color = color;
    return;
  }

  public VehicleInfo(
      final Number seats,
      final Number doors,
      final String model,
      final Number year,
      final String fuel,
      final String type,
      final String brand,
      final String color) {

    cg_init_VehicleInfo_1(seats, doors, model, year, fuel, type, brand, color);
  }

  public Number getSeats() {
    return Seats;
  }

  public Number getDoors() {
    return Doors;
  }

  public String getModel() {
    return Model;
  }

  public Number getYear() {
    return Year;
  }

  public String getFuelType() {
    return FuelType;
  }

  public String getType() {
    return Type;
  }

  public String getBrand() {
    return Brand;
  }

  public String getColor() {
    return Color;
  }

  public VehicleInfo() {}

  public String toString() {

    return "VehicleInfo{"
        + "Seats := "
        + Utils.toString(Seats)
        + ", Doors := "
        + Utils.toString(Doors)
        + ", Model := "
        + Utils.toString(Model)
        + ", Year := "
        + Utils.toString(Year)
        + ", FuelType := "
        + Utils.toString(FuelType)
        + ", Type := "
        + Utils.toString(Type)
        + ", Brand := "
        + Utils.toString(Brand)
        + ", Color := "
        + Utils.toString(Color)
        + "}";
  }
}
