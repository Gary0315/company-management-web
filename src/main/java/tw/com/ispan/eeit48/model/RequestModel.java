package tw.com.ispan.eeit48.model;

public class RequestModel {
	
    private String type;
    private String message="";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RequestModel(String type){
        setType(type);
    }

    public RequestModel(String type,String message){
        setType(type);
        setMessage(message);
    }
}
