package handler;

public class CheckMessage {
    private String message;

    public CheckMessage(String message){
        this.message = message;
    }

    public boolean WelcomeCheck(){
        String[] text = new String[]{"Hi", "Привет"};

        for (String str: text)
            if (message.equalsIgnoreCase(str))
                return true;

        return false;
    }
}
