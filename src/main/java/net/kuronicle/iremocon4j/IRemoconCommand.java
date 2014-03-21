package net.kuronicle.iremocon4j;

public enum IRemoconCommand {

    CHECK_CONNECTION("au"),
    SEND_IR("is"),
    START_LEARNING_IR("ic"),
    STOP_LEARNING_IR("cc"),
    SET_TIMER("tm"),
    GET_TIMER_LIST("tl"),
    DELETE_TIMER("td"),
    SET_TIME("tg"),
    GET_TIME("tg"),
    GET_VERSION("vr");
    
    private String command;
    
    private IRemoconCommand(String command) {
        this.command = command;
    }
    
    public String getCommand() {
        return command;
    }
}