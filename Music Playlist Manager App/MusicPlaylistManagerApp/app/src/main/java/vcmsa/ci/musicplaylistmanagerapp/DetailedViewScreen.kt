package vcmsa.ci.musicplaylistmanagerapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailedViewScreen : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailed_view_screen)



        val songs = intent.getStringArrayListExtra("songs") ?: arrayListOf()
        val artists = intent.getStringArrayListExtra("artists") ?: arrayListOf()
        val ratings = intent.getIntegerArrayListExtra("ratings") ?: arrayListOf()
        val comments = intent.getStringArrayListExtra("comments") ?: arrayListOf()

       
        val songList = findViewById<TextView>(R.id.songList)
        val avgText = findViewById<TextView>(R.id.avgRating)
        val backBtn = findViewById<Button>(R.id.backBtn)
        val showBtn = findViewById<Button>(R.id.showBtn)
        val avgBtn = findViewById<Button>(R.id.avgBtn)

        
        showBtn.setOnClickListener {
            var display = ""
            for (i in songs.indices) {
                display += "${songs[i]} by ${artists[i]}, Rating: ${ratings[i]}, Comment: ${comments[i]}\n\n"
            }
            songList.text = if (display.isNotEmpty()) display else { "No songs to show." }
        }

        
        avgBtn.setOnClickListener {
            if (ratings.isNotEmpty()) {
                val avg = ratings.sum().toDouble() / ratings.size
                avgText.text = "Average Rating: %.2f".format(avg)
            } else {
                avgText.text = "No ratings to calculate average."
            }
        }

        
        backBtn.setOnClickListener {
            finish()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
