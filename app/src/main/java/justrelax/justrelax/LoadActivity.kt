package justrelax.justrelax

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent



class LoadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)
        val mySuperIntent = Intent(this@LoadActivity, MainActivity::class.java)
           startActivity(mySuperIntent)

//        Handler().postDelayed({
//            //Do any action here. Now we are moving to next page
//
//            val mySuperIntent = Intent(this@LoadActivity, MainActivity::class.java)
//            startActivity(mySuperIntent)
//            finish()
//        }, 500)
    }
}
