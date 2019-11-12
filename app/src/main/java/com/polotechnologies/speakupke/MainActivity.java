package com.polotechnologies.speakupke;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.gridlayout.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout mainGrid = findViewById(R.id.main_grid);
        int childCount = mainGrid.getChildCount();

        gridClickEvent(mainGrid);
    }

    private void gridClickEvent(GridLayout mainGrid) {
        for (int i=0; i<mainGrid.getChildCount(); i++){
            CardView mCardView  = (CardView) mainGrid.getChildAt(i);
            final int index = i;
            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(index){
                        case 0:
                            Intent openReportActivityRape = new Intent(MainActivity.this, ReportingActivity.class);
                            openReportActivityRape.putExtra("crime","Rape");
                            startActivity(openReportActivityRape);
                        break;

                        case 1:
                            Intent openReportActivityChildAbuse = new Intent(MainActivity.this, ReportingActivity.class);
                            openReportActivityChildAbuse.putExtra("crime","Child Abuse");
                            startActivity(openReportActivityChildAbuse);
                            break;

                        case 2:
                            Intent openReportActivitySexualHarassment = new Intent(MainActivity.this, ReportingActivity.class);
                            openReportActivitySexualHarassment.putExtra("crime","Sexual Harassment");
                            startActivity(openReportActivitySexualHarassment);
                            break;

                        case 3:
                            Intent openReportActivitySexualDisabled = new Intent(MainActivity.this, ReportingActivity.class);
                            openReportActivitySexualDisabled.putExtra("crime","Disabled Person Abuse");
                            startActivity(openReportActivitySexualDisabled);
                            break;

                        case 4:
                            Intent openReportActivityElderlyAbuse = new Intent(MainActivity.this, ReportingActivity.class);
                            openReportActivityElderlyAbuse.putExtra("crime","Elderly Abuse");
                            startActivity(openReportActivityElderlyAbuse);
                            break;

                        case 5:
                            Intent openReportActivityDrugAbuse = new Intent(MainActivity.this, ReportingActivity.class);
                            openReportActivityDrugAbuse.putExtra("crime","Drug Abuse");
                            startActivity(openReportActivityDrugAbuse);
                            break;
                    }
                }
            });

        }
    }
}
