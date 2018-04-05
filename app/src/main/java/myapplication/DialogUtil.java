package myapplication;


//import android.app.AlertDialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.youdu.R;

/**
 * Created by Administrator on 2017/9/12.
 */

public class DialogUtil {

    public static Dialog initPhotoDialog(View.OnClickListener listener1,Context context) {
        Dialog definiteDialog;
        Button takePhoto,choosePhoto,btnCancel;
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_photo, null);
        definiteDialog = new Dialog(context, R.style.ActionSheetDialogAnimation);
        definiteDialog.setContentView(inflate);
        Window dialogWindow = definiteDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;
        lp.width = -1;
        dialogWindow.setAttributes(lp);
        definiteDialog.show();
        takePhoto = (Button) inflate.findViewById(R.id.takePhoto);
        choosePhoto = (Button) inflate.findViewById(R.id.choosePhoto);
        btnCancel = (Button) inflate.findViewById(R.id.btn_cancel);
        takePhoto.setOnClickListener(listener1);
        choosePhoto.setOnClickListener(listener1);
        btnCancel.setOnClickListener(listener1);
        return definiteDialog;
    }
}
