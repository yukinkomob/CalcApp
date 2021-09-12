package jp.techacademy.yuki.nishimura.calcapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plusButton.setOnClickListener(this)
        minusButton.setOnClickListener(this)
        mulButton.setOnClickListener(this)
        divButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val isInput1Empty = numberInput1.text.toString().isEmpty()
        val isInput2Empty = numberInput2.text.toString().isEmpty()
        if (isInput1Empty || isInput2Empty) {
            var errText = ""
            if (isInput1Empty) {
                errText += "数字１を入力してください。"
            }
            if (isInput2Empty) {
                if (isInput1Empty) {
                    errText += "\n"
                }
                errText += "数字２を入力してください。"
            }
            if (v != null) {
                showErrorText(v, errText)
            }
            return
        }

        val num1 = numberInput1.text.toString().toFloat()
        val num2 = numberInput2.text.toString().toFloat()
        val ans: Float = when (v?.id) {
            R.id.plusButton -> (num1 + num2)
            R.id.minusButton -> (num1 - num2)
            R.id.mulButton -> (num1 * num2)
            else -> (num1 / num2)
        }
        sendIntentWithAnswer(ans)
    }

    private fun sendIntentWithAnswer(num: Float) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("ANS", num)
        startActivity(intent)
    }

    private fun showErrorText(view: View, text: String) {
        Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }
}
