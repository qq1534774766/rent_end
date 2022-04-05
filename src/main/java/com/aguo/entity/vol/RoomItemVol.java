package com.aguo.entity.vol;

import com.aguo.entity.ArchiBBuilding;
import com.aguo.entity.ArchiRRoom;
import com.aguo.entity.Renting;

public class RoomItemVol {
    private ArchiBBuilding archiBBuilding;
    private ArchiRRoom archiRRoom;
    private Renting renting;

    public RoomItemVol() {
    }

    public ArchiBBuilding getArchiBBuilding() {
        return archiBBuilding;
    }

    public void setArchiBBuilding(ArchiBBuilding archiBBuilding) {
        this.archiBBuilding = archiBBuilding;
    }

    public ArchiRRoom getArchiRRoom() {
        return archiRRoom;
    }

    public void setArchiRRoom(ArchiRRoom archiRRoom) {
        this.archiRRoom = archiRRoom;
    }

    public Renting getRenting() {
        return renting;
    }

    public void setRenting(Renting renting) {
        this.renting = renting;
    }

    @Override
    public String toString() {
        return "RoomItemVol{" +
                "archiBBuilding=" + archiBBuilding +
                ", archiRRoom=" + archiRRoom +
                ", renting=" + renting +
                '}';
    }
}
