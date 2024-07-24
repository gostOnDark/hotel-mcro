package com.company.hotel.admin.entity;

import java.util.Objects;

public class room {
    private String id;

    private String roomno;

    private Boolean aircondition;

    private String bed;

    private Boolean status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno == null ? null : roomno.trim();
    }

    public Boolean getAircondition() {
        return aircondition;
    }

    public void setAircondition(Boolean aircondition) {
        this.aircondition = aircondition;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed == null ? null : bed.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        room room = (room) o;
        return Objects.equals(id, room.id) && Objects.equals(roomno, room.roomno) && Objects.equals(aircondition, room.aircondition) && Objects.equals(bed, room.bed) && Objects.equals(status, room.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomno, aircondition, bed, status);
    }
}