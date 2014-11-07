package globalindustryinc.fittrackr;

/**
 * Created by jccline on 10/5/2014.
 */
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

import java.util.LinkedList;

import static com.jjoe64.graphview.GraphView.GraphViewData;

public class MeasureGraphFragment extends Fragment {
    MySQLiteHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // first init data
        db = new MySQLiteHelper(getActivity());
        LinkedList<Exercise> exercises;
        int f = 0;
        int start = 0;
        int end = 1;
        int max =0;
        int min =100;
        GraphView graphView = new LineGraphView(
                container.getContext()
                , "Measurement Graph"
        );
        GraphViewData[] data;

        exercises = Database.retrieveExercises(getActivity(), Exercise.EXERCISE_TYPE.MEASURE);
        int size = exercises.size();
        String[] names = new String[size];
        for(Exercise exercise : exercises) {
            names[f] = exercise.name;
            f++;
        }
        for (f =0; f<size; f++) {
            int[] measure = db.getAllMeasure(names[f]);
            Log.d("name", names[f]);
            for (int i = 0; i < measure.length; i++) {
                if (max < measure[i]){
                    max = measure[i];
                }
                if (min > measure[i]){
                    min = measure[i];
                }
            }
            if(start < (measure.length-10)){
                start = measure.length-10;
            }  else {
                if(end<measure.length-1) {
                    end = measure.length - 1;
                }
            }

            data = new GraphViewData[measure.length];

            for (int i = 0; i < measure.length; i++) {
                data[i] = new GraphViewData(i, measure[i]);
            }
            GraphViewSeries lifting = new GraphViewSeries(names[f], new GraphViewSeries.GraphViewSeriesStyle(Color.rgb((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)), size), data);
            graphView.addSeries(lifting);
        }

// optional - set exercise port, start=2, size=10

        String[] labelX = new String[start+10];
        String[] labelY = new String[max/10];
        for (int i =0; i<start+10; i ++){
            labelX[i] = String.valueOf(i);
        }

        for (int i=0; i<(max/10); i++){
            labelY[((max/10)-1)-i] = String.valueOf(10*i);
        }
        if(start > 0){
            end = 10;
        }
        graphView.setViewPort(start, end);
        graphView.setScalable(true);
// optional - legend
        graphView.setShowLegend(true);
        graphView.setLegendAlign(GraphView.LegendAlign.BOTTOM);
        graphView.setManualYAxisBounds(max,0);
        graphView.setLegendWidth(250);
        return graphView;
    }
}
