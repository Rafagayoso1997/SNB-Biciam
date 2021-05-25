package utils;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableStatisticsData {

    private SimpleStringProperty calendarId;
    private SimpleStringProperty lessTeam;
    private SimpleStringProperty moreTeam;
    private SimpleDoubleProperty calendarDistance;
    private SimpleDoubleProperty lessTeamDistance;
    private SimpleDoubleProperty moreTeamDistance;


    public TableStatisticsData(String calendarId, String lessTeam, String moreTeam, double calendarDistance,
                               double lessTeamDistance, double moreTeamDistance){
        this.calendarId = new SimpleStringProperty(calendarId);
        this.lessTeam = new SimpleStringProperty(lessTeam);
        this.moreTeam = new SimpleStringProperty(moreTeam);
        this.calendarDistance = new SimpleDoubleProperty(calendarDistance);
        this.lessTeamDistance = new SimpleDoubleProperty(lessTeamDistance);
        this.moreTeamDistance = new SimpleDoubleProperty(moreTeamDistance);
    }


    public String getCalendarId() {
        return calendarId.get();
    }

    public SimpleStringProperty calendarIdProperty() {
        return calendarId;
    }

    public void setCalendarId(String calendarId) {
        this.calendarId.set(calendarId);
    }

    public String getLessTeam() {
        return lessTeam.get();
    }

    public SimpleStringProperty lessTeamProperty() {
        return lessTeam;
    }

    public void setLessTeam(String lessTeam) {
        this.lessTeam.set(lessTeam);
    }

    public String getMoreTeam() {
        return moreTeam.get();
    }

    public SimpleStringProperty moreTeamProperty() {
        return moreTeam;
    }

    public void setMoreTeam(String moreTeam) {
        this.moreTeam.set(moreTeam);
    }

    public double getCalendarDistance() {
        return calendarDistance.get();
    }

    public SimpleDoubleProperty calendarDistanceProperty() {
        return calendarDistance;
    }

    public void setCalendarDistance(double calendarDistance) {
        this.calendarDistance.set(calendarDistance);
    }

    public double getLessTeamDistance() {
        return lessTeamDistance.get();
    }

    public SimpleDoubleProperty lessTeamDistanceProperty() {
        return lessTeamDistance;
    }

    public void setLessTeamDistance(double lessTeamDistance) {
        this.lessTeamDistance.set(lessTeamDistance);
    }

    public double getMoreTeamDistance() {
        return moreTeamDistance.get();
    }

    public SimpleDoubleProperty moreTeamDistanceProperty() {
        return moreTeamDistance;
    }

    public void setMoreTeamDistance(double moreTeamDistance) {
        this.moreTeamDistance.set(moreTeamDistance);
    }
}
