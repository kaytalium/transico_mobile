package com.transico.codezero.transico.ReportLog;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.transico.codezero.transico.FirestoreConnection.ReportLogger;
import com.transico.codezero.transico.R;
import com.transico.codezero.transico.SystemHelper.Helper;
import com.transico.codezero.transico.SystemHelper.TimeKeeper;
import com.transico.codezero.transico.SystemHelper.databaseCommand;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReportLogAdapter extends FirestoreRecyclerAdapter<ReportLogger,ReportLogAdapter.ReportLogHolder> {

    //vaiables
    Context mContext;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ReportLogAdapter(@NonNull FirestoreRecyclerOptions<ReportLogger> options, Context context) {
        super(options);
        mContext = context;


    }

    @Override
    protected void onBindViewHolder(@NonNull ReportLogHolder reportLogHolder, int i, @NonNull ReportLogger reportLogger) {
        reportLogHolder.bind(reportLogger, mContext);
    }

    @NonNull
    @Override
    public ReportLogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_log_recycler_main_component, parent, false);
        return new ReportLogHolder(v);
    }

    public class ReportLogHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView reportTitle;
        TextView reportGroup;
        TextView reportDate;
        TextView reportDetail;
        TextView reportBusModel;
        TextView reportStatus;


        //latest Responds Variables
        TextView latestName;
        TextView latestPostTime;
        TextView latestDetail;
        TextView latestViewMore;
        TextView addComment;

        //Variables
        private boolean isExpand = false;

        //testing objects
        private TextView wordCount;
        private TextView latestHeader;



        public ReportLogHolder(@NonNull View itemView) {
            super(itemView);

            reportTitle = itemView.findViewById(R.id.tv_rlrmc_title);
            reportTitle.setText("");
            reportGroup = itemView.findViewById(R.id.tv_rlrmc_group);
            reportGroup.setText("");
            reportDate = itemView.findViewById(R.id.tv_rlrmc_date);
            reportDate.setText("");

            reportDetail = itemView.findViewById(R.id.tv_rlrmc_detail);
            reportDetail.setText("");
            reportBusModel = itemView.findViewById(R.id.tv_rlrmc_bus_model);
            reportBusModel.setText("");

            reportStatus = itemView.findViewById(R.id.tv_rlrmc_status_value);
            reportStatus.setText("");


            //Latest responds variables
            latestName = itemView.findViewById(R.id.tv_rlrmc_respond_agent_name);
            latestName.setText("");
            latestPostTime = itemView.findViewById(R.id.tv_rlrmc_respond_time);
            latestPostTime.setText("");
            latestDetail =itemView.findViewById(R.id.tv_rlrmc_respond_detail);
            latestDetail.setText("");
            latestViewMore =itemView.findViewById(R.id.tv_rlrmc_respond_view_more);
            latestViewMore.setText("");

            wordCount = itemView.findViewById(R.id.word_count);
            latestHeader = itemView.findViewById(R.id.latest_header);

            //comment icon
            addComment = itemView.findViewById(R.id.comment_icon);



        }

        public void bind(final ReportLogger model, final Context context){

            reportTitle.setText(model.getTitle());
            reportGroup.setText(model.getWorkGroup());
            reportBusModel.setText(Helper.stringBuilder(databaseCommand.DriverReportLog.busModel,model.getBusModel()));
            reportStatus.setText(model.getReportStatus());


            //Setup date and time here
            String mDate = Helper.DateFormatter(databaseCommand.DateTimeFormat.shortMonthDate,model.getPostTime().toDate());
            String mTime = Helper.DateFormatter(databaseCommand.DateTimeFormat.timeFormat,model.getPostTime().toDate());
            reportDate.setText(Helper.stringBuilder("%s at %s",TimeKeeper.formatDate(model.getPostTime()), mTime));
//            reportTime.setText();


            //Setup Detail here
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Spanned s = Html.fromHtml(model.getDetail(), Html.FROM_HTML_MODE_COMPACT);
                reportDetail.setText(s);
                wordCount.setText(String.valueOf(s.length()));
            } else {
                reportDetail.setText(Html.fromHtml(model.getDetail()));
                wordCount.setText(Html.fromHtml(model.getDetail()).length());
            }

            reportDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Spanned s;

                    ViewGroup.LayoutParams params = v.getLayoutParams();
                    if(isExpand){
                        params.height = dpToPx(150, context);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            s = Html.fromHtml(model.getDetail(), Html.FROM_HTML_MODE_COMPACT);
                            reportDetail.setText(getInitialDisplay(s,350));
                            wordCount.setText(String.valueOf(s.length()));
                        } else {
                            reportDetail.setText(Html.fromHtml(model.getDetail()));
                            wordCount.setText(Html.fromHtml(model.getDetail()).length());
                        }


                    }

                    if(!isExpand){
                        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            s = Html.fromHtml(model.getDetail(), Html.FROM_HTML_MODE_COMPACT);
                            reportDetail.setText(s);

                        } else {
                            reportDetail.setText(Html.fromHtml(model.getDetail()));

                        }
                    }

                    v.setLayoutParams(params);
                    isExpand = !isExpand;
                }
            });

            //Latest respond detail
            if(model.getLatestResponds() !=null) {

                latestName.setText(model.getLatestResponds().getUserName());
                latestDetail.setText(model.getLatestResponds().getRespondText());

                //setup view all responses if any

                if (model.getLatestResponds().getRespondCount() > 1) {
                    latestViewMore.setText(Helper.stringBuilder(databaseCommand.DriverReportLog.viewAll, model.getLatestResponds().getRespondCount()));
                }

                if (model.getLatestResponds().getRespondCount() == 0) {
                    latestViewMore.setText(databaseCommand.DriverReportLog.noResponds);
                }

                if (model.getLatestResponds().getRespondCount() == 1) {
                    latestViewMore.setText(databaseCommand.DriverReportLog.onlyOneResponds);
                }

                //Add on click listener to responses textview
                latestViewMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context,ResponseActivity.class);
                        intent.putExtra("reportID","rid");
                        intent.putExtra("driverID","34264");
                        context.startActivity(intent);
                    }
                });

                //Response time lap counter
                TimeKeeper.timeLaspe(model.getLatestResponds().getPostTime(), latestPostTime);
            }else{
                latestHeader.setVisibility(View.GONE);
                latestName.setVisibility(View.GONE);
                latestDetail.setVisibility(View.GONE);
                latestViewMore.setText(Helper.stringBuilder(databaseCommand.DriverReportLog.noResponds));


            }



            //Add listener to add Comment... textview
            addComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,ResponseActivity.class);
                    intent.putExtra("reportID","rid");
                    intent.putExtra("driverID","34264");
                    context.startActivity(intent);
                }
            });

        }

        /**
         *
         * @param spanned
         * @param i
         * @return
         */
        private String getInitialDisplay(Spanned spanned, int i) {
            if(spanned.length() !=0 && spanned.length() < i){
                return Helper.stringBuilder("%s...",spanned);
            }
            if(spanned.length() > 0 && spanned.length() > i){
                return  Helper.stringBuilder("%s... read more",spanned.subSequence(0,i));
            }
            return Helper.stringBuilder("%s",spanned);
        }

        /**
         *
         * @param v view
         */
        @Override
        public void onClick(View v) {

            Toast.makeText(v.getContext(),"View is click",Toast.LENGTH_LONG).show();

        }

        /**
         *
         * @param dp density pixel value e.g 50
         * @param context view context
         * @return integer in Pixel
         */
        private int dpToPx(int dp, Context context) {
            float density = context.getResources()
                    .getDisplayMetrics()
                    .density;
            return Math.round((float) dp * density);
        }
    }
}
