package qsoft.com.androidutils

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import qsoft.com.aescryptutil.AesCryptUtil
import qsoft.com.utils.*
import qsoft.com.utils.Utils.Companion.eb

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCtrl()
    }

    private fun initCtrl() {
        btnShowMessage.setOnClickListener(::clickShowMessage)
        btnConfirmYesNo.setOnClickListener(::clickConfirmYesNo)
        btnInputDialog.setOnClickListener(::clickInputDialog)
        btnToastOnUi.setOnClickListener(::clickToastOnUi)
        btnSnackOnUi.setOnClickListener(::clickSnackOnUi)
        btnEncrypt.setOnClickListener(::clickEncrypt)
        btnDecrypt.setOnClickListener(::clickDecrypt)
    }

    private fun clickShowMessage(v: View) {
        Utils.showMessage(this, "showMessage() demo")
    }

    private fun clickConfirmYesNo(v: View) {
        Utils.confirmYesNo(this, "confirmYesNo() demo") {
            if (it == Dialog.BUTTON_POSITIVE) toast("Yes")
            else toast("No")
        }
    }

    private fun clickInputDialog(v: View) {
        Utils.inputDialog(this, "inputDialog() demo", null, "input your message here", null, null) {
            toastOnUi("your message:$it")
        }
    }

    private fun clickToastOnUi(v: View) {
        toastOnUi("toastOnUi() demo")
    }

    private fun clickSnackOnUi(v: View) {
        v.snackOnUi("snackOnUi() demo")
    }

    private fun clickEncrypt(v: View) {
        Utils.inputDialog(this, "encrypt() demo", null, "input a message to encrypt", null, null) {
            try {
                val encryptd = AesCryptUtil.encrypt("password", it)
                clipCopy(encryptd)
                toastOnUi("encrypted message is:$encryptd")
            } catch (e: Exception) {
                eb(this, e)
            }
        }
    }

    private fun clickDecrypt(v: View) {
        val message = clipPaste()
        Utils.inputDialog(this, "decrypt() demo", message, "input a encrypted message to decrypt", null, null) {
            try {
                val raw = AesCryptUtil.decrypt("password", it)
                toastOnUi("decryptd message is:$raw")
            } catch (e: Exception) {
                eb(this, e)
            }
        }
    }
}
