package com.jdyy.cfunding.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdyy.cfunding.R;
import com.jdyy.cfunding.activity.InvestmentRecordActivity;
import com.jdyy.cfunding.activity.MessageCenterActivity;
import com.jdyy.cfunding.activity.MyCollectionActivity;
import com.jdyy.cfunding.activity.MyGoodsActivity;
import com.jdyy.cfunding.activity.MyProjectActivity;
import com.jdyy.cfunding.activity.MyWalletActivity;
import com.jdyy.cfunding.activity.NoPayOrderActivity;
import com.jdyy.cfunding.activity.ReckoningActivity;
import com.jdyy.cfunding.activity.SettingActivity;
import com.jdyy.cfunding.utils.Bimp;
import com.jdyy.cfunding.utils.FileUtils;
import com.jdyy.cfunding.utils.ImageCompress;
import com.jdyy.cfunding.utils.T;
import com.jdyy.cfunding.view.BottomMenu;
import com.jdyy.cfunding.view.CircleImageView;
import com.jdyy.cfunding.view.WaveHelper;
import com.jdyy.cfunding.view.WaveView;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/23 0023.
 */

public class MeFragment extends BaseFragment implements View.OnClickListener {

    private static final int REQUEST_CODE_PICK_IMAGE = 1;
    private static final int REQUEST_CODE_CAPTURE_CAMEIA = 0;
    private static final int REQUEST_CODE_GETIMAGE_BYSDCARD = 2;
    @Bind(R.id.rl_tou)
    RelativeLayout mRlTou;
    @Bind(R.id.rl_head)
    RelativeLayout mRlHead;
    @Bind(R.id.iv_me_message)
    ImageView mIvMeMessage;
    @Bind(R.id.waveView)
    WaveView mWaveView;
    @Bind(R.id.iv_user_picture)
    CircleImageView mIvUserPicture;
    @Bind(R.id.tv_user_name)
    TextView mTvUserName;
    @Bind(R.id.iv_user_fit)
    ImageView mIvUserFit;
    @Bind(R.id.tv_user_wallet)
    TextView mTvUserWallet;
    @Bind(R.id.rl_user_wallet)
    RelativeLayout mRlUserWallet;
    @Bind(R.id.tv_amount_investment)
    TextView mTvAmountInvestment;
    @Bind(R.id.rl_amount_investment)
    RelativeLayout mRlAmountInvestment;
    @Bind(R.id.tv_user_reckoning)
    TextView mTvUserReckoning;
    @Bind(R.id.rl_user_reckoning)
    RelativeLayout mRlUserReckoning;
    @Bind(R.id.ll_investment_record)
    LinearLayout mLlInvestmentRecord;
    @Bind(R.id.ll_my_project)
    LinearLayout mLlMyProject;
    @Bind(R.id.ll_launch_project)
    LinearLayout mLlLaunchProject;
    @Bind(R.id.ll_investment_statistics)
    LinearLayout mLlInvestmentStatistics;
    @Bind(R.id.ll_financial_statistics)
    LinearLayout mLlFinancialStatistics;
    @Bind(R.id.ll_my_collection)
    LinearLayout mLlMyCollection;
    @Bind(R.id.tv_no_complete)
    TextView mTvNoComplete;

    private WaveHelper mWaveHelper;
    private BottomMenu menuWindow;
    private File tempFile1;
    private String imageName1 = "";
    private Uri uri;  //图片保存uri

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }


    @Override
    protected void initView(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mRlTou.setVisibility(View.VISIBLE);
            mRlTou.setBackgroundResource(R.color.statusColor);
        }
        mRlHead.setBackgroundResource(R.color.lucency);
        mIvMeMessage.setVisibility(View.VISIBLE);
        mWaveHelper = new WaveHelper(mWaveView);
        setlistener();
    }

    @Override
    protected void loadData() {

    }


    private void setlistener() {
        mIvUserFit.setOnClickListener(this);
        mIvMeMessage.setOnClickListener(this);
        mIvUserPicture.setOnClickListener(this);
        mRlUserWallet.setOnClickListener(this);
        mRlAmountInvestment.setOnClickListener(this);
        mRlUserReckoning.setOnClickListener(this);
        mLlInvestmentRecord.setOnClickListener(this);
        mLlLaunchProject.setOnClickListener(this);
        mLlInvestmentStatistics.setOnClickListener(this);
        mLlFinancialStatistics.setOnClickListener(this);
        mLlMyCollection.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_user_fit:
                goToActivity(SettingActivity.class);
                break;
            case R.id.iv_me_message:
                goToActivity(MessageCenterActivity.class);
                break;
            case R.id.iv_user_picture:
                menuWindow = new BottomMenu(getHoldingActivity(), clickListener);
                menuWindow.show();
                break;

            case R.id.rl_user_wallet:
                goToActivity(MyWalletActivity.class);
                break;
            case R.id.rl_amount_investment:

                break;
            case R.id.ll_investment_record:
                goToActivity(NoPayOrderActivity.class);
                break;
            case R.id.ll_my_project:
                goToActivity(InvestmentRecordActivity.class);
                break;
            case R.id.ll_launch_project:
                goToActivity(MyGoodsActivity.class);
                break;
            case R.id.ll_investment_statistics:
                goToActivity(MyProjectActivity.class);
                break;
            case R.id.ll_financial_statistics:
//                发起项目
                break;
            case R.id.ll_my_collection:
                goToActivity(MyCollectionActivity.class);
                break;
            case R.id.rl_user_reckoning:
                goToActivity(ReckoningActivity.class);
                break;

        }
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.takePhotoBtn:
                    changeHeadIcon(1);
                    break;
                case R.id.pickPhotoBtn:
                    changeHeadIcon(0);
                    break;
                default:
                    break;
            }
        }
    };

    @SuppressLint("SimpleDateFormat")
    private String getNowTime() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmssSS");
        return dateFormat.format(date);
    }

    private void changeHeadIcon(int item) {
        String saveDir;
        File imgFile;
        imageName1 = getNowTime() + ".png";
        if (item == 0) {
            saveDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/CFunding/";
            imgFile = new File(saveDir);
            if (!imgFile.exists()) {
                imgFile.mkdir();
            }
            tempFile1 = new File(saveDir, imageName1);
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
        } else if (item == 1) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                saveDir = Environment.getExternalStorageDirectory() + "/ytwp/";
                imgFile = new File(saveDir);
                if (!imgFile.exists()) {
                    imgFile.mkdir();
                }
                tempFile1 = new File(saveDir, imageName1);
                Uri uri = Uri.fromFile(tempFile1);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, REQUEST_CODE_CAPTURE_CAMEIA);
            }
        } else {
            T.showShort(getActivity(), "未找到存储卡，无法存储照片！");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_CAPTURE_CAMEIA:
                if (resultCode == -1) {
                    try {
                        crop(Uri.fromFile(tempFile1));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                } else {
                    T.showShort(getActivity(), "未找到存储卡，无法存储照片！");
                }
                break;
            case REQUEST_CODE_PICK_IMAGE:
                if (null != data) {
                    Uri y = data.getData();
                    try {
                        crop(y);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    Log.e("图片路径？？", data.getData() + "");
                }
            case REQUEST_CODE_GETIMAGE_BYSDCARD:
                if (null != data) {
                    uri = Uri.fromFile(tempFile1);
                    tempFile1 = ImageCompress.scal(uri);
                    Bitmap bitmap = Bimp.getLoacalBitmap(tempFile1.getAbsolutePath());
                    if (bitmap == null) {
                        mIvUserPicture.setImageResource(R.mipmap.w_wdl);
                    } else {
                        mIvUserPicture.setImageBitmap(bitmap);
                    }
                }
                break;
        }
    }

    private void crop(Uri uri) throws IOException, URISyntaxException {

        SimpleDateFormat sDateFormat = new SimpleDateFormat(
                "yyyyMMddhhmmss");
        String address = sDateFormat.format(new Date());
        if (!FileUtils.isFileExist("")) {
            FileUtils.createSDDir("");

        }
        Uri imageUri = Uri.parse("file:///sdcard/formats/" + address + ".JPEG");

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        tempFile1 = new File(new URI(imageUri.toString()));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        intent.putExtra("return-data", false);
        startActivityForResult(intent, REQUEST_CODE_GETIMAGE_BYSDCARD);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mWaveHelper.cancel();
    }

    @Override
    public void onResume() {
        super.onResume();
        mWaveHelper.start();
    }
}
