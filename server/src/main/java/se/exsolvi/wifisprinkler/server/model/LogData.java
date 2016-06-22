package se.exsolvi.wifisprinkler.server.model;

public class LogData {
    
    public enum Level {
        NONE,
        ERROR,
        WARN,
        INFO,
        DEBUG,
        TRACE
    }
    
    private Level loglevel;
    private String message;
    
    public Level getLoglevel() {
        return loglevel;
    }
    public void setLoglevel(Level loglevel) {
        this.loglevel = loglevel;
    }
    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
    
    

}
