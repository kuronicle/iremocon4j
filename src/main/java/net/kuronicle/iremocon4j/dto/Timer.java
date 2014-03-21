package net.kuronicle.iremocon4j.dto;

public class Timer {
    
    private int id;
    private int irId;
    private long timerDateSec;
    private int repeatSec;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIrId() {
        return irId;
    }
    public void setIrId(int irId) {
        this.irId = irId;
    }
    public long getTimerDateSec() {
        return timerDateSec;
    }
    public void setTimerDateSec(long timerDateSec) {
        this.timerDateSec = timerDateSec;
    }
    public int getRepeatSec() {
        return repeatSec;
    }
    public void setRepeatSec(int repeatSec) {
        this.repeatSec = repeatSec;
    }
    
}
