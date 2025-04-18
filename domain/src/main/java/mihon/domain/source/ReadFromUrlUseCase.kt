package mihon.domain.usecase

import mihon.domain.source.UrlContentReader
import javax.inject.Inject

class ReadFromUrlUseCase @Inject constructor(private val urlContentReader: UrlContentReader) {
    fun read(url: String, selector: String): String {
        return urlContentReader.read(url, selector)
    }
}