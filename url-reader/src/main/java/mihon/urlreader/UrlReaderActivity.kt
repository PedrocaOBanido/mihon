package mihon.urlreader

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mihon.domain.usecase.ReadFromUrlUseCase
import javax.inject.Inject

@AndroidEntryPoint
class UrlReaderActivity : AppCompatActivity() {

    @Inject
    lateinit var readFromUrlUseCase: ReadFromUrlUseCase

    private lateinit var urlEditText: EditText
    private lateinit var selectorEditText: EditText
    private lateinit var readButton: Button
    private lateinit var contentTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_url_reader)

        urlEditText = findViewById(R.id.urlEditText)
        selectorEditText = findViewById(R.id.selectorEditText)
        readButton = findViewById(R.id.readButton)
        contentTextView = findViewById(R.id.contentTextView)

        readButton.setOnClickListener {
            val url = urlEditText.text.toString()
            val selector = selectorEditText.text.toString()

            CoroutineScope(Dispatchers.Main).launch {
                val content = readFromUrlUseCase.read(url, selector)
                contentTextView.text = content
            }
        }
    }
}