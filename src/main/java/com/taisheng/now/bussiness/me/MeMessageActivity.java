package com.taisheng.now.bussiness.me;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.taisheng.now.Constants;
import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;
import com.taisheng.now.bussiness.user.UserInstance;
import com.taisheng.now.view.crop.Crop;

import java.io.File;
import java.text.DecimalFormat;

/**
 * Created by dragon on 2019/6/29.
 */

public class MeMessageActivity extends BaseActivity {
    ImageView iv_back;
    View ll_nickname;
    View ll_zhanghao;
    View ll_bindphone;
    View ll_updatepwd;
    View ll_avatar;

    TextView tv_nickname;
    TextView tv_zhanghao;
    TextView tv_phone;
    SimpleDraweeView sdv_header;
    private final int REQ_CODE_PHOTO_SOURCE = 6;//选择方式
    private final int REQ_CODE_GET_PHOTO_FROM_GALLERY = 10;//从相册获取
    private final int REQ_CODE_GET_PHOTO_FROM_TAKEPHOTO = 11;//拍照完

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memessage);
        initView();
    }

    void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ll_avatar = findViewById(R.id.ll_avatar);
        ll_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyAvatar();
            }
        });
        sdv_header = (SimpleDraweeView) findViewById(R.id.sdv_header);





        ll_nickname = findViewById(R.id.ll_nickname);
        ll_nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeMessageActivity.this, UpdateNickActivity.class);
                startActivity(intent);
            }
        });
        ll_zhanghao = findViewById(R.id.ll_zhanghao);
        ll_zhanghao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo  修改账号
            }
        });
        ll_bindphone = findViewById(R.id.ll_bindphone);
        ll_bindphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(MeMessageActivity.this,BindPhoneActivity.class);
//                startActivity(intent);
            }
        });
        ll_updatepwd = findViewById(R.id.ll_updatepwd);
        ll_updatepwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeMessageActivity.this, UpdatePasswordFirstActivity.class);
                startActivity(intent);
            }
        });

        tv_nickname = (TextView) findViewById(R.id.tv_nickname);

        tv_zhanghao = (TextView) findViewById(R.id.tv_zhanghao);

        tv_phone = (TextView) findViewById(R.id.tv_phone);

    }


    public void modifyAvatar() {
        Intent intent = new Intent(this, SelectAvatarSourceDialog.class);
        startActivityForResult(intent, REQ_CODE_PHOTO_SOURCE);
    }


    private void onPhotoSource(int mode) {
        if (mode == R.id.btn_pick_from_library) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, REQ_CODE_GET_PHOTO_FROM_GALLERY);

        } else if (mode == R.id.btn_take_photo) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment
                    .getExternalStorageDirectory(), "temp.jpg")));
            startActivityForResult(intent, REQ_CODE_GET_PHOTO_FROM_TAKEPHOTO);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (UserInstance.getInstance().userInfo.avatar != null) {
            Uri uri = Uri.parse(Constants.Url.Host+UserInstance.getInstance().userInfo.avatar);
            sdv_header.setImageURI(uri);
        }
        tv_nickname.setText(UserInstance.getInstance().getNickname());
        tv_zhanghao.setText(UserInstance.getInstance().getZhanghao());
        tv_phone.setText(UserInstance.getInstance().getPhone());
    }

    private void beginCrop(Uri source, Bundle bundle) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), "Bcropped"));
        Crop.of(source, destination).asSquare().start(this, bundle);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case REQ_CODE_PHOTO_SOURCE:
                if (data != null) {
                    int mode = data.getIntExtra(SelectAvatarSourceDialog.TAG_MODE, -1);
                    onPhotoSource(mode);
                }
                break;
            case REQ_CODE_GET_PHOTO_FROM_GALLERY:
                if (data != null && data.getData() != null) {
                    Bundle bundle = new Bundle();
                    // 选择图片后进入裁剪
                    String path = data.getData().getPath();
                    Uri source = data.getData();
                    beginCrop(source, bundle);
                }
                break;
            case REQ_CODE_GET_PHOTO_FROM_TAKEPHOTO:
                // 判断相机是否有返回
                File picture = new File(Environment.getExternalStorageDirectory()
                        + "/temp.jpg");
                if (!picture.exists()) {
                    return;
                }
                Bundle bundle = new Bundle();
                // 选择图片后进入裁剪
                Uri source = Uri.fromFile(picture);
                beginCrop(source, bundle);

                break;

            case Crop.REQUEST_CROP:
//                modifyBean.logo_url = PetInfoInstance.getInstance().getPackBean().logo_url;
                //todo avatar为空
                Uri uri = Uri.parse(Constants.Url.Host+UserInstance.getInstance().userInfo.avatar);
                if (sdv_header == null) {
                    return;
                }
                sdv_header.setImageURI(uri);
                break;
        }
    }

}
