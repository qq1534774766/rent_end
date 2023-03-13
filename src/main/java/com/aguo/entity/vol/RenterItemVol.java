package com.aguo.entity.vol;

import com.aguo.entity.URole;
import com.aguo.entity.UUser;

import java.util.Objects;

public class RenterItemVol {
    private URole uRole;
    private UUserVol uUserVol;
    private Boolean rentState;

    public URole getuRole() {
        return uRole;
    }

    public void setuRole(URole uRole) {
        this.uRole = uRole;
    }

    public UUserVol getuUserVol() {
        return uUserVol;
    }

    public void setuUserVol(UUserVol uUserVol) {
        this.uUserVol = uUserVol;
    }

    public Boolean getRentState() {
        return rentState;
    }

    public void setRentState(Boolean rentState) {
        this.rentState = rentState;
    }

    @Override
    public String toString() {
        return "RenterItemVol{" +
                "uRole=" + uRole +
                ", uUserVol=" + uUserVol +
                ", rentState=" + rentState +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RenterItemVol that = (RenterItemVol) o;
        return Objects.equals(uRole, that.uRole) &&
                Objects.equals(uUserVol, that.uUserVol) &&
                Objects.equals(rentState, that.rentState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uRole, uUserVol, rentState);
    }
}
