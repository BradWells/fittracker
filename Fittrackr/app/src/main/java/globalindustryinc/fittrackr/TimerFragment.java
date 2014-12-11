package globalindustryinc.fittrackr;


import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Brad on 12/10/2014.
 */
public class TimerFragment extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        startTimer();
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    public void startTimer(){
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER);
        startActivity(intent);
    }
}
