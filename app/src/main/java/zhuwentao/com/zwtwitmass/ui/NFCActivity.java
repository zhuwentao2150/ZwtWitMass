package zhuwentao.com.zwtwitmass.ui;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

import zhuwentao.com.zwtwitmass.R;

/**
 * NFC的简单使用
 * Created by zhuwentao on 2017-04-24.
 */
public class NFCActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "NfcDemo";

    private TextView mShowDataTv;
    private Button mTestNfcBtn;
    private Button mReadDataBtn;
    private Button mWriteDataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_nfc_test);

        initView();

        initListener();

    }

    private void initView() {
        mShowDataTv = (TextView) findViewById(R.id.tv_show_data);
        mWriteDataBtn = (Button) findViewById(R.id.btn_write_data);
        mReadDataBtn = (Button) findViewById(R.id.btn_read_data);
        mTestNfcBtn = (Button) findViewById(R.id.btn_test_nfc);
    }

    private void initListener() {
        mWriteDataBtn.setOnClickListener(this);
        mReadDataBtn.setOnClickListener(this);
        mTestNfcBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test_nfc:
                TestNFC();
                break;
            case R.id.btn_read_data:
                ReadNFCData();
                break;
            case R.id.btn_write_data:
                WriteNFCData();
                break;
        }
    }

    /**
     * 测试NFC功能是否可用
     */
    private void TestNFC() {
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (nfcAdapter == null) {
            mShowDataTv.setText("手机不支持NFC功能");
        } else {
            if (!nfcAdapter.isEnabled()) {
                mShowDataTv.setText("NFC功能未打开");
            } else {
                mShowDataTv.setText("NFC功能可用");
            }

        }
    }

    /**
     * 读取NFC标签
     */
    private void ReadNFCData() {
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            Parcelable[] array = getIntent().getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage nfcmessage = (NdefMessage) array[0];
            NdefRecord ndefRecord = nfcmessage.getRecords()[0];
            try {
                String readResult = new String(ndefRecord.getPayload(), "UTF-8");
                System.out.println("读取结果:" + readResult);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "没有意图", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * 写入NFC标签
     */
    private void WriteNFCData() {

    }

    @Override
    protected void onResume() {
        super.onResume();

        // 当刷卡时，生命周期onPause->onNewIntent->onResume
        ReadNFCData();
    }
}
