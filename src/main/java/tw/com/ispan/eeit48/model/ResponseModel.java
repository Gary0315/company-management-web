package tw.com.ispan.eeit48.model;

public class ResponseModel {

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

    public ResponseModel(String type,String message){
        setType(type);
        setMessage(message);
    }
}
