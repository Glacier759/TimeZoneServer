
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.apache.log4j.Logger;



/**
 * Created by Glacierlx on 2015/12/17.
 */
public class Test {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Test.class);

        RequestQueue requestQueue = Volley.newRequestQueue(null);

        StringRequest stringRequest = new StringRequest("http://222.24.63.100:9160/TimeZone/account/login?username=04121110&password=glacierlx1994",
                new Response.Listener<String>() {
                    public void onResponse(String response) {
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println(volleyError.getMessage());
            }
        });

        requestQueue.add(stringRequest);

    }


}




