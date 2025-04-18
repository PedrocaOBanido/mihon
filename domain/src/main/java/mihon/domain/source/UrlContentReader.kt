package mihon.domain.source

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class UrlContentReader {
    fun read(url: String, selector: String): String {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    return "Error: Network request failed - ${response.code}"
                }

                val responseBody = response.body?.string() ?: return "Error: Empty response body"
                val regex = Regex("<$selector>(.*?)</$selector>", RegexOption.DOT_MATCHES_ALL)
                val matchResult = regex.find(responseBody)

                if (matchResult != null) {
                    return matchResult.groupValues[1].trim()
                } else {
                    return "Error: Content not found"
                }
            }
        } catch (e: IOException) {
            return "Error: Network request failed - ${e.message}"
        }
    }
}