import com.example.data.response.Article

data class TrendingNewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)