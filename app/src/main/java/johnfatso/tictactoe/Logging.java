package johnfatso.tictactoe;

import android.util.Log;

public class Logging {
    private static final boolean LOGGING_ENABLED = true;
    
    static void v(String logTag, String log){
        if(LOGGING_ENABLED){
            Log.v(logTag, log);
        }
    }
}
